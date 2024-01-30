package com.companyname.services.core.users;

import com.companyname.services.core.users.api.behavior.RegisterUser;
import com.companyname.services.core.users.api.model.UserRegistrationRequest;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class UserService implements RegisterUser {

    @Value("${keycloak.base-url}")
    private String KEYCLOAK_BASE_URL;

    @Value("${keycloak.admin.realm}")
    private String KEYCLOAK_ADMIN_REALM;

    @Value("${keycloak.admin.username}")
    private String KEYCLOAK_ADMIN_USERNAME;

    @Value("${keycloak.admin.password}")
    private String KEYCLOAK_ADMIN_PASSWORD;

    @Value("${keycloak.admin.client-name}")
    private String KEYCLOAK_ADMIN_CLIENT_NAME;

    @Value("${keycloak.employee-management-service.base-url}")
    private String KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_BASE_URL;

    @Value("${keycloak.employee-management-service.users-api-url}")
    private String KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_USERS_API;

    @Value("${keycloak.employee-management-service.token-schema}")
    private String KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_TOKEN_SCHEMA;

    @Override
    public void forRequest(UserRegistrationRequest request) {
        Keycloak keycloak = Keycloak.getInstance(KEYCLOAK_BASE_URL, KEYCLOAK_ADMIN_REALM, KEYCLOAK_ADMIN_USERNAME, KEYCLOAK_ADMIN_PASSWORD, KEYCLOAK_ADMIN_CLIENT_NAME);
        String accessToken = KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_TOKEN_SCHEMA + " " + keycloak.tokenManager().getAccessTokenString();

        WebClient webClient = WebClient
                .builder()
                .baseUrl(KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_BASE_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.ACCEPT, ALL_VALUE);
                    httpHeaders.add(HttpHeaders.AUTHORIZATION, accessToken);
                })
                .build();

        try {
            KeycloakResponse keycloakResponse = webClient
                    .post()
                    .uri(KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_USERS_API)
                    .contentType(APPLICATION_JSON)
                    .bodyValue(new KeycloakUser().mappedFrom(request))
                    .exchangeToMono(response -> response.bodyToMono(KeycloakResponse.class))
                    .block(Duration.ofSeconds(10));
            if (keycloakResponse != null && keycloakResponse.getError() != null && !keycloakResponse.getError().isEmpty()) {
                throw new RuntimeException("An error occurred while registering the user with message from Keycloak: " + keycloakResponse.getError());
            }
        } catch (Exception ex) {
            throw new RuntimeException("An error occurred while registering the user", ex);
        }
    }
}
