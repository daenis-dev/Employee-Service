package com.companyname.services.core.users.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserRegistrationRequestTest {

    private UserRegistrationRequest userRegistrationRequest;

    @BeforeEach
    void init() {
        userRegistrationRequest = new UserRegistrationRequest();
    }

    @Test
    void setsFirstNameForValidInput() {
        String theFirstName = "Jimmy";

        UserRegistrationRequest theUpdatedRequest = userRegistrationRequest.withFirstName(theFirstName);

        String theFirstNameFromTheUpdatedRequest = theUpdatedRequest.getFirstName();
        assertThat(theFirstNameFromTheUpdatedRequest).isEqualTo(theFirstName);
    }

    @Test
    void doesNotSetTheFirstNameForNullInput() {
        String thePredictedMessage = "First name required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> userRegistrationRequest.withFirstName(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheFirstNameForEmptyInput() {
        String thePredictedMessage = "First name required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> userRegistrationRequest.withFirstName(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsLastNameForValidInput() {
        String theLastName = "Recard";

        UserRegistrationRequest theUpdatedRequest = userRegistrationRequest.withLastName(theLastName);

        String theLastNameFromTheUpdatedRequest = theUpdatedRequest.getLastName();
        assertThat(theLastNameFromTheUpdatedRequest).isEqualTo(theLastName);
    }

    @Test
    void doesNotSetTheLastNameForNullInput() {
        String thePredictedMessage = "Last name required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> userRegistrationRequest.withLastName(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheLastNameForEmptyInput() {
        String thePredictedMessage = "Last name required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> userRegistrationRequest.withLastName(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsEmailAddressForValidInput() {
        String theEmailAddress = "jimmy.recard@jr.com";

        UserRegistrationRequest theUpdatedRequest = userRegistrationRequest.withEmailAddress(theEmailAddress);

        String theEmailAddressFromTheUpdatedRequest = theUpdatedRequest.getEmailAddress();
        assertThat(theEmailAddressFromTheUpdatedRequest).isEqualTo(theEmailAddress);
    }

    @Test
    void doesNotSetTheEmailAddressForNullInput() {
        String thePredictedMessage = "Email address required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> userRegistrationRequest.withEmailAddress(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheEmailAddressForEmptyInput() {
        String thePredictedMessage = "Email address required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> userRegistrationRequest.withEmailAddress(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsPasswordForValidInput() {
        String thePassword = "changeit";

        UserRegistrationRequest theUpdatedRequest = userRegistrationRequest.withPassword(thePassword);

        String thePasswordFromTheUpdatedRequest = theUpdatedRequest.getPassword();
        assertThat(thePasswordFromTheUpdatedRequest).isEqualTo(thePassword);
    }

    @Test
    void doesNotSetThePasswordForNullInput() {
        String thePredictedMessage = "Password required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> userRegistrationRequest.withPassword(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetThePasswordForEmptyInput() {
        String thePredictedMessage = "Password required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> userRegistrationRequest.withPassword(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void confirmPasswordDoesNothingIfPasswordsMatch() {
        String thePassword = "changeit";
        userRegistrationRequest.withPassword(thePassword);

        userRegistrationRequest.confirmPassword(thePassword);
    }

    @Test
    void confirmPasswordThrowsAnExceptionIfPasswordsDoNotMatch() {
        String thePredictedMessage = "Passwords do not match for new account";
        userRegistrationRequest.withPassword("changeit_1");

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> userRegistrationRequest.confirmPassword("changeit_2"));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }
}