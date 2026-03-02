package org.example.entities;

import org.example.items.Weapon;

@SuppressWarnings("unchecked")
public abstract class AbstractCharacter<T extends AbstractCharacter<T>> implements Character<T>{
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int level;
    protected int experience;
    protected Weapon weapon;

    //<editor-fold desc="Gettery i Settery">
    public String getName() { return name; }
    public T setName(String name) { this.name = name; return (T)this; }

    public int getHealth() { return health; }
    public T setHealth(int health) { this.health = health; return (T)this; }

    public void heal(int amount) {
        this.health += amount;
        if(this.health > maxHealth) {
            this.health = maxHealth;
        }
    }
    public void takeDamage(int amount) { this.health -= amount; }

    public int getMaxHealth() { return this.maxHealth; }
    public T setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; return (T)this; }

    public int getLevel() { return level; }
    public T setLevel(int level) { this.level = level; return (T)this; }

    public void levelUp() {

    }

    public int getExperience() { return experience; }
    public T setExperience(int experience) { this.experience = experience; return (T)null; }

    public void addExperience(int amount) {
        this.experience += amount;
        if(this.experience >= getMaxExperience()){
            levelUp();
        }
    }

    public Weapon getWeapon() { return weapon; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    //</editor-fold>
}
