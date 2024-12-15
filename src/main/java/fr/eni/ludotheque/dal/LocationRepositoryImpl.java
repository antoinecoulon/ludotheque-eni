package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class LocationRowMapper implements RowMapper<Location> {
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setId_location(rs.getInt("id_location"));
            location.setDate_debut_location(rs.getDate("date_debut_location").toLocalDate());
            location.setPaye(rs.getBoolean("paye"));
            location.setPrix_total(rs.getDouble("prix_total"));
            return location;
        }

    }

    @Override
    public List<Location> findAll() {
        String sql = "select * from location";
        List<Location> locations = jdbcTemplate.query(sql, new LocationRowMapper());
        return locations;
    }

    public Client findClient(int id_location) {
        String sql = "select c.nom, c.prenom from location l inner join client c on l.id_client = c.id_client where l.id_location = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id_location}, (rs, rowNum) -> {
            Client client = new Client();
            client.setNom(rs.getString("nom"));
            client.setPrenom(rs.getString("prenom"));
            return client;
        });
    }
}
