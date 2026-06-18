package stima.core.battle;

import java.util.ArrayList;
import java.util.List;

import stima.core.field.FieldEffect;
import stima.core.pokemon.Pokemon;
import stima.core.pokemon.PokemonBattleState;

public class TeamState {
    private final int teamID;
    private final List<PokemonBattleState> team;
    private TeamPosition position;

    public TeamState(int teamID, List<Pokemon> pokemon) {
        this.teamID = teamID;
        team = new ArrayList<>();
        for (Pokemon p : pokemon) {
            team.add(new PokemonBattleState(p, this));
        }
        position = null;
    }

    public TeamState(TeamState other) {
        this.teamID = other.teamID;
        this.team = new ArrayList<>();
        for (PokemonBattleState p : other.team) {
            team.add(new PokemonBattleState(p, this));
        }
    }

    public List<PokemonBattleState> getAlivePokemon() {
        return team.stream().filter(e -> e.getHealth() > 0).toList();
    }

    public List<PokemonBattleState> getDownedPokemon() {
        return team.stream().filter(e -> e.getHealth() <= 0).toList();
    }

    public PokemonBattleState getPokemonOnSlot(int id) {
        return team.get(id);
    }

    public void setTeamPosition(TeamPosition position) {
        this.position = position;
    }

    public void setPokemonOnSlot(PokemonBattleState pokemon, int id) {
        team.set(id, pokemon);
    }

    public TeamPosition getTeamPosition() {
        return position;
    }

    public int getTeamID() {
        return teamID;
    }

    public String teamStateLog() {
        StringBuilder state = new StringBuilder("Team " + teamID)
                                  .append(" : ").append(getAlivePokemon().size())
                                  .append("/").append(team.size())
                                  .append(" Pokemon\n");
        for (FieldEffect fe : position.getActiveFieldEffects()) {
            state.append("[" + fe.getClass().getSimpleName() + "]");
        }
        state.append("--------------\n");
        for (int i = 0; i < team.size(); i++) {
            if (i < 2) {
                state.append("(OUT)");
            }
            state.append(team.get(i).getStateLog()).append("\n");
        }
        return state.toString();
    }
}
