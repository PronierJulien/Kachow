package kachow.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Document
public class Joueur {
    @MongoId
    private String id;
    private int lvl;
    private int xp;
    private List<String> monstres;
    private int max_monstres;
    private int nb_monstres;
    private int xp_for_lvlup;

    public Joueur(String id){
        this.id = id;
        this.lvl = 1;
        this.xp = 0;
        this.xp_for_lvlup = 50;
        this.max_monstres = 10;
        this.nb_monstres = 0;
        this.monstres = new ArrayList<>();
    }
}