package stima.core.moves.effects;

import stima.core.battle.RNGSeed;
import stima.core.field.weathers.Weather;
import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.ChangesAccuracy;

public class NeverMissInWeather extends MoveEffect implements ChangesAccuracy {
    private final Weather weather;

    public NeverMissInWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities) {
        
    }

    @Override
    public int changeAccuracyTo(PokemonBattleState user, PokemonBattleState target, Move move) {
        return user.effectedByFieldEffect(weather) ? 101 : move.getAccuracy();
    }
}
