package stima.core.battle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import stima.core.field.FieldEffect;

public abstract class FieldState {
    private final Map<FieldEffect, Integer> fieldEffects;

    public FieldState() {
        this.fieldEffects = new HashMap<>();
    }

    protected FieldState(FieldState other) {
        this.fieldEffects = new HashMap<>();
        for (FieldEffect fe : other.fieldEffects.keySet()) {
            Class<? extends FieldEffect> st = fe.getClass();
            FieldEffect newS = null;
            try {
                newS = st.getDeclaredConstructor(st).newInstance(fe);
            } catch (Exception e) {
                try {
                    newS = st.getDeclaredConstructor().newInstance();
                } catch (Exception e2) {

                }
            }
            this.fieldEffects.put(newS, other.fieldEffects.get(fe));
        }
    }

    public void addFieldEffect(FieldEffect effect) {
        fieldEffects.put(effect, 0);
    }
    
    public void updateFieldEffectCounter() {
        
    }

    public void removeFieldEffect(FieldEffect effect) {
        fieldEffects.remove(effect);
    }

    public boolean hasEffect(FieldEffect effect) {
        return fieldEffects.keySet().stream().filter(e -> effect.getClass().isInstance(e)).count() > 0;
    }

    public Set<FieldEffect> getActiveFieldEffects() {
        return fieldEffects.keySet();
    }

    public List<?> getFieldEffectsWithProperty(Class<?> property) {
        return fieldEffects.keySet().stream().filter(e -> property.isInstance(e)).toList();
    }
}
