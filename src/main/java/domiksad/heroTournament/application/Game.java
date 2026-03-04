package domiksad.heroTournament.application;

import domiksad.heroTournament.domain.character.EnemyFactory;
import domiksad.heroTournament.domain.character.Player;
import domiksad.heroTournament.domain.items.Weapon;
import domiksad.heroTournament.domain.items.WeaponFactory;
import domiksad.heroTournament.domain.mechanics.Fight;
import domiksad.heroTournament.domain.mechanics.Health;
import domiksad.heroTournament.domain.mechanics.Level;
import domiksad.heroTournament.domain.mechanics.Stats;
import domiksad.heroTournament.domain.ultimate.Overheal;
import domiksad.heroTournament.domain.ultimate.SuperAttack;
import domiksad.heroTournament.domain.ultimate.Ultimate;
import domiksad.heroTournament.domain.ultimate.UltimateFactory;
import domiksad.heroTournament.infrastructure.db.Database;

import java.io.IOException;

import static java.lang.System.exit;

public class Game {
    public static String readLine() {
        StringBuilder sb = new StringBuilder();
        int c;

        try {
            while ((c = System.in.read()) != -1) {
                if (c == '\n') break;
                if (c != '\r') {
                    sb.append((char) c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
    public static char getChar(){
        int c;
        try {
            do {
                c = System.in.read();
            } while (c == '\n' || c == '\r'); // pomijamy enter

            return (char)c;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    Player player;
    Weapon sword = WeaponFactory.createSword();
    Weapon spear = WeaponFactory.createSpear();
    SuperAttack superAttack = UltimateFactory.createSupperAttack();
    Overheal overheal = UltimateFactory.createOverheal();

    int latestSaveId;

    public void lose(){
        System.out.println("You lost loser");
        exit(1);
    }

    public void start(){
        // Setup database
        Database.init();
        latestSaveId = Database.getLatestSaveId();

        System.out.println("What is your name?");
        String name = readLine();

        System.out.println("Hello " + name + ". Pick your weapon:");
        System.out.println("1. " + sword.getFullDescription());
        System.out.println("2. " + spear.getFullDescription());
        Weapon weapon = null;

        do {
            switch (getChar()) {
                case '1' -> weapon = WeaponFactory.createSword();
                case '2' -> weapon = WeaponFactory.createSpear();
            }
        } while (weapon == null);

        System.out.println("Pick your ultimate:");
        System.out.println("1. " + superAttack.getFullDescription());
        System.out.println("2. " + overheal.getFullDescription());
        Ultimate ultimate = null;

        do {
            switch (getChar()) {
                case '1' -> ultimate = UltimateFactory.createSupperAttack();
                case '2' -> ultimate = UltimateFactory.createOverheal();
            }
        } while (ultimate == null);


        player = new Player(name,
                new Health(100),
                new Level(),
                new Stats(1, 1),
                weapon,
                ultimate,
                100);

        gameloop();
    }

    public void gameloop(){
        char input; int day = 1;
        do {
            System.out.println("Day " + day);
            System.out.println(player.getFullDescription());
            System.out.println("1. Fight");
            System.out.println("2. Go to tavern");
            System.out.println("8. Quicksave");
            System.out.println("9. Quickload");

            input = getChar();
            switch (input){
                case '1':
                    Fight.Result result = Fight.fight(player, EnemyFactory.getRandomEnemy());
                    if(result == Fight.Result.WIN){
                        System.out.println("U won. Congrats");
                    } else {
                        lose();
                    }
                    break;

                case '2':
                    do {
                        System.out.println("1. Have a drink (heals 20 hp) - 5 gold");
                        System.out.println("2. Leave");
                        input = getChar();
                        switch (input){
                            case '1':
                                if(player.getGold() >= 5){
                                    player.getHealth().heal(20);
                                    System.out.format("Youve got healed. Current life: %d / %d\n", player.getHealth().getCurrent(), player.getHealth().getMax());
                                } else {
                                    System.out.println("Youre too broke loser");
                                }
                        }
                    } while(input != '2');
                    break;

                case '8':
                    latestSaveId = Database.save(player);
                    System.out.println("Saved");
                    break;

                case '9':
                    player = Database.load(latestSaveId);
                    System.out.println("Loaded");
                    break;
            }
        } while(input != 'q');
    }
}
