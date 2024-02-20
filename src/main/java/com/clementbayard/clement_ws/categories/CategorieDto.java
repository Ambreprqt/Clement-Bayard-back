package com.clementbayard.clement_ws.categories;

import com.clementbayard.clement_ws.article.Article;

import java.util.ArrayList;
import java.util.List;

public class CategorieDto {
    private Long categorieId;
    private String nom;
    private List<Article> article=new ArrayList<>();
    public void setId(Long id) {
        this.categorieId = id;
    }

    public Long getId() {
        return categorieId;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }
}
