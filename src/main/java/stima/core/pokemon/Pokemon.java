package stima.core.pokemon;

import java.util.EnumMap;

public class Pokemon {
    private PokemonSpecies species;
    private Nature nature;
    private EnumMap<Stat, Integer> ev;
    

    public Pokemon(PokemonSpecies species, Nature nature, EnumMap<Stat, Integer> ev) {
        this.species = species;
        this.nature = nature;
        this.ev = ev;
    }

    public String getName() {
        return species.getName();
    }

    public Typing getTyping() {
        return species.getTyping();
    }

    public int getStat(Stat stat) {
        return stat.calculateStat(species.getBaseStats().get(stat), ev.get(stat), nature);
    }
}
