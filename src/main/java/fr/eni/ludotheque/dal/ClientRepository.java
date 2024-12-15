package fr.eni.ludotheque.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.ludotheque.bo.Client;

public interface ClientRepository {

	Client addClient(Client client);
	void supprimerClient(int id_client);

	List<Client> findAllClients();
	Optional<Client> findClientById(int id_client);
	void modifierClient(Client client);
}
