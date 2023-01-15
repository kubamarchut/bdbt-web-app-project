package bdbt_projekt.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
        String sql = "SELECT * FROM PRACOWNICY NATURAL JOIN STANOWISKA NATURAL JOIN (select nr_biura, nazwa, " +
                "data_zalozenia, nr_telefonu as nr_telefonu_biura, adres_email as adres_email_biura, " +
                "procent_z_prowizji, nr_adresu as nr_adresu_biura from biura) ORDER BY NR_PRACOWNIKA";

        List<Employee> listEmployee = jdbcTemplate.query(sql,
                (rs, arg1) -> {
                    Employee employee = new Employee();
                    Position position = new Position();
                    Office office = new Office();

                    employee.setNr_pracownika(rs.getInt("nr_pracownika"));
                    employee.setImie(rs.getString("imie"));
                    employee.setNazwisko(rs.getString("nazwisko"));
                    employee.setData_urodzenia(rs.getDate("data_urodzenia"));
                    employee.setPlec(rs.getString("plec"));
                    employee.setPesel(rs.getString("pesel"));
                    employee.setData_zatrudnienia(rs.getDate("data_zatrudnienia"));
                    employee.setNr_konta(rs.getString("nr_konta"));
                    employee.setNr_telefonu(rs.getString("nr_telefonu"));
                    employee.setAdres_email(rs.getString("adres_email"));
                    employee.setNr_adresu(rs.getInt("nr_adresu"));

                    position.setNr_stanowiska(rs.getInt("nr_stanowiska"));
                    position.setNazwa_stanowiska(rs.getString("nazwa_stanowiska"));
                    position.setOpis(rs.getString("opis"));

                    office.setNr_biura(rs.getInt("nr_biura"));
                    office.setNazwa(rs.getString("nazwa"));
                    office.setData_zalozenia(rs.getDate("data_zalozenia"));
                    office.setNr_telefonu(rs.getString("nr_telefonu"));
                    office.setAdres_email(rs.getString("adres_email"));
                    office.setProcent_z_prowizji(rs.getInt("procent_z_prowizji"));
                    office.setNr_adresu(rs.getInt("nr_adresu"));

                    employee.setStanowisko(position);
                    employee.setBiuro(office);

                    return employee;
                });

        //BeanPropertyRowMapper.newInstance(Employee.class));

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
