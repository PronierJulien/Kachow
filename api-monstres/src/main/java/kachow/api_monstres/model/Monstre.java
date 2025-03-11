package kachow.api_monstres.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document
public class Monstre {
    @MongoId
    private UUID id;
    private Type type;
    private int hp;
    private int atk;
    private int def;
    private int vit;
    private int xp;
    private int xp_for_lvlup;
    private int lvl;
    private int available_lvl;
    private List<Competence> competences = new ArrayList<Competence>(3);
    private String idJoueur;

    public Monstre(){
        this.xp_for_lvlup = 50;
    }

}
