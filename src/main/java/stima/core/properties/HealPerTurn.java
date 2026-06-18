package stima.core.properties;

import stima.core.pokemon.PokemonBattleState;

public interface HealPerTurn {
    int applyHeal(PokemonBattleState pokemon);
}
