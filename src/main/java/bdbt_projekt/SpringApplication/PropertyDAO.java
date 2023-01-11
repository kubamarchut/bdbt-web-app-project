package bdbt_projekt.SpringApplication;
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

    public void update(Property property){

    }

    public void delete(int nr_nieruchomosci){

    }

}
