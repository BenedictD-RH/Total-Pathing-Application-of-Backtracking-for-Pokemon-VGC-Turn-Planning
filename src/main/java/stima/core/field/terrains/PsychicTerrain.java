package stima.core.field.terrains;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Type;
import stima.core.properties.EffectsOutgoingDamage;
import stima.core.properties.GivesImmunity;

public class PsychicTerrain extends Terrain implements EffectsOutgoingDamage, GivesImmunity {
    public PsychicTerrain(int turnDuration) {
        super(turnDuration);
    }

    @Override
    public float damageModifier(Move move) {
        if (move.getType() == Type.PSYCHIC) {
            return 1.3f;
        } else {
            return 1;
        }
    }

    @Override
    public boolean isImmune(Move move, PokemonBattleState pokemon) {
        return move.getPriority() > 0 && move.targetsOpponents();
    }
}
