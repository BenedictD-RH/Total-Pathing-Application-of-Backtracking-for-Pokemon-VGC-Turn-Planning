package stima.core.abilities;

import stima.core.battle.BattleState;
import stima.core.pokemon.PokemonBattleState;

public abstract class Ability {
    abstract public void applyEffect(PokemonBattleState pokemon, PokemonBattleState opponent, BattleState state);
}
