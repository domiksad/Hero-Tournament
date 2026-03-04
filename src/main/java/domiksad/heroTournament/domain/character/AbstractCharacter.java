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
    protected int gold;
    protected ArrayList<Effect> effects = new ArrayList<>();

    public AbstractCharacter(String name, Health health, Level level, Stats stats, Weapon weapon, Ultimate ultimate, int gold) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.stats = stats;
        this.weapon = weapon;
        this.ultimate = ultimate;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Ultimate getUltimate() {
        return ultimate;
    }

    public void setUltimate(Ultimate ultimate) {
        this.ultimate = ultimate;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
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

    public String getFullDescription(){
        return String.format("%s hp: %d/%d, lvl: %d (%d/%d), stats: %d damage; %d armor, utltimate: %s, gold: %d",
                name,
                health.getCurrent(),
                health.getMax(),
                level.getLevel(),
                level.getExperience(),
                level.getExperienceTreshhold(),
                stats.getDamage(),
                stats.getArmor(),
                ultimate.getFullDescription(),
                gold);
    }
}
