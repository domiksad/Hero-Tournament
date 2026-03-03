package org.example.items;

import org.example.entities.Enemy;
import org.example.entities.Player;

public class Weapon implements Item{
    private String name;
    private int damage;
    private int cooldown;
    private int levelReq;
    private int cost;
    private int cooldownTimer = 0;

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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String displayWeapon(){
        return String.format("%s (%d dmg, %d cooldown, %d level rq)", name, damage, cooldown, levelReq);
    }

    public boolean use(Player player, Enemy enemy){
        if(cooldownTimer == 0){
            cooldownTimer = cooldown;
            return true;
        } else {
            return false;
        }
    }

    public void decrementCooldownTimer(){
        cooldownTimer -= 1;
        if(cooldownTimer < 0) cooldownTimer = 0;
    }

    public void resetCooldownTimer(){
        cooldownTimer = 0;
    }
    //</editor-fold>
}
