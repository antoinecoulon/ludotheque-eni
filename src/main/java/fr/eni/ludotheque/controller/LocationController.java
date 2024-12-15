package fr.eni.ludotheque.controller;

import fr.eni.ludotheque.bll.LocationService;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Location;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LocationController {

    LocationService locationService;
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    // Affichage
    @GetMapping("/location")
    public String afficherLocations(Model model) {
        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        Location location = new Location();
        Client client = locationService.findClient(location.getId_location());
        model.addAttribute("client", client);
        return "/location";
        // TODO: associer et afficher le client à sa location
    }

    //TODO: gérer les locations, calcul du prix total, association avec CLIENT et JEU
}
