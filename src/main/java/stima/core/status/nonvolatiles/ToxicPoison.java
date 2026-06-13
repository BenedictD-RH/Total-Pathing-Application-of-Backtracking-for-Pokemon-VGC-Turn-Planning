package stima.core.status.nonvolatiles;

import stima.core.status.properties.DamagePerTurn;
import stima.core.status.properties.Resettable;

public class ToxicPoison extends NonVolatileStatus implements DamagePerTurn, Resettable {
    private float currentDamagePercentage = 0.0625f;

    @Override
    public float damagePercentage() {
        float damageNow = currentDamagePercentage;
        currentDamagePercentage *= 2;
        return damageNow;
    }

    @Override
    public void reset() {
        currentDamagePercentage = 0.0625f;
    }
}   
