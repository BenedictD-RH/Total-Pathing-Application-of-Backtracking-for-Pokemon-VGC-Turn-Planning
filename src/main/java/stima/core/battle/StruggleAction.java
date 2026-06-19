package stima.core.battle;

import java.util.List;

import stima.core.moves.MoveCategory;
import stima.core.pokemon.PokemonBattleState;

public class StruggleAction extends Action {
    private int userSlot;
    private int targetSlot;

    public StruggleAction(int userSlot, int targetSlot) {
        this.userSlot = userSlot;
        this.targetSlot = targetSlot;
    }

    @Override
    public boolean canDoAction(TeamState team, TeamState opposingTeam) {
        return true;
    }

    @Override
    public int getPriority(TeamState team) {
        return 0;
    }

    @Override
    public MoveCategory getCategory(TeamState team) {
        return MoveCategory.PHYSICAL;
    }

    @Override
    public String actionLog(TeamState team, TeamState opposingTeam) {
        return "";
    }


    @Override
    public void commitAction(TeamState team, TeamState opposingTeam, RNGSeed seed) {
        PokemonBattleState target = opposingTeam.getPokemonOnSlot(targetSlot);
        StringBuilder moveLog = new StringBuilder(team.getPokemonOnSlot(userSlot).getPokemon().getName())
                                    .append("(").append(team.getPokemonOnSlot(userSlot).getHealth())
                                    .append("/").append(team.getPokemonOnSlot(userSlot).getMaxHealth())
                                    .append("HP) used ")
                                    .append("Struggle")
                                    .append(" on ").append(target.getPokemon().getName());
        //System.out.println(moveLog.toString());
        team.getPokemonOnSlot(userSlot).getStruggle()
            .useMove(team.getPokemonOnSlot(userSlot), List.of(target), seed);
    }
}
