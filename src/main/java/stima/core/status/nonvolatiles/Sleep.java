package stima.core.status.nonvolatiles;

import stima.core.properties.ChanceToEnd;
import stima.core.properties.EndsBeforePokemonTurn;
import stima.core.properties.FixedEffected;
import stima.core.properties.Immobilizing;

public class Sleep extends NonVolatileStatus implements ChanceToEnd, EndsBeforePokemonTurn, Immobilizing, FixedEffected {
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

    @Override
    public String effectLog() {
        return " woke up.";
    }

}
