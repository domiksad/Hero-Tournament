package domiksad.heroTournament.domain.ultimate;

import domiksad.heroTournament.domain.character.AbstractCharacter;
import domiksad.heroTournament.domain.mechanics.Cooldown;

public class SuperAttack implements Ultimate {
    private String name = "Super attack";
    private Cooldown cooldown = new Cooldown(5, 2);
    private String description = "Use your superiority to deal huge amount of damage. Scales with level";
    private int baseDamage = 50;


    @Override
    public String getDescription() {
        return "%s";
    }

    @Override
    public void use(AbstractCharacter user, AbstractCharacter target) {
        int damage = user.calculateDamage(baseDamage) / target.getStats().getArmor();
        target.getHealth().damage(damage);
    }

    @Override
    public Cooldown getCooldown() {
        return cooldown;
    }
}
