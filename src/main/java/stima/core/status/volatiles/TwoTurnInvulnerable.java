package stima.core.status.volatiles;

import stima.core.moves.Move;
import stima.core.status.properties.EffectsIncomingDamage;

public abstract class TwoTurnInvulnerable extends TwoTurnMoveStatus implements EffectsIncomingDamage {
    public TwoTurnInvulnerable(Move moveUsed) {
        super(moveUsed);
    }
}
