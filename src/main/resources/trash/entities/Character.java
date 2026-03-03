package domiksad.heroTournament.entities;

public interface Character<T extends Character<T> > {
    // String name
    String getName();
    T setName(String name);

    // int health
    int getHealth();
    T setHealth(int health);
    void heal(int amount);
    void takeDamage(int amount);

    // int maxHealth
    int getMaxHealth();
    T setMaxHealth(int maxHealth);

    int MAX_LEVEL = 100;
    int EXP_PER_LEVEL = 1000;

    // int level
    int getLevel();
    T setLevel(int level);
    void levelUp();

    // int experience
    int getExperience();
    T setExperience(int experience);
    void addExperience(int amount);

    default int getMaxExperience(){
        return getLevel() * EXP_PER_LEVEL;
    }
}
