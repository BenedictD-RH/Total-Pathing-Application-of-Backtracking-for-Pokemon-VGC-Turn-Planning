package stima.core.properties;

import stima.core.moves.Move;

public interface RestrictsMoveChoice {
    boolean canUseMove(Move move);
}
