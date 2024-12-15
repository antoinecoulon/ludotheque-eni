package fr.eni.ludotheque.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Jeu {

//----------------------------------ATTRIBUTS--------------------------------------------------------	
	// REVOIR LA VALIDATION DES TYPES int ET float !!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private int id_jeu;		// PRIMARY KEY
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
	@Size(min=2, max=30, message="Le titre doit contenir entre 2 et 30 caractères.")
	private String titre;

// Cette validation ne marche pas pour un long. TODO: valider dans la logique métier	
//	@Min(value=13, message="La référence se compose d'exactement 13 chiffres.")
//	@Max(value=13, message="La référence se compose d'exactement 13 chiffres.")
	private long reference; 	// UNIQUE
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
	@Size(min=2, max=255, message="Le titre doit contenir entre 2 et 255 caractères.")
	private String description;
	
	@Digits(integer = 3, fraction = 2, message = "La durée doit avoir jusqu'à 3 chiffres avant la virgule et 2 après.")
	private float tarifJournee;
	
	@Min(value = 1, message = "L'âge minimum doit être au moins 0.")
	@Max(value = 98, message = "L'âge minimum ne peut dépasser 98.")
	private int ageMin; // BDD Check ageMin >= 0 & <= 99
	
	@Digits(integer = 3, fraction = 2, message = "La durée doit avoir jusqu'à 3 chiffres avant la virgule et 2 après.")
	private float duree;
	
	private List<Genre> genres = new ArrayList<>();

//-------------------------------------------------CONSTRUCTEURS--------------------------------------------------	
	public Jeu() {
		//genres = new ArrayList<>();
	}
	
	public Jeu(int id_jeu) {
		this();
		this.id_jeu = id_jeu;
	}
	
	public Jeu(int id_jeu, String titre, long reference, String description, float tarifJournee, int ageMin,
			float duree, List<Genre> genres) {
		this.id_jeu = id_jeu;
		this.titre = titre;
		this.reference = reference;
		this.description = description;
		this.tarifJournee = tarifJournee;
		this.ageMin = ageMin;
		this.duree = duree;
		this.genres = genres;
	}
	
//----------------------------------GETTERS SETTERS--------------------------------------------------	
	public int getId_jeu() {
		return id_jeu;
	}
	public void setId_jeu(int id_jeu) {
		this.id_jeu = id_jeu;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public long getReference() {
		return reference;
	}
	public void setReference(long reference) {
		this.reference = reference;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getTarifJournee() {
		return tarifJournee;
	}
	public void setTarifJournee(float tarifJournee) {
		this.tarifJournee = tarifJournee;
	}
	public int getAgeMin() {
		return ageMin;
	}
	public void setAgeMin(int ageMin) {
		this.ageMin = ageMin;
	}
	public float getDuree() {
		return duree;
	}
	public void setDuree(float duree) {
		this.duree = duree;
	}
	
	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	//---------------------------------------------AUTRES METHODES-----------------------------------------------------	
	@Override
	public String toString() {
		return "Jeu [noJeu=" + id_jeu + ", titre=" + titre + ", reference=" + reference + ", description=" + description
				+ ", tarifJournee=" + tarifJournee + ", ageMin=" + ageMin + ", duree=" + duree + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_jeu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jeu other = (Jeu) obj;
		return Objects.equals(id_jeu, other.id_jeu);
	}

	
	
	
	
}
