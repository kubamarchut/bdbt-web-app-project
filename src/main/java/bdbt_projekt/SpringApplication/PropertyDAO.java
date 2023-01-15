package bdbt_projekt.SpringApplication;
import bdbt_projekt.SpringApplication.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropertyDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PropertyDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Property> list() {
        String sql = "SELECT * FROM NIERUCHOMOSCI";

        List<Property> listProperty = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Property.class));

        return listProperty;

    }

    public void save (Property property) {


    }

    public Property get(int property_id){
        Object[] args = {property_id};

        String sql = "SELECT * FROM NIERUCHOMOSCI WHERE NR_NIERUCHMOSCI = ?";

        Property property = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Property.class));

        return property;
    }

    public void update(Property property) {
        String sql = "INSERT INTO NIERUCHOMOSCI nr_nieruchomosci, metraz, liczba_pokoi, nr_ksiegi_wieczystej, liczba_pieter, " +
                "stan_wykonczenia, miejsce_parkingowe, rynek, cena, nr_adresu, nr_biura VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql, property.getNr_nieruchomosci(), property.getMetraz(), property.getLiczba_pokoi(),
                property.getNr_ksiegi_wieczystej(), property.getLiczba_pieter(), property.getStan_wykonczenia(), property.getMiejsce_parkingowe(),
                property.getRynek(), property.getCena(), property.getNr_adresu(), property.getNr_biura());

        if (insert == 1) {

        }

    }



    public void delete(int nr_nieruchomosci){

    }

}
