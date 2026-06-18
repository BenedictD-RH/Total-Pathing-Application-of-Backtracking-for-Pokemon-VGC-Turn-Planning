package stima.core.battle;

public class BattlePosition extends FieldState {
    private final TeamPosition team;

    public BattlePosition(TeamPosition team) {
        this.team = team;
    }

    public BattlePosition(BattlePosition other, TeamPosition otherTeam) {
        super(other);
        this.team = otherTeam;
    }

    public TeamPosition getTeamPosition() {
        return team;
    }
}
