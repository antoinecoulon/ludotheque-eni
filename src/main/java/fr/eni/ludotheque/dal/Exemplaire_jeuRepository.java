package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Exemplaire_jeu;

import java.util.List;

public interface Exemplaire_jeuRepository {
    int getNbExemplaireJeu(int id_jeu);
    int getNbExemplaireLouable(int id_jeu);
    List<Exemplaire_jeu> findAll();
    Exemplaire_jeu addExemplaire(Exemplaire_jeu exemplaire_jeu, int id_jeu);
    List<Exemplaire_jeu> findByIdJeu(int id_jeu);
}
