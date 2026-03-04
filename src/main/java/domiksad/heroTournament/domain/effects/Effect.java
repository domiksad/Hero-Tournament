package domiksad.heroTournament.domain.effects;

public interface Effect {
    String getDescription();
    String getFullDescription();

    int getModifiedDamage(int damage);
}
