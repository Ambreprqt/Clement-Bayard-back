package com.clementbayard.clement_ws.photographe;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.clementbayard.clement_ws.photo.Photo;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Photographe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;

    private String prenom;

    private String numero_de_telephone;

    @OneToMany( targetEntity= Photo.class, mappedBy="photographe" , fetch = FetchType.LAZY)
    List<Photo> photos;

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

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
