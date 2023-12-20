package com.clementbayard.clement_ws.photographe;

import java.util.List;

public class PhotographeDto {
    private long id;

    private String nom;

    private String prenom;

    private String numero_de_telephone;

    List<Long> photos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getNumero_de_telephone() {
        return numero_de_telephone;
    }

    public void setNumero_de_telephone(String numero_de_telephone) {
        this.numero_de_telephone = numero_de_telephone;
    }

    public List<Long> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Long> photos) {
        this.photos = photos;
    }
}
