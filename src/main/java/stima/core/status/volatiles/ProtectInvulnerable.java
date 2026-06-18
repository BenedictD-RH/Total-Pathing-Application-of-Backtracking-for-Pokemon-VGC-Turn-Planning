package stima.core.status.volatiles;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.EndsAtEndOfTurn;
import stima.core.properties.GivesImmunity;

public class ProtectInvulnerable extends VolatileStatus implements GivesImmunity, EndsAtEndOfTurn {
    @Override
    public boolean isImmune(Move move, PokemonBattleState pokemon) {
        return move.targetsOpponents();
    }

    @Override
    public int endsAfter() {
        return 0;
    }
}
