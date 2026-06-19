package stima.core.moves.effects;

import java.util.EnumMap;

import stima.core.battle.RNGSeed;
import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;

public class ApplyStatChangeToTarget extends MoveEffect {
    private final EnumMap<Stat, Integer> statChange;

    public ApplyStatChangeToTarget(EnumMap<Stat, Integer> statChange) {
        this.statChange = statChange;
    }

    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities) {
        user.addStatChange(statChange);
    }
}
