package stima.core.status.nonvolatiles;

import stima.core.status.properties.ChanceToEnd;
import stima.core.status.properties.FixedEffected;
import stima.core.status.properties.FixedToEnd;
import stima.core.status.properties.Immobilizing;

public class Sleep extends NonVolatileStatus implements ChanceToEnd, FixedToEnd, Immobilizing, FixedEffected {
    @Override
    public float endChance() {
        return 0.25f;
    }

    @Override
    public int endsAfter() {
        return 3;
    }
    
    @Override
    public float immobilizedChance() {
        return 1;
    }

    @Override
    public int fixedEffectFor() {
        return 1;
    }
}
