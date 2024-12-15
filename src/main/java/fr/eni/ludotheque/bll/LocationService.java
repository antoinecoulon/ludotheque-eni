package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Location;

import java.util.List;

public interface LocationService {
    List<Location> findAll();
    Client findClient(int id_location);
}
