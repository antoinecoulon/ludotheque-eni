package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Location;

import java.util.List;

public interface LocationRepository {
    List<Location> findAll();
    Client findClient(int id_location);
}
