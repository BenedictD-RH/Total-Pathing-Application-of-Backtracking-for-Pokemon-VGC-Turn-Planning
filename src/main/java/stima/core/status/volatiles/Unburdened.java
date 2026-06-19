package stima.core.status.volatiles;

import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;
import stima.core.properties.EffectsStat;

public class Unburdened extends VolatileStatus implements EffectsStat {
    @Override
    public float statModifier(Stat stat, PokemonBattleState pokemon) {
        return stat == Stat.SPE ? 2 : 1;
    }
}
