package kachow.api_invocations.model;

import kachow.api_invocations.model.Competence;
import kachow.api_invocations.dto.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Monstre {
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

    public Monstre(List<Competence> competences2, int hp, int atk, int def, int vit, Type type2, String idJoueur) {
        this.id = UUID.randomUUID();
        this.lvl = 0;
        this.available_lvl = 0;
        this.xp = 0;
        this.xp_for_lvlup = 50;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.vit = vit;
        this.type = type2;
        this.idJoueur = idJoueur;
    }

    public String getId() {
        return id.toString();
    }

}