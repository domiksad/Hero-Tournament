package domiksad.heroTournament.domain.items;

import domiksad.heroTournament.domain.mechanics.Cooldown;

public class WeaponFactory {
    public static Weapon createSword() {
        Weapon sword = new Weapon()
                .setName("Sword")
                .setPrice(10)
                .setLevelReq(1)
                .setDescription("Basic sword for begginers")
                .setDamage(6)
                .setCooldown(new Cooldown(0));
        return sword;
    }

    public static Weapon createSpear() {
        Weapon spear = new Weapon()
                .setName("Spear")
                .setPrice(10)
                .setLevelReq(1)
                .setDescription("Long spear. Deals higher damage then basic sword but has higher cooldown")
                .setDamage(13)
                .setCooldown(new Cooldown(1));
        return spear;
    }
}
