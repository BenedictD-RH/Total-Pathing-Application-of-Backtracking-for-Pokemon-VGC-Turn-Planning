package stima.core.status.volatiles;

import stima.core.moves.Move;

public class Submerged extends TwoTurnInvulnerable{
    public Submerged(Move moveUsed) {
        super(moveUsed);
    }

    @Override
    public float damageModifier(Move move) {
        switch(move) {
            case SURF:
            case WHIRLPOOL:
                return 2.0f;
            default:
                return 0;
        }
    }
}
