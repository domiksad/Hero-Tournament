package domiksad.heroTournament.domain.character;

import domiksad.heroTournament.domain.items.Weapon;
import domiksad.heroTournament.domain.mechanics.Health;
import domiksad.heroTournament.domain.mechanics.Level;
import domiksad.heroTournament.domain.mechanics.Stats;
import domiksad.heroTournament.domain.ultimate.Ultimate;

public class Player extends AbstractCharacter {
    public Player(String name, Health health, Level level, Stats stats, Weapon weapon, Ultimate ultimate, int gold) {
        super(name, health, level, stats, weapon, ultimate, gold);
    }

    public void levelUp(){
        stats = new Stats(stats.getDamage() + 2, stats.getArmor() + 1);
        health = new Health(health.getCurrent() + 10, health.getMax() + 10);
    }
}
