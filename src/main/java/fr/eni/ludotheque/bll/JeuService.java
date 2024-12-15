package fr.eni.ludotheque.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;

public interface JeuService {

	List<Jeu> getAll();

	Optional<Jeu> findJeuById(int id_jeu);

	void addJeu(Jeu jeu);

	void modifierJeu(Jeu jeu, List<Genre> genre);

	void supprimerJeu(int id_jeu);
}
