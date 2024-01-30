package it.employees;

import com.companyname.services.employees.api.behavior.CreateEmployee;
import com.companyname.services.employees.api.model.CreateEmployeeRequest;
import com.companyname.services.employees.api.model.EmployeeDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CreateEmployeeIT {

    @Autowired
    private CreateEmployee createEmployee;

    @Test
    void createsEmployee() {
        String theFirstName = "Jimmy";
        String theLastName = "Recard";
        String theJobTitle = "Software Engineer";
        String theSalary = "200000.00";
        EmployeeDetails theReturnedDetails = createEmployee.executeFor(new CreateEmployeeRequest()
                .withFirstName(theFirstName)
                .withLastName(theLastName)
                .withJobTitle(theJobTitle)
                .withSalary(theSalary));

        assertThat(theReturnedDetails.getFirstName()).isEqualTo(theFirstName);
        assertThat(theReturnedDetails.getLastName()).isEqualTo(theLastName);
        assertThat(theReturnedDetails.getSalary()).isEqualTo(Double.parseDouble(theSalary));
        assertThat(theReturnedDetails.getTitle()).isEqualTo(theJobTitle);
    }
}
