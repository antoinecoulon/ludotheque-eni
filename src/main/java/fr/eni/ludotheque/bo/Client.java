package fr.eni.ludotheque.bo;

import java.beans.ConstructorProperties;
import java.util.Objects;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Client {

	private int id_client;
	
	// Attributs avec validation
	@NotEmpty(message="Le champ ne doit pas être vide.")
	@Size(
			min=2, 
			max=30, 
			message="Le nom doit contenir entre 2 et 30 caractères."
			)
	private String nom;
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
	@Size(
			min=2, 
			max=30, 
			message="Le prénom doit contenir entre 2 et 30 caractères."
			)
	private String prenom;
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
	@Pattern(
			regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$",
			message = "Le format de l\'email doit être valide."
			)
	private String email;		// UNIQUE
	
	@Pattern(
			regexp = "^[0-9]{10}$",
			message = "Le numéro de téléphone doit se composer de 10 caractères numériques.")
	private String telephone;	// Non obligatoire
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
	private String rue;
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
	@Pattern(
			regexp = "^[0-9]{5}$",
			message = "Le code postal doit comporter 5 caractères numériques."
			)
	private String codePostal;
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
	private String ville;
	
	
	// Constructeur
	@ConstructorProperties({"id_client", "nom", "prenom", "email", "telephone", "rue", "codePostal", "ville"})
	public Client(int id_client, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville) {
		super();
		this.id_client = id_client;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public Client() {
	}
	
	
	//Getters Setters
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return String.format(
				"Client [id_client=%s, nom=%s, prenom=%s, email=%s, telephone=%s, rue=%s, codePostal=%s, ville=%s]",
				id_client, nom, prenom, email, telephone, rue, codePostal, ville);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id_client);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(email, other.email) && id_client == other.id_client;
	}
	
	
	
}
