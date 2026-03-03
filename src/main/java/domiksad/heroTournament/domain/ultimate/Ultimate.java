package domiksad.heroTournament.domain.ultimate;

import domiksad.heroTournament.domain.character.AbstractCharacter;
import domiksad.heroTournament.domain.mechanics.Cooldown;

public interface Ultimate {
    String getDescription();
    void use(AbstractCharacter user, AbstractCharacter target);
    Cooldown getCooldown();
}
