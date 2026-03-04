package domiksad.heroTournament.domain.mechanics;

public class Health {
    private int current;
    private int max;

    public Health(int maxHealth) {
        current = maxHealth;
        max = maxHealth;
    }

    public Health(int currentHealth, int maxHealth) {
        current = currentHealth;
        max = maxHealth;
    }

    public int getMax() {
        return max;
    }

    public Health setMax(int max) {
        this.max = max;
        return this;
    }

    public int getCurrent() {
        return current;
    }

    public Health setCurrent(int current) {
        this.current = current;
        return this;
    }

    public Health heal(int amount) {
        current = Math.min(current + amount, max);
        return this;
    }

    public Health hurt(int amount) {
        current -= amount;
        return this;
    }

    public boolean isDead(){
        return current <= 0;
    }
}
