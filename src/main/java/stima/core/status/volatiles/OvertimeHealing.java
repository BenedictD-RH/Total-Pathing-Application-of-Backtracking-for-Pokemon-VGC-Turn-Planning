package stima.core.status.volatiles;

import stima.core.status.properties.HealPerTurn;

public class OvertimeHealing extends VolatileStatus implements HealPerTurn {
    @Override
    public float healPercentage() {
        return 0.0625f;
    }
}
