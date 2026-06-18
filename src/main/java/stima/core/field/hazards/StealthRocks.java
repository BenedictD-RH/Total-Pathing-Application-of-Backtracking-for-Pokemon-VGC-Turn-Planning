package stima.core.field.hazards;

import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Type;

public class StealthRocks extends EntryHazard {
    public StealthRocks() {
        super(1, false);
    }

    public void applyEffect(PokemonBattleState pokemon) {
        pokemon.reduceHealth(pokemon.getMaxHealthPercentage(0.125f*pokemon.getPokemon().getTyping().defenseMultipier(Type.ROCK)));
    }
}
