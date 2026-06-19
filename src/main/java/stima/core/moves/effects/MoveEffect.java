package stima.core.moves.effects;

import java.util.ArrayList;
import java.util.List;

import stima.core.battle.RNGSeed;
import stima.core.pokemon.PokemonBattleState;

public abstract class MoveEffect {
    
    abstract public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities);

    public int rngDependentEvents() {
        return 0;
    }

    public List<String> rngDependentEventsLog() {
        return new ArrayList<>();
    }
    
}
