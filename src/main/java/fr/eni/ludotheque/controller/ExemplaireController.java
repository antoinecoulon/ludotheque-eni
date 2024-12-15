package fr.eni.ludotheque.controller;

import fr.eni.ludotheque.bll.Exemplaire_jeuService;
import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.bo.Exemplaire_jeu;
import fr.eni.ludotheque.bo.Jeu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ExemplaireController {

    Exemplaire_jeuService exemplaire_jeuService;
    JeuService jeuService;
    public ExemplaireController(Exemplaire_jeuService exemplaire_jeuService, JeuService jeuService) {
        this.exemplaire_jeuService = exemplaire_jeuService;
        this.jeuService = jeuService;
    }

    // Affichage
    @GetMapping("/exemplaire/{id_jeu}")
    public String afficherExemplaires(@PathVariable("id_jeu") int id_jeu, Model model) {
        Optional<Jeu> jeu = jeuService.findJeuById(id_jeu);
        if(jeu.isEmpty()) {
            throw new RuntimeException("Aucun jeu trouvé avec l'ID : " + id_jeu);
        }
        model.addAttribute("jeu", jeu.get());

        // Obtenez et configurez l'objet Exemplaire_jeu
        Exemplaire_jeu exemplaire_jeu = new Exemplaire_jeu();
        exemplaire_jeu.setId_jeu(id_jeu); // Assignez correctement l'ID ici !!!
        model.addAttribute("exemplaire_jeu", exemplaire_jeu);

        // Obtenir la liste des exemplaires du jeu
        List<Exemplaire_jeu> exemplaires = exemplaire_jeuService.findByIdJeu(id_jeu);
        model.addAttribute("exemplaires", exemplaires);

        // Obtenir le nombre d'exemplaires total d'un jeu
        int nombre_exemplaires = exemplaire_jeuService.getNbExemplaires(id_jeu);
        model.addAttribute("nombre_exemplaires", nombre_exemplaires);

        // Obtenir le nombre d'exemplaires LOUABLES d'un jeu
        int nombre_exemplaires_louables = exemplaire_jeuService.getNbExemplaireLouable(id_jeu);
        model.addAttribute("nombre_exemplaires_louables", nombre_exemplaires_louables);

        return "/exemplaire";
    }

    // Ajouter un exemplaire
    @PostMapping("/exemplaire/ajouterExemplaire/{id_jeu}")
    public String ajouterExemplaire(Exemplaire_jeu exemplaire_jeu, @PathVariable("id_jeu") Integer id_jeu, Model model) {
        // Vérifiez si l'ID du jeu est correctement transmis
        if (id_jeu == null || exemplaire_jeu.getId_jeu() == 0) {
            throw new IllegalArgumentException("L'ID du jeu est manquant !");
        }

        exemplaire_jeuService.addExemplaire(exemplaire_jeu, id_jeu);

        System.out.println("Exemplaire reçu : " + exemplaire_jeu);
        System.out.println("ID Jeu : " + exemplaire_jeu.getId_jeu());
        return "redirect:/exemplaire/" + id_jeu;
    }
}
