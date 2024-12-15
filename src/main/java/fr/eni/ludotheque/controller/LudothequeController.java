package fr.eni.ludotheque.controller;

import java.util.List;
import java.util.Optional;

import fr.eni.ludotheque.bll.*;
import fr.eni.ludotheque.bo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dal.GenreRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import jakarta.validation.Valid;

@Controller
public class LudothequeController {

	public LudothequeController() {}

	// Page d'accueil

	/**
	 * Définit la page d'accueil de l'application.
	 * En dev : 'http://localhost:8080/[home]'
	 */
	@GetMapping({"/", "/home"})
	public String home() { return "home"; }

	
}
