package stima.core.moves;

import java.util.ArrayList;
import java.util.List;

import stima.core.field.weathers.HarshSunlight;
import stima.core.field.weathers.Rain;
import stima.core.moves.effects.ApplyStatChangeToSelf;
import stima.core.moves.effects.ApplyStatusToOpponent;
import stima.core.moves.effects.ApplyStatusToSelf;
import stima.core.moves.effects.ChanceToApplyStatus;
import stima.core.moves.effects.ChangeOnWeather;
import stima.core.moves.effects.DealsRecoil;
import stima.core.moves.effects.MoveEffect;
import stima.core.moves.effects.MultipleEffects;
import stima.core.moves.effects.NeverMissInWeather;
import stima.core.moves.effects.PowerUpByDownedAllies;
import stima.core.moves.effects.SkipChargeTurnInWeather;
import stima.core.moves.effects.SuckerPunch;
import stima.core.pokemon.Stat;
import stima.core.pokemon.Type;
import stima.core.status.nonvolatiles.Burn;
import stima.core.status.nonvolatiles.Paralysis;
import stima.core.status.nonvolatiles.Poison;
import stima.core.status.nonvolatiles.Sleep;
import stima.core.status.volatiles.Bounded;
import stima.core.status.volatiles.Confusion;
import stima.core.status.volatiles.Flinch;
import stima.core.status.volatiles.ProtectInvulnerable;
import stima.core.status.volatiles.ThroatChopped;

public enum Move {
    AQUA_JET(Type.WATER, 40, 100, 20, MoveCategory.PHYSICAL, null, MoveTarget.SINGLE, 1, true),
    CLOSE_COMBAT(Type.FIGHTING, 120, 100, 5, MoveCategory.PHYSICAL, new MultipleEffects(new ApplyStatChangeToSelf(Stat.DEF, -1), new ApplyStatChangeToSelf(Stat.SPD, -1)), MoveTarget.SINGLE, 0, true),
    DIRE_CLAW(Type.POISON, 80, 100, 10, MoveCategory.PHYSICAL, new MultipleEffects(new ChanceToApplyStatus(new Paralysis(), 0.1f), new ChanceToApplyStatus(new Sleep(), 0.1f), new ChanceToApplyStatus(new Poison(), 0.1f)), MoveTarget.SINGLE, 0, true, MoveProperty.SLICE),
    DRAGON_CLAW(Type.DRAGON, 80, 100, 15, MoveCategory.PHYSICAL, null, MoveTarget.SINGLE, 0, true, MoveProperty.SLICE),
    EARTHQUAKE(Type.GROUND, 100, 100, 12, MoveCategory.PHYSICAL, null, MoveTarget.ALL),
    FAKE_OUT(Type.NORMAL, 40, 100, 10, MoveCategory.PHYSICAL, new ApplyStatusToOpponent(new Flinch()), MoveTarget.SINGLE, 3, true),
    HEAT_WAVE(Type.FIRE, 95, 100, 10, MoveCategory.SPECIAL, new ChanceToApplyStatus(new Burn(), 30), MoveTarget.ADJACENT),
    HURRICANE(Type.FLYING, 110, 70, 12, MoveCategory.SPECIAL, new MultipleEffects(new ChanceToApplyStatus(new Confusion(), 0.3f), new NeverMissInWeather(new Rain(-1))), MoveTarget.SINGLE),
    IRON_HEAD(Type.STEEL, 80, 100, 10, MoveCategory.PHYSICAL, new ChanceToApplyStatus(new Flinch(), 0.2f), MoveTarget.SINGLE, 0, true),
    KOWTOW_CLEAVE(Type.DARK, 85, 101, 10, MoveCategory.PHYSICAL, null, MoveTarget.SINGLE, 0, true, MoveProperty.SLICE),
    LAST_RESPECTS(Type.GHOST, 50, 100, 10, MoveCategory.PHYSICAL, new PowerUpByDownedAllies(), MoveTarget.SINGLE),
    PROTECT(Type.NORMAL, 0, 101, 8, MoveCategory.STATUS, new ApplyStatusToSelf(new ProtectInvulnerable()), MoveTarget.SELF, 4, false),
    ROCK_SLIDE(Type.ROCK, 75, 90, 10, MoveCategory.PHYSICAL, new ChanceToApplyStatus(new Flinch(), 0.3f), MoveTarget.ADJACENT),
    SMACK_DOWN(Type.ROCK, 50, 100, 16, MoveCategory.PHYSICAL, null, MoveTarget.SINGLE),
    SOLAR_BEAM(Type.GRASS, 120, 100, 10, MoveCategory.SPECIAL, new SkipChargeTurnInWeather(new HarshSunlight(-1)), MoveTarget.SINGLE),
    SUCKER_PUNCH(Type.DARK, 70, 100, 5, MoveCategory.PHYSICAL, new SuckerPunch(), MoveTarget.SINGLE, 2, true),
    SURF(Type.WATER, 90, 100, 16, MoveCategory.SPECIAL, null, MoveTarget.ALL),
    THROAT_CHOP(Type.DARK, 80, 100, 15, MoveCategory.PHYSICAL, new ApplyStatusToOpponent(new ThroatChopped()), MoveTarget.SINGLE, 0, true),
    THUNDER(Type.ELECTRIC, 110, 70, 12, MoveCategory.SPECIAL, new MultipleEffects(new ChanceToApplyStatus(new Paralysis(), 0.3f), new NeverMissInWeather(new Rain(-1))), MoveTarget.SINGLE),
    WAVE_CRASH(Type.WATER, 120, 100, 10, MoveCategory.PHYSICAL, new DealsRecoil(1/3.0f), MoveTarget.SINGLE, 0, true),
    WEATHER_BALL(Type.NORMAL, 50, 100, 10, MoveCategory.SPECIAL, new ChangeOnWeather(), MoveTarget.SINGLE),
    WHIRLPOOL(Type.WATER, 35, 85, 16, MoveCategory.SPECIAL, new ApplyStatusToOpponent(new Bounded()), MoveTarget.SINGLE);


