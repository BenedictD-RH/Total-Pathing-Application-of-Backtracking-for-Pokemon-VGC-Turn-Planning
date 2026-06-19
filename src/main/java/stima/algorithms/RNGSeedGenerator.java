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
    public static List<RNGSeed> generateSeeds
        (BattleState state, 
        int userSlot, 
        int userTeamIdx, 
        int moveSlot, 
        boolean targetOpposingTeam, 
        int targetSlot) {
        PokemonBattleState user = state.getTeam(userTeamIdx)
                                       .getPokemonOnSlot(userSlot);
        PokemonBattleState target = targetOpposingTeam ? 
                                    state.getOpposingTeam(userTeamIdx)
                                    .getPokemonOnSlot(targetSlot) : 
                                    state.getTeam(userTeamIdx)
                                    .getPokemonOnSlot(targetSlot);
        MoveState move = user.getMoves().get(moveSlot);
        int seedLength = 0;
        List<String> log = new ArrayList<>();

        for (Object chance : 
            user.getStatusesWithProperty
            (RNGDependent.class)) {
            if (chance instanceof Immobilizing) {
                if (((Immobilizing)chance).immobilizedChance() < 1) {
                    seedLength++;
                    log.add(user.getPokemon().getName() + 
                    " immobilized by " +
                     chance.getClass().getSimpleName());
                } else {
                    seedLength++;
                    log.add(user.getPokemon().getName() + 
                    ((RNGDependent)chance).effectLog());
                }
            } 
        }
        MoveAction action = new MoveAction(userSlot, 
                                moveSlot, 
                                targetOpposingTeam, 
                                targetSlot);
        if (move.getAccuracy(user, target) < 100) {
            for (PokemonBattleState targetCandidates : action
                .getMoveActionTargets(state.getTeam(userTeamIdx), 
                state.getOpposingTeam(userTeamIdx))) {
                seedLength++;
                log.add(user.getPokemon().getName() + 
                "'s " + move.getMove().name() + 
                " missed on " + 
                targetCandidates.getPokemon().getName());
            }
        }
        
        if (move.getMove().getEffect() != null) {
            for (PokemonBattleState targetCandidates : 
                action.getMoveActionTargets(state.getTeam(userTeamIdx), 
                state.getOpposingTeam(userTeamIdx))) {
                seedLength += move.getMove().getEffect()
                                  .rngDependentEvents();
                for (String effectLog : move.getMove().getEffect()
                    .rngDependentEventsLog()) {
                    log.add(targetCandidates.getPokemon().getName() + 
                    effectLog);
                }
            }
            
        }

        List<RNGSeed> seedStrings = new ArrayList<>();
        getAllRNGSeeds(seedStrings, "", seedLength, log);
        return seedStrings;
    }
    
    private static void getAllRNGSeeds(List<RNGSeed> seeds, 
                                       String seed, 
                                       int seedLength, 
                                       List<String> seedLog) {
        if (seed.length() == seedLength) {
            seeds.add(new RNGSeed(seed, seedLog));
        } else {
            getAllRNGSeeds(seeds, seed + "0", seedLength, seedLog);
            getAllRNGSeeds(seeds, seed + "1", seedLength, seedLog);
        }
    }
}
