package stima.core.battle;

import java.util.ArrayList;
import java.util.List;

public class Battlefield extends FieldState {
    private final List<TeamPosition> teams;

    public Battlefield() {
        teams = new ArrayList<>();
        teams.add(new TeamPosition(this));
        teams.add(new TeamPosition(this));
    }

    public Battlefield(Battlefield other) {
        super(other);
        this.teams = new ArrayList<>();
        this.teams.add(new TeamPosition(other.teams.get(0), this));
        this.teams.add(new TeamPosition(other.teams.get(1), this));
    }

    public TeamPosition getTeamPosition(int id) {
        return teams.get(id);
    }
}
