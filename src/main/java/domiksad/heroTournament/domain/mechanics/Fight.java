package domiksad.heroTournament.domain.mechanics;

import domiksad.heroTournament.domain.character.Enemy;
import domiksad.heroTournament.domain.character.Player;

import static domiksad.heroTournament.application.Game.getChar;

public class Fight {
    public enum Result{
        WIN, LOSE;
    }

    public static Result fight(Player player, Enemy enemy){
        do{
            System.out.println(player.getFullDescription() + "\n vs\n" + enemy.getFullDescription());
            boolean choseValidAction = false;

            do {
                System.out.println("What do you want to do: ");
                System.out.println("1. Use weapon (" + player.getWeapon().getDescription() + ")");
                System.out.println("2. Use ult (" + player.getUltimate().getDescription() + ")");
                System.out.println("3. Wait");
                System.out.println("4. Bleed out (u die)");
                char input = getChar();
                switch (input) {
                    case '1':
                        if (player.getWeapon().getCooldown().isReady()) {
                            int damage = player.calculateDamage();
                            enemy.getHealth().hurt(damage);
                            player.getWeapon().getCooldown().trigger();
                            choseValidAction = true;
                            System.out.format("You dealt %d damage\n", damage);

                            player.getUltimate().getCooldown().decrement();
                        } else{
                            System.out.format("You cant use your %s now\n", player.getWeapon().getName());
                        }
                        break;

                    case '2':
                        if (player.getUltimate().getCooldown().isReady()) {
                            player.getUltimate().use(player, enemy);
                            choseValidAction = true;
                            System.out.format("You used your ultimate\n");

                            player.getWeapon().getCooldown().decrement();
                        } else{
                            System.out.format("You cant use your %s now\n", player.getUltimate().getName());
                        }
                        break;

                    case '3':
                        player.getUltimate().getCooldown().decrement();
                        player.getWeapon().getCooldown().decrement();

                        System.out.println("You wait");
                        choseValidAction = true;
                        break;

                    case '4':
                        player.getHealth().hurt(1000000);
                        choseValidAction = true;
                        break;
                }
            } while(!choseValidAction);

            if(enemy.getUltimate().getCooldown().isReady()){
                enemy.getUltimate().use(enemy, player);
                System.out.format("Enemy used his ultimate\n");

                enemy.getWeapon().getCooldown().decrement();
            } else if(enemy.getWeapon().getCooldown().isReady()){
                int damage = enemy.calculateDamage();
                player.getHealth().hurt(damage);
                enemy.getWeapon().getCooldown().trigger();
                System.out.format("Enemy dealt %d damage\n", damage);

                enemy.getUltimate().getCooldown().decrement();
            } else {
                System.out.println("Enemy waits");
                enemy.getUltimate().getCooldown().decrement();
                enemy.getWeapon().getCooldown().decrement();
            }

        } while(!player.getHealth().isDead() && !enemy.getHealth().isDead());

        if(player.getHealth().isDead()){
            return Result.LOSE;
        } else {
            return Result.WIN;
        }
    }
}
