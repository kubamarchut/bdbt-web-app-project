package bdbt_projekt.SpringApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOTest extends Object {

    private EmployeeDAO dao;
    private PositionDAO posDAO;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@194.29.170.4:1521:xe");
        dataSource.setUsername("BDBTGRC06");
        dataSource.setPassword("BDBTGRC06");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new EmployeeDAO(new JdbcTemplate(dataSource));
        posDAO = new PositionDAO(new JdbcTemplate(dataSource));
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
        int id = 3;
        Employee newEmployee = dao.get(id);
        System.out.println(newEmployee);
        assertNotNull(newEmployee);
    }

    @Test
    void update() {
        int id = 3;
        Employee newEmployee = dao.get(id);
        String oldName = newEmployee.getImie();
        newEmployee.setImie("Grzegorz");

        dao.update(newEmployee);
        newEmployee = dao.get(id);

        assertNotEquals(newEmployee.getImie(), oldName);
    }

    @Test
    void delete() {
        int nOfRecords = dao.list().size();
        int id = 24;
        dao.delete(24);
        int newNOfRecords = dao.list().size();

        assertEquals(nOfRecords - 1, newNOfRecords);
    }
}