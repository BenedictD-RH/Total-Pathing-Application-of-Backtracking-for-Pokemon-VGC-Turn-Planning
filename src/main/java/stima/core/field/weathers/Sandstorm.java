package stima.core.field.weathers;

import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;
import stima.core.pokemon.Type;
import stima.core.properties.DamagePerTurn;
import stima.core.properties.EffectsStat;

public class Sandstorm extends Weather implements DamagePerTurn, EffectsStat {
    public Sandstorm(int turnDuration) {
        super(turnDuration);
    }

    public Sandstorm(Sandstorm other) {
        super(other);
    }

    @Override
    public int dealDamage(PokemonBattleState pokemon) {
        if (pokemon.getTyping().isType(Type.ROCK) || 
            pokemon.getTyping().isType(Type.GROUND) ||
            pokemon.getTyping().isType(Type.STEEL)) {
            return 0;
        } else {
            return pokemon.applyPercentageDamage(0.0625f);
        }
    }

    @Override
    public float statModifier(Stat stat, PokemonBattleState pokemon) {
        if (stat == Stat.SPD && pokemon.getTyping().isType(Type.ROCK)) {
            return 1.5f;
        } else {
            return 1;
        }
    }
}
