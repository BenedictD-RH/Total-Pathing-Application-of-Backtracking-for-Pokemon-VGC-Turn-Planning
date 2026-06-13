package stima.core.status.nonvolatiles;

import stima.core.status.properties.EffectsSpeed;
import stima.core.status.properties.Immobilizing;

public class Paralysis extends NonVolatileStatus implements EffectsSpeed, Immobilizing {
    @Override
    public float speedModifier() {
        return 0.5f;
    }

    @Override
    public float immobilizedChance() {
        return 0.125f;
    }
}
