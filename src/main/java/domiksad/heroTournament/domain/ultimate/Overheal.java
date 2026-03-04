package domiksad.heroTournament.domain.ultimate;

import domiksad.heroTournament.domain.character.AbstractCharacter;
import domiksad.heroTournament.domain.mechanics.Cooldown;

public class Overheal implements Ultimate {
    private String name = "Overheal";
    private Cooldown cooldown = new Cooldown(4, 2);
    private String description = "Restores X Health. If you are already at maximum Health, it increases your Health above the maximum by X.";
    private int heal = 40;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        String status = cooldown.isReady()
                ? "READY NOW"
                : String.format("ready in %d turns", cooldown.getRemainingCooldown());

        return String.format("%s (%d heal) - %s (cooldown: %d turns)", name, heal, status, cooldown.getMaxCooldown());
    }

    @Override
    public String getFullDescription() {
        return String.format("%s - Heals: %d, Cooldown: %d turns Description: %s", name, heal, cooldown.getMaxCooldown(), description);
    }

    @Override
    public void use(AbstractCharacter user, AbstractCharacter target) {
        user.getHealth().setCurrent(user.getHealth().getMax() + heal);
        cooldown.trigger();
    }

    @Override
    public Cooldown getCooldown() {
        return cooldown;
    }
}

