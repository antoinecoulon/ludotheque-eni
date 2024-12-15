package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Utilisateurs;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UtilisateursRepositoryImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Utilisateurs> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        RowMapper<Utilisateurs> rowMapper = (rs, rowNum) -> {
            Utilisateurs users = new Utilisateurs();
            users.setId(rs.getInt("id"));
            users.setUsername(rs.getString("username"));
            users.setPassword(rs.getString("password"));
            users.setRole(rs.getString("role"));
            return users;
        };

        try {
            Utilisateurs users = jdbcTemplate.queryForObject(sql, rowMapper, username);
            return Optional.ofNullable(users);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
