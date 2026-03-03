package org.example;

import org.example.entities.Enemy;
import org.example.entities.Player;

import static org.example.Game.goblin;

public class FightManager {
    public enum Result {
        WIN, DRAW, LOSE;
    }

    public static Enemy getEnemy(Player player){
        return goblin;
    }

    public static Result startBattle(Player player, Enemy enemy) throws Exception {
        do {
            System.out.println();
            System.out.format("%s HP: %d/%d LVL: %d\tVS\t%s HP: %d/%d LVL: %d\n",
                    player.getName(), player.getHealth(), player.getMaxHealth(), player.getLevel(),
                    enemy.getName(), enemy.getHealth(), enemy.getMaxHealth(), enemy.getLevel());
            System.out.format("1. Attack with %s\n", player.getWeapon().displayWeapon());
            System.out.format("2. Use ultimate %s\n", player.getUlt().getDesc());
            System.out.println("3. Bleed out (u die)");
            System.out.println();
            char input = (char)System.in.read();
            System.in.read(); // handle IDK what

            switch (input){
                case '1':
                    break;
                case '3':
                    player.takeDamage(100000);
            }
        } while(player.getHealth() > 0 && enemy.getHealth() > 0);
        if(player.getHealth() <= 0){
            System.out.println("Ure ded");
            return Result.LOSE;
        } else if(enemy.getHealth() <= 0){
            System.out.println("Enemy is ded");
            return Result.WIN;
        } else {
            throw new Exception("How did u manage to get here");
        }
    }
}
