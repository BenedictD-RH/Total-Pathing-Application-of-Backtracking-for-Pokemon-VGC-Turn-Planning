package stima.core.items;

import stima.core.battle.MoveAction;
import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;
import stima.core.properties.EffectsStat;
import stima.core.properties.RestrictsMoveChoice;

public class ChoiceScarf extends HeldItem implements EffectsStat, RestrictsMoveChoice {
    @Override
    public void applyEffect(PokemonBattleState pokemon) {

    }

    @Override
    public float statModifier(Stat stat, PokemonBattleState pokemon) {
        return stat == Stat.SPE ? 1.5f : 1;
    }

    @Override
    public boolean canUseMove(Move move, PokemonBattleState pokemon) {
        if (pokemon.getLastAction() instanceof MoveAction) {
            return ((MoveAction)pokemon.getLastAction()).getMove(pokemon.getTeam()) == move;
        } else {
            return true;
        }
    }
}
