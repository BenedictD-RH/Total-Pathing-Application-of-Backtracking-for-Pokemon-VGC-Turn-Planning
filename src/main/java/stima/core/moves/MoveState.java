package stima.core.moves;

import java.util.List;

import stima.core.battle.RNGSeed;
import stima.core.moves.effects.ChargeFirst;
import stima.core.pokemon.PokemonBattleState;
import stima.core.pokemon.Stat;
import stima.core.pokemon.Type;
import stima.core.properties.CanFail;
import stima.core.properties.EffectsDamage;
import stima.core.properties.EffectsIncomingDamage;
import stima.core.properties.EffectsOutgoingDamage;
import stima.core.properties.EndsAfterPokemonTurn;
import stima.core.properties.EndsBeforePokemonTurn;
import stima.core.properties.Immobilizing;
import stima.core.status.volatiles.ProtectInvulnerable;

public class MoveState {
    private Move move;
    private int pp;

    public MoveState(Move move) {
        this.move = move;
        this.pp = move.getMaxPP();
    }

    public MoveState(MoveState other) {
        this.move = other.move;
        this.pp = other.pp;
    }

    public Move getMove() {
        return move;
    }

    public int getPP() {
        return pp;
    }

    public int getPriority(PokemonBattleState user) {
        return move.getPriority();
    }

    public MoveCategory getCategory(PokemonBattleState user) {
        return move.getCategory();
    }

    public Type getType(PokemonBattleState user) {
        return move.getType();
    }

    public void reducePP() {
        pp--;
    }

    public int getBasePower(PokemonBattleState user, PokemonBattleState target) {
        return move.getBasePower();
    }

    public int getAccuracy(PokemonBattleState user, PokemonBattleState target) {
        return move.getAccuracy();
    }

    public void useMove(PokemonBattleState user, List<PokemonBattleState> targets, RNGSeed probabilities) {
        int damageDone = 0;
        boolean immobilized = false;
        boolean effectApplied = false;
        if (move.getEffect() instanceof ChargeFirst) {
            move.getEffect().applyEffect(user, targets.get(0), damageDone, probabilities);
            effectApplied = true;
        }
        user.updateStatusCounter(EndsBeforePokemonTurn.class, probabilities);
        for (Object immobile : user.getStatusesWithProperty(Immobilizing.class)) {
            Immobilizing immobiled = (Immobilizing) immobile;
            if (immobiled.immobilizedChance() == 1) {
                immobilized = true;
            } else if (immobiled.immobilizedChance() > 0) {
                immobilized = probabilities.consume();
            }
        }

        for (PokemonBattleState target : targets) {
            boolean miss = move.getAccuracy() < 100 ? probabilities.consume() : false;
            boolean moveSucceeds = true;
            if (move.getEffect() instanceof CanFail) {
                moveSucceeds = ((CanFail) move.getEffect()).moveSucceeds(user, target, move);
                if (!moveSucceeds) System.out.println("But it failed!");
            }

            if (!immobilized && !target.hasStatus(new ProtectInvulnerable()) && !miss && moveSucceeds) {
                float baseDamage = 0;
                if (getCategory(user) == MoveCategory.PHYSICAL) {
                    baseDamage = ((22.0f*getBasePower(user, target)*(user.calculateStat(Stat.ATK)/(float)target.calculateStat(Stat.DEF)))/50.0f) + 2;
                } else if (getCategory(user) == MoveCategory.SPECIAL) {
                    baseDamage = ((22.0f*getBasePower(user, target)*(user.calculateStat(Stat.SPA)/(float)target.calculateStat(Stat.SPD)))/50.0f) + 2;
                }
                for (Object damageBoost : user.getStatusesWithProperty(EffectsOutgoingDamage.class)) {
                    baseDamage *= ((EffectsDamage) damageBoost).damageModifier(move);
                }
                for (Object damageBoost : target.getStatusesWithProperty(EffectsIncomingDamage.class)) {
                    baseDamage *= ((EffectsDamage) damageBoost).damageModifier(move);
                }
                for (Object damageBoost : user.getBattlePosition().getFieldEffectsWithProperty(EffectsOutgoingDamage.class)) {
                    baseDamage *= ((EffectsDamage) damageBoost).damageModifier(move);
                }
                for (Object damageBoost : target.getBattlePosition().getFieldEffectsWithProperty(EffectsIncomingDamage.class)) {
                    baseDamage *= ((EffectsDamage) damageBoost).damageModifier(move);
                }
                for (Object damageBoost : user.getBattlePosition().getTeamPosition().getFieldEffectsWithProperty(EffectsOutgoingDamage.class)) {
                    baseDamage *= ((EffectsDamage) damageBoost).damageModifier(move);
                }
                for (Object damageBoost : target.getBattlePosition().getTeamPosition().getFieldEffectsWithProperty(EffectsIncomingDamage.class)) {
                    baseDamage *= ((EffectsDamage) damageBoost).damageModifier(move);
                }
                for (Object damageBoost : user.getBattlePosition().getTeamPosition().getBattlefield().getFieldEffectsWithProperty(EffectsDamage.class)) {
                    baseDamage *= ((EffectsDamage) damageBoost).damageModifier(move);
                }
                baseDamage *= target.getTyping().defenseMultipier(getType(user));
                baseDamage *= user.getTyping().isType(getType(user)) ? 1.5f : 1;
                baseDamage *= targets.size() > 1 ? 0.75f : 1;
                damageDone = target.applyDamage((int)Math.floor(baseDamage));

                if (!effectApplied && move.getEffect() != null) {
                    move.getEffect().applyEffect(user, target, damageDone, probabilities);
                }
            } else if (target.hasStatus(new ProtectInvulnerable())) {
                System.out.println(target.getPokemon().getName() + " was protected from the attack!");
            } else if (miss) {
                System.out.println(target.getPokemon().getName() + " avoided the attack!");
            }
        }
        if (immobilized) {
            System.out.println(user.getPokemon().getName() + " can't move!");
        }

        

        user.updateStatusCounter(EndsAfterPokemonTurn.class, probabilities);
    }
}
