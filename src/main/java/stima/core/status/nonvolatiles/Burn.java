package stima.core.status.nonvolatiles;

import stima.core.moves.Move;
import stima.core.status.properties.DamagePerTurn;
import stima.core.status.properties.EffectsOutgoingDamage;

public class Burn extends NonVolatileStatus implements DamagePerTurn, EffectsOutgoingDamage {
    @Override
    public float damageModifier(Move move) {
        return 0.5f;
    }

    @Override
    public float damagePercentage() {
        return 0.0625f;
    }
}
