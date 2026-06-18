package stima.core.moves.effects;

import stima.core.battle.RNGSeed;
import stima.core.pokemon.PokemonBattleState;

public class DealsRecoil extends MoveEffect {
    private final float recoilPercentage;

    public DealsRecoil(float recoilPercentage) {
        this.recoilPercentage = recoilPercentage;
    }

    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities) {
        user.applyDamage((int)Math.floor(damageDone*recoilPercentage));
    }
}
