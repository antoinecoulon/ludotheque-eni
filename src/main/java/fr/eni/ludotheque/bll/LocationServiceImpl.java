package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dal.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Client findClient(int id_location) {
        return locationRepository.findClient(id_location);
    }
}
