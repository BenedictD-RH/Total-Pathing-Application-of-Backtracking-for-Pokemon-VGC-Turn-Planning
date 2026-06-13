package stima.core.status.volatiles;

import stima.core.moves.Move;
import stima.core.status.properties.EffectsIncomingDamage;
import stima.core.status.properties.EndsAtEndOfTurn;

public class ProtectInvulnerable extends VolatileStatus implements EffectsIncomingDamage, EndsAtEndOfTurn {
    @Override
    public float damageModifier(Move move) {
        return 0;
    }
}
