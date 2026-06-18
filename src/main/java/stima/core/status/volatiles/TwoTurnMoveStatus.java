package stima.core.status.volatiles;

import stima.core.properties.EndsBeforePokemonTurn;
import stima.core.properties.Immobilizing;
import stima.core.properties.SkipsTurnChoice;

public class TwoTurnMoveStatus extends VolatileStatus implements Immobilizing, SkipsTurnChoice, EndsBeforePokemonTurn {
    @Override
    public float immobilizedChance() {
        return 1;
    }

    @Override
    public int endsAfter() {
        return 1;
    }

    @Override
    public int skipFor() {
        return 1;
    }
}
