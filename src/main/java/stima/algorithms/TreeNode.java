package stima.algorithms;

import java.util.ArrayList;
import java.util.List;

import stima.core.battle.BattleState;

public class TreeNode {
    private static int currID = 0;
    private int nodeID;
    private int depth;
    private BattleState state;
    private List<TreeNode> children;
    private boolean checkpointNode;
    private String edgeLog;

    public TreeNode(BattleState state, int depth, boolean checkpointNode, String edgeLog) {
        this.nodeID = currID++;
        this.state = state;
        this.depth = depth;
        this.children = new ArrayList<>();
        this.checkpointNode = checkpointNode;
        this.edgeLog = edgeLog;
    }

    public TreeNode(TreeNode other) {
        this.nodeID = other.nodeID;
        this.state = other.state;
        this.depth = other.depth;
        this.children = new ArrayList<>();
        this.checkpointNode = other.checkpointNode;
    }

    public int getNodeID() {
        return nodeID;
    }

    public int getNodeDepth() {
        return depth;
    }

    public BattleState getBattleState() {
        return state;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    private static void flattenTree(TreeNode root, TreeNode lastCheckpoint, TreeNode newTree) {
        if (root.checkpointNode) {
            TreeNode newCheckpoint;
            if (lastCheckpoint == null) {
                newCheckpoint = newTree;
            } else {
                newCheckpoint = new TreeNode(root);
                lastCheckpoint.addChild(newCheckpoint);
            }
            for (TreeNode child : root.children) {
                flattenTree(child, newCheckpoint, newTree);
            }
        } else {
            for (TreeNode child : root.children) {
                flattenTree(child, lastCheckpoint, newTree);
            }
        }
    }

    public TreeNode getFlattenedTree() {
        TreeNode newTree = new TreeNode(this);
        flattenTree(this, null, newTree);
        return newTree;
    }
    
    public void printTree() {
        String space = String.valueOf("  ").repeat(depth);
        System.out.println(space + nodeID);
        for (TreeNode child : children) {
            child.printTree();
        }
    }

    public String getEdgeLog() {
        return edgeLog;
    }
}
