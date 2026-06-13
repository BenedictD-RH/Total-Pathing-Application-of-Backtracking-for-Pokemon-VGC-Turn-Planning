package stima.core.pokemon;

import java.util.EnumMap;

public class PokemonBattleState {
    private Pokemon pokemon;
    private int health;
    private EnumMap<Stat, Integer> statChanges; 

    public PokemonBattleState(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.health = pokemon.getStat(Stat.HP);
        this.statChanges = new EnumMap<>(Stat.class);
        for (Stat stat : Stat.values()) {
            if (stat != Stat.HP) {
                statChanges.put(stat, 0);
            }
        }
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public void addHealth(int heal) {
        health += heal;
        if (health > getMaxHealth()) {
            health = getMaxHealth();
        }
    }

    public int getMaxHealth() {
        return pokemon.getStat(Stat.HP);
    }

    public void addStatChange(Stat stat, int amount) {
        statChanges.put(stat, statChanges.get(stat) + amount);
        if (statChanges.get(stat) > 6) {
            statChanges.put(stat, 6);
        } else if (statChanges.get(stat) < -6) {
            statChanges.put(stat, -6);
        }
    }

    public int calculateStat(Stat stat) {
        float statChangeMult = statChanges.get(stat) > 0 ? (1 + statChanges.get(stat)*0.5f) : 1/(1 - statChanges.get(stat)*0.5f);
        return (int) Math.floor(pokemon.getStat(stat) * statChangeMult);
    }
}
