package fr.eni.ludotheque.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.ludotheque.bo.Genre;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private List<Genre> genres;
	
	public GenreRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		genres = new ArrayList<Genre>();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	//ROW MAPPER < GENRE > remplacé par BeanPropertyRowMappaer<>(Genre.class)
//	class GenreRowMapper implements RowMapper<Genre> {
//		@Override
//		public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Genre genre = new Genre();
//			genre.setId_genre(rs.getInt("id_genre"));
//			genre.setLibelle(rs.getString("libelle"));
//			return genre;
//		}
//	}
	
	// AFFICHER LA LISTE DE TOUS LES JEUX
	@Override
	public List<Genre> getAll() {
	    String sql = "SELECT id_genre, libelle FROM genre"; // Exemple de requête SQL
	    genres = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Genre.class));

		return genres.stream().collect(Collectors.toList());
	}
	
	@Override
	public Optional<Genre> getById(int id_genre) {
		return genres.stream().filter(genre->genre.getId_genre()==id_genre).findFirst();
	}
}

