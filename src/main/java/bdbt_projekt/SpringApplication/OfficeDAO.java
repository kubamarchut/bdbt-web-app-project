package bdbt_projekt.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfficeDAO {
    /* Import org.springframework.jd....Template */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /* Import java.util.List (zawiera info z bazy danych) */
    public OfficeDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Employee> list(){
        String sql = "SELECT * FROM PRACOWNICY";

        List<Employee> listEmployee = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Employee.class));

        return listEmployee;
    }
    public List<Employee> listAgents(){
        String sql = "SELECT * FROM PRACOWNICY NATURAL JOIN STANOWISKA WHERE NAZWA_STANOWISKA like \'Agent nieruchomości\'";

        List<Employee> listEmployee = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Employee.class));

        return listEmployee;
    }
    public List<Language> getEmployeesLanguages(int employeesID){
        String sql = "select nr_jezyki, kod_jezyka, nazwa_jezyka from agenci_nieruchomosci natural join znajomosc_jezykow natural join jezyki where nr_pracownika = \'"+employeesID+"\'";

        List<Language> employeesLanguages = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Language.class));

        return employeesLanguages;
    }
    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Employee employee) {

        System.out.print(employee);
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("pracownicy").usingColumns("imie", "nazwisko", "data_urodzenia", "plec",
                                                                "pesel", "data_zatrudnienia", "nr_konta", "nr_telefonu",
                                                                "adres_email", "nr_biura", "nr_adresu", "nr_stanowiska");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        insertActor.execute(param);
    }
    /* Read – odczytywanie danych z bazy */
    public Employee get(int id) {
        Object[] args = {id};
        String sql = "SELECT * FROM PRACOWNICY WHERE NR_PRACOWNIKA = ?";

        Employee employee = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Employee.class));

        return employee;
    }
    public Employee get(String name) {
        Object[] args = {name.split("-")[0], name.split("-")[1]};
        String sql = "SELECT * FROM PRACOWNICY NATURAL JOIN AGENCI_NIERUCHOMOSCI WHERE IMIE = ? AND NAZWISKO = ?";

        Employee employee = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Employee.class));

        return employee;
    }
    public boolean checkIfRealEstateAgent(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM AGENCI_NIERUCHOMOSCI WHERE NR_PRACOWNIKA = ?";

        try{
        jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Employee.class));
        }
        catch (Exception EmptyResultDataAccessException){
            return false;
        }
        return true;
    }
    public boolean checkIfRealEstateAgent(String name){
        Object[] args = {name.split("-")[0], name.split("-")[1]};
        String sql = "SELECT * FROM PRACOWNICY NATURAL JOIN AGENCI_NIERUCHOMOSCI WHERE IMIE = ? AND NAZWISKO = ?";

        try{
            jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Employee.class));
        }
        catch (Exception EmptyResultDataAccessException){
            return false;
        }
        return true;
    }

    /* Update – aktualizacja danych */
    public void update(Language sale) {
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
    }
}
