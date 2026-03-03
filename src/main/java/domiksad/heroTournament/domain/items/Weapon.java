package domiksad.heroTournament.domain.items;

import domiksad.heroTournament.domain.effects.Effect;
import domiksad.heroTournament.domain.mechanics.Cooldown;

public class Weapon implements Item{
    String name;
    int price;
    int levelReq = 1;
    String description;
    int damage;
    Cooldown cooldown;
    Effect[] effects;

    public String getName() {
        return name;
    }

    public Weapon setName(String name) {
        this.name = name;
        return this;
    }

    public Weapon setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getLevelReq() {
        return levelReq;
    }

    public Weapon setLevelReq(int levelReq) {
        this.levelReq = levelReq;
        return this;
    }

    public Weapon setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getDamage() {
        return damage;
    }

    public Weapon setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public Cooldown getCooldown() {
        return cooldown;
    }

    public Weapon setCooldown(Cooldown cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    public Effect[] getEffects() {
        return effects;
    }

    public Weapon setEffects(Effect[] effects) {
        this.effects = effects;
        return this;
    }

    @Override
    public String getDescription() {
        String status = cooldown.isReady()
                ? "READY NOW"
                : String.format("ready in %d turns", cooldown.getRemainingCooldown());

        return String.format(
                "%s (%d dmg) - %s (cooldown: %d turns)",
                this.name,
                this.damage,
                status,
                this.cooldown.getMaxCooldown()
        );
    }

    @Override
    public String getFullDescription() {
        String effectsDesc = "";
        for(int i = 0; i < effects.length; i++){
            effectsDesc += effects[i].getDescription() + (i != effects.length-1 ? ", " : "");
        }
        if(effectsDesc != ""){
            effectsDesc = "Effects: " + effectsDesc;
        }
        return String.format("%s - Damage: %d, Price: %d, Cooldown: %d Description: %s %s\n", name, damage, price, cooldown.getMaxCooldown(), description, effectsDesc);
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getLevelRequirement() {
        return levelReq;
    }
}
