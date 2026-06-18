package stima.core.field.teameffects;

import stima.core.field.TeamwideFieldEffect;
import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;
import stima.core.properties.EffectsStat;
import stima.core.properties.EndsAtEndOfTurn;

public class Tailwind extends TeamwideFieldEffect implements EndsAtEndOfTurn, EffectsStat {
    @Override
    public int endsAfter() {
        return 4;
    }

    @Override
    public float statModifier(Stat stat, PokemonBattleState pokemon) {
        return stat == Stat.SPE ? 2 : 1;
    }
}
