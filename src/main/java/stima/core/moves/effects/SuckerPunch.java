package stima.core.moves.effects;

import stima.core.battle.RNGSeed;
import stima.core.moves.Move;
import stima.core.moves.MoveCategory;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.CanFail;

public class SuckerPunch extends MoveEffect implements CanFail {
    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState opponent, int damageDone, RNGSeed probabilities) {

    }

    @Override
    public boolean moveSucceeds(PokemonBattleState user, PokemonBattleState opponent, Move move) {
        return user.getChosenAction().getCategory(user.getTeam()) != MoveCategory.STATUS && opponent.getChosenAction().getPriority(opponent.getTeam()) < move.getPriority();
    }
}
