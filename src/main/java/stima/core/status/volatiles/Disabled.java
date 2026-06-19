package stima.core.status.volatiles;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.EndsAtEndOfTurn;
import stima.core.properties.RestrictsMoveChoice;

public class Disabled extends VolatileStatus implements EndsAtEndOfTurn, RestrictsMoveChoice {
    private Move disabledMove;

    public Disabled(Move move) {
        disabledMove = move;
    }

    public Disabled(Disabled other) {
        this.disabledMove = other.disabledMove;
    }

    @Override
    public int endsAfter() {
        return 4;
    }

    @Override
    public boolean canUseMove(Move move, PokemonBattleState pokemon) {
        return move != disabledMove;
    }

}
