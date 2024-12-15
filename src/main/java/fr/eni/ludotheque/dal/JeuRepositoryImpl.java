package fr.eni.ludotheque.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;

@Repository
public class JeuRepositoryImpl implements JeuRepository {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public JeuRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	
	record JeuGenreDto (Integer id_jeu, Integer id_genre) {};

	// AJOUTER UN NOUVEAU JEU
	@Override
	@Transactional
	public void addJeu(Jeu newJeu) {
		String sql = "INSERT INTO Jeu (titre, reference, description, tarifJournee, ageMin, duree)"
					+ " VALUES (:titre, :reference, :description, :tarifJournee, :ageMin, :duree)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(newJeu), keyHolder, new String[]{"id_jeu"});
		Integer cleJeu = keyHolder.getKeyAs(Integer.class);
		
		List<JeuGenreDto> jeuGenreDtos = newJeu.getGenres().stream().map(genre->new JeuGenreDto(cleJeu, genre.getId_genre())).toList();
			
		sql = "INSERT INTO jeu_genre (id_jeu, id_genre) VALUES (:id_jeu, :id_genre)";
		SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(jeuGenreDtos);
		this.namedParameterJdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	
	//ROW MAPPER < Jeu > remplacé par BeanPropertyRowMapper<>(Jeu.class)
//	class JeuRowMapper implements RowMapper<Jeu> {
//		@Override
//		public Jeu mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Jeu jeu = new Jeu();
//			jeu.setId_jeu(rs.getInt("id_jeu"));
//			jeu.setTitre(rs.getString("titre"));
//			jeu.setReference(rs.getLong("reference"));
//			jeu.setDescription(rs.getString("description"));
//			jeu.setTarifJournee(rs.getFloat("tarifJournee"));
//			jeu.setAgeMin(rs.getInt("ageMin"));
//			jeu.setDuree(rs.getFloat("duree"));
//			return jeu;
//		}
//	}
	
	// AFFICHER LA LISTE DE TOUS LES JEUX
	@Override
	public List<Jeu> getAll() {
		
		String sql = "SELECT * FROM Jeu ORDER BY id_jeu ASC";
		List<Jeu> jeux = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Jeu.class));
		
		for (Jeu jeu : jeux) {
	        List<Genre> genres = getGenresByIdJeu(jeu.getId_jeu());  // Charger les genres pour chaque jeu
	        jeu.setGenres(genres);  // Associer les genres au jeu
	    }
		
		return jeux;
	}
	
	// SELECTIONNER UN SEUL JEU
	@Override
	public Optional<Jeu> findJeuById(int id_jeu){
		String sql = "SELECT * FROM Jeu WHERE id_jeu = ?";
		Jeu jeu = jdbcTemplate.queryForObject(sql, 
				new BeanPropertyRowMapper<>(Jeu.class), id_jeu);
		
		List<Genre> genres = getGenresByIdJeu(jeu.getId_jeu());
		jeu.setGenres(genres);

		return Optional.ofNullable(jeu);		
	}
	
	@Override
	public List<Genre> getGenresByIdJeu(Integer id_jeu) {		
		String sql = "select genre.id_genre, libelle "
				+ " from jeu_genre inner join genre on jeu_genre.id_genre = genre.id_genre"
				+ " where jeu_genre.id_jeu = ? ";
		List<Genre> genres = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<>(Genre.class), id_jeu);
		
		return genres;
	}

	// MODIFIER UN JEU EXISTANT
//	@Override
//	public void modifierJeu(Jeu jeu, List<Genre> genre) {
//		String sql = "UPDATE Jeu SET nom=:nom, prenom=:prenom, email=:email, telephone=:telephone, rue=:rue, codePostal=:codePostal, ville=:ville"
//					+ " WHERE id_client = :id_client";
//		jeu.setGenres(genre);
//		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(jeu));
//	}

	// SUPPRIMER UN JEU
	@Override
	public void supprimerJeu(int id_jeu) {
		String sql = "DELETE FROM Jeu WHERE id_jeu=" + id_jeu;
		int nbRowsDeleted = jdbcTemplate.update(sql);
		if(nbRowsDeleted != 1) {
			throw new RuntimeException("Erreur: la ligne n'a pas été supprimée.");
		}
	}

}
