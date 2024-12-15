package fr.eni.ludotheque.bo;

import java.time.LocalDate;
import java.util.Objects;

public class Location {

    private int id_location;
    private LocalDate date_debut_location;
    private boolean paye;
    private double prix_total;

    private Client client;

    public Location(int id_location, LocalDate date_debut_location, boolean paye, double prix_total, Client client) {
        this.id_location = id_location;
        this.date_debut_location = date_debut_location;
        this.paye = paye;
        this.prix_total = prix_total;
        this.client = client;
    }

    public Location() {

    }

    public int getId_location() {
        return id_location;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
    }

    public LocalDate getDate_debut_location() {
        return date_debut_location;
    }

    public void setDate_debut_location(LocalDate date_debut_location) {
        this.date_debut_location = date_debut_location;
    }

    public boolean isPaye() {
        return paye;
    }

    public void setPaye(boolean paye) {
        this.paye = paye;
    }

    public double getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(double prix_total) {
        this.prix_total = prix_total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Location{");
        sb.append("id_location=").append(id_location);
        sb.append(", date_debut_location=").append(date_debut_location);
        sb.append(", paye=").append(paye);
        sb.append(", prix_total=").append(prix_total);
        sb.append(", client=").append(client);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id_location == location.id_location && paye == location.paye && Double.compare(prix_total, location.prix_total) == 0 && Objects.equals(date_debut_location, location.date_debut_location) && Objects.equals(client, location.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_location, date_debut_location, paye, prix_total, client);
    }
}
