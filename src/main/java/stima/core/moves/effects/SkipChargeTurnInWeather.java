package stima.core.moves.effects;

import stima.core.battle.RNGSeed;
import stima.core.field.weathers.Weather;
import stima.core.pokemon.PokemonBattleState;

public class SkipChargeTurnInWeather extends ChargeFirst {
    private final Weather weather;

    public SkipChargeTurnInWeather(Weather weather) {
        super();
        this.weather = weather;
    }

    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities) {
        if (!user.effectedByFieldEffect(weather)) {
            user.addStatus(appliedStatus);
        }
    }
}
