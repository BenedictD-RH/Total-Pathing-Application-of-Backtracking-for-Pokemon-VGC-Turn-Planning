package stima.core.properties;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;

public interface ChangesAccuracy {
    int changeAccuracyTo(PokemonBattleState user, PokemonBattleState opponent, Move move);
}
