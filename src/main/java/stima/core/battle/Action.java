package stima.core.battle;

import java.util.ArrayList;
import java.util.List;

import stima.core.moves.MoveCategory;

public abstract class Action {
    abstract public boolean canDoAction(TeamState team, TeamState opposingTeam);

    abstract public void commitAction(TeamState team, TeamState opposingTeam, RNGSeed seed);

    abstract public int getPriority(TeamState team);

    abstract public MoveCategory getCategory(TeamState team);

    public List<RNGSeed> getRNGSeedsForAction(BattleState state, TeamState team) {
        return new ArrayList<>();
    }
}
