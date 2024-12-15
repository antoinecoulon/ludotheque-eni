package fr.eni.ludotheque.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.ludotheque.bo.Client;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final Logger log = LoggerFactory.getLogger(ClientRepositoryImpl.class);

	public ClientRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	//ROW MAPPER
	class ClientRowMapper implements RowMapper<Client> {
		@Override
		public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
			Client client = new Client();
			client.setId_client(rs.getInt("id_client"));
			client.setNom(rs.getString("nom"));
			client.setPrenom(rs.getString("prenom"));
			client.setEmail(rs.getString("email"));
			client.setTelephone(rs.getString("telephone"));
			client.setRue(rs.getString("rue"));
			client.setCodePostal(rs.getString("codePostal"));
			client.setVille(rs.getString("ville"));
			return client;
		}
	}
	
	// AFFICHER TOUS LES CLIENTS
	@Override
	public List<Client> findAllClients(){
		String sql = "SELECT * FROM Client ORDER BY id_client ASC";
		List<Client> clients = jdbcTemplate.query(sql, new ClientRowMapper());
		return clients;
	}
	
	// SELECTIONNER UN SEUL CLIENT
	@Override
	public Optional<Client> findClientById(int id_client) {
		String sql = "SELECT * FROM Client WHERE id_client = ? ";
		try {
		Client client = jdbcTemplate.queryForObject(sql, new ClientRowMapper(), id_client);
		return Optional.ofNullable(client);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	// AJOUTER UN NOUVEAU CLIENT
	@Override
	public Client addClient(Client client) {
		log.info("Création du client : {}", client );
		try {
			String sql = "INSERT INTO Client (nom, prenom, email, telephone, rue, codePostal, ville)"
					+ " VALUES (:nom, :prenom, :email, :telephone, :rue, :codePostal, :ville)";
			int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(client));
			if(nbRows != 1) {
				throw new RuntimeException("Aucune ligne n'a été ajoutée");
			}
			log.debug("Client {} créé avec succès", client);
		} catch (Exception e) {
			log.error("Erreur lors de la création du client : {}", client, e);
		}
		return client;
	}
	
	// MODIFIER UN CLIENT EXISTANT
	@Override
	public void modifierClient(Client client) {
		String sql = "UPDATE Client SET nom=:nom, prenom=:prenom, email=:email, telephone=:telephone, rue=:rue, codePostal=:codePostal, ville=:ville"
				+ " WHERE id_client = :id_client";
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(client));
	}

	// SUPPRIMER UN CLIENT
	@Override
	public void supprimerClient(int id_client) {
		String sql = "DELETE FROM Client WHERE id_client=" + id_client;
		int nbRowsDeleted = jdbcTemplate.update(sql);
		if(nbRowsDeleted != 1) {
			throw new RuntimeException("Erreur: la ligne n'a pas été supprimée.");
		}
	}
	
	
	

}
