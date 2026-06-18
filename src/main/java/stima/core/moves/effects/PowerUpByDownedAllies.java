package stima.core.moves.effects;

import stima.core.battle.RNGSeed;
import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.ChangesBasePower;

public class PowerUpByDownedAllies extends MoveEffect implements ChangesBasePower{
    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities) {
        
    }

    @Override
    public int changePowerTo(PokemonBattleState user, PokemonBattleState target, Move move) {
        return move.getBasePower() + user.getTeam().getDownedPokemon().size()*50;
    }
}
