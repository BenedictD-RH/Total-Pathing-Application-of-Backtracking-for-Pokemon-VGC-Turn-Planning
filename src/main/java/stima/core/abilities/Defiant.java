package stima.core.abilities;

import stima.core.battle.BattleState;
import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;
import stima.core.pokemon.StatChangeBuilder;
import stima.core.properties.TriggerWhenStatDrops;

public class Defiant extends Ability implements TriggerWhenStatDrops {

    @Override
    public void applyEffect(PokemonBattleState pokemon, PokemonBattleState opponent, BattleState state) {
        pokemon.addStatChange(new StatChangeBuilder().addStatChange(Stat.ATK, 2).build());
    }
}
