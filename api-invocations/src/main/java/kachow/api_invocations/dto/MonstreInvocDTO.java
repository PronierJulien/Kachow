package kachow.api_invocations.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.MongoId;

public class MonstreInvocDTO {

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

    // Getters et Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }
}