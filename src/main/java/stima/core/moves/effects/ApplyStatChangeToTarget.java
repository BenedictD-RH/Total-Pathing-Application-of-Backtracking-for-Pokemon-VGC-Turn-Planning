package stima.core.moves.effects;

import stima.core.battle.RNGSeed;
import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;

public class ApplyStatChangeToTarget extends MoveEffect {
    private final Stat stat;
    private final int amount;

    public ApplyStatChangeToTarget(Stat stat, int amount) {
        this.stat = stat;
        this.amount = amount;
    }

    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities) {
        user.addStatChange(stat, amount);
    }
}
