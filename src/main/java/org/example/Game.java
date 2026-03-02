package org.example;

import org.example.entities.Player;
import org.example.items.Weapon;

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

    static Player player = new Player();

    static final Weapon sword = new Weapon("Sword", 2, 1, 1);
    static final Weapon spear = new Weapon("Spear", 3, 2, 1);
    static final Weapon axe = new Weapon("Spear", 4, 3, 2);

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
    }

    public static void gameLoop() throws Exception {

    }
}
