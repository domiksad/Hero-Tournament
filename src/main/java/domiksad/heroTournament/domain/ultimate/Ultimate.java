package domiksad.heroTournament.domain.ultimate;

import domiksad.heroTournament.domain.character.AbstractCharacter;
import domiksad.heroTournament.domain.mechanics.Cooldown;

public interface Ultimate {
    String getName();
    String getDescription();
    String getFullDescription();
    void use(AbstractCharacter user, AbstractCharacter target);
    Cooldown getCooldown();
}
