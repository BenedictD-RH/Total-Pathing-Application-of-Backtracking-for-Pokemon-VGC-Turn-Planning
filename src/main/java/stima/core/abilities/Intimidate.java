package stima.core.abilities;

import stima.core.battle.BattleState;
import stima.core.battle.TeamState;
import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;
import stima.core.pokemon.StatChangeBuilder;
import stima.core.properties.TriggerWhenSwitchIn;

public class Intimidate extends Ability implements TriggerWhenSwitchIn {
    @Override
    public void applyEffect(PokemonBattleState pokemon, PokemonBattleState opponent, BattleState state) {
        TeamState opposingTeam = state.getOpposingTeam(pokemon.getTeam().getTeamID());
        if (opposingTeam.getPokemonOnSlot(0).isAlive()) {
            opposingTeam.getPokemonOnSlot(0).addStatChange(new StatChangeBuilder().addStatChange(Stat.ATK, -1).build());
        }
        if (opposingTeam.getPokemonOnSlot(1).isAlive()) {
            opposingTeam.getPokemonOnSlot(1).addStatChange(new StatChangeBuilder().addStatChange(Stat.ATK, -1).build());
        }
    }
}
