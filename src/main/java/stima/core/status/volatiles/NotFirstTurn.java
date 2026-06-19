package stima.core.status.volatiles;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.RestrictsMoveChoice;

public class NotFirstTurn extends VolatileStatus implements RestrictsMoveChoice {
    @Override
    public boolean canUseMove(Move move, PokemonBattleState pokemon) {
        return move != Move.FAKE_OUT;
    }
}
