package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Exemplaire_jeu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Exemplaire_jeuRepositoryImpl implements Exemplaire_jeuRepository {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public Exemplaire_jeuRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	RowMapper<Exemplaire_jeu> exemplaire_JeuRowMapper = (rs, rowNum) -> {
		Exemplaire_jeu exemplaire_jeu = new Exemplaire_jeu();
		exemplaire_jeu.setId_exemplaire(rs.getInt("id_exemplaire"));
		exemplaire_jeu.setCode_barre(rs.getString("code_barre"));
		exemplaire_jeu.setLouable(rs.getBoolean("louable"));
		exemplaire_jeu.setId_jeu(rs.getInt("id_jeu"));
		return exemplaire_jeu;
	};

	public List<Exemplaire_jeu> findAll() {
		String sql = "SELECT * FROM Exemplaire_jeu";
		return namedParameterJdbcTemplate.query(sql, exemplaire_JeuRowMapper);
	}

	public List<Exemplaire_jeu> findByIdJeu(int id_jeu) {
		String sql = "SELECT * FROM Exemplaire_jeu WHERE id_jeu = ?";
		return jdbcTemplate.query(sql, new Object[]{id_jeu}, exemplaire_JeuRowMapper);
	}

	public int getNbExemplaireJeu(int id_jeu) {
		String sql = "SELECT COUNT(id_exemplaire) FROM Exemplaire_jeu WHERE id_jeu = ?";
		int nbExemplaires = jdbcTemplate.queryForObject(sql, Integer.class, id_jeu);
		return nbExemplaires;
	}
	public int getNbExemplaireLouable(int id_jeu) {
		String sql = "SELECT COUNT(id_exemplaire) FROM Exemplaire_jeu WHERE louable=true AND id_jeu = ?";
		int nbExemplaires = jdbcTemplate.queryForObject(sql, Integer.class, id_jeu);
		return nbExemplaires;
	}

	public Exemplaire_jeu addExemplaire(Exemplaire_jeu exemplaire_jeu, int id_jeu) {
		String sql = "INSERT INTO Exemplaire_jeu (code_barre, louable, id_jeu) VALUES (:code_barre, :louable, :id_jeu)";
		int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(exemplaire_jeu));
		if (nbRows != 1) {
			throw new RuntimeException("Aucune ligne n'a été ajoutée !");
		}
		return exemplaire_jeu;
	}
}
