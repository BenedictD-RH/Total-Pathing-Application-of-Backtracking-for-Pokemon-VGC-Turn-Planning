package stima.core.properties;

import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;

public interface EffectsStat {
    float statModifier(Stat stat, PokemonBattleState pokemon);
}
