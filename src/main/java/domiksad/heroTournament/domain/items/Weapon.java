package domiksad.heroTournament.domain.items;

import domiksad.heroTournament.domain.character.AbstractCharacter;
import domiksad.heroTournament.domain.effects.Effect;
import domiksad.heroTournament.domain.mechanics.Cooldown;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Weapon implements Item{
    private String name;
    private int price;
    private int levelReq = 1;
    private String description;
    private int damage;
    private Cooldown cooldown;
    private ArrayList<Effect> effects = new ArrayList<>();

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

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public Weapon setEffects(ArrayList<Effect> effects) {
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
        for(int i = 0; i < effects.size(); i++){
            effectsDesc += effects.get(i).getDescription() + (i != effects.size()-1 ? ", " : "");
        }
        if(effectsDesc != ""){
            effectsDesc = "Effects: " + effectsDesc;
        }
        return String.format("%s - Damage: %d, Price: %d, Cooldown: %d Description: %s %s", name, damage, price, cooldown.getMaxCooldown(), description, effectsDesc);
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
