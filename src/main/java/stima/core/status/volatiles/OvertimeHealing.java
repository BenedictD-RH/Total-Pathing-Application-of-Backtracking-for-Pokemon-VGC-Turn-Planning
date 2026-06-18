package stima.core.status.volatiles;

import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.HealPerTurn;

public class OvertimeHealing extends VolatileStatus implements HealPerTurn {
    @Override
    public int applyHeal(PokemonBattleState pokemon) {
        return pokemon.applyPercentageHeal(0.0625f);
    }
}
