package stima.core.properties;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Type;

public interface ChangesDamagingType {
    Type changeTypeTo(PokemonBattleState user, PokemonBattleState opponent, Move move);
}
