package stima.core.status.volatiles;

import stima.core.moves.Move;
import stima.core.status.properties.EndsBeforePokemonTurn;
import stima.core.status.properties.FixedToEnd;
import stima.core.status.properties.Immobilizing;
import stima.core.status.properties.SkipsTurnChoice;

public abstract class TwoTurnMoveStatus extends VolatileStatus implements Immobilizing, SkipsTurnChoice, FixedToEnd, EndsBeforePokemonTurn {
    private Move move;

    public TwoTurnMoveStatus(Move usedMove) {
        this.move = usedMove;
    }
    
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

    public Move getUsedMove() {
        return move;
    }
}
