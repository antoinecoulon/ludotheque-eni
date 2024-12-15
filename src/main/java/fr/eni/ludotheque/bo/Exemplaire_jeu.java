package fr.eni.ludotheque.bo;

import java.util.Objects;

public class Exemplaire_jeu {

	private int id_exemplaire;
	private String code_barre;
	private boolean louable;
	private int id_jeu;
	
	
	public Exemplaire_jeu(String code_barre, boolean louable, int id_jeu) {
		super();
		this.code_barre = code_barre;
		this.louable = louable;
		this.id_jeu = id_jeu;
	}

	public Exemplaire_jeu(int id_exemplaire, String code_barre, boolean louable, int id_jeu) {
		this.id_exemplaire = id_exemplaire;
		this.code_barre = code_barre;
		this.louable = louable;
		this.id_jeu = id_jeu;
	}

	public Exemplaire_jeu() {

	}


	public int getId_exemplaire() {
		return id_exemplaire;
	}


	public void setId_exemplaire(int id_exemplaire) {
		this.id_exemplaire = id_exemplaire;
	}


	public String getCode_barre() {
		return code_barre;
	}


	public void setCode_barre(String code_barre) {
		this.code_barre = code_barre;
	}


	public boolean isLouable() {
		return louable;
	}


	public void setLouable(boolean louable) {
		this.louable = louable;
	}


	public int getId_jeu() {
		return id_jeu;
	}


	public void setId_jeu(int id_jeu) {
		this.id_jeu = id_jeu;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Exemplaire_jeu{");
		sb.append("id_exemplaire=").append(id_exemplaire);
		sb.append(", code_barre='").append(code_barre).append('\'');
		sb.append(", louable=").append(louable);
		sb.append(", id_jeu=").append(id_jeu);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Exemplaire_jeu that = (Exemplaire_jeu) o;
		return id_exemplaire == that.id_exemplaire && id_jeu == that.id_jeu;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_exemplaire, id_jeu);
	}
}
