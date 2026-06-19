package stima.core.properties;

import stima.core.pokemon.Stat;

public interface TriggerWhenNegativeStats {
    boolean doesTrigger(Stat stat, int amount);
}
