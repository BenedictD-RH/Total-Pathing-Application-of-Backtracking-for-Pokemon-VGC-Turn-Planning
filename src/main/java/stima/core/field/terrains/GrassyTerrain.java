package stima.core.field.terrains;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Type;
import stima.core.properties.EffectsOutgoingDamage;
import stima.core.properties.HealPerTurn;

public class GrassyTerrain extends Terrain implements HealPerTurn, EffectsOutgoingDamage {
    public GrassyTerrain(int turnDuration) {
        super(turnDuration);
    }

    @Override
    public int applyHeal(PokemonBattleState pokemon) {
        return pokemon.applyPercentageHeal(0.0625f);
    }

    @Override
    public float damageModifier(Move move) {
        if (move.getType() == Type.GRASS) {
            return 1.3f;
        } else if (move == Move.EARTHQUAKE) {
            return 0.5f;
        } else {
            return 1;
        }
    }
}
