package com.companyname.services.employees.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import lombok.Getter;

@Getter
public final class CreateEmployeeRequest {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String jobTitle;
    private double salary;

    public CreateEmployeeRequest withFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new InvalidRequestException("First name required for new employee");
        }
        this.firstName = firstName;
        return this;
    }

    public CreateEmployeeRequest withLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidRequestException("Last name required for new employee");
        }
        this.lastName = lastName;
        return this;
    }

    public CreateEmployeeRequest withEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new InvalidRequestException("Email address required for new employee");
        }
        this.emailAddress = emailAddress;
        return this;
    }

    public CreateEmployeeRequest withJobTitle(String jobTitle) {
        if (jobTitle == null || jobTitle.isEmpty()) {
            throw new InvalidRequestException("Job title required for new employee");
        }
        this.jobTitle = jobTitle;
        return this;
    }

    public CreateEmployeeRequest withSalary(String salary) {
        if (salary == null || salary.isEmpty()) {
            throw new InvalidRequestException("Salary required for new employee");
        }
        boolean theSalaryContainsNonNumericCharacters = !salary.matches("[\\d]\\d*[.][\\d]\\d*") && !salary.matches("[\\d]\\d*");
        if (theSalaryContainsNonNumericCharacters) {
            throw new InvalidRequestException("Salary value must be numeric for new employee");
        }
        this.salary = Double.parseDouble(salary);
        return this;
    }
}
