package stima.core.status.volatiles;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.EndsAtEndOfTurn;
import stima.core.properties.RestrictsMoveChoice;

public class Encored extends VolatileStatus implements EndsAtEndOfTurn, RestrictsMoveChoice {
    private Move encoredTo;

    public Encored(Move move) {
        encoredTo = move;
    }

    public Encored(Encored other) {
        this.encoredTo = other.encoredTo;
    }

    @Override
    public int endsAfter() {
        return 3;
    }

    @Override
    public boolean canUseMove(Move move, PokemonBattleState pokemon) {
        return encoredTo == move;
    }
}
