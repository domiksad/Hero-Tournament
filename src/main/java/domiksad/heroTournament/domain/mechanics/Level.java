package domiksad.heroTournament.domain.mechanics;

import static domiksad.heroTournament.domain.config.GameBalance.BASE_EXP_PER_LEVEL;
import static domiksad.heroTournament.domain.config.GameBalance.MAX_LEVEL;

public class Level {
    private int experience;
    private int level;

    public Level(){
        experience = 0;
        level = 1;
    }
    public Level(int level){
        experience = 0;
        this.level = level;
    }
    public Level(int level, int experience){
        this.experience = experience;
        this.level = level;
    }

    public int getExperienceTreshhold(){
        return level * BASE_EXP_PER_LEVEL;
    }

    public Level addExperience(int amount){
        if(level >= MAX_LEVEL) return this;

        experience += amount;
        return this;
    }

    public boolean hasLeveledUp(){
        int treshhold = getExperienceTreshhold();
        if(experience >= treshhold && !isMaxLevel()){
            experience -= treshhold;
            return true;
        }
        return false;
    }

    public boolean isMaxLevel(){
        if(level == MAX_LEVEL){
            return true;
        }
        return false;
    }
}
