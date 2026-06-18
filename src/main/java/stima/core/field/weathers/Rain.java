package stima.core.field.weathers;

import stima.core.moves.Move;
import stima.core.properties.EffectsDamage;

public class Rain extends Weather implements EffectsDamage {
    public Rain(int turnDuration) {
        super(turnDuration);
    }

    public Rain(Rain other) {
        super(other);
    }

    @Override
    public float damageModifier(Move move) {
        switch(move.getType()) {
            case WATER :
                return 1.5f;
            case FIRE :
                return 0.5f;
            default :
                return 1;
        } 
    }
}
