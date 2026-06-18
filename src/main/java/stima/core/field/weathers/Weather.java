package stima.core.field.weathers;

import stima.core.field.UniversalFieldEffect;
import stima.core.properties.EndsAtEndOfTurn;

public abstract class Weather extends UniversalFieldEffect implements EndsAtEndOfTurn {
    private int turnDuration;

    public Weather(int turnDuration) {
        this.turnDuration = turnDuration;
    }

    public Weather(Weather other) {
        this.turnDuration = other.turnDuration;
    }

    @Override
    public int endsAfter() {
        return turnDuration;
    }
}
