package stima.core.battle;

import stima.core.field.FieldEffect;

public class BattleState {
    private TeamState team1;
    private TeamState team2;
    private Battlefield field;

    public BattleState(TeamState team1, TeamState team2, Battlefield field) {
        this.team1 = team1;
        this.team2 = team2;
        this.field = field;

        this.team1.setTeamPosition(field.getTeamPosition(0));
        this.team2.setTeamPosition(field.getTeamPosition(1));

        this.team1.getPokemonOnSlot(0).setBattlePosition(field.getTeamPosition(0).getBattlePosition(0));
        this.team1.getPokemonOnSlot(1).setBattlePosition(field.getTeamPosition(0).getBattlePosition(1));

        this.team2.getPokemonOnSlot(0).setBattlePosition(field.getTeamPosition(1).getBattlePosition(0));
        this.team2.getPokemonOnSlot(1).setBattlePosition(field.getTeamPosition(1).getBattlePosition(1));
    }

    public BattleState(BattleState other) {
        this.team1 = new TeamState(other.team1);
        this.team2 = new TeamState(other.team2);
        this.field = new Battlefield(other.field);

        this.team1.setTeamPosition(field.getTeamPosition(0));
        this.team2.setTeamPosition(field.getTeamPosition(1));

        this.team1.getPokemonOnSlot(0).setBattlePosition(field.getTeamPosition(0).getBattlePosition(0));
        this.team1.getPokemonOnSlot(1).setBattlePosition(field.getTeamPosition(0).getBattlePosition(1));

        this.team2.getPokemonOnSlot(0).setBattlePosition(field.getTeamPosition(1).getBattlePosition(0));
        this.team2.getPokemonOnSlot(1).setBattlePosition(field.getTeamPosition(1).getBattlePosition(1));
    }

    public TeamState getTeam(int id) {
        if (id == 0) return team1;
        else if (id == 1) return team2;
        return null;
    }

    public TeamState getOpposingTeam(int id) {
        if (id == 1) return team1;
        else if (id == 0) return team2;
        return null;
    }

    public String getBattleStateLog() {
        StringBuilder state = new StringBuilder()
                                  .append(team1.teamStateLog())
                                  .append(team2.teamStateLog())
                                  .append("===================\n");
        for (FieldEffect fe : field.getActiveFieldEffects()) {
            state.append("[" + fe.getClass().getSimpleName() + "]");
        }
        return state.toString();
    }
}

