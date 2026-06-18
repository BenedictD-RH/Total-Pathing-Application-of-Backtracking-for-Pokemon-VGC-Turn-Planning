package stima.core.status.volatiles;

import stima.core.pokemon.Typing;
import stima.core.properties.ChangesType;

public class TypeOverwrite extends VolatileStatus implements ChangesType {
    private Typing overwrittingType;
    public TypeOverwrite(Typing type) {
        overwrittingType = type;
    }

    public TypeOverwrite(TypeOverwrite other) {
        this.overwrittingType = other.overwrittingType;
    }

    @Override
    public Typing changeTo() {
        return overwrittingType;
    }
}