    private final Type type;
    private final int basePower;
    private final int accuracy;
    private final int pp;
    private final MoveCategory category;
    private final MoveEffect effect;
    private final MoveTarget target;
    private final int priority;
    private final List<MoveProperty> properties;
    private final boolean makesContact;

    private Move(Type type, 
                 int basePower, 
                 int accuracy, 
                 int pp, 
                 MoveCategory category, 
                 MoveEffect effect, 
                 MoveTarget target, 
                 int priority,
                 boolean makesContact,
                 MoveProperty ... properties) {
        this.type = type;
        this.basePower = basePower;
        this.accuracy = accuracy;
        this.pp = pp;
        this.category = category;
        this.effect = effect;
        this.target = target;
        this.priority = priority;
        this.makesContact = makesContact;
        this.properties = new ArrayList<>(List.of(properties));
    }

    private Move(Type type, int basePower, int accuracy, int pp, MoveCategory category, MoveEffect effect, MoveTarget target) {
        this(type, basePower, accuracy, pp, category, effect, target, 0, false);
    }

    public Type getType() {
        return type;
    }

    public int getBasePower() {
        return basePower;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public MoveCategory getCategory() {
        return category;
    }

    public int getMaxPP() {
        return pp;
    }

    public int getPriority() {
        return priority;
    }

    public MoveTarget getTarget() {
        return target;
    }

    public boolean hasProperty(MoveProperty property) {
        return properties.contains(property);
    }

    public boolean targetsOpponents() {
        return target == MoveTarget.SINGLE || target == MoveTarget.ADJACENT || target == MoveTarget.ALL;
    }

    public boolean doesMakeContact() {
        return makesContact;
    }

    public MoveEffect getEffect() {
        return effect;
    }
}
