package stima.core.status.volatiles;

import stima.core.properties.EndsAtEndOfTurn;
import stima.core.properties.Immobilizing;
import stima.core.properties.SkipsTurnChoice;

public class Recharging extends VolatileStatus implements Immobilizing, SkipsTurnChoice, EndsAtEndOfTurn {
    @Override
    public float immobilizedChance() {
        return 1;
    }

    @Override
    public int endsAfter() {
        return 1;
    }

    @Override
    public String effectLog() {
        return "";
    }

    @Override
    public int skipFor() {
        return 1;
    }
}
