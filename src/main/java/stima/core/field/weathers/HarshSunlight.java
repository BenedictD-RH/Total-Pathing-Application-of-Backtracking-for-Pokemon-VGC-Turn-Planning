package stima.core.field.weathers;

import stima.core.moves.Move;
import stima.core.properties.EffectsDamage;

public class HarshSunlight extends Weather implements EffectsDamage{
    public HarshSunlight(int turnDuration) {
        super(turnDuration);
    }

    public HarshSunlight(HarshSunlight other) {
        super(other);
    }

    @Override
    public float damageModifier(Move move) {
        switch(move.getType()) {
            case FIRE :
                return 1.5f;
            case WATER :
                return 0.5f;
            default :
                return 1;
        } 
    }
}
