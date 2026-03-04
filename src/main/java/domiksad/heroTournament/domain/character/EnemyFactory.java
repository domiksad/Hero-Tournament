package domiksad.heroTournament.domain.character;

import domiksad.heroTournament.domain.items.WeaponFactory;
import domiksad.heroTournament.domain.mechanics.Health;
import domiksad.heroTournament.domain.mechanics.Level;
import domiksad.heroTournament.domain.mechanics.Stats;
import domiksad.heroTournament.domain.ultimate.UltimateFactory;

import java.util.Random;

public class EnemyFactory {
    public static Enemy[] getEnemyList(int level){
        return new Enemy[]{
                createGoblin(level),
                createWitch(level),
                createKnight(level)
        };
    }

    public static Enemy createGoblin(int level){
        return new Enemy(
                "Goblin",
                new Health(50+5*(level-1)),
                new Level(level),
                new Stats(1 + 1*(level-1), 1+1*(level-1)),
                WeaponFactory.createKnife(),
                UltimateFactory.createSupperAttack(),
                10,
                50*level
        );
    }

    public static Enemy createKnight(int level){
        return new Enemy(
                "Knight",
                new Health(100+10*(level-1)),
                new Level(level),
                new Stats(5 + 1*(level-1), 2+1*(level-1)),
                WeaponFactory.createSpear(),
                UltimateFactory.createSupperAttack(),
                100,
                100*level
        );
    }

    public static Enemy createWitch(int level){
        return new Enemy(
                "Witch",
                new Health(200+10*(level-1)),
                new Level(level),
                new Stats(10 + 1*(level-1), 2+1*(level-1)),
                WeaponFactory.createSpear(),
                UltimateFactory.createOverheal(),
                5,
                200*level
        );
    }


    public static Enemy getRandomEnemy(int level){
        Enemy[] enemies = getEnemyList(level);
        Random random = new Random();
        int index = random.nextInt(enemies.length);
        return enemies[index];
    }
}
