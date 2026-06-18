package stima.core.status.volatiles;

import stima.core.moves.Move;

public class Underground extends TwoTurnInvulnerable {

    @Override
    public float damageModifier(Move move) {
        switch(move) {
            case EARTHQUAKE:
                return 2.0f;
            default:
                return 0;
        }
    }
}
