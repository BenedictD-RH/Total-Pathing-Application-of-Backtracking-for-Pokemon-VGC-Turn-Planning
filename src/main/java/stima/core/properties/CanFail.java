package stima.core.properties;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;

public interface CanFail {
    boolean moveSucceeds(PokemonBattleState user, PokemonBattleState opponent, Move move);
}
