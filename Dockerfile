# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-oracle

# Configure Postgres
FROM postgres:latest

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=ui24v00l
ENV POSTGRES_DB=employee-service
COPY src/main/resources/schema.sql /docker-entrypoint-initdb.d/

EXPOSE 5432

CMD ["postgres"]

# Set the working directory inside the container
WORKDIR /app

# Copy the application files into the container
COPY target/employee-service-1.0-SNAPSHOT.jar /app
COPY src/main/resources/certs/employee-service.p12 /app
# Below command causing issues - remove from
COPY --from=postgres-run /docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/

# Expose the port the database and app run on
EXPOSE 8080

# Specify the command to run on container start (include VM args) (try )
CMD ["java", "-Dkeystore-path=/app/employee-service.p12", "-Dkeystore-password=8ai5Au0t2AjC", "-Dkeystore-type=pkcs12", "-Dkeystore-alias=employee-service", "-Ddatabase-url=jdbc:postgresql://localhost:5432/employee-service", "-Ddatabase-username=postgres", "-Ddatabase-password=ui24v00l", "-Dkeycloak-admin-username=auth-admin", "-Dkeycloak-admin-password=836Jw4XceeWT", "-jar", "employee-service-1.0-SNAPSHOT.jar"]

