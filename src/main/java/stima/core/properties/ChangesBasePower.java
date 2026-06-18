package stima.core.properties;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;

public interface ChangesBasePower {
    int changePowerTo(PokemonBattleState user, PokemonBattleState opponent, Move move);
}
