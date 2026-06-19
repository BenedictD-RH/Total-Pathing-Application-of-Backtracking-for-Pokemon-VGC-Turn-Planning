package stima.core.battle;

import java.util.ArrayList;
import java.util.List;

import stima.algorithms.RNGSeedGenerator;
import stima.core.moves.Move;
import stima.core.moves.MoveCategory;
import stima.core.pokemon.PokemonBattleState;
import stima.core.properties.RestrictsMoveChoice;

public class MoveAction extends Action {
    private int userSlot;
    private int moveSlot;
    private boolean targetOpposingTeam;
    private int targetSlot;

    public MoveAction(int userSlot, 
                    int moveSlot, 
                    boolean targetOpposingTeam, 
                    int targetSlot) {
        this.userSlot = userSlot;
        this.moveSlot = moveSlot;
        this.targetOpposingTeam = targetOpposingTeam;
        this.targetSlot = targetSlot;
    }

    @Override
    public boolean canDoAction(
        TeamState team, 
        TeamState opposingTeam) {
        Move move = team.getPokemonOnSlot(userSlot)
                        .getMoves()
                        .get(moveSlot)
                        .getMove();
        for (Object restriction : 
            team.getPokemonOnSlot(userSlot)
                .getStatusesWithProperty
                (RestrictsMoveChoice.class)) {
            if (!((RestrictsMoveChoice) restriction)
                .canUseMove(
                    move, 
                    team.getPokemonOnSlot(userSlot))) {
                return false;
            }
        }
        if (!team.getPokemonOnSlot(userSlot).isAlive()) {
            return false;
        }

        if (team.getPokemonOnSlot(userSlot)
                .getMoves()
                .get(moveSlot)
                .getPP() <= 0) {
            return false;
        }

        if (team.getPokemonOnSlot(userSlot)
            .getMoves().get(moveSlot)
            .getMove() == Move.FAKE_OUT && 
            team.getPokemonOnSlot(userSlot)
            .getLastAction() != null) {
            return false;
        }

        if (team.getPokemonOnSlot(userSlot)
            .getMoves()
            .get(moveSlot)
            .getMove() == Move.PROTECT && 
            team.getPokemonOnSlot(userSlot)
            .getConsecutiveProtects() > 0) {
            return false;
        }

        switch(move.getTarget()) {
            case SINGLE:
                return targetOpposingTeam;
            case ADJACENT:
            case ALL:
                return targetOpposingTeam && targetSlot == userSlot;
            case FIELD:
            case TEAM:
            case SELF:
                return !targetOpposingTeam && targetSlot == userSlot;
            case ALLY:
                return !targetOpposingTeam && targetSlot != userSlot;
            default:
                return false;
        }

        
    }

    @Override
    public int getPriority(TeamState team) {
        return team.getPokemonOnSlot(userSlot).getMoves().get(moveSlot).getPriority(team.getPokemonOnSlot(userSlot));
    }

    @Override
    public String actionLog(TeamState team, TeamState opposingTeam) {
        List<PokemonBattleState> targets = getMoveActionTargets(team, opposingTeam);
        StringBuilder moveLog = new StringBuilder(team.getPokemonOnSlot(userSlot).getPokemon().getName())
                                    .append(" chooses to use ")
                                    .append(team.getPokemonOnSlot(userSlot).getMoves().get(moveSlot).getMove().name())
                                    .append(" on ").append(getTargetNames(targets));
        return moveLog.toString();
    }

    @Override
    public MoveCategory getCategory(TeamState team) {
        return team.getPokemonOnSlot(userSlot).getMoves().get(moveSlot).getCategory(team.getPokemonOnSlot(userSlot));
    }

    public Move getMove(TeamState team) {
        return team.getPokemonOnSlot(userSlot).getMoves().get(moveSlot).getMove();
    }
    
    public List<PokemonBattleState> getMoveActionTargets(TeamState team, TeamState opposingTeam) {
        List<PokemonBattleState> targets = new ArrayList<>();
        switch(team.getPokemonOnSlot(userSlot).getMoves().get(moveSlot).getMove().getTarget()) {
            case SINGLE :
                TeamState targetTeam = targetOpposingTeam ? opposingTeam : team;
                if (targetTeam.getPokemonOnSlot(targetSlot).isAlive()) {
                    targets.add(targetTeam.getPokemonOnSlot(targetSlot));
                } else if (targetOpposingTeam && !targetTeam.getPokemonOnSlot(targetSlot).isAlive()) {
                    targets.add(targetTeam.getPokemonOnSlot(targetSlot == 1 ? 0 : 1));
                }
                break;
            case ADJACENT :
                if (opposingTeam.getPokemonOnSlot(0).isAlive()) {
                    targets.add(opposingTeam.getPokemonOnSlot(0));
                }
                if (opposingTeam.getPokemonOnSlot(1).isAlive()) {
                    targets.add(opposingTeam.getPokemonOnSlot(1));
                }
                break;
            case ALL :
                if (opposingTeam.getPokemonOnSlot(0).isAlive()) {
                    targets.add(opposingTeam.getPokemonOnSlot(0));
                }
                if (opposingTeam.getPokemonOnSlot(1).isAlive()) {
                    targets.add(opposingTeam.getPokemonOnSlot(1));
                }
                if (team.getPokemonOnSlot(userSlot == 1 ? 0 : 1).isAlive()) {
                    targets.add(team.getPokemonOnSlot(userSlot == 1 ? 0 : 1));
                }
                break;
            case ALLY :
                if (team.getPokemonOnSlot(userSlot == 1 ? 0 : 1).isAlive()) {
                    targets.add(team.getPokemonOnSlot(userSlot == 1 ? 0 : 1));
                }
                break;
            case TEAM :
            case SELF :
            case FIELD :
                targets.add(team.getPokemonOnSlot(userSlot));
            default :
        }

        return targets;
    }

    public List<RNGSeed> getRNGSeedsForAction(BattleState state, TeamState team) {
        return RNGSeedGenerator.generateSeeds(state, userSlot, team.getTeamID(), moveSlot, targetOpposingTeam, targetSlot);
    }

    private String getTargetNames(List<PokemonBattleState> targets) {
        StringBuilder nameLog = new StringBuilder();
        int i = 0;
        for (PokemonBattleState target : targets) {
            if (i == targets.size() - 1 && i != 0) {
                nameLog.append(" and ");
            } else if (i > 0) {
                nameLog.append(", ");
            }
            nameLog.append(target.getPokemon().getName());
            i++;
        }
        return nameLog.toString();
    }

    @Override
    public void commitAction(TeamState team, TeamState opposingTeam, RNGSeed seed) {
        List<PokemonBattleState> targets = getMoveActionTargets(team, opposingTeam);
        
        //System.out.println(moveLog.toString());
        team.getPokemonOnSlot(userSlot).getMoves().get(moveSlot)
            .useMove(team.getPokemonOnSlot(userSlot), targets, seed);
    }
}
