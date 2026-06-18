package stima.core.field.hazards;

import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Type;
import stima.core.status.nonvolatiles.Poison;
import stima.core.status.nonvolatiles.ToxicPoison;

public class ToxicSpikes extends EntryHazard {
    public ToxicSpikes() {
        super(2, true);
    }

    public void applyEffect(PokemonBattleState pokemon) {
        if (pokemon.getTyping().isType(Type.POISON)) {
            pokemon.getBattlePosition().getTeamPosition().removeFieldEffect(this);
        } else if (getCurrentLayer() == 1) {
            pokemon.addStatus(new Poison());
        } else {
            pokemon.addStatus(new ToxicPoison());
        }
    }
}
