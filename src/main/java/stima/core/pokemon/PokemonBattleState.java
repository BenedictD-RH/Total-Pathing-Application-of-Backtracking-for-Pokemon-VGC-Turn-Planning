package stima.core.pokemon;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stima.core.battle.Action;
import stima.core.battle.BattlePosition;
import stima.core.battle.RNGSeed;
import stima.core.battle.TeamState;
import stima.core.field.FieldEffect;
import stima.core.moves.Move;
import stima.core.moves.MoveState;
import stima.core.properties.ChanceToEnd;
import stima.core.properties.FixedToEnd;
import stima.core.status.Status;
import stima.core.status.nonvolatiles.NonVolatileStatus;

public class PokemonBattleState {
    private Pokemon pokemon;
    private int health;
    private EnumMap<Stat, Integer> statChanges; 
    private List<MoveState> moves;
    private Map<Status, Integer> statuses;
    private BattlePosition position;
    private Typing currTyping;
    private final TeamState team;
    private Action chosenAction;
    private Action lastAction;
    private int consecutiveProtects = 0;

    public PokemonBattleState(Pokemon pokemon, TeamState team) {
        this.pokemon = pokemon;
        this.health = pokemon.getStat(Stat.HP);
        this.statChanges = new EnumMap<>(Stat.class);
        for (Stat stat : Stat.values()) {
            if (stat != Stat.HP) {
                statChanges.put(stat, 0);
            }
        }
        this.moves = new ArrayList<>();
        for (Move move : pokemon.getMoves()) {
            moves.add(new MoveState(move));
        }
        this.statuses = new HashMap<>(); 
        this.position = null;
        this.currTyping = pokemon.getTyping();
        this.team = team;
        this.chosenAction = null;
        this.lastAction = null;
    }

