package stima.core.field.terrains;


import stima.core.moves.Move;
import stima.core.pokemon.Type;
import stima.core.properties.EffectsOutgoingDamage;
import stima.core.properties.PreventsStatus;
import stima.core.status.Status;
import stima.core.status.nonvolatiles.Sleep;

public class ElectricTerrain extends Terrain implements PreventsStatus, EffectsOutgoingDamage {
    public ElectricTerrain(int turnDuration) {
        super(turnDuration);
    }

    @Override
    public boolean canApplyStatus(Status status) {
        return status instanceof Sleep;
    }

    @Override
    public float damageModifier(Move move) {
        return move.getType() == Type.ELECTRIC ? 1.3f : 1;
    }
}
