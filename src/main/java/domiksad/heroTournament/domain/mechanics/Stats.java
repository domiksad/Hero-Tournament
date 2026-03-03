package domiksad.heroTournament.domain.mechanics;

public class Stats {
    private int damage;
    private int armor;

    public Stats(int damage, int armor) {
        this.damage = damage;
        this.armor = armor;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }
}
