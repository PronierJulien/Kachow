package kachow.api_invocations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import kachow.api_invocations.model.Competence;

public class Skill {

    @JsonProperty("num")
    private int num;

    @JsonProperty("dmg")
    private int dmg;

    @JsonProperty("ratio")
    private Ratio ratio;

    @JsonProperty("cooldown")
    private int cooldown;

    @JsonProperty("lvlMax")
    private int lvlMax;

    public Skill(@JsonProperty("num") int num, @JsonProperty("dmg") int dmg, @JsonProperty("ratio") Ratio ratio, @JsonProperty("cooldown") int cooldown, @JsonProperty("lvlMax") int lvlMax) {
        this.num = num;
        this.dmg = dmg;
        this.ratio = ratio;
        this.cooldown = cooldown;
        this.lvlMax = lvlMax;
    }

    public Competence toCompetence() {
        return new Competence(dmg, ratio.getPercent(), cooldown, lvlMax);
    }
}