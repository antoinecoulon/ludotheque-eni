package fr.eni.ludotheque.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.JeuRepository;

@Service
public class JeuServiceImpl implements JeuService {

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	JeuRepository jeuRepository;
	public JeuServiceImpl(JeuRepository jeuRepository, JdbcTemplate jdbcTemplate) {
		this.jeuRepository = jeuRepository;
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// AFFICHER JEUX
	@Override
	public List<Jeu> getAll() {
		return jeuRepository.getAll();
	}
	
	// SELECTIONNER UN JEU PAR SON ID
	@Override
	public Optional<Jeu> findJeuById(int id_jeu){
		return jeuRepository.findJeuById(id_jeu);
	}
	
	// AJOUTER UN JEU
	@Override
	public void addJeu(Jeu jeu) {
		jeuRepository.addJeu(jeu);
	}
	
	// MODIFIER UN JEU EXISTANT
		@Override
		public void modifierJeu(Jeu jeu, List<Genre> genre) {
			String sql = "UPDATE Jeu SET nom=:nom, prenom=:prenom, email=:email, telephone=:telephone, rue=:rue, codePostal=:codePostal, ville=:ville"
						+ " WHERE id_client = :id_client";
			jeu.setGenres(genre);
			namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(jeu));
		}

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
