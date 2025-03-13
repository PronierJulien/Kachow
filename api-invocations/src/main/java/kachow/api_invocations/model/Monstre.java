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

    public Monstre(List<Competence> competences, int hp, int atk, int def, int vit, Type type2, String idJoueur) {
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
        this.competences = competences;
    }

    public String getId() {
        return id.toString();
    }

    public int getAtk() {
        return atk;
    }

    public int getHp() {
        return hp;
    }

    public int getDef() {
        return def;
    }

    public int getVit() {
        return vit;
    }

    public int getXp() {
        return xp;
    }

    public int getXpForLvlup() {
        return xp_for_lvlup;
    }

    public int getLvl() {
        return lvl;
    }

    public int getAvailableLvl() {
        return available_lvl;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public String getIdJoueur() {
        return idJoueur;
    }

    public Type getType() {
        return type;
    }

    public String toString() {
        return "Monstre [id=" + id + ", type=" + type + ", hp=" + hp + ", atk=" + atk + ", def=" + def + ", vit=" + vit
                + ", xp=" + xp + ", xp_for_lvlup=" + xp_for_lvlup + ", lvl=" + lvl + ", available_lvl=" + available_lvl
                + ", competences=" + competences + ", idJoueur=" + idJoueur + "]";
    }

}