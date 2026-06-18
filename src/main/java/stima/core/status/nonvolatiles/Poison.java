package stima.core.status.nonvolatiles;

import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.DamagePerTurn;

public class Poison extends NonVolatileStatus implements DamagePerTurn {
    @Override
    public int dealDamage(PokemonBattleState pokemon) {
        return pokemon.applyPercentageDamage(0.125f);
    }
}
