package bdbt_projekt.SpringApplication;

import bdbt_projekt.SpringApplication.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionDAO {
    /* Import org.springframework.jd....Template */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /* Import java.util.List (zawiera info z bazy danych) */
    public PositionDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Position> list(){
        String sql = "SELECT * FROM STANOWISKA";

        List<Position> listPosition = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Position.class));

        return listPosition;
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Position position) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("STANOWISKA").usingColumns("nazwa_stanowiska", "opis");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(position);
        insertActor.execute(param);
    }
    /* Read – odczytywanie danych z bazy */
    public Position get(int id) {
        Object[] args = {id};
        String sql = "SELECT * FROM STANOWISKA WHERE NR_STANOWISKA = ?";

        Position position = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Position.class));

        return position;
    }

    /* Update – aktualizacja danych */
    public void update(Position position) {
        String sql = "UPDATE STANOWISKA SET nazwa_stanowiska=:nazwa_stanowiska, opis=:opis WHERE nr_stanowiska=:nr_stanowiska";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(position);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql ="DELETE FROM STANOWISKA WHERE nr_stanowiska = ?";
        jdbcTemplate.update(sql, id);
    }
}
