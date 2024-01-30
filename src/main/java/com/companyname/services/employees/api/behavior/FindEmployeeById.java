package com.companyname.services.employees.api.behavior;

import com.companyname.services.employees.api.model.EmployeeDetails;

public interface FindEmployeeById {

    EmployeeDetails executeFor(long id);
}
