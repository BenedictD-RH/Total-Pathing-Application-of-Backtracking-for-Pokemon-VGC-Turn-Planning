package stima.core.properties;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;

public interface GivesImmunity {
    boolean isImmune(Move move, PokemonBattleState pokemon);
}
