package stima.core.status.volatiles;

import stima.core.moves.Move;
import stima.core.moves.MoveProperty;
import stima.core.properties.FixedToEnd;
import stima.core.properties.RestrictsMoveChoice;

public class ThroatChopped extends VolatileStatus implements FixedToEnd, RestrictsMoveChoice {
    @Override
    public int endsAfter() {
        return 2;
    }

    @Override
    public boolean canUseMove(Move move) {
        return !move.hasProperty(MoveProperty.SOUND);
    }
}
