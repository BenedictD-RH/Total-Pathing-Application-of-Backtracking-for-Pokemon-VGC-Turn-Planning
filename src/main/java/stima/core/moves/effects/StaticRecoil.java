package stima.core.moves.effects;

import stima.core.battle.RNGSeed;
import stima.core.pokemon.PokemonBattleState;

public class StaticRecoil extends DealsRecoil {
    public StaticRecoil(float recoilPercentage) {
        super(recoilPercentage);
    }

    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities) {
        user.applyDamage((int)Math.floor(user.getMaxHealth()*recoilPercentage));
    }
}
