package stima.core.field.hazards;

import stima.core.pokemon.PokemonBattleState;

public class Spikes extends EntryHazard {
    public Spikes() {
        super(3, true);
    }

    public void applyEffect(PokemonBattleState pokemon) {
        pokemon.reduceHealth(pokemon.getMaxHealthPercentage(getCurrentLayer()*0.0625f));
    }
}