    public PokemonBattleState(PokemonBattleState other, TeamState copiedTeam) {
        this.pokemon = other.pokemon;
        this.health = other.health;
        this.statChanges = other.statChanges.clone();
        this.moves = new ArrayList<>();
        for (MoveState m : other.moves) {
            this.moves.add(new MoveState(m));
        }
        this.statuses = new HashMap<>();
        for (Status s : other.statuses.keySet()) {
            Class<? extends Status> st = s.getClass();
            Status newS = null;
            try {
                newS = st.getDeclaredConstructor(st).newInstance(s);
            } catch (Exception e) {
                try {
                    newS = st.getDeclaredConstructor().newInstance();
                } catch (Exception e2) {

                }
            }
            this.statuses.put(newS, other.statuses.get(s));
        }
        this.position = null;
        this.currTyping = other.getTyping();
        this.team = copiedTeam;
        this.chosenAction = other.chosenAction;
        this.lastAction = other.lastAction;
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

    public int applyDamage(int damage) {
        int damageDone = damage > health ? health : damage;
        reduceHealth(damageDone);
        return damageDone;
    }

    public int applyPercentageDamage(float percentage) {
        return applyDamage(getMaxHealthPercentage(percentage));
    }

    public int applyHeal(int heal) {
        int healingDone = heal > (getHealth() - health) ? (getHealth() - health) : heal;
        reduceHealth(healingDone);
        return healingDone;
    }

    
    public int applyPercentageHeal(float percentage) {
        return applyHeal(getMaxHealthPercentage(percentage));
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

    public int getMaxHealthPercentage(float percentage) {
        return (int) Math.floor(getMaxHealth()*percentage);
    }

    public void addStatChange(Stat stat, int amount) {
        statChanges.put(stat, statChanges.get(stat) + amount);
        if (statChanges.get(stat) > 6) {
            statChanges.put(stat, 6);
        } else if (statChanges.get(stat) < -6) {
            statChanges.put(stat, -6);
        }
    }

    public void addStatus(Status status) {
        for (Status s : statuses.keySet()) {
            if ((s instanceof NonVolatileStatus) && (status instanceof NonVolatileStatus)){
                return;
            }

            if ((s.getClass().isInstance(status))) {
                return;
            }
        }
        System.out.println(pokemon.getName() + " has been afflicted with " + status.getClass().getSimpleName());
        statuses.put(status, 0);
    }

    public void removeStatus(Status status) {
        
    }

    public void updateStatusCounter(Class<? extends FixedToEnd> endCheck, RNGSeed probabilities) {
        for (Status status : statuses.keySet()) {
            if (endCheck.isInstance(status)) {
                FixedToEnd fteStatus = (FixedToEnd) status;
                if (fteStatus.endsAfter() <= statuses.get(status)) {
                    statuses.remove(status);
                } else {
                    statuses.put(status, statuses.get(status) + 1);
                }
                if (fteStatus instanceof ChanceToEnd) {
                    if (probabilities.consume()) {
                        statuses.remove(status);
                    }
                }
            }
        }
    }

    public boolean hasStatus(Status status) {
        return statuses.keySet().stream().filter(e -> status.getClass().isInstance(e)).count() > 0;
    }

    public boolean hasStatusWithProperty(Class<?> property) {
        return statuses.keySet().stream().filter(e -> property.isInstance(e)).count() > 0;
    }

    public List<?> getStatusesWithProperty(Class<?> property) {
        return statuses.keySet().stream().filter(e -> property.isInstance(e)).toList();
    }

    public boolean effectedByFieldEffect(FieldEffect effect) {
        if (position.hasEffect(effect) && effect.isEffected(this)) {
            return true;
        } else if (position.getTeamPosition().hasEffect(effect) && effect.isEffected(this)) {
            return true;
        } else if (position.getTeamPosition().getBattlefield().hasEffect(effect) && effect.isEffected(this)) {
            return true;
        }

        return false;
    }

    public void setBattlePosition(BattlePosition position) {
        this.position = position;
    }

    public BattlePosition getBattlePosition() {
        return position;
    }

    public int calculateStat(Stat stat) {
        float statChangeMult = statChanges.get(stat) > 0 ? (1 + statChanges.get(stat)*0.5f) : 1/(1 - statChanges.get(stat)*0.5f);
        return (int) Math.floor(pokemon.getStat(stat) * statChangeMult);
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public Typing getTyping() {
        return currTyping;
    }

    public boolean isGrounded() {
        return !currTyping.isType(Type.FLYING);
    }

    public TeamState getTeam() {
        return team;
    }

    public List<MoveState> getMoves() {
        return moves;
    }

    public Action getChosenAction() {
        return chosenAction;
    }

    public void setChosenAction(Action action) {
        lastAction = chosenAction;
        chosenAction = action;
    }

    public Action getLastAction() {
        return lastAction;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int movesBefore(PokemonBattleState other) {
        if (this.chosenAction.getPriority(team) != other.chosenAction.getPriority(other.getTeam())) {
            return (this.chosenAction.getPriority(team) > other.chosenAction.getPriority(other.getTeam())) ? -1 : 1;
        }

        if (this.calculateStat(Stat.SPE) != other.calculateStat(Stat.SPE)) {
            return (this.calculateStat(Stat.SPE) > other.calculateStat(Stat.SPE)) ? -1 : 1;
        }

        return 0;
    }

    public int getConsecutiveProtects() {
        return consecutiveProtects;
    }

    public String getStateLog() {
        StringBuilder state = new StringBuilder(pokemon.getName())
                                  .append("(").append(getHealth())
                                  .append("/").append(getMaxHealth())
                                  .append(" HP) ");
        for (Status s : statuses.keySet()) {
            state.append("[" + s.getClass().getSimpleName() + "]");
        }

        if (position != null) {
            for (FieldEffect fe : position.getActiveFieldEffects()) {
                state.append("[" + fe.getClass().getSimpleName() + "]");
            }
        }

        return state.toString();
    }
}
