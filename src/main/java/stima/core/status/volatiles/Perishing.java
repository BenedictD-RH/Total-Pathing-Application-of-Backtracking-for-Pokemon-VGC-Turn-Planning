package stima.core.status.volatiles;

import stima.core.properties.EndsAtEndOfTurn;
import stima.core.properties.KOAfterEnding;

public class Perishing extends VolatileStatus implements EndsAtEndOfTurn, KOAfterEnding {
    @Override
    public int endsAfter() {
        return 4;
    }
}
