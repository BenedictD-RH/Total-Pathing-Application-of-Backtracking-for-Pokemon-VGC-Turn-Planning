package stima.core.field.teameffects;

import stima.core.field.TeamwideFieldEffect;
import stima.core.moves.Move;
import stima.core.moves.MoveTarget;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.EndsAtEndOfTurn;
import stima.core.properties.GivesImmunity;

public class WideGuard extends TeamwideFieldEffect implements GivesImmunity, EndsAtEndOfTurn {
    @Override
    public int endsAfter() {
        return 0;
    }

    @Override
    public boolean isImmune(Move move, PokemonBattleState pokemon) {
        return move.getTarget() == MoveTarget.ALL || move.getTarget() == MoveTarget.ADJACENT;
    }
}
