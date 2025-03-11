package kachow.api_monstres.model;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Competence {
    private int damage;
    private int ratio;
    private int cooldown;
    private int lvl;
    private final int maxLevel;

    public Competence(int damage, int ratio, int cooldown, int lvl) {
        this.damage = damage;
        this.ratio = ratio;
        this.cooldown = cooldown;
        this.lvl = lvl;
        this.maxLevel = lvl;
    }

    public void levelUp() {
        damage *= 1.1;
        ratio *= 1.1;
        cooldown *= 0.9;
        lvl = 1;
    }
}
