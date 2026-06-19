package stima.core.battle;

import java.util.ArrayList;
import java.util.List;

import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.DamagePerTurn;
import stima.core.properties.EndsAtEndOfTurn;
import stima.core.properties.EndsAtStartOfTurn;
import stima.core.properties.HealPerTurn;
import stima.core.properties.TriggerWhenSwitchIn;

public class TurnManager {
    public record BattleIdx(int teamID, int teamSlotID) {}
    private BattleState state;

    public TurnManager(BattleState state) {
        this.state = state;
    }

    public List<BattleIdx> getTurnOrder() {
        List<BattleIdx> turnOrder = new ArrayList<>();
        turnOrder.add(new BattleIdx(0, 0));
        turnOrder.add(new BattleIdx(0, 1));
        turnOrder.add(new BattleIdx(1, 0));
        turnOrder.add(new BattleIdx(1, 1));

        return turnOrder.stream().sorted((e1, e2) -> getPokemon(e1).movesBefore(getPokemon(e2))).toList();
    }

    public PokemonBattleState getPokemon(BattleIdx id) {
        return state.getTeam(id.teamID()).getPokemonOnSlot(id.teamSlotID());
    }

    public BattleState getCurrentState() {
        return state;
    }

    public void turnStart() {
        for (BattleIdx id  : getTurnOrder()) {
            getPokemon(id).updateStatusCounter(EndsAtStartOfTurn.class, null);
            if (getPokemon(id).getLastAction() == null) {
                if (getPokemon(id).getAbility() instanceof TriggerWhenSwitchIn) {
                    getPokemon(id).getAbility().applyEffect(getPokemon(id), null, state);
                }
            }
        }
    }

    public void turnEnd() {
        for (BattleIdx id  : getTurnOrder()) {
            getPokemon(id).updateStatusCounter(EndsAtEndOfTurn.class, null);
            for (Object dmgPerTurn : getPokemon(id).getStatusesWithProperty(DamagePerTurn.class)) {
                ((DamagePerTurn)dmgPerTurn).dealDamage(getPokemon(id));
            }
            for (Object healPerTurn : getPokemon(id).getStatusesWithProperty(HealPerTurn.class)) {
                ((HealPerTurn)healPerTurn).applyHeal(getPokemon(id));
            }
        }
    }

    public void printTurnState() {
        System.out.println(state.getBattleStateLog());
    }
 
    public List<RNGSeed> getRNGSeedsForTurnOrder(int order) {
        PokemonBattleState pokemon = getPokemon(getTurnOrder().get(order));
        return pokemon.getChosenAction().getRNGSeedsForAction(state, pokemon.getTeam());
    }

    public void doTurn(int order, RNGSeed seed) {
        PokemonBattleState pokemon = getPokemon(getTurnOrder().get(order));
        if (pokemon.isAlive()) {
            pokemon.getChosenAction().commitAction(pokemon.getTeam(), state.getOpposingTeam(pokemon.getTeam().getTeamID()), seed);
        }
        
    }
}
