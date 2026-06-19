package stima.core.status.volatiles;

import stima.core.properties.EndsAtEndOfTurn;
import stima.core.properties.Immobilizing;

public class Flinch extends VolatileStatus implements Immobilizing, EndsAtEndOfTurn {
    @Override
    public float immobilizedChance() {
        return 1;
    }

    @Override
    public String effectLog() {
        return "";
    }

    @Override
    public int endsAfter() {
        return 0;
    }
}
