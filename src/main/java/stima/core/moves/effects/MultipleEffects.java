package stima.core.moves.effects;

import java.util.ArrayList;
import java.util.List;

import stima.core.battle.RNGSeed;
import stima.core.pokemon.PokemonBattleState;

public class MultipleEffects extends MoveEffect {
    private final List<MoveEffect> effects;

    public MultipleEffects(MoveEffect... effects) {
        this.effects = new ArrayList<>(List.of(effects));
    }

    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities) {
        for (MoveEffect me : effects) {
            me.applyEffect(user, target, damageDone, probabilities);
        }
    }

    @Override
    public int rngDependentEvents() {
        return effects.stream().map(MoveEffect::rngDependentEvents).reduce((e1, e2) -> e1 + e2).orElse(0);
    }
}
