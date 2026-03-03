package domiksad.heroTournament.domain.character;

import domiksad.heroTournament.domain.effects.Effect;
import domiksad.heroTournament.domain.items.Weapon;
import domiksad.heroTournament.domain.mechanics.Health;
import domiksad.heroTournament.domain.mechanics.Level;
import domiksad.heroTournament.domain.mechanics.Stats;
import domiksad.heroTournament.domain.ultimate.Ultimate;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public abstract class AbstractCharacter {
    protected String name;
    protected Health health;
    protected Level level;
    protected Stats stats;
    protected Weapon weapon;
    protected Ultimate ultimate;
    protected ArrayList<Effect> effects;

    public String getName() {
        return name;
    }

    public AbstractCharacter setName(String name) {
        this.name = name;
        return this;
    }

    public Health getHealth() {
        return health;
    }

    public AbstractCharacter setHealth(Health health) {
        this.health = health;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public AbstractCharacter setLevel(Level level) {
        this.level = level;
        return this;
    }

    public Stats getStats() {
        return stats;
    }

    public AbstractCharacter setStats(Stats stats) {
        this.stats = stats;
        return this;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public AbstractCharacter setWeapon(Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

    public Ultimate getUltimate() {
        return ultimate;
    }

    public AbstractCharacter setUltimate(Ultimate ultimate) {
        this.ultimate = ultimate;
        return this;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public int calculateDamage(){
        return calculateDamage(0);
    }
    public int calculateDamage(int damage){
        damage += stats.getDamage() + weapon.getDamage();
        for(var effect : effects){
            damage = effect.getModifiedDamage(damage);
        }
        return damage;
    }
}
