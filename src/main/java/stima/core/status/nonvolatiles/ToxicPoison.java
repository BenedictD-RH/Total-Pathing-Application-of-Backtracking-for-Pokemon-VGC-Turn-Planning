package stima.core.status.nonvolatiles;

import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.DamagePerTurn;
import stima.core.properties.Resettable;

public class ToxicPoison extends NonVolatileStatus implements DamagePerTurn, Resettable {
    private float currentDamagePercentage;

    public ToxicPoison() {
        this.currentDamagePercentage = 0.0625f;
    }

    public ToxicPoison(ToxicPoison other) {
        this.currentDamagePercentage = other.currentDamagePercentage;
    }

    @Override
    public int dealDamage(PokemonBattleState pokemon) {
        int damageDone =  pokemon.applyPercentageDamage(currentDamagePercentage);
        currentDamagePercentage*=2;
        return damageDone;
    }

    @Override
    public void reset() {
        currentDamagePercentage = 0.0625f;
    }
}   
