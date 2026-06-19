package stima.core.items;

import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;
import stima.core.properties.TriggerWhenNegativeStats;

public class WhiteHerb extends Consumable implements TriggerWhenNegativeStats {
    @Override
    public boolean doesTrigger(Stat stat, int amount) {
        return amount < 0;
    }

    @Override
    public void applyEffect(PokemonBattleState pokemon) {
        pokemon.resetStatChange();
        super.applyEffect(pokemon);
    }
}
