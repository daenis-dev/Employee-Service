package com.companyname.services.employees.api.controller;

import com.companyname.services.employees.api.behavior.CreateEmployee;
import com.companyname.services.employees.api.behavior.FindAllEmployees;
import com.companyname.services.employees.api.behavior.FindEmployeeById;
import com.companyname.services.employees.api.model.CreateEmployeeRequest;
import com.companyname.services.employees.api.model.EmployeeDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public final class EmployeeController {

    private final CreateEmployee createEmployee;
    private final FindAllEmployees findAllEmployees;
    private final FindEmployeeById findEmployeeById;

    @PostMapping("/v1/employees")
    public ResponseEntity<EmployeeDetails> createEmployee(
            @RequestParam("first-name") String firstName,
            @RequestParam("last-name") String lastName,
            @RequestParam("email-address") String emailAddress,
            @RequestParam("job-title") String jobTitle,
            @RequestParam("salary") String salary) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createEmployee.executeFor(new CreateEmployeeRequest()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withEmailAddress(emailAddress)
                        .withJobTitle(jobTitle)
                        .withSalary(salary)));
    }

    @GetMapping("/v1/employees")
    public ResponseEntity<List<EmployeeDetails>> findAll() {
        return ResponseEntity.ok(findAllEmployees.execute());
    }

    @GetMapping("/v1/employees/{id}")
    public ResponseEntity<EmployeeDetails> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(findEmployeeById.executeFor(id));
    }
}
