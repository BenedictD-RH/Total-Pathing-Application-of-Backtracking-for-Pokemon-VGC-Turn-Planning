package stima.core.status.volatiles;

import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.DamagePerTurn;

public class LeechSeeded extends VolatileStatus implements DamagePerTurn {
    @Override
    public int dealDamage(PokemonBattleState pokemon) {
        return pokemon.applyPercentageDamage(0.125f);
    }
}
