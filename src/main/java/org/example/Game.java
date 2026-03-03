package org.example;

import org.example.entities.Enemy;
import org.example.entities.Player;
import org.example.items.Weapon;
import org.example.ultimates.Overheal;
import org.example.ultimates.SuperAttack;

import static org.example.FightManager.getEnemy;

public class Game {
    public static String readLine() throws Exception {
        StringBuilder sb = new StringBuilder();
        int c;

        while ((c = System.in.read()) != '\n') {
            if (c != '\r') {
                sb.append((char) c);
            }
        }

        return sb.toString();
    }

    static Player player = new Player().setHealth(10).setMaxHealth(10).setLevel(1).setExperience(0).setGold(10);

    static final Weapon sword = new Weapon("Sword", 2, 1, 1);
    static final Weapon spear = new Weapon("Spear", 5, 2, 1);
    static final Weapon axe = new Weapon("Spear", 8, 3, 2);

    static final Enemy goblin = new Enemy().setName("Goblin").setHealth(10).setMaxHealth(10).setLevel(1).setWeapon(sword);

    static final Overheal overheal = new Overheal();
    static final SuperAttack superAttack = new SuperAttack();

    public static void start() throws Exception {
        System.out.println("What is your name?");
        player.setName(readLine());
        System.out.println("Hello " + player.getName() + ". Pick your weapon:\n1. " + sword.displayWeapon() + "\n2. " + spear.displayWeapon());
        do {
            switch (System.in.read()){
                case '1':
                    player.setWeapon(sword);
                    break;
                case '2':
                    player.setWeapon(spear);
                    break;
            }
        } while(player.getWeapon() == null);

        System.out.format("Pick your ultimate: \n1. %s\n2. %s\n", overheal.getDesc(), superAttack.getDesc());
        do {
            switch (System.in.read()){
                case '1':
                    player.(sword);
                    break;
                case '2':
                    player.setWeapon(spear);
                    break;
            }
        } while(player.getWeapon() == null);

        System.in.read(); // handle IDK what
        gameLoop();
    }

    public static void lose() throws Exception {
        System.out.println("U lost loser");
        System.exit(0);
    }

    public static void gameLoop() throws Exception {
        char input;
        int day = 1;
        do {
            System.out.format("\nDay %d\n", day);
            System.out.println("1. Fight in arena");
            System.out.println("2. Tavern");
            System.out.println("\nOption (q-quit):");
            input = (char)System.in.read();
            System.in.read(); // handle IDK what

            switch (input) {
                case '1':
                    Enemy enemy = getEnemy(player);
                    FightManager.Result res = FightManager.startBattle(player, enemy);
                    if(res == FightManager.Result.WIN){
                        System.out.println("Yippy");
                    } else {
                        lose();
                    }
                    break;
                case '2':
                    System.out.println("\nYoure in tavern");
                    System.out.println("1. Sleep (heal 5HP) - 2 gold");
                    System.out.println("2. Leave");
                    input = (char)System.in.read();
                    System.in.read(); // handle IDK what
                    switch (input) {
                        case '1':
                            if (player.getGold() >= 2){
                                player.takeGold(2);
                                player.heal(5);
                                System.out.format("Healed for 5 hp. Current HP: %d/%d\n", player.getHealth(), player.getMaxHealth());
                            } else {
                                System.out.println("Youre too broke loser");
                            }
                            break;
                    }
            }
        } while (input != 'q');
    }
}