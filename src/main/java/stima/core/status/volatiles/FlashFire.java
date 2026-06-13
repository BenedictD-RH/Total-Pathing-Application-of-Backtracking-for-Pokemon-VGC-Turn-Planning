package stima.core.status.volatiles;

import stima.core.moves.Move;
import stima.core.pokemon.Type;
import stima.core.status.properties.EffectsOutgoingDamage;

public class FlashFire extends VolatileStatus implements EffectsOutgoingDamage {
    @Override
    public float damageModifier(Move move) {
        if (move.getType() == Type.FIRE) {
            return 1.5f;
        } else {
            return 1;
        }
    }
}
