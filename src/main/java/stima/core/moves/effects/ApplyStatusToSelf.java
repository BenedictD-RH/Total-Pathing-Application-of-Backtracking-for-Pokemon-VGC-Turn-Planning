package stima.core.moves.effects;



import stima.core.battle.RNGSeed;
import stima.core.pokemon.PokemonBattleState;
import stima.core.status.Status;

public class ApplyStatusToSelf extends MoveEffect {
    protected final Status appliedStatus;
    
    public ApplyStatusToSelf(Status status) {
        appliedStatus = status;
    }

    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities) {
        user.addStatus(appliedStatus);
    }
}
