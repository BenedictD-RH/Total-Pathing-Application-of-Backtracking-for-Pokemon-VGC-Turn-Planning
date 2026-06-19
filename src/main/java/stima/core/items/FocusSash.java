package stima.core.items;

import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.TriggerWhenDamageKOs;

public class FocusSash extends Consumable implements TriggerWhenDamageKOs {
    @Override
    public void applyEffect(PokemonBattleState pokemon) {
        pokemon.applyHeal(1);
    }
}
