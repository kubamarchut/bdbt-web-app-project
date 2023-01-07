package bdbt_projekt.SpringApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LanguageDAOTest extends Object {

    private LanguageDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@194.29.170.4:1521:xe");
        dataSource.setUsername("BDBTGRC06");
        dataSource.setPassword("BDBTGRC06");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new LanguageDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Language> listLanguages = dao.list();
        System.out.println(Arrays.toString(listLanguages.toArray()));

        assertTrue(!listLanguages.isEmpty());
    }

    @Test
    void save() {
        fail("Not yet implemented");
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