package org.example.items;

public class Weapon {
    private String name;
    private int damage;
    private int cooldown;
    private int levelReq;

    public Weapon(String name, int damage, int cooldown, int levelReq) {
        this.name = name;
        this.damage = damage;
        this.cooldown = cooldown;
        this.levelReq = levelReq;
    }

    //<editor-fold desc="Gettery i Settery">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getLevelReq() {
        return levelReq;
    }

    public void setLevelReq(int levelReq) {
        this.levelReq = levelReq;
    }

    public String displayWeapon(){
        return String.format("%s (%d dmg, %d cooldown, %d level rq)", name, damage, cooldown, levelReq);
    }
    //</editor-fold>
}
