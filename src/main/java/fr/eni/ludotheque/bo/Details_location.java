package fr.eni.ludotheque.bo;

import java.time.LocalDate;
import java.util.Objects;

public class Details_location {

    private int id_ligne;
    private LocalDate date_retour;
    private double tarif_location;

    private Jeu jeu;

    public Details_location(int id_ligne, LocalDate date_retour, double tarif_location, Jeu jeu) {
        this.id_ligne = id_ligne;
        this.date_retour = date_retour;
        this.tarif_location = tarif_location;
        this.jeu = jeu;
    }

    public int getId_ligne() {
        return id_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public LocalDate getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(LocalDate date_retour) {
        this.date_retour = date_retour;
    }

    public double getTarif_location() {
        return tarif_location;
    }

    public void setTarif_location(double tarif_location) {
        this.tarif_location = tarif_location;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Details_location{");
        sb.append("id_ligne=").append(id_ligne);
        sb.append(", date_retour=").append(date_retour);
        sb.append(", tarif_location=").append(tarif_location);
        sb.append(", jeu=").append(jeu);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Details_location that = (Details_location) o;
        return id_ligne == that.id_ligne && Double.compare(tarif_location, that.tarif_location) == 0 && Objects.equals(date_retour, that.date_retour) && Objects.equals(jeu, that.jeu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_ligne, date_retour, tarif_location, jeu);
    }
}
