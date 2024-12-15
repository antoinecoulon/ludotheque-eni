package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Exemplaire_jeu;
import fr.eni.ludotheque.dal.Exemplaire_jeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Exemplaire_jeuServiceImpl implements Exemplaire_jeuService {

    @Autowired
    private Exemplaire_jeuRepository exemplaire_jeuRepository;

    public int getNbExemplaires(int id_jeu) {
        return exemplaire_jeuRepository.getNbExemplaireJeu(id_jeu);
    }

    @Override
    public int getNbExemplaireLouable(int id_jeu) {
        return exemplaire_jeuRepository.getNbExemplaireLouable(id_jeu);
    }

    @Override
    public List<Exemplaire_jeu> findAll() {
        return exemplaire_jeuRepository.findAll();
    }

    @Override
    public Exemplaire_jeu addExemplaire(Exemplaire_jeu exemplaire_jeu, int id_jeu) {
        return exemplaire_jeuRepository.addExemplaire(exemplaire_jeu, id_jeu);
    }

    @Override
    public List<Exemplaire_jeu> findByIdJeu(int id_jeu) {
        return exemplaire_jeuRepository.findByIdJeu(id_jeu);
    }
}
