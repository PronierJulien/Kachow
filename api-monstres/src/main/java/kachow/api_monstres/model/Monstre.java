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

    public Monstre(List<Competence> competence, int hp, int atk, int def, int vit, Type type, String idJoueur) {
        this.id = UUID.randomUUID();
        this.lvl = 0;
        this.available_lvl = 0;
        this.xp = 0;
        this.xp_for_lvlup = 50;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.vit = vit;
        this.type = type;
        this.idJoueur = idJoueur;
    }

}
