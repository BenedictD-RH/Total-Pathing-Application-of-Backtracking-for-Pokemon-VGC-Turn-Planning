package stima.core.status.nonvolatiles;

import stima.core.properties.ChanceToEnd;
import stima.core.properties.EndsBeforePokemonTurn;
import stima.core.properties.Immobilizing;

public class Freeze extends NonVolatileStatus implements ChanceToEnd, EndsBeforePokemonTurn, Immobilizing {
    @Override
    public float endChance() {
        return 0.25f;
    }

    @Override
    public String effectLog() {
        return " is unfrozen.";
    }

    @Override
    public int endsAfter() {
        return 3;
    }
    
    @Override
    public float immobilizedChance() {
        return 1;
    }
}
