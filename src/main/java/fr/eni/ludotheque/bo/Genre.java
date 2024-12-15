package fr.eni.ludotheque.bo;

import java.util.Objects;

import jakarta.validation.constraints.Size;

public class Genre {

//---------------------------------------ATTRIBUTS-------------------------------------------------------	
	private Integer id_genre;
	@Size(min=2,max=100,message="Le libelle ne peut pas contenir plus de 100 caractères.")
	private String libelle;

//--------------------------------------CONSTRUCTEURS----------------------------------------------------	
	public Genre() {
		
	}
	public Genre(Integer id_genre, String libelle) {
		super();
		this.id_genre = id_genre;
		this.libelle = libelle;
	}
	
//------------------------------------GETTERS SETTERS---------------------------------------------------------	
	public Integer getId_genre() {
		return id_genre;
	}
	public void setId_genre(Integer id_genre) {
		this.id_genre = id_genre;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	@Override
	public String toString() {
		return String.format("Genre [id_genre=%s, libelle=%s]", id_genre, libelle);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id_genre, libelle);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		return id_genre == other.id_genre && Objects.equals(libelle, other.libelle);
	}
	
}
