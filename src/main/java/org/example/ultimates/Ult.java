package org.example.ultimates;

import org.example.entities.Enemy;
import org.example.entities.Player;

@SuppressWarnings("unchecked")
public abstract class Ult<T extends Ult<T>> {
    protected String name;
    protected int damage;
    protected int heal;
    protected int cooldown;
    protected int cooldownTimer;
    protected String desc = "Basic not working ult";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public T setDamage(int damage) {
        this.damage = damage;
        return (T)this;
    }

    public int getHeal() {
        return heal;
    }

    public T setHeal(int heal) {
        this.heal = heal;
        return (T)this;
    }

    public int getCooldown() {
        return cooldown;
    }

    public T setCooldown(int cooldown) {
        this.cooldown = cooldown;
        return (T)this;
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
        if(cooldownTimer<0) cooldownTimer = 0;
    }

    public void resetCooldownTimer(){
        cooldownTimer = cooldown;
    }

    public String getDesc(){
        return String.format("%s (Enemy hp: %d, Your hp: %d, Cooldown: %d). %s", name, damage, heal, cooldown, desc);
    }
}
