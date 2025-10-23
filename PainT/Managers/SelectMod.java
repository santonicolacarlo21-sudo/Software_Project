package Managers;

import java.util.ArrayList;

import GeneralShapes.GeneralShape;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

public class SelectMod {

    private Node node;
    private AnchorPane anchorPane;
    private ArrayList<GeneralShape> selectedShape;

    public SelectMod(Node node, AnchorPane anchorPane, ArrayList<GeneralShape> selectedShape) {
        this.node = node;
        this.selectedShape = selectedShape;
        this.anchorPane = anchorPane;
        this.anchorPane.setMinHeight(this.anchorPane.getHeight());
        this.anchorPane.setMinWidth(this.anchorPane.getWidth());
    }
    public Node getNode() {
        return node;
    }

    public void makeSelectable(){
        this.getNode().setOnMouseClicked(e -> {
            if(e.getButton() == MouseButton.SECONDARY){
                selectedShape.clear();
                selectedShape.add((GeneralShape) this.getNode());
            }
            
        });
}
}
