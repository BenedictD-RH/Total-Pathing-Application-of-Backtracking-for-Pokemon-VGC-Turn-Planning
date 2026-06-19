package stima.algorithms;

import java.util.List;

import stima.core.battle.Action;
import stima.core.battle.BattleState;
import stima.core.battle.RNGSeed;
import stima.core.battle.TurnManager;

public class TreeBuilder {
    private int winConditionFound = 0;
    public TreeNode buildActionNodes(BattleState currentState, int currentDepth, int userSlot, int teamID, int maxDepth, String edgeLog) {
        TreeNode node = new TreeNode(currentState, currentDepth, userSlot == 0 && teamID == 0, edgeLog);
        if (currentState.teamWins(0)) {
            System.out.println("Win Condition Found : " + ++winConditionFound);
            return node;
        } else if (currentState.teamWins(1) || currentDepth >= maxDepth) {
            return null;
        }
        List<Action> actions = ActionManager.getPossibleActions(currentState, userSlot, teamID);
        for (Action action : actions) {
            BattleState nexState = new BattleState(currentState);
            nexState.getTeam(teamID).getPokemonOnSlot(userSlot).setChosenAction(action);
            TreeNode child = null;
            String nextLog = action.actionLog(currentState.getTeam(teamID), currentState.getOpposingTeam(teamID));
            if (userSlot == 0 && teamID == 0) child = buildActionNodes(nexState, currentDepth, 1, 0, maxDepth, nextLog);
            else if (userSlot == 1 && teamID == 0) child = buildActionNodes(nexState, currentDepth, 0, 1, maxDepth, nextLog);
            else if (userSlot == 0 && teamID == 1) child = buildActionNodes(nexState, currentDepth, 1, 1, maxDepth, nextLog);
            else if (userSlot == 1 && teamID == 1) child = buildOrderNodes(nexState, currentDepth, 0, maxDepth, nextLog);
            if (child != null) {
                node.addChild(child);
            }
        }
        if (actions.size() == 0) {
            BattleState nexState = new BattleState(currentState);
            TreeNode child = null;
            String nextLog = "";
            if (userSlot == 0 && teamID == 0) child = buildActionNodes(nexState, currentDepth, 1, 0, maxDepth, nextLog);
            else if (userSlot == 1 && teamID == 0) child = buildActionNodes(nexState, currentDepth, 0, 1, maxDepth, nextLog);
            else if (userSlot == 0 && teamID == 1) child = buildActionNodes(nexState, currentDepth, 1, 1, maxDepth, nextLog);
            else if (userSlot == 1 && teamID == 1) child = buildOrderNodes(nexState, currentDepth, 0, maxDepth, nextLog);
            if (child != null) {
                node.addChild(child);
            }
        }

        return node.getChildren().size() > 0 ? node : null;
    }


    public TreeNode buildOrderNodes(BattleState currentState, int currentDepth, int order, int maxDepth, String edgeLog) {
        TreeNode node = new TreeNode(currentState, currentDepth, false, edgeLog);
        if (currentState.teamWins(0)) {
            System.out.println("Win Condition Found : " + ++winConditionFound);
            return node;
        } else if (currentState.teamWins(1) || currentDepth >= maxDepth) {
            return null;
        }
        TurnManager turnManager = new TurnManager(currentState);
        if (order == 0) {
            turnManager.turnStart();
        }
        while (!turnManager.getPokemon(turnManager.getTurnOrder().get(order)).isAlive() && order < 3) {
            order++;
        }
        List<RNGSeed> seeds = turnManager.getRNGSeedsForTurnOrder(order);
        for (RNGSeed seed : seeds) {
            turnManager = new TurnManager(new BattleState(currentState));
            turnManager.doTurn(order, seed);
            TreeNode child = null;
            String nextLog = seed.getSeedLog();
            if (order < 3) {
                child = buildOrderNodes(turnManager.getCurrentState(), currentDepth, order + 1, maxDepth, nextLog);
            } else {
                turnManager.turnEnd();
                child = buildActionNodes(turnManager.getCurrentState(), currentDepth + 1, 0, 0, maxDepth, nextLog);
            }

            if (child != null) {
                node.addChild(child);
            }
        }
        if (seeds.size() == 0) {
            turnManager = new TurnManager(new BattleState(currentState));
            turnManager.doTurn(order, null);
            TreeNode child = null;
            if (order < 3) {
                child = buildOrderNodes(turnManager.getCurrentState(), currentDepth, order + 1, maxDepth, "");
            } else {
                turnManager.turnEnd();
                child = buildActionNodes(turnManager.getCurrentState(), currentDepth + 1, 0, 0, maxDepth, "");
            }
            if (child != null) {
                node.addChild(child);
            }
        }
        return node.getChildren().size() > 0 ? node : null;
    }

    public TreeNode buildRoot(BattleState currentState, int maxDepth) {
        return buildActionNodes(currentState, 0, 0, 0, maxDepth, "");
    }
}
