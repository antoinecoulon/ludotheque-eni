package fr.eni.ludotheque.controller;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Client;
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
public class ClientController {

    ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Affichage des clients
    @GetMapping({"/clients"})
    public String afficherClients(Model model) {
        // Initialiser une instance de Client
        model.addAttribute("client", new Client());

        List<Client> listeClients = clientService.getAllClients();
        model.addAttribute("clients", listeClients);

        return "clients";
    }

    // Page pour ajouter un nouveau client
    @GetMapping("/ajouterClient")
    public String ajouterClientPage(Client client, Model model) {
        return "ajouterClient";
    }

    // Création des clients
    @PostMapping("/ajouterClient")
    public String ajouterClient(@Valid Client client, BindingResult result, Model model) {
        // Si on a une erreur on renvoie sur le formulaire avec un message d'erreur...
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Une erreur est survenue...");
            return "ajouterClient";
        }

        // ...si pas d'erreur on ajoute le client et on redirige vers la page Clients
        Client nouveauClient = clientService.addClient(client);
        model.addAttribute("nouveauClient", nouveauClient);

        return "redirect:/clients";
    }

    // Supprimer un client
    @PostMapping("/clients/supprimer")
    public String supprimerClient(@RequestParam("id_client") int id_client) {
        clientService.deleteClient(id_client);
        return "redirect:/clients";
    }

    // Page pour modifier un client
    @GetMapping("/modifierClient/{id_client}")
    public String modifierClient(@PathVariable("id_client") int id_client, Model model) {
        Optional<Client> client = clientService.findClientById(id_client);
        if(client.isEmpty()) {
            throw new RuntimeException("Aucun client trouvé avec l'ID : " + id_client);
        }
        model.addAttribute("client", client.get());
        return "modifierClient";
    }

    // Modifier un client existant
    @PostMapping("/modifierClient/modifier")
    public String modifier(@Valid Client client, BindingResult result, Model model) {
        // Si on a une erreur on renvoie sur le formulaire avec un message d'erreur...
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Une erreur est survenue...");
            return "modifierClient";
        }

        // ...si pas d'erreur on modifie le client et on redirige vers 'clients'
        clientService.modifierClient(client);
        return "redirect:/clients";
    }

    // Afficher détails d'un client
    @GetMapping("/detailsClient/{id_client}")
    public String afficherDetailsClient(@PathVariable("id_client") int id_client, Model model) {
        Optional<Client> client = clientService.findClientById(id_client);
        if(client.isEmpty()) {
            throw new RuntimeException("Aucun client trouvé avec l'ID : " + id_client);
        }
        model.addAttribute("client", client.get());
        return "/detailsClient";
    }
}
