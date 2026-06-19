package stima.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import stima.algorithms.TreeNode;

public class TreeView {
    public static void showTree(Stage primaryStage, TreeNode root) {
        Pane lineOverlay = new Pane();

        TreeViewNode rootTreeViewNode = new TreeViewNode(root);
        
        
        StackPane treeStructure = createNodeView(root, rootTreeViewNode);
        
        
        StackPane visualRootCanvas = new StackPane(lineOverlay, treeStructure);
        
        
        ScrollPane scroll = new ScrollPane(visualRootCanvas);
        scroll.setPannable(true);
        
        Scene scene = new Scene(scroll, 800, 600);
        primaryStage.setTitle("JavaFX Tree Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();

        Platform.runLater(() -> drawEdges(lineOverlay, rootTreeViewNode));
    }

    private static StackPane createNodeView(TreeNode node, TreeViewNode viewNode) {
        StackPane stack = new StackPane();
        StackPane rootNode = createVisualNode(node.isActionNode(), Integer.toString(node.getNodeID()), node.getEdgeLog() + "\n" + node.getBattleState().getBattleStateLog());
        viewNode.setElement(rootNode);
        
        HBox hBox = new HBox(rootNode);
        hBox.setPadding(new Insets(3, 20, 3, 20));
        
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(3, 20, 3, 20));
        
        for (TreeNode child : node.getChildren()) {
            TreeViewNode childViewNode = new TreeViewNode(child);
            StackPane childNode = createNodeView(child, childViewNode);
            viewNode.addChild(childViewNode);
            vBox.getChildren().add(childNode);
        }
        
        hBox.getChildren().add(vBox);
        stack.getChildren().add(hBox);
        return stack;
    }

    private static void drawEdges(Pane parentPane, TreeViewNode treeViewNode) {
        for (TreeViewNode child : treeViewNode.getChildren()) {
            connectNodes(parentPane, treeViewNode.getElement(), child.getElement(), child.getActualNode().getEdgeLog());
            drawEdges(parentPane, child);
        }
    }

    public static void connectNodes(Pane drawingPane, Node nodeA, Node nodeB, String tooltipString) {
        double localCenterXA = nodeA.getBoundsInLocal().getWidth() / 2;
        double localCenterYA = nodeA.getBoundsInLocal().getHeight() / 2;
        Point2D scenePtA = nodeA.localToScene(localCenterXA, localCenterYA);
        Point2D panePtA = drawingPane.sceneToLocal(scenePtA);
        
        double localCenterXB = nodeB.getBoundsInLocal().getWidth() / 2;
        double localCenterYB = nodeB.getBoundsInLocal().getHeight() / 2;
        Point2D scenePtB = nodeB.localToScene(localCenterXB, localCenterYB);
        Point2D panePtB = drawingPane.sceneToLocal(scenePtB);

        Line visibleLine = new Line(panePtA.getX(), panePtA.getY(), panePtB.getX(), panePtB.getY());
        visibleLine.setStroke(Color.BLACK);
        visibleLine.setStrokeWidth(1.5);
        visibleLine.setMouseTransparent(true); 

    
        Line hitboxLine = new Line(panePtA.getX(), panePtA.getY(), panePtB.getX(), panePtB.getY());
        hitboxLine.setStroke(Color.TRANSPARENT);
        hitboxLine.setStrokeWidth(12.0); 


        Tooltip edgeTooltip = new Tooltip(tooltipString);
        edgeTooltip.setStyle(
            "-fx-background-color: #2c3e50;" +  
            "-fx-text-fill: #ffffff;" +        
            "-fx-font-size: 11px;" +            
            "-fx-padding: 6px;" +               
            "-fx-background-radius: 4px;"      
        );
        edgeTooltip.setShowDelay(Duration.millis(50));
        Tooltip.install(hitboxLine, edgeTooltip);

        hitboxLine.setOnMouseEntered(e -> visibleLine.setStroke(Color.RED));
        hitboxLine.setOnMouseExited(e -> visibleLine.setStroke(Color.BLACK));

        drawingPane.getChildren().addAll(visibleLine, hitboxLine);
    }

    private static StackPane createVisualNode(boolean actionNode, String text, String tooltipString) {
        Circle circle = new Circle(15, actionNode ? Color.LIGHTBLUE : Color.GOLDENROD);
        circle.setStroke(Color.DARKBLUE);
        
        String nodeLabel = text.contains(":") ? text.substring(text.lastIndexOf(":") + 1).trim() : text;
        Label label = new Label(nodeLabel);
        
        StackPane nodePane = new StackPane(circle, label);

        Tooltip hoverBox = new Tooltip("Details:\n" + tooltipString);
        
        hoverBox.setStyle(
            "-fx-background-color: #333333;" +  
            "-fx-text-fill: #ffffff;" +        
            "-fx-font-size: 12px;" +            
            "-fx-padding: 8px;" +               
            "-fx-background-radius: 4px;"      
        );
        
        hoverBox.setShowDelay(Duration.millis(50));

    
        Tooltip.install(nodePane, hoverBox);

        return nodePane;
    }
}
