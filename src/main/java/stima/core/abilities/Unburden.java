package stima.core.abilities;

import stima.core.battle.BattleState;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.TriggerWhenItemConsumed;
import stima.core.status.volatiles.Unburdened;

public class Unburden extends Ability implements TriggerWhenItemConsumed {
    @Override
    public void applyEffect(PokemonBattleState pokemon, PokemonBattleState opponent, BattleState state) {
        pokemon.addStatus(new Unburdened());
    }
}
