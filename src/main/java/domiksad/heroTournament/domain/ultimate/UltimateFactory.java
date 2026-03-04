package domiksad.heroTournament.domain.ultimate;

public class UltimateFactory {
    public static Ultimate[] getUltimateList(){
        return new Ultimate[]{
            createOverheal(),
            createSupperAttack()
        };
    };

    public static SuperAttack createSupperAttack(){
        return new SuperAttack();
    }

    public static Overheal createOverheal(){
        return new Overheal();
    }
}
