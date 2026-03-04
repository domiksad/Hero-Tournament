package domiksad.heroTournament.domain.character;

import domiksad.heroTournament.domain.items.WeaponFactory;
import domiksad.heroTournament.domain.mechanics.Health;
import domiksad.heroTournament.domain.mechanics.Level;
import domiksad.heroTournament.domain.mechanics.Stats;
import domiksad.heroTournament.domain.ultimate.UltimateFactory;

public class EnemyFactory {
    public static Enemy createGoblin(int level){
        return new Enemy(
                "Goblin",
                new Health(50),
                new Level(level),
                new Stats(1, 1),
                WeaponFactory.createKnife(),
                UltimateFactory.createSupperAttack(),
                10
        );
    }


    public static Enemy getRandomEnemy(){
        return createGoblin(2);
    }
}
