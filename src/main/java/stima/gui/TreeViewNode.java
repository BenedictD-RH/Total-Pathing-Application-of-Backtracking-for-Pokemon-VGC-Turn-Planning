package stima.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import stima.algorithms.TreeNode;

public class TreeViewNode {
    private Node nodeEl;
    private TreeNode actualNode;
    private List<TreeViewNode> children;
    
    public TreeViewNode(TreeNode actualNode) {
        this.actualNode = actualNode;
        this.nodeEl = null;
        this.children = new ArrayList<>();
    }

    public void setElement(Node nodeEl) {
        this.nodeEl = nodeEl;
    }

    public Node getElement() {
        return nodeEl;
    }

    public void addChild(TreeViewNode child) {
        children.add(child);
    }

    public List<TreeViewNode> getChildren() {
        return children;
    }

    public TreeNode getActualNode() {
        return actualNode;
    }
}
