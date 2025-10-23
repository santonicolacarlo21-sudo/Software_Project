package Managers;


import java.util.ArrayList;

import Main.testController;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

public class DragMod {
    private double mousex = 0;
    private double mousey = 0;
    private Node node;
    private AnchorPane anchorPane;
    private double rightX = 1500;
    private double bottomY = 750;
    private testController test;
    private CareTaker ct;
    private ArrayList<Double> newPos = new ArrayList<>();
     
    public DragMod(Node node, AnchorPane anchorPane, testController testController, CareTaker ct) {
        this.node = node;
        this.anchorPane = anchorPane;
        this.anchorPane.setMinHeight(this.anchorPane.getHeight());
        this.anchorPane.setMinWidth(this.anchorPane.getWidth());
        this.test = testController;
        this.ct = ct;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public double getRightX() {
        return rightX;
    }

    public void setRightX(double rightX) {
        this.rightX += rightX;
    }

    public double getBottomY() {
        return bottomY;
    }

    public void setBottomY(double bottomY) {
        this.bottomY += bottomY;
    }
    
    public boolean isInDragZone(double X, double Y){
        return X > 0 && Y > 150;        
    }
    
    public boolean isOverDragZone(double X, double Y){
        return X < anchorPane.getMinHeight() && Y < anchorPane.getMinHeight();
    }
    public void makeDraggable(){

        this.getNode().setOnMousePressed(eh ->{
            if (eh.getButton() == MouseButton.PRIMARY){
                mousex = eh.getSceneX() - this.getNode().getTranslateX();
                mousey = eh.getSceneY() - this.getNode().getTranslateY();
            }
        });

        this.getNode().setOnDragDetected(eh ->{
            ct.save(test);
        });

        this.getNode().setOnMouseDragged(eh -> {
            if(eh.getButton() == MouseButton.PRIMARY){
                if(isInDragZone(eh.getSceneX(), eh.getSceneY())){
                    //this.getNode().setTranslateX(Math.pow(1/0.75, 2)*eh.getSceneX() - Math.pow(1/0.75, 2)*mousex);
                    //this.getNode().setTranslateY(Math.pow(1/0.75, 2)*eh.getSceneY() - Math.pow(1/0.75, 2)*mousey);
                    this.getNode().setTranslateX(eh.getSceneX() - mousex);
                    this.getNode().setTranslateY(eh.getSceneY() - mousey);
                
                if(!(isOverDragZone(eh.getSceneX(), eh.getSceneY()))){
                    anchorPane.setMinHeight(anchorPane.getHeight() + 700);
                    anchorPane.setMinWidth(anchorPane.getWidth() + 1500);
                    anchorPane.setMaxHeight(anchorPane.getMinHeight() * 100);
                    anchorPane.setMaxWidth(anchorPane.getMinWidth() * 100);
                }}else{
                    this.getNode().setTranslateX(0);
                    this.getNode().setTranslateY(0);      
                }
            }
        });   

    }

}

