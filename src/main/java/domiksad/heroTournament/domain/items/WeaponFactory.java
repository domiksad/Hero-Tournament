package domiksad.heroTournament.domain.items;

import domiksad.heroTournament.domain.mechanics.Cooldown;

public class WeaponFactory {
    public static Weapon[] getWeaponList(){
            return new Weapon[]{
                createKnife(),
                createSpear(),
                createSword()
            };
    };

    public static Weapon createKnife() {
        Weapon sword = new Weapon()
                .setName("Knife")
                .setPrice(2)
                .setLevelReq(1)
                .setDescription("Poor knife")
                .setDamage(4)
                .setCooldown(new Cooldown(0));
        return sword;
    }

    public static Weapon createSword() {
        Weapon sword = new Weapon()
                .setName("Sword")
                .setPrice(10)
                .setLevelReq(1)
                .setDescription("Basic sword for begginers")
                .setDamage(12)
                .setCooldown(new Cooldown(0));
        return sword;
    }

    public static Weapon createSpear() {
        Weapon spear = new Weapon()
                .setName("Spear")
                .setPrice(10)
                .setLevelReq(1)
                .setDescription("Long spear. Deals higher damage then basic sword but has higher cooldown")
                .setDamage(26)
                .setCooldown(new Cooldown(1));
        return spear;
    }
}
