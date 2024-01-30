package it.employees;

import com.companyname.services.employees.api.model.EmployeeDetails;
import com.companyname.services.employees.api.behavior.FindEmployeeById;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FindEmployeeByIdIT {

    @Autowired
    private FindEmployeeById findEmployeeById;

    @Test
    void findsEmployeeById() {
        EmployeeDetails theEmployeeDetails = findEmployeeById.executeFor(1);

        assertThat(theEmployeeDetails.getFirstName()).isEqualTo("Jon");
        assertThat(theEmployeeDetails.getLastName()).isEqualTo("Doe");
        assertThat(theEmployeeDetails.getTitle()).isEqualTo("Software Engineer");
        assertThat(theEmployeeDetails.getSalary()).isEqualTo(130000.00);
    }
}
