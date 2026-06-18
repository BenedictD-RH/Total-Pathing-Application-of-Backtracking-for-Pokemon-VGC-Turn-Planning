package stima.core.field.hazards;

import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;

public class StickyWeb extends EntryHazard {
    public StickyWeb() {
        super(1, true);
    }

    public void applyEffect(PokemonBattleState pokemon) {
        pokemon.addStatChange(Stat.SPE, -1);
    }
}
