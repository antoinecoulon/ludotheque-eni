package fr.eni.ludotheque.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;

public interface JeuRepository {
	
	List<Jeu> getAll();

	Optional<Jeu> findJeuById(int id_jeu);

	void addJeu(Jeu newJeu);

	List<Genre> getGenresByIdJeu(Integer id_jeu);

	//void modifierJeu(Jeu jeu, List<Genre> genre);

	void supprimerJeu(int id_jeu);

}
