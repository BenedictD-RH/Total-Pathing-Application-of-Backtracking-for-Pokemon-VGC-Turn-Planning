package stima.core.status.nonvolatiles;

import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;
import stima.core.properties.EffectsStat;
import stima.core.properties.Immobilizing;

public class Paralysis extends NonVolatileStatus implements EffectsStat, Immobilizing {
    @Override
    public float statModifier(Stat stat, PokemonBattleState pokemon) {
        return stat == Stat.SPE ? 0.5f : 1;
    }

    @Override
    public float immobilizedChance() {
        return 0.125f;
    }
}
