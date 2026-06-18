package stima.core.status.volatiles;

import stima.core.moves.Move;
import stima.core.properties.RestrictsMoveChoice;

public class NotFirstTurn extends VolatileStatus implements RestrictsMoveChoice {
    @Override
    public boolean canUseMove(Move move) {
        return move != Move.FAKE_OUT;
    }
}
