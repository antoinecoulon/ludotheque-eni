package fr.eni.ludotheque.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.ludotheque.bo.Client;

public interface ClientService {

	List<Client> getAllClients();

	Client addClient(Client client);

	void deleteClient(int id_client);

	Optional<Client> findClientById(int id_client);

	void modifierClient(Client client);
	
}
