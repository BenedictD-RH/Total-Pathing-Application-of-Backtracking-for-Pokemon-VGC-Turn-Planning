package stima.core.status.nonvolatiles;

import stima.core.moves.Move;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.DamagePerTurn;
import stima.core.properties.EffectsOutgoingDamage;

public class Burn extends NonVolatileStatus implements DamagePerTurn, EffectsOutgoingDamage {
    @Override
    public float damageModifier(Move move) {
        return 0.5f;
    }

    @Override
    public int dealDamage(PokemonBattleState pokemon) {
        System.out.println(pokemon.getPokemon().getName() + " was hurt by the Burn.");
        return pokemon.applyPercentageDamage(0.0625f);
    }
}
