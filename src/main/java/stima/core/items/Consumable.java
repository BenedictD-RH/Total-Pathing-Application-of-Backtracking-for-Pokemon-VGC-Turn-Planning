package stima.core.items;

import stima.core.pokemon.PokemonBattleState;

public abstract class Consumable extends HeldItem {
    @Override
    public void applyEffect(PokemonBattleState pokemon) {
        pokemon.setItem(null);
    }
}
