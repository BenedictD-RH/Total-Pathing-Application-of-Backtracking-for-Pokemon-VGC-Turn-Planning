package stima.core.abilities;

import stima.core.battle.BattleState;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.TriggerOnContact;

public class RoughSkin extends Ability implements TriggerOnContact {
    @Override
    public void applyEffect(PokemonBattleState pokemon, PokemonBattleState opponent, BattleState state) {
        opponent.applyDamage(opponent.getMaxHealthPercentage(0.0625f));
    }
}
