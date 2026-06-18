package stima.core.field.hazards;

import stima.core.field.TeamwideFieldEffect;
import stima.core.pokemon.PokemonBattleState;

public abstract class EntryHazard extends TeamwideFieldEffect {
    private final int maxLayers;
    private final boolean grounded;
    private int currentLayer;
    
    public EntryHazard(int maxLayers, boolean grounded) {
        this.maxLayers = maxLayers;
        this.grounded = grounded;
        this.currentLayer = 1;
    }

    abstract public void applyEffect(PokemonBattleState pokemon);

    public int getMaxLayers() {
        return maxLayers;
    }

    public int getCurrentLayer() {
        return currentLayer;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public void addLayer() {
        currentLayer++;
    }

    @Override
    public boolean isEffected(PokemonBattleState pokemon) {
        return (pokemon.isGrounded() && grounded) || !grounded;
    }
}
