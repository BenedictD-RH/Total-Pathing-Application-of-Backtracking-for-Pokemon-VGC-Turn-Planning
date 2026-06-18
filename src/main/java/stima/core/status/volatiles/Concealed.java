package stima.core.status.volatiles;

import stima.core.moves.Move;

public class Concealed extends TwoTurnInvulnerable {

    @Override
    public float damageModifier(Move move) {
        return 0;
    }
}
