package stima.core.moves.effects;

import stima.core.battle.RNGSeed;
import stima.core.field.weathers.HarshSunlight;
import stima.core.field.weathers.Rain;
import stima.core.field.weathers.Sandstorm;
import stima.core.field.weathers.Snow;
import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Type;
import stima.core.properties.ChangesBasePower;
import stima.core.properties.ChangesDamagingType;

public class ChangeOnWeather extends MoveEffect implements ChangesBasePower, ChangesDamagingType {
    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities) {
        
    }

    @Override
    public Type changeTypeTo(PokemonBattleState user, PokemonBattleState target, Move move) {
        if (user.effectedByFieldEffect(new HarshSunlight(-1))) {
            return Type.FIRE;
        } else if (user.effectedByFieldEffect(new Rain(-1))) {
            return Type.WATER;
        } else if (user.effectedByFieldEffect(new Snow(-1))) {
            return Type.ICE;
        } else if (user.effectedByFieldEffect(new Sandstorm(-1))) {
            return Type.ROCK;
        }
        return move.getType();
    }

    @Override
    public int changePowerTo(PokemonBattleState user, PokemonBattleState target, Move move) {
        if (user.effectedByFieldEffect(new HarshSunlight(-1))) {
            return move.getBasePower()*2;
        } else if (user.effectedByFieldEffect(new Rain(-1))) {
            return move.getBasePower()*2;
        } else if (user.effectedByFieldEffect(new Snow(-1))) {
            return move.getBasePower()*2;
        } else if (user.effectedByFieldEffect(new Sandstorm(-1))) {
            return move.getBasePower()*2;
        }
        return move.getBasePower();
    }
}
