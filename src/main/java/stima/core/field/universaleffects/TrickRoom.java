package stima.core.field.universaleffects;

import stima.core.field.UniversalFieldEffect;
import stima.core.properties.EndsAfterPokemonTurn;

public class TrickRoom extends UniversalFieldEffect implements EndsAfterPokemonTurn {
    @Override
    public int endsAfter() {
        return 6;
    }
}
