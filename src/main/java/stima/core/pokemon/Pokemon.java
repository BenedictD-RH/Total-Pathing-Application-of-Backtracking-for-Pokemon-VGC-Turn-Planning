package stima.core.pokemon;

import java.util.EnumMap;
import java.util.List;

import stima.core.moves.Move;

public class Pokemon {
    private final PokemonSpecies species;
    private final Nature nature;
    private final EnumMap<Stat, Integer> ev;
    private final List<Move> moves;

    public Pokemon(PokemonSpecies species, Nature nature, EnumMap<Stat, Integer> ev, List<Move> moves) {
        this.species = species;
        this.nature = nature;
        this.ev = ev;
        this.moves = moves;
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

    public List<Move> getMoves() {
        return moves;
    }
}
