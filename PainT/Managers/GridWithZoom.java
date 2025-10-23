package Managers;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class GridWithZoom {

    private AnchorPane anchorPane;
    private CheckBox checkBox;
    private ScrollPane scrollPane;
    private Paint bg1, bg2; 
    private BackgroundFill backgroundFill1, backgroundFill2;
    private Canvas canvas = new Canvas(); 
    private SnapshotParameters sp = new SnapshotParameters();
    private Slider zoomSlider;


    public GridWithZoom(Slider zoomSlider,AnchorPane anchorPane, CheckBox checkBox, ScrollPane scrollPane) {
        this.zoomSlider = zoomSlider;
        this.anchorPane = anchorPane;
        this.checkBox = checkBox;
        this.scrollPane = scrollPane;
        
    }
    
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    
    public void updateGrid(double gridSize) {
        double size = gridSize*zoomSlider.getValue();
        
        if(!this.getCheckBox().isSelected()){
            bg1 = Paint.valueOf("white");
            backgroundFill1 = new BackgroundFill(bg1, null, null);
            anchorPane.setBackground(new Background(backgroundFill1));
        }
        else{
            bg2 = patternTransparent(size);
            backgroundFill2 = new BackgroundFill(bg2, null, null);
            anchorPane.setBackground(new Background(backgroundFill1, backgroundFill2));
        }
    }
    
    public void updateAnchorPane() {
        anchorPane.setPrefWidth(Math.max(scrollPane.getViewportBounds().getWidth(), anchorPane.getBoundsInParent().getMaxX()));
        anchorPane.setPrefHeight(Math.max(scrollPane.getViewportBounds().getHeight(), anchorPane.getBoundsInParent().getMaxY()));
    }

    ImagePattern patternTransparent(double size) {
        canvas.setHeight(size);
        canvas.setWidth(size);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, size, size);
        gc.setFill(Color.BLACK);
        //gc.setLineWidth(2);
        gc.strokeLine(0, 0, 0, size);
        gc.strokeLine(1, 0, size, 0);
        sp.setFill(Color.TRANSPARENT);
        WritableImage image = canvas.snapshot(sp, null);
        return new ImagePattern(image, 0, 0, size, size, false);
      }
    
}
