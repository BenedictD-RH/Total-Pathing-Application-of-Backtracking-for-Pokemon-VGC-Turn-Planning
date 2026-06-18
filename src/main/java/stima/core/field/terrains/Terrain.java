package stima.core.field.terrains;

import stima.core.field.UniversalFieldEffect;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.EndsAtEndOfTurn;

public class Terrain extends UniversalFieldEffect implements EndsAtEndOfTurn {
    private int turnDuration;

    public Terrain(int turnDuration) {
        this.turnDuration = turnDuration;
    }

    @Override
    public int endsAfter() {
        return turnDuration;
    }

    @Override
    public boolean isEffected(PokemonBattleState pokemon) {
        return pokemon.isGrounded();
    }
}
