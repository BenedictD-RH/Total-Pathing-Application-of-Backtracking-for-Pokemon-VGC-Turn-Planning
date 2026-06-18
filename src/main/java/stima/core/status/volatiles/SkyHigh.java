package stima.core.status.volatiles;

import stima.core.moves.Move;

public class SkyHigh extends TwoTurnInvulnerable {

    @Override
    public float damageModifier(Move move) {
        switch(move) {
            case THUNDER:
            case SMACK_DOWN:
            case HURRICANE:
                return 1.0f;
            default:
                return 0;
        }
    }
}
