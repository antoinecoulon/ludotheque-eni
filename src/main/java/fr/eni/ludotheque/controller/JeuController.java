package fr.eni.ludotheque.controller;

import fr.eni.ludotheque.bll.GenreService;
import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class JeuController {

    JeuService jeuService;
    GenreService genreService;
    public JeuController(JeuService jeuService, GenreService genreService) {
        this.jeuService = jeuService;
        this.genreService = genreService;
    }

    // Affichage des jeux
    @GetMapping("/jeux")
    public String afficherJeux(Model model) {
        model.addAttribute("jeu", new Jeu());
        List<Jeu> jeux = jeuService.getAll();
        model.addAttribute("jeux", jeux);
        List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);

        return "jeux";
    }

    // Page pour ajouter un nouveau jeu
    @GetMapping("/ajouterJeu")
    public String ajouterJeuPage(Jeu jeu, Model model) {
        model.addAttribute("jeu", new Jeu());

        List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);

        return "ajouterJeu";
    }

    // Création des jeux
    @PostMapping("/ajouterJeu")
    public String ajouterJeu(@Valid Jeu jeu, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Collecte tous les messages d'erreur
            List<String> errorMessages = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage()) // Récupère le message d'erreur par défaut
                    .toList();

            // Ajoute les messages d'erreur au modèle
            model.addAttribute("errorMessages", errorMessages);
            return "ajouterJeu";
        }

        // Si pas d'erreur, on ajoute le jeu et on redirige vers la page Jeux
        jeuService.addJeu(jeu);
        List<Genre> genres = genreService.getAll();
        model.addAttribute(genres);

        return "redirect:/jeux";
    }

    // Afficher détails d'un jeu
    @GetMapping("/detailsJeu/{id_jeu}")
    public String afficherDetailsJeu(@PathVariable("id_jeu") int id_jeu, Model model) {
        Optional<Jeu> jeu = jeuService.findJeuById(id_jeu);
        if(jeu.isEmpty()) {
            throw new RuntimeException("Aucun jeu trouvé avec l'ID : " + id_jeu);
        }
        model.addAttribute("jeu", jeu.get());
        return "/detailsJeu";
    }

    // Supprimer un jeu
    @PostMapping("/jeux/supprimer")
    public String supprimerJeu(@RequestParam("id_jeu") int id_jeu) {
        jeuService.supprimerJeu(id_jeu);
        return "redirect:/jeux";
    }
}
