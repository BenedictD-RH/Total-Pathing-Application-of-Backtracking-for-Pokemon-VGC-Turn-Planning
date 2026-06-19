package stima.core.pokemon;

import java.util.EnumMap;


public class StatChangeBuilder {
    private EnumMap<Stat, Integer> statChange;

    public StatChangeBuilder() {
        statChange = new EnumMap<>(Stat.class);
        for (Stat s : Stat.values()) {
            if (s != Stat.HP) {
                statChange.put(s, 0);
            }
        }
    }

    public StatChangeBuilder(EnumMap<Stat, Integer> statChange) {
        this.statChange = statChange;
    }

    public StatChangeBuilder addStatChange(Stat stat, int amount) {
        statChange.put(stat, statChange.get(stat) + amount);
        return this;
    }

    public StatChangeBuilder append(EnumMap<Stat, Integer> statChange) {
        for (Stat s : Stat.values()) {
            if (s != Stat.HP) {
                this.statChange.put(s, this.statChange.get(s) + statChange.get(s));
                if (this.statChange.get(s) > 6) {
                    this.statChange.put(s, 6);
                } else if (this.statChange.get(s) < -6) {
                    this.statChange.put(s, -6);
                }
            }
        }

        return this;
    }

    public EnumMap<Stat, Integer> build() {
        return statChange;
    }
}

