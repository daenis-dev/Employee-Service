package com.companyname.services.employees.api;

import com.companyname.services.employees.api.behavior.CreateEmployee;
import com.companyname.services.employees.api.behavior.FindAllEmployees;
import com.companyname.services.employees.api.controller.EmployeeController;
import com.companyname.services.employees.api.model.EmployeeDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private CreateEmployee createEmployee;

    @Mock
    private FindAllEmployees findAllEmployees;

    @Mock
    private FindEmployeeById findEmployeeById;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new EmployeeController(createEmployee, findAllEmployees, findEmployeeById))
                .build();
    }

    @Test
    void createsEmployee() throws Exception {
        EmployeeDetails employeeDetails = new EmployeeDetails(1, "", "", "", "", 0);

        when(createEmployee.executeFor(any())).thenReturn(employeeDetails);

        mockMvc.perform(post("/v1/employees")
                        .param("first-name", "Jimmy")
                        .param("last-name", "Recard")
                        .param("email-address", "jimmy.recard@jr.com")
                        .param("job-title", "Engineer")
                        .param("salary", "100000.00"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void findsAllEmployees() throws Exception {
        EmployeeDetails employeeDetails = new EmployeeDetails(1, "jon", "doe", "Software Engineer", "", 150000.00);

        when(findAllEmployees.execute()).thenReturn(Collections.singletonList(employeeDetails));

        mockMvc.perform(get("/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    void findsEmployeeById() throws Exception {
        EmployeeDetails employeeDetails = new EmployeeDetails(1, "jon", "doe", "Software Engineer", "", 150000.00);

        when(findEmployeeById.executeFor(1)).thenReturn(employeeDetails);

        mockMvc.perform(get("/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }
}
