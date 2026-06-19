package stima.core.properties;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;

public interface RestrictsMoveChoice {
    boolean canUseMove(Move move, PokemonBattleState pokemon);
}
