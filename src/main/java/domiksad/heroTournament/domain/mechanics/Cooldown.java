package domiksad.heroTournament.domain.mechanics;

public class Cooldown {
    int initialValue;
    int current; // Always set current to initialValue before fight
    int max;

    public Cooldown(int maxCooldown){
        this(maxCooldown, 0);
    }
    public Cooldown(int maxCooldown, int initialCooldown){
        initialValue = initialCooldown;
        max = maxCooldown;
        current = initialValue;
    }

    public boolean isReady(){
        return current == 0;
    }

    public void trigger(){
        current = max;
    }

    public void reset(){
        current = max;
    }

    public int getRemainingCooldown(){
        return current;
    }

    public int getMaxCooldown(){
        return max;
    }
}
