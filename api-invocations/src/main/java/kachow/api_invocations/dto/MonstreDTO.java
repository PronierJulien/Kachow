package kachow.api_invocations.dto;

import java.util.List;

public class MonstreDTO {

    private String id;
    private String nom;
    private String typeElementaire; // feu, eau, vent
    private int hp;
    private int atk;
    private int def;
    private int vit;
    private double tauxInvocation; // Probabilité d'invocation en %
    private List<String> competences;

    // Getters et Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypeElementaire() {
        return typeElementaire;
    }

    public void setTypeElementaire(String typeElementaire) {
        this.typeElementaire = typeElementaire;
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

    public double getTauxInvocation() {
        return tauxInvocation;
    }

    public void setTauxInvocation(double tauxInvocation) {
        this.tauxInvocation = tauxInvocation;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public void setCompetences(List<String> competences) {
        this.competences = competences;
    }
}