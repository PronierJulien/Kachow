package kachow.api_invocations.model;

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

    public int getDamage() {
        return damage;
    }

    public int getRatio() {
        return ratio;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getLvl() {
        return lvl;
    }

    public int getMaxLevel() {
        return maxLevel;
    }
}