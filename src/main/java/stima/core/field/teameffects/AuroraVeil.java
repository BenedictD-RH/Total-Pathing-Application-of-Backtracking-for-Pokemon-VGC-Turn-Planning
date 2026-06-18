package stima.core.field.teameffects;

import stima.core.field.TeamwideFieldEffect;
import stima.core.moves.Move;
import stima.core.properties.EffectsIncomingDamage;
import stima.core.properties.EndsAtEndOfTurn;

public class AuroraVeil extends TeamwideFieldEffect implements EndsAtEndOfTurn, EffectsIncomingDamage {
    @Override
    public int endsAfter() {
        return 5;
    }

    @Override
    public float damageModifier(Move move) {
        return (1/1.5f);
    }
}
