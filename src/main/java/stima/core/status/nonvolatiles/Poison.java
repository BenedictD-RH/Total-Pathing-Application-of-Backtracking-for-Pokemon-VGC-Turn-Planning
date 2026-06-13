package stima.core.status.nonvolatiles;

import stima.core.status.properties.DamagePerTurn;

public class Poison extends NonVolatileStatus implements DamagePerTurn {
    @Override
    public float damagePercentage() {
        return 0.125f;
    }
}
