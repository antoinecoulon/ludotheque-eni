package fr.eni.ludotheque.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
	
	ClientRepository clientRepository;
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	//AFFICHER CLIENTS
	@Override
	public List<Client> getAllClients(){
		return clientRepository.findAllClients();
	}
	
	// SELECTIONNER UN CLIENT PAR SON ID
	@Override
	public Optional<Client> findClientById(int id_client) {
		return clientRepository.findClientById(id_client);
	}
	
	//AJOUTER CLIENT
	@Override
	public Client addClient(Client client) {
		return clientRepository.addClient(client);
	}
	
	//MODIFIER CLIENT
	@Override
	public void modifierClient(Client client) {
		clientRepository.modifierClient(client);
	}
	
	//SUPPRIMER CLIENT
	@Override
	public void deleteClient(int id_client) {
		clientRepository.supprimerClient(id_client);
	}
}
