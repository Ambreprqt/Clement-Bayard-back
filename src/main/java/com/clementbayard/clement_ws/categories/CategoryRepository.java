package com.clementbayard.clement_ws.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Categorie,Long> {

    @Query("SELECT c FROM Categorie c WHERE c.nom = :nom")
    public Categorie findByNom(@Param("nom") String nom);
}
