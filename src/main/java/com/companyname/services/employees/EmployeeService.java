package com.companyname.services.employees;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import com.companyname.services.employees.api.behavior.CreateEmployee;
import com.companyname.services.employees.api.behavior.FindAllEmployees;
import com.companyname.services.employees.api.behavior.FindEmployeeById;
import com.companyname.services.employees.api.model.CreateEmployeeRequest;
import com.companyname.services.employees.api.model.EmployeeDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
final class EmployeeService implements CreateEmployee, FindAllEmployees, FindEmployeeById {

    private final EmployeeRepository employeeRepository;
    private final JobTitleRepository jobTitleRepository;

    @Override
    public EmployeeDetails executeFor(CreateEmployeeRequest theRequest) {
        Employee employee = new Employee();
        employee.setFirstName(theRequest.getFirstName());
        employee.setLastName(theRequest.getLastName());
        employee.setJobTitle(jobTitleRepository.findByName(theRequest.getJobTitle()).orElseThrow(() -> new InvalidRequestException("Please select a valid job title")));
        employee.setEmailAddress(theRequest.getEmailAddress());
        employee.setSalary(theRequest.getSalary());
        return employeeRepository.save(employee).getDetails();
    }

    @Override
    public List<EmployeeDetails> execute() {
        return employeeRepository.findAllEmployeeDetails();
    }

    @Override
    public EmployeeDetails executeFor(long id) {
        return employeeRepository.findEmployeeDetailsById(id).orElseThrow(() -> new InvalidRequestException("Employee does not exist for ID"));
    }
}
