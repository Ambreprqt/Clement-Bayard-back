package com.clementbayard.clement_ws.article;

import com.clementbayard.clement_ws.categories.Categorie;
import com.clementbayard.clement_ws.categories.CategorieDto;

public class ArticleDto {
    private Long id;

    private String nom;

    private String description;

    private String categorieNom;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorieNom() {
        return categorieNom;
    }

    public void setCategorieNom(String categorieNom) {
        this.categorieNom = categorieNom;
    }
}
