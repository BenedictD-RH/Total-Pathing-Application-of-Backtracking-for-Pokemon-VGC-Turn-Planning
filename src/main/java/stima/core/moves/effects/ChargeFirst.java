package stima.core.moves.effects;

import stima.core.status.volatiles.TwoTurnMoveStatus;

public class ChargeFirst extends ApplyStatusToSelf {
    public ChargeFirst() {
        super(new TwoTurnMoveStatus());
    }
}
