package domiksad.heroTournament.presentation;

import domiksad.heroTournament.domain.character.Player;
import domiksad.heroTournament.domain.items.Weapon;
import domiksad.heroTournament.domain.items.WeaponFactory;
import domiksad.heroTournament.domain.mechanics.Health;
import domiksad.heroTournament.domain.mechanics.Level;
import domiksad.heroTournament.domain.mechanics.Stats;
import domiksad.heroTournament.domain.ultimate.SuperAttack;

import java.io.IOException;

public class Game {
    public String readLine() {
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

    public char getChar(){
        try {
            return (char)System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    Player player;
    Weapon sword = WeaponFactory.createSword();
    Weapon spear = WeaponFactory.createSpear();

    public void start(){
        System.out.println("What is your name?");
        String name = readLine();

        System.out.println("Hello " + name + ". Pick your weapon:\n1. " + sword.getFullDescription() + "\n2. " + spear.getFullDescription());
        Weapon weapon = null;

        do {
            switch (getChar()) {
                case '1' -> weapon = WeaponFactory.createSword();
                case '2' -> weapon = WeaponFactory.createSpear();
            }
        } while (weapon == null);

        // ROBZBIC I USUNAC CHAINY
        player = new Player()
                .setName(name)
                .setHealth(new Health(100))
                .setLevel(new Level())
                .setStats(new Stats(1, 1))
                .setUltimate(new SuperAttack())
                .setWeapon(weapon);
    }
}
