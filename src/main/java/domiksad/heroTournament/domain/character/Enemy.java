package domiksad.heroTournament.domain.character;

import domiksad.heroTournament.domain.items.Weapon;
import domiksad.heroTournament.domain.mechanics.Health;
import domiksad.heroTournament.domain.mechanics.Level;
import domiksad.heroTournament.domain.mechanics.Stats;
import domiksad.heroTournament.domain.ultimate.Ultimate;

public class Enemy extends AbstractCharacter{
    public final int exp;
    public Enemy(String name, Health health, Level level, Stats stats, Weapon weapon, Ultimate ultimate, int gold, int exp) {
        super(name, health, level, stats, weapon, ultimate, gold);
        this.exp = exp;
    }
}
