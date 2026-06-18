package stima.core.status.volatiles;

import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.CannotSwitch;
import stima.core.properties.DamagePerTurn;
import stima.core.properties.EndsAtEndOfTurn;

public class Bounded extends VolatileStatus implements EndsAtEndOfTurn, DamagePerTurn, CannotSwitch{
    @Override
    public int endsAfter() {
        return 1;
    }

    @Override
    public int dealDamage(PokemonBattleState pokemon) {
        return pokemon.applyPercentageDamage(0.125f);
    }
}
