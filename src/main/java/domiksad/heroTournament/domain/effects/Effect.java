package domiksad.heroTournament.domain.effects;

public interface Effect {
    String getDescription();

    int getModifiedDamage(int damage);
}
