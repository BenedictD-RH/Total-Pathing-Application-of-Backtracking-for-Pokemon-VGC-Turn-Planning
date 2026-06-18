package stima.algorithms;

import java.util.ArrayList;
import java.util.List;

import stima.core.battle.BattleState;
import stima.core.battle.MoveAction;
import stima.core.battle.RNGSeed;
import stima.core.moves.MoveState;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.Immobilizing;
import stima.core.properties.RNGDependent;

public class RNGSeedGenerator {
    public static List<RNGSeed> generateSeeds(BattleState state, int userSlot, int userTeamIdx, int moveSlot, boolean targetOpposingTeam, int targetSlot) {
        PokemonBattleState user = state.getTeam(userTeamIdx).getPokemonOnSlot(userSlot);
        PokemonBattleState target = targetOpposingTeam ? state.getOpposingTeam(userTeamIdx).getPokemonOnSlot(targetSlot) : state.getTeam(userTeamIdx).getPokemonOnSlot(targetSlot);
        MoveState move = user.getMoves().get(moveSlot);
        int seedLength = 0;

        for (Object chance : user.getStatusesWithProperty(RNGDependent.class)) {
            if (chance instanceof Immobilizing) {
                if (((Immobilizing)chance).immobilizedChance() < 1) {
                    seedLength++;
                } else {
                    seedLength++;
                }
            }
        }
        MoveAction action = new MoveAction(userSlot, moveSlot, targetOpposingTeam, targetSlot);
        seedLength += (move.getAccuracy(user, target) < 100 ? 1 : 0)*action.getMoveActionTargets(state.getTeam(userTeamIdx), state.getOpposingTeam(userTeamIdx)).size();
        if (move.getMove().getEffect() != null) {
            seedLength += move.getMove().getEffect().rngDependentEvents();
        }

        List<RNGSeed> seedStrings = new ArrayList<>();
        getAllRNGSeeds(seedStrings, "", seedLength);
        return seedStrings;
    }
    
    private static void getAllRNGSeeds(List<RNGSeed> seeds, String seed, int seedLength) {
        if (seed.length() == seedLength) {
            seeds.add(new RNGSeed(seed));
        } else {
            getAllRNGSeeds(seeds, seed + "0", seedLength);
            getAllRNGSeeds(seeds, seed + "1", seedLength);
        }
    }
}
