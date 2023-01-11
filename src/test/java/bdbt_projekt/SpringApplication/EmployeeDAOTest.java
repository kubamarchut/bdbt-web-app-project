package bdbt_projekt.SpringApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class EmployeeDAOTest extends Object {

    private EmployeeDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@194.29.170.4:1521:xe");
        dataSource.setUsername("BDBTGRC06");
        dataSource.setPassword("BDBTGRC06");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new EmployeeDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Employee> listEmployees = dao.list();
        System.out.println(Arrays.toString(listEmployees.toArray()));

        assertTrue(!listEmployees.isEmpty());
    }

    @Test
    void save() {
        Employee newEmployee = new Employee(0, "Jan", "Kowal", new Date(), "M",
                "09876509876", new Date(), "123456789098765", "123456789",
                "jan.kowal@domyidomki.pl", 1, 15, 3);

        dao.save(newEmployee);
    }

    @Test
    void get() {
        fail("Not yet implemented");
    }

    @Test
    void update() {
        fail("Not yet implemented");
    }

    @Test
    void delete() {
    }
}