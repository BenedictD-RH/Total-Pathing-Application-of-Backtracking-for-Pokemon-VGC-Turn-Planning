package stima.algorithms;

import java.util.ArrayList;
import java.util.List;

import stima.core.battle.Action;
import stima.core.battle.BattleState;
import stima.core.battle.MoveAction;
import stima.core.battle.StruggleAction;
import stima.core.battle.SwitchAction;
import stima.core.pokemon.PokemonBattleState;

public class ActionManager {
    public static List<Action> getPossibleActions(BattleState state, 
                                                int userSlot, 
                                                int userTeamID) {
        PokemonBattleState pokemon = state
                                    .getTeam(userTeamID)
                                    .getPokemonOnSlot(userSlot);
        List<Action> actions = new ArrayList<>();
        if (!pokemon.isAlive()) return actions;
        for (int moveSlot = 0; 
            moveSlot < pokemon.getMoves().size(); 
            moveSlot++) {
            for (int teamID = 0; teamID < 2; teamID++) {
                for (int targetSlot = 0; 
                    targetSlot < 2; 
                    targetSlot++) {
                    MoveAction moveAction = 
                        new MoveAction(
                        userSlot, 
                        moveSlot, 
                        teamID != userTeamID, 
                        targetSlot);
                    if (moveAction.canDoAction
                        (state.getTeam(userTeamID), 
                        state.getOpposingTeam(userTeamID))) {
                        actions.add(moveAction);
                    }
                }
            }
        }
        for (int pokemonSlot = 0;
            pokemonSlot < pokemon.getTeam().getAllPokemon().size();
            pokemonSlot++) {
            SwitchAction switchAction = 
                new SwitchAction(userSlot, pokemonSlot);
            if (switchAction.canDoAction(state.getTeam(userTeamID), 
                state.getOpposingTeam(userTeamID))) {
                actions.add(switchAction);
            }
        }
        if (actions.size() == 0) {
            for (int targetSlot = 0; targetSlot < 2; targetSlot++) {
                    StruggleAction moveAction = 
                        new StruggleAction(userSlot, targetSlot);
                    if (moveAction.canDoAction
                        (state.getTeam(userTeamID),
                        state.getOpposingTeam(userTeamID))) {
                        actions.add(moveAction);
                    }
                }
        }

        return actions;
    }
}
