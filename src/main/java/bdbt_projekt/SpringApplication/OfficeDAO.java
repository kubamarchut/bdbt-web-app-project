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
    public List<Office> list(){
        String sql = "SELECT * FROM BIURA";

        List<Office> listOffices = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Office.class));

        return listOffices;
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Office office) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("BIURA").usingColumns("nazwa_stanowiska", "opis");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(office);
        insertActor.execute(param);
    }
    /* Read – odczytywanie danych z bazy */
    public Office get(int id) {
        Object[] args = {id};
        String sql = "SELECT * FROM BIURA WHERE NR_BIURA = ?";

        Office office = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Office.class));

        return office;
    }

    /* Update – aktualizacja danych */
    public void update(Position position) {
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
    }
}
