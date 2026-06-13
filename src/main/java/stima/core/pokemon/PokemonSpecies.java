package stima.core.pokemon;

import java.util.EnumMap;
import java.util.List;

public class PokemonSpecies {
    private String name;
    private Typing typing;
    private EnumMap<Stat, Integer> baseStats;

    public PokemonSpecies(String name, Typing typing, List<Integer> stats) {
        this.name = name;
        this.typing = typing;
        int i = 0;
        this.baseStats = new EnumMap<>(Stat.class);
        for (Stat stat : Stat.values()) {
            this.baseStats.put(stat, stats.get(i++));
        }
    }

    public PokemonSpecies(String name, Typing typing, EnumMap<Stat, Integer> stats) {
        this.name = name;
        this.typing = typing;
        this.baseStats = stats;
    }

    public String getName() {
        return name;
    }

    public Typing getTyping() {
        return typing;
    }

    public EnumMap<Stat, Integer> getBaseStats() {
        return baseStats;
    }
}
