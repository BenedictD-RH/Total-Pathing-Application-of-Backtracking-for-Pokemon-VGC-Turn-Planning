package stima.core.field.weathers;

import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;
import stima.core.pokemon.Type;
import stima.core.properties.EffectsStat;

public class Snow extends Weather implements EffectsStat {
    public Snow(int turnDuration) {
        super(turnDuration);
    }

    public Snow(Snow other) {
        super(other);
    }

    @Override
    public float statModifier(Stat stat, PokemonBattleState pokemon) {
        if (stat == Stat.DEF && pokemon.getTyping().isType(Type.ICE)) {
            return 1.5f;
        } else {
            return 1;
        }
    }
}
