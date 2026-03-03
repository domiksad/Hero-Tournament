package domiksad.heroTournament.ultimates;

import domiksad.heroTournament.entities.Enemy;
import domiksad.heroTournament.entities.Player;

public class Overheal extends Ult<Overheal>{
    {
        desc = "Overheals user. Ignores max HP";
    }
    @Override
    public boolean use(Player player, Enemy enemy){
        if(cooldownTimer == 0){
            cooldownTimer = cooldown;
            player.setHealth(player.getHealth()+heal);
            return true;
        } else {
            return false;
        }
    }

}
