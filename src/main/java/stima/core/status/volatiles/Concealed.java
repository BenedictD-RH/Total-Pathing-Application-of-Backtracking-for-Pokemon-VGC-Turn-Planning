package stima.core.status.volatiles;

import stima.core.moves.Move;

public class Concealed extends TwoTurnInvulnerable {
    public Concealed(Move moveUsed) {
        super(moveUsed);
    }

    @Override
    public float damageModifier(Move move) {
        return 0;
    }
}
