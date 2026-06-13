package stima.core.moves;

import stima.core.moves.effects.MoveEffect;
import stima.core.pokemon.Type;

public enum Move {
    THUNDER(Type.ELECTRIC, 110, 70, 12, MoveCategory.SPECIAL, null, MoveTarget.SINGLE, 0),
    HURRICANE(Type.FLYING, 110, 70, 12, MoveCategory.SPECIAL, null, MoveTarget.SINGLE, 0),
    PROTECT(Type.NORMAL, 0, 101, 8, MoveCategory.STATUS, null, MoveTarget.SELF, 4),
    EARTHQUAKE(Type.GROUND, 100, 100, 12, MoveCategory.PHYSICAL, null, MoveTarget.ALL, 0),
    SMACK_DOWN(Type.ROCK, 50, 100, 16, MoveCategory.PHYSICAL, null, MoveTarget.SINGLE, 0),
    SURF(Type.WATER, 90, 100, 16, MoveCategory.SPECIAL, null, MoveTarget.ALL, 0),
    WHIRLPOOL(Type.WATER, 35, 85, 16, MoveCategory.SPECIAL, null, MoveTarget.SINGLE, 0);


    private Type type;
    private int basePower;
    private int accuracy;
    private int pp;
    private MoveCategory category;
    private MoveEffect effect;
    private MoveTarget target;
    private int priority;

    private Move(Type type, 
                 int basePower, 
                 int accuracy, 
                 int pp, 
                 MoveCategory category, 
                 MoveEffect effect, 
                 MoveTarget target, 
                 int priority) {
        this.type = type;
        this.basePower = basePower;
        this.accuracy = accuracy;
        this.pp = pp;
        this.effect = effect;
        this.target = target;
    }

    public Type getType() {
        return type;
    }
}
