package domiksad.heroTournament.application;

import domiksad.heroTournament.domain.character.Enemy;
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

import static domiksad.heroTournament.infrastructure.db.Database.*;
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
        deleteByName(player.getName());
        exit(1);
    }

    public void chooseSaves(){
        System.out.println("Create new character or load from save: ");
        System.out.println(listSaves());

        System.out.println("\nOption (c - create new): ");
        String input;
        input = readLine();
        int num;
        if(input.equals("c")){
            start();
        } else {
            num = Integer.parseInt(input);
            player = load(num);
            if(player == null){
                System.out.println("No save with number " + num);
                chooseSaves();
            } else {
                gameloop();
            }
        }
    }

    public void start(){
        // Setup database
        Database.init();

        String name = "";
        do{
            System.out.println("What is your name?");
            name = readLine();
            if(name.equals("") || isInDatabase(name)){
                System.out.println("Name already in save list. Choose different name");
            } else {
                break;
            }
        } while(true);


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
                    Enemy enemy = EnemyFactory.getRandomEnemy(player.getLevel().getLevel()+(int)Math.round(Math.random()*2-1));
                    Fight.Result result = Fight.fight(player, enemy);
                    if(result == Fight.Result.WIN){
                        System.out.println("U won. Congrats");
                        player.getLevel().addExperience(enemy.exp);
                        player.setGold(player.getGold() + enemy.getGold());

                        while(player.getLevel().hasLeveledUp()){
                            System.out.println("U leveled up. Congrats. Your base stats are increased");
                            player.levelUp();
                        }
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
                                    player.setGold(player.getGold()-5);
                                } else {
                                    System.out.println("Youre too broke loser");
                                }
                                break;

                            case '2':
                                break;

                            default:
                                System.out.println("There is no such option");
                                break;
                        }
                    } while(input != '2');
                    break;

                case '8':
                    latestSaveId = Database.save(player);
                    System.out.println("Saved");
                    break;

                case '9':
                    player = load(latestSaveId);
                    System.out.println("Loaded");
                    break;

                default:
                    System.out.println("There is no such option");
                    break;
            }
        } while(input != 'q');
    }
}
