package stima.core.status.volatiles;

import stima.core.moves.Move;
import stima.core.moves.MoveCategory;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.EndsAfterPokemonTurn;
import stima.core.properties.RestrictsMoveChoice;

public class Taunted extends VolatileStatus implements EndsAfterPokemonTurn, RestrictsMoveChoice {
    @Override
    public int endsAfter() {
        return 4;
    }

    @Override
    public boolean canUseMove(Move move, PokemonBattleState pokemon) {
        return move.getCategory() != MoveCategory.STATUS;
    }
}
