package stima.core.battle;

import stima.core.moves.MoveCategory;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.CannotSwitch;
import stima.core.properties.SkipsTurnChoice;

public class SwitchAction extends Action {
    private int switchFromSlot;
    private int switchToSlot;
    
    public SwitchAction(int fromSlot, int toSlot) {
        switchFromSlot = fromSlot;
        switchToSlot = toSlot;
    }

    @Override
    public boolean canDoAction(
                    TeamState team, 
                    TeamState opposingTeam) {
        return !team.getPokemonOnSlot(switchFromSlot)
                    .hasStatusWithProperty(CannotSwitch.class) && 
               !team.getPokemonOnSlot(switchFromSlot)
                    .hasStatusWithProperty(SkipsTurnChoice.class) &&
                team.getPokemonOnSlot(switchToSlot).isAlive() &&
                switchFromSlot != switchToSlot &&
                (switchToSlot != 1 && switchToSlot != 0);
    }

    @Override
    public int getPriority(TeamState team) {
        return 10;
    }

    @Override
    public MoveCategory getCategory(TeamState team) {
        return null;
    }

    @Override
    public String actionLog(TeamState team, TeamState opposingTeam) {
        return "";
    }

    @Override
    public void commitAction(TeamState team, TeamState opposingTeam, RNGSeed seed) {
        System.out.println(team.getPokemonOnSlot(switchFromSlot).getPokemon().getName() + " switched to " + team.getPokemonOnSlot(switchFromSlot).getPokemon().getName());
        PokemonBattleState temp = team.getPokemonOnSlot(switchFromSlot);
        team.setPokemonOnSlot(team.getPokemonOnSlot(switchToSlot), switchFromSlot);
        team.setPokemonOnSlot(temp, switchToSlot);
    }
}
