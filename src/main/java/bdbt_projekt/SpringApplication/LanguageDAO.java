package bdbt_projekt.SpringApplication;

import java.util.List;

import bdbt_projekt.SpringApplication.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LanguageDAO {
    /* Import org.springframework.jd....Template */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /* Import java.util.List (zawiera info z bazy danych) */
    public LanguageDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Language> list(){
        String sql = "SELECT * FROM JEZYKI";

        List<Language> listLanguage = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Language.class));

        return listLanguage;
    }


    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Language sale) {
    }


    /* Read – odczytywanie danych z bazy */
    public Language get(int id) {
        return null;
    }


    /* Update – aktualizacja danych */
    public void update(Language language) {
    }


    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
    }
}
