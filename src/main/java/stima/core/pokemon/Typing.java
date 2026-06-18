package stima.core.pokemon;

public class Typing {
    private Type primaryType;
    private Type secondaryType;
    

    public Typing(Type primaryType, Type secondaryType) {
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }

    public Typing(Type primaryType) {
        this(primaryType, null);
    }

    public Type getPrimaryType() {
        return primaryType;
    }

    public Type getSecondaryType() {
        return secondaryType;
    }

    public float defenseMultipier(Type attackingType) {
        return isSingleType() ? primaryType.defenseMultiplier(attackingType) : 
                                primaryType.defenseMultiplier(attackingType)*secondaryType.defenseMultiplier(attackingType);
    }

    public boolean isSingleType() {
        return secondaryType == null;
    }

    public boolean isType(Type type) {
        return primaryType == type || secondaryType == type;
    }
}
