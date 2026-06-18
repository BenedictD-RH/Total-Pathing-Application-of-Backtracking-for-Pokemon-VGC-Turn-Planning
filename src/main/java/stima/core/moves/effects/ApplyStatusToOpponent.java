package stima.core.moves.effects;



import stima.core.battle.RNGSeed;
import stima.core.pokemon.PokemonBattleState;
import stima.core.status.Status;

public class ApplyStatusToOpponent extends MoveEffect {
    protected final Status appliedStatus;
    
    public ApplyStatusToOpponent(Status status) {
        appliedStatus = status;
    }

    @Override
    public void applyEffect(PokemonBattleState user, PokemonBattleState target, int damageDone, RNGSeed probabilities) {
        target.addStatus(appliedStatus);
    }
}
