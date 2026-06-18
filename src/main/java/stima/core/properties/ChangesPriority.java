package stima.core.properties;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;

public interface ChangesPriority {
    int ChangesPriorityTo(PokemonBattleState user, PokemonBattleState opponent, Move move);
}
