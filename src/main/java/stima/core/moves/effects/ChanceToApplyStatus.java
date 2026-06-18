package stima.core.moves.effects;

import stima.core.battle.RNGSeed;
import stima.core.pokemon.PokemonBattleState;
import stima.core.status.Status;

public class ChanceToApplyStatus extends ApplyStatusToOpponent {
    private float chance;

    public ChanceToApplyStatus(Status status, float chance) {
        super(status);
        this.chance = chance;
    }

    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities) {
        if (probabilities.consume()) {
            target.addStatus(appliedStatus);
        }
    }

    @Override
    public int rngDependentEvents() {
        return 1;
    }
}
