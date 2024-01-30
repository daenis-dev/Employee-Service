package it.employees;

import com.companyname.services.employees.api.model.EmployeeDetails;
import com.companyname.services.employees.api.behavior.FindAllEmployees;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FindAllEmployeesIT {

    @Autowired
    private FindAllEmployees findAllEmployees;

    @Test
    void findsAllEmployees() {
        final long THE_PREDICTED_ID = 1L;

        List<EmployeeDetails> theReturnedEmployees = findAllEmployees.execute();

        long theFirstEmployeeId = theReturnedEmployees.get(0).getId();
        assertThat(theFirstEmployeeId).isEqualTo(THE_PREDICTED_ID);
    }
}
