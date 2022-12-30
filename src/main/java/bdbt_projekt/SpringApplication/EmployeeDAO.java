package bdbt_projekt.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO {
    /* Import org.springframework.jd....Template */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /* Import java.util.List (zawiera info z bazy danych) */
    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Employee> list(){
        String sql = "SELECT * FROM PRACOWNICY";

        List<Employee> listEmployee = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Employee.class));

        return listEmployee;
    }
    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Employee employee) {
    }
    /* Read – odczytywanie danych z bazy */
    public Employee get(int id) {
        return null;
    }
    /* Update – aktualizacja danych */
    public void update(Language sale) {
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
    }
}
