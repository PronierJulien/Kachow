package kachow.api_invocations.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.MongoId;

import kachow.api_invocations.model.Competence;
import kachow.api_invocations.model.Monstre;

public class MonstreInvocDTO {

    @MongoId
    private String _id;
    private Element element;
    private int hp;
    private int atk;
    private int def;
    private int vit;
    private List<Skill> skills = new ArrayList<Skill>(3);
    private double lootRate;

    // Getters et Setters

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Element getElement() {
        return element;
    }

    public void setType(Element element) {
        this.element = element;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
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

    public double getLootRate(){
        return this.lootRate;
    }

    public void setLootRate(double lootRate){
        this.lootRate = lootRate;
    }

    public Monstre toMonstre(String idJoueur) {
        List<Competence> competences = new ArrayList<Competence>(3);
        for (Skill skill : this.skills) {
            System.out.println("BOOOO");
            competences.add(skill.toCompetence());
        }
        Monstre monstre = new Monstre(competences, this.hp, this.atk, this.def, this.vit, Element.toType(this.element), idJoueur);
        return monstre;
    }

}