package stima.core.items;

import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.TriggerWhenHealthDrops;

public class SitrusBerry extends Consumable implements TriggerWhenHealthDrops {
    @Override
    public float percentageThreshold() {
        return 0.5f;
    }

    @Override
    public void applyEffect(PokemonBattleState pokemon) {
        pokemon.applyHeal(pokemon.getMaxHealthPercentage(0.25f));
    }
}
