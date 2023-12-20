package com.clementbayard.clement_ws.photo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.clementbayard.clement_ws.photographe.Photographe;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    private String filename; // Champ pour stocker le nom du fichier


    @ManyToOne
    @JoinColumn(name="photographe_id", nullable=false)
    private Photographe photographe;


}
