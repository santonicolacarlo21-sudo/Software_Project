package Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Commands.BackCommand;
import Commands.ChangeBackgroundColorCommand;
import Commands.ChangeBorderColorCommand;
import Commands.CopyCommand;
import Commands.DeleteCommand;
import Commands.ExportCommand;
import Commands.FrontCommand;
import Commands.MirrorCommand;
import Commands.OpenCommand;
import Commands.PasteCommand;
import Commands.ResizeCommand;
import Commands.RotateCommand;
import Commands.SaveCommand;
import Commands.StretchCommand;
import Creators.CreatorElipse;
import Creators.CreatorLine;
import Creators.CreatorPolygon;
import Creators.CreatorRectangle;
import Creators.CreatorShape;
import Creators.CreatorText;
import GeneralShapes.GeneralShape;
import Managers.CareTaker;
import Managers.DragMod;
import Managers.GridWithZoom;
import Managers.SelectMod;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Scale;

public class testController implements Initializable{

    @FXML
    private CheckBox checkGrid;
    
    @FXML
    private ContextMenu contextMenu;

    @FXML
    private Slider zoomSlider;

    @FXML
    private Label labelFontSize;

    @FXML
    private MenuItem copyItem, cutItem, pasteItem, deleteBtn, saveItem, loadItem, newItem;

    @FXML
    private Button backgroundColorValue, borderColorValue;

    @FXML
    private Button blackBtn, blueBtn, cyanBtn, greenBtn, orangeBtn, pinkBtn, purpleBtn, redBtn, whiteBtn, yellowBtn;

    @FXML
    private TextField degreeTextField, resizeTextField, textShapeField, drawWidthTf, drawHeightTf;

    @FXML
    private AnchorPane drawPane;

    @FXML
    private MenuButton menuGrid;
    
    @FXML
    private Button polyButton, rectBtn, segmentBtn, ellipsesBtn;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField stretchX, stretchY;
    

    CreatorShape shape ;
    ArrayList<GeneralShape> selectedShape = new ArrayList<>();
    GeneralShape copiedShape;
    ArrayList<Double> selectedPositions = new ArrayList<>();
    boolean polygon = false, dragging = false;
    SelectMod selecter;
    Color border = Color.BLACK;
    Color background = Color.WHITE;
    Boolean borderColorSelected = false;
    Boolean backgroundColorSelected = false;
    int counter = 0;
    Group marker = new Group();
    ArrayList<Double> positionsPaste = new ArrayList<>();
    String textShape = null;
    int gridSize = 20;
    CareTaker ct = new CareTaker();
    int textFontSize = 20;
    double oldScaleX, oldScaleY, oldWidth, oldHeight;
    SimpleBooleanProperty shapeIsSelected = new SimpleBooleanProperty(true) ;
    GridWithZoom gwz;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gwz = new GridWithZoom(zoomSlider, drawPane, checkGrid, scrollPane);
        
        menuGrid.textProperty().addListener((v,o,n)->{
            if(n.equals("Small")){
                gridSize = 20;
            }
            else if(n.equals("Medium"))
                gridSize = 30;
            else 
                gridSize = 40;
            gwz.updateGrid(gridSize);
        });

        drawHeightTf.setText(String.valueOf(drawPane.getPrefHeight()));
        drawWidthTf.setText(String.valueOf(drawPane.getPrefWidth()));
        stretchY.setText("100");
        stretchX.setText("100");

        oldScaleX = drawPane.getScaleX();
        oldScaleY = drawPane.getScaleY();
        oldWidth = drawPane.getMinWidth();
        oldHeight = drawPane.getMinHeight();
        zoomSlider.valueProperty().addListener((v, o, n) ->{
            Scale sc = new Scale();
            if(n.doubleValue() != 1){
                gwz.updateGrid(gridSize);
                sc.setX((oldScaleX)*n.doubleValue());
                sc.setY((oldScaleY)*n.doubleValue());
                sc.setPivotX(0);
                sc.setPivotY(0);
                drawPane.getTransforms().clear();
                drawPane.getTransforms().add(sc);
                //drawPane.setScaleX((oldScaleX)*n.doubleValue());
                //drawPane.setScaleY((oldScaleY)*n.doubleValue());
                //Node node = drawPane.getChildren().get(0);
                //node.setTranslateX(-1*drawPane.getWidth()/2);     
                //node.setTranslateY(-1*drawPane.getHeight()/2);

            }else
                drawPane.getTransforms().clear();
        });
        
        copyItem.setAccelerator(KeyCombination.valueOf("shortcut+c"));
        pasteItem.setAccelerator(KeyCombination.valueOf("shortcut+v"));
        cutItem.setAccelerator(KeyCombination.valueOf("shortcut+x"));

        for (MenuItem mi : contextMenu.getItems()){
            if(mi.getText().equals("Paste")){
                mi.setDisable(true);
                continue;
            }
            mi.disableProperty().bind(shapeIsSelected);
            
        }

        newItem.setAccelerator(KeyCombination.valueOf("shortcut+n"));
        saveItem.setAccelerator(KeyCombination.valueOf("shortcut+s"));
        loadItem.setAccelerator(KeyCombination.valueOf("shortcut+o"));
    }
    
    /** 
     * @param newColor
     * Change the shape background color with the color specified by @param
     */
    public void changeBackground(Color newColor){
        Color temp = background;
        background = newColor;
        ChangeBackgroundColorCommand newBackground = new ChangeBackgroundColorCommand(selectedShape.get(0), background);
        ct.save(this);
        newBackground.execute();
        background = temp;
    }

    
    /** 
     * @param newColor
     * Change the shape background color with the color specified by @param
     */
    public void changeBorder(Color newColor){
        Color temp = border;
        border = newColor;
        ChangeBorderColorCommand newBorder = new ChangeBorderColorCommand(selectedShape.get(0), border);
        ct.save(this);
        newBorder.execute();        
        border = temp;
    }

    
    /** 
     * @param event
     * Action function that get called when the Black color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Background.
     */
    @FXML
    void changeBackgroundToBlack(ActionEvent event) {
        changeBackground(Color.BLACK);
    }

    
    /** 
     * @param event
     * Action function that get called when the Blue color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Background.
     */
    @FXML
    void changeBackgroundToBlue(ActionEvent event) {
        changeBackground(Color.BLUE);
    }

    
    /** 
     * @param event
     * Action function that get called when the Cyan color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Background.
     */
    @FXML
    void changeBackgroundToCyan(ActionEvent event) {
        changeBackground(Color.CYAN);
    }

    
    /** 
     * @param event
     * Action function that get called when the Green color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Background.
     */
    @FXML
    void changeBackgroundToGreen(ActionEvent event) {
        changeBackground(Color.GREEN);
    }

    
    /** 
     * @param event
     * Action function that get called when the Orange color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Background.
     */
    @FXML
    void changeBackgroundToOrange(ActionEvent event) {
        changeBackground(Color.ORANGE);
    }

    
    /** 
     * @param event
     * Action function that get called when the Pink color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Background.
     */
    @FXML
    void changeBackgroundToPink(ActionEvent event) {
        changeBackground(Color.PINK);
    }

    
    /** 
     * @param event
     * Action function that get called when the Purple color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Background.
     */
    @FXML
    void changeBackgroundToPurple(ActionEvent event) {
        changeBackground(Color.PURPLE);
    }

    
    /** 
     * @param event
     * Action function that get called when the Red color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Background.
     */
    @FXML
    void changeBackgroundToRed(ActionEvent event) {
        changeBackground(Color.RED);
    }

    
    /** 
     * @param event
     * Action function that get called when the White color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Background.
     */
    @FXML
    void changeBackgroundToWhite(ActionEvent event) {
        changeBackground(Color.WHITE);
    }

    
    /** 
     * @param event
     * Action function that get called when the Yellow color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Background.
     */
    @FXML
    void changeBackgroundToYellow(ActionEvent event) {
        changeBackground(Color.YELLOW);
    }

    
    /** 
     * @param event
     * Action function that get called when the Black color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Border.
     */
    @FXML
    void changeBorderToBlack(ActionEvent event) {
        changeBorder(Color.BLACK);
    }

    
    /** 
     * @param event
     * Action function that get called when the Blue color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Border.
     */
    @FXML
    void changeBorderToBlue(ActionEvent event){
        changeBorder(Color.BLUE);
    }

    
    /** 
     * @param event
     * Action function that get called when the Cyan color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Border.
     */
    @FXML
    void changeBorderToCyan(ActionEvent event){
        changeBorder(Color.CYAN);
    }

    
    /** 
     * @param event
     * Action function that get called when the Green color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Border.
     */
    @FXML
    void changeBorderToGreen(ActionEvent event){
        changeBorder(Color.GREEN);
    }

    
    /** 
     * @param event
     * Action function that get called when the Orange color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Border.
     */
    @FXML
    void changeBorderToOrange(ActionEvent event){
        changeBorder(Color.ORANGE);
    }

    
    /** 
     * @param event
     * Action function that get called when the Pink color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Border.
     */
    @FXML
    void changeBorderToPink(ActionEvent event){
        changeBorder(Color.PINK);
    }

    
    /** 
     * @param event
     * Action function that get called when the Purple color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Border.
     */
    @FXML
    void changeBorderToPurple(ActionEvent event){
        changeBorder(Color.PURPLE);
    }

    
    /** 
     * @param event
     * Action function that get called when the Red color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Border.
     */
    @FXML
    void changeBorderToRed(ActionEvent event) {
        changeBorder(Color.RED);
    }

    
    /** 
     * @param event
     * Action function that get called when the White color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Border.
     */
    @FXML
    void changeBorderToWhite(ActionEvent event) {
        changeBorder(Color.WHITE);
    }

    
    /** 
     * @param event
     * Action function that get called when the Yellow color from the palette in the context menu get selected from the user.
     * Used for changing the color of the Border.
     */
    @FXML
    void changeBorderToYellow(ActionEvent event) {
        changeBorder(Color.YELLOW);
    }

    /** 
     * Action function that get called when the Blue color from the palette get selected from the user.
     * Used for changing the main color of the Border/Background.
     * 
     * @param event
     */
    @FXML
    void blueAction(ActionEvent event) {
        if(borderColorSelected){
            borderColorValue.setStyle("-fx-background-color: blue;-fx-border-color:black");
            border = Color.BLUE;
        }
        else{
            backgroundColorValue.setStyle("-fx-background-color: blue;-fx-border-color:black");
            background = Color.BLUE;
        }
    }
    
    /** 
     * * Action function that get called when the Black color from the palette get selected from the user.
     * Used for changing the main color of the Border/Background.
     * 
     * @param event
     */
    @FXML
    void blackAction(ActionEvent event) {
        if(borderColorSelected){
            borderColorValue.setStyle("-fx-background-color: black;-fx-border-color:black");
            border = Color.BLACK;
        }
        else{
            backgroundColorValue.setStyle("-fx-background-color: black;-fx-border-color:black");
            background = Color.BLACK;
        }
    }
    
    /** 
     * * Action function that get called when the White color from the palette get selected from the user.
     * Used for changing the main color of the Border/Background.
     * 
     * @param event
     */
    @FXML
    void whiteAction(ActionEvent event) {
        if(borderColorSelected){
            borderColorValue.setStyle("-fx-background-color: white;-fx-border-color:black");
            border = Color.WHITE;
        }
        else{
            backgroundColorValue.setStyle("-fx-background-color: white;-fx-border-color:black");
            background = Color.WHITE;
        }
    }
    
    /** 
     * * Action function that get called when the Yellow color from the palette get selected from the user.
     * Used for changing the main color of the Border/Background.
     * 
     * @param event
     */
    @FXML
    void yellowAction(ActionEvent event) {
        if(borderColorSelected){
            borderColorValue.setStyle("-fx-background-color: yellow;-fx-border-color:black");
            border = Color.YELLOW;
        }
        else{
            backgroundColorValue.setStyle("-fx-background-color: yellow;-fx-border-color:black");
            background = Color.YELLOW;
        }
    }
    
    /** 
     * * Action function that get called when the Orange color from the palette get selected from the user.
     * Used for changing the main color of the Border/Background.
     * 
     * @param event
     */
    @FXML
    void orangeAction(ActionEvent event) {
        if(borderColorSelected){
            borderColorValue.setStyle("-fx-background-color: orange;-fx-border-color:black");
            border = Color.ORANGE;
        }
        else{
            backgroundColorValue.setStyle("-fx-background-color: orange;-fx-border-color:black");
            background = Color.ORANGE;
        }
    }
    
    /** 
     * * Action function that get called when the Green color from the palette get selected from the user.
     * Used for changing the main color of the Border/Background.
     * 
     * @param event
     */
    @FXML
    void greenAction(ActionEvent event) {
        if(borderColorSelected){
            borderColorValue.setStyle("-fx-background-color: green;-fx-border-color:black");
            border = Color.GREEN;
        }
        else{
            backgroundColorValue.setStyle("-fx-background-color: green;-fx-border-color:black");
            background = Color.GREEN;
        }
    }
    
    /** 
     * * Action function that get called when the Pink color from the palette get selected from the user.
     * Used for changing the main color of the Border/Background.
     * 
     * @param event
     */
    @FXML
    void pinkAction(ActionEvent event) {
        if(borderColorSelected){
            borderColorValue.setStyle("-fx-background-color: pink;-fx-border-color:black");
            border = Color.PINK;
        }
        else{
            backgroundColorValue.setStyle("-fx-background-color: pink;-fx-border-color:black");
            background = Color.PINK;
        }
    }
    
    /** 
     * * Action function that get called when the Cyan color from the palette get selected from the user.
     * Used for changing the main color of the Border/Background.
     * 
     * @param event
     */
    @FXML
    void cyanAction(ActionEvent event) {
        if(borderColorSelected){
            borderColorValue.setStyle("-fx-background-color: cyan;-fx-border-color:black");
            border = Color.CYAN;
        }
        else{
            backgroundColorValue.setStyle("-fx-background-color: cyan;-fx-border-color:black");
            background = Color.CYAN;
        }
    }
    
    /** 
     * * Action function that get called when the Purple color from the palette get selected from the user.
     * Used for changing the main color of the Border/Background.
     * 
     * @param event
     */
    @FXML
    void purpleAction(ActionEvent event) {
        if(borderColorSelected){
            borderColorValue.setStyle("-fx-background-color: purple;-fx-border-color:black");
            border = Color.PURPLE;
        }
        else{
            backgroundColorValue.setStyle("-fx-background-color: purple;-fx-border-color:black");
            background = Color.PURPLE;
        }
    }
    
    /** 
     * * Action function that get called when the Red color from the palette get selected from the user.
     * Used for changing the main color of the Border/Background.
     * 
     * @param event
     */
    @FXML
    void redAction(ActionEvent event) {
        if(borderColorSelected){
            borderColorValue.setStyle("-fx-background-color: red;-fx-border-color:black");
            border = Color.RED;
        }
        else{
            backgroundColorValue.setStyle("-fx-background-color: red;-fx-border-color:black");
            background = Color.RED;
        }
    }


    /** 
     * @param event
     * Action function that get called when the cut item in the context menu get selected by the user.
     * Used to cut a shape.
     */
    @FXML
    void cutAction(ActionEvent event) {
        CopyCommand copy = new CopyCommand(selectedShape.get(0));
        copy.execute();
        pasteItem.setDisable(false);
        copiedShape = copy.getCopy();
        drawPane.getChildren().remove((Node) selectedShape.get(0));
    }

    
    /** 
     * @param event
     * Action function that get called when the delete item in the context menu get selected by the user.
     * Used to delete a shape.
     */
    @FXML
    void deleteAction(ActionEvent event) {
        DeleteCommand delete = new DeleteCommand(drawPane, selectedShape.get(0));
        ct.save(this);
        delete.execute();

    }

    
    /** 
     * @param event
     * Event function that get called when a shape is dragged by the user.
     * Used to reset the shape selection .
     */
    @FXML
    void dragDetected(MouseEvent event) {
        gwz.updateGrid(gridSize);
        if (event.getTarget() != drawPane ){
            selectedPositions.clear();
            marker.getChildren().clear();
            polygon = false;
            dragging = true;
            resetEffect();

        }
    }


    /** 
     * @param event
     * Action function that get called when the paste item in the context menu get selected by the user.
     * Used to paste a cut or copied shape.
     */
    @FXML
    void pasteAction(ActionEvent event) {   
        PasteCommand paste = new PasteCommand(copiedShape, drawPane, positionsPaste,selectedShape, this, ct);
        ct.save(this);
        paste.execute();

    }

    
    /** 
     * @param event
     * Action function that get called when the copy item in the context menu get selected by the user.
     * Used to copy a shape.
     */
    @FXML
    void copyAction(ActionEvent event) {
        CopyCommand copy = new CopyCommand(selectedShape.get(0));
        copy.execute();
        pasteItem.setDisable(false);
        copiedShape = copy.getCopy();
    }

    /** 
     * @param event
     * Event function that get called when the user click using the mouse on the anchor pane.
     * Used to draw a shape.
     */
    @FXML
    void drawAction(MouseEvent event) {
        gwz.updateGrid(gridSize);
        if(event.getButton() == MouseButton.PRIMARY && !dragging ){
            Circle c = new Circle(event.getX(), event.getY(),5,Color.RED);
            marker.getChildren().add(c);
            shape.createShape(border,background);
            selectedPositions.add(event.getX());
            selectedPositions.add(event.getY());
            counter++;
            shape.figuraGenerica.setCounter(counter);
            drawPane.getChildren().remove(marker);
            drawPane.getChildren().add(marker);
            if(shape.figuraGenerica.isDrawable()){
                drawPane.getChildren().remove(marker);
                marker.getChildren().clear();
                shape.figuraGenerica.setTextString(textShape, textFontSize);
                shape.figuraGenerica.draw(selectedPositions);
                selectedPositions.clear();
                ((Node) shape.figuraGenerica).setCursor(Cursor.CLOSED_HAND);
                DragMod mover = new DragMod((Node) shape.figuraGenerica,drawPane,this,ct);
                selecter = new SelectMod((Node) shape.figuraGenerica, drawPane, selectedShape);
                selecter.makeSelectable();
                mover.makeDraggable();
                
            }

            if (!dragging && shape.figuraGenerica.isDrawable() ){
                //Scale scaleTrans = new Scale(1/1.25, 1/1.25,0,0);
                //drawPane.getTransforms().add(scaleTrans);
                ct.save(this);
                drawPane.getChildren().add((Node) shape.figuraGenerica);
                counter = 0;
                shape.figuraGenerica.setCounter(counter);
            }
        }
        else{
            if(event.getTarget() != drawPane){
                shapeIsSelected.set(false);
            }
            else 
                shapeIsSelected.set(true);
            positionsPaste.clear();
            positionsPaste.add(event.getX());
            positionsPaste.add(event.getY());

            marker.getChildren().clear();
            
        }
    }

    
    /** 
     * @param event
     * Action function that get called when the Ellipses shape is selected from the shape menu by the user.
     * Used to draw an ellipses.
     */
    @FXML
    void ellipsesSelected(ActionEvent event) {
        selectedPositions.clear();
        //drawPane.getChildren().remove(marker);
        shape = new CreatorElipse();
        resetEffect();
        ellipsesBtn.setStyle("-fx-background-color:Grey");
        polygon = false;
        dragging = false;
       
    }   

    
    /** 
     * @param event
     */
    @FXML
    void gridAction(ActionEvent event) {
        gwz.updateGrid(gridSize);
    }

    
    /** 
     * @param event
     * Action function that get called when the Rettangle shape is selected from the shape menu by the user.
     * Used to draw a rectangle.
     */
    @FXML
    void rectSelected(ActionEvent event) {
        selectedPositions.clear();
        drawPane.getChildren().remove(marker);
        shape = new CreatorRectangle();
        resetEffect();
        rectBtn.setStyle("-fx-background-color:Grey");
        polygon = false;
        dragging = false;

    }

    
    /** 
     * @param event
     * Action function that get called when the Polygon shape is selected from the shape menu by the user.
     * Used to draw an arbitrary polygon.
     */
    @FXML
    void polyAction(ActionEvent event) {

        counter = 0;
        drawPane.getChildren().remove(marker);
        marker.getChildren().clear();
        dragging = false;
        if(!polygon){
            resetEffect();
            polyButton.setStyle("-fx-background-color:Grey");
            selectedPositions.clear();
            shape = new CreatorPolygon();
            polygon = true;
        }else{
            ct.save(this);
            shape.figuraGenerica.draw(selectedPositions);
            marker.getChildren().clear();
            drawPane.getChildren().add((Node) shape.figuraGenerica);
            ((Node) shape.figuraGenerica).setCursor(Cursor.CLOSED_HAND);
            DragMod mover = new DragMod((Node) shape.figuraGenerica,drawPane,this,ct);
            selecter = new SelectMod((Node) shape.figuraGenerica, drawPane, selectedShape);
            selecter.makeSelectable();
            mover.makeDraggable();
            selectedPositions.clear();
            counter = 0;
            polygon = false;
            resetEffect();
        }
    }

    
    /** 
     * @param event
     * Action function that get called when the Segment shape is selected from the shape menu by the user.
     * Used to draw a segment.
     */
    @FXML
    void segmentSelected(ActionEvent event) {
        drawPane.getChildren().remove(marker);
        marker.getChildren().clear();
        selectedPositions.clear();
        shape = new CreatorLine();
        shape.createShape(border, background);
        counter = 0;
        shape.figuraGenerica.setCounter(counter);
        resetEffect();
        segmentBtn.setStyle("-fx-background-color:Grey");
        polygon = false;
        dragging = false;
    }

    
    /** 
     * @param event
     * Action function that get called when the to the front item in the context menu get selected by the user.
     * Used to put a shape in foreground.
     */
    @FXML
    void frontAction(ActionEvent event) {
        FrontCommand front = new FrontCommand(selectedShape.get(0));
        ct.save(this);
        front.execute();
    }

    
    /** 
     * @param event
     * Action function that get called when the to the back item in the context menu get selected by the user.
     * Used to put a shape in background.
     */
    @FXML
    void backAction(ActionEvent event) {
        BackCommand back = new BackCommand(selectedShape.get(0));
        ct.save(this);
        back.execute();
    }

    
    /** 
     * @param event
     * Action function that get called when the load item in the file menu get selected by the user.
     * Used to load an editable file in the window.
     * @throws FileNotFoundException
     */
    @FXML
    void loadAction(ActionEvent event) {
        OpenCommand open = new OpenCommand(drawPane, border, background);
        open.execute();
        ct.reset();
        for (GeneralShape shape : open.getShapes()){
            DragMod mover = new DragMod((Node) shape,drawPane,this,ct);
            SelectMod selecter = new SelectMod((Node) shape, drawPane, selectedShape);
            selecter.makeSelectable();
            mover.makeDraggable();
            ((Node) shape).setCursor(Cursor.CLOSED_HAND);
            drawPane.getChildren().add((Node) shape);
        }
    }
    
    /** 
     * @param event
     * Action function that get called when the mirror horizontally item in the context menu get selected by the user.
     * Used to mirror a shape along the X-axis.
     */
    @FXML
    void mirrorHorizontally(ActionEvent event) {
        MirrorCommand mirror = new MirrorCommand(selectedShape.get(0),true);
        ct.save(this);
        mirror.execute();
    }

    
    /** 
     * @param event
     * Action function that get called when the mirror vertically item in the context menu get selected by the user.
     * Used to mirror a shape along the Y-axis.
     */
    @FXML
    void mirrorVertically(ActionEvent event) {
        MirrorCommand mirror = new MirrorCommand(selectedShape.get(0),false);
        ct.save(this);
        mirror.execute();
    }

    
    /** 
     * @param event
     * Action function that get called when the resize item in the context menu get selected by the user.
     * Used to resize a shape from the center and constrain proportions.
     */
    @FXML
    void resizeAction(ActionEvent event) {
        if(Double.parseDouble(resizeTextField.getText())>0){
            ResizeCommand resize = new ResizeCommand(selectedShape.get(0), Double.parseDouble(resizeTextField.getText()));
            resizeTextField.clear();
            ct.save(this);
            resize.execute();
        }
        else
            resizeTextField.clear();

    }

    
    /** 
     * @param event
     * Action function that get called when the rotate item in the context menu get selected by the user.
     * Used to rotate a shape on a corner.
     */
    @FXML
    void rotateAction(ActionEvent event) {
        RotateCommand rotate = new RotateCommand(selectedShape.get(0), Integer.parseInt(degreeTextField.getText()));
        ct.save(this);
        rotate.execute();
        degreeTextField.clear();
    }

    
    /** 
     * @param event
     * Action function that get called when the save item in the file menu get selected by the user.
     * Used to save a png image.
     */
    @FXML
    void exportAction(ActionEvent event) {
        ExportCommand export = new ExportCommand(drawPane);
        export.execute();
    }

    @FXML
    void saveAction(ActionEvent event) throws IOException {
        SaveCommand save = new SaveCommand(drawPane);
        save.execute();
    }
    
    /** 
     * @param event
     * Action function that get called when the change background color item in the context menu get selected by the user.
     * Used to change the background color of an already existing shape.
     */
    @FXML
    void setBackgroundColor(ActionEvent event) {
        backgroundColorSelected = true;
        borderColorSelected = false;
    }

    
    /** 
     * @param event
     * Action function that get called when the change border color item in the context menu get selected by the user.
     * Used to change the border color of an already existing shape.
     */
    @FXML
    void setBorderColor(ActionEvent event) {
        backgroundColorSelected = false;
        borderColorSelected = true;
    }

    
    /** 
     * @param event
     * Action function that get called when the stretch horizontal item in the context menu get selected by the user.
     * Used to resize a shape along the X-axis costraining the vertical proportions.
     */
    @FXML
    void stretchXAction(ActionEvent event) {
        if(Double.parseDouble(stretchX.getText())>1){
            StretchCommand stretch = new StretchCommand(selectedShape.get(0), Double.parseDouble(stretchX.getText()), -1);
            ct.save(this);
            stretch.execute();
            
        }
        stretchX.setText("100");
    }

    
    /** 
     * @param event
     * Action function that get called when the stretch vertical item in the context menu get selected by the user.
     * Used to resize a shape along the Y-axis costraining the horizontal proportions.
     */
    @FXML
    void stretchYAction(ActionEvent event) {
        if(Double.parseDouble(stretchY.getText())>1){
        StretchCommand stretch = new StretchCommand(selectedShape.get(0), -1, Double.parseDouble(stretchY.getText()));
        ct.save(this);
        stretch.execute();
        }
        stretchY.setText("100");
    }

    
    /** 
     * @param event
     * Action function that get called when the large grid size is selected by the user.
     * Used to add a large grid to the anchorpane.
     */
    @FXML
    void gridLarge(ActionEvent event) {
        gridSize = 40;
        menuGrid.textProperty().setValue("Large");
    }

    
    /** 
     * @param event
     * Action function that get called when the medium grid size is selected by the user.
     * Used to add a medium grid to the anchorpane.
     */
    @FXML
    void gridMedium(ActionEvent event) {
        gridSize = 30;
        menuGrid.textProperty().setValue("Medium");
    }

    
    /** 
     * @param event
     * Action function that get called when the small grid size is selected by the user.
     * Used to add a small grid to the anchorpane.
     */
    @FXML
    void gridSmall(ActionEvent event) {
        gridSize = 20;
        menuGrid.textProperty().setValue("Small");
    }

    public void resetEffect(){
        ellipsesBtn.setStyle(null);
        rectBtn.setStyle(null);
        segmentBtn.setStyle(null);
        polyButton.setStyle(null);
        textShapeField.setStyle(null);
    }

    
    /** 
     * @param event
     * Action function that get called when the Text shape is selected from the shape menu by the user.
     * Used to draw a text shape.
     */
    @FXML
    void textSelected(ActionEvent event) {
        drawPane.getChildren().remove(marker);
        marker.getChildren().clear();
        selectedPositions.clear();
        selectedPositions.clear();
        drawPane.getChildren().remove(marker);
        shape = new CreatorText();
        shape.createShape(border, background);
        textShape = textShapeField.getText();
        resetEffect();
        textShapeField.setStyle("-fx-background-color:LightGrey");
        polygon = false;
        dragging = false;
    }

    
    /** 
     * @param event
     * Action function that get called when the font size for theText shape is decreased from the shape menu by the user.
     * Used to set the fontisize for the text shape.
     */
    public void decreaseFontSize(ActionEvent event){
        if(textFontSize>8)
            textFontSize-=2;
        labelFontSize.textProperty().setValue(String.valueOf(textFontSize));
    }

    
    /** 
     * @param event
     * Action function that get called when the font size for theText shape is increased from the shape menu by the user.
     * Used to set the fontisize for the text shape.
     */
    public void increaseFontSize(ActionEvent event){
        textFontSize+=2;
        labelFontSize.textProperty().setValue(String.valueOf(textFontSize));
    }

    
    /** 
     * @return ObservableList<Node>
     */
    public ObservableList<Node> getItems(){
        return drawPane.getChildren();
    }

    
    /** 
     * @return AnchorPaneMemento
     */
    public AnchorPaneMemento save(){
        return new AnchorPaneMemento(getItems());
    }

    
    /** 
     * @param apMem
     */
    public void revert(AnchorPaneMemento apMem){
        drawPane.getChildren().clear();
        for(Node n : apMem.getShapes()){
            ArrayList<Double> shapePos = new ArrayList<>();
            shapePos.add(((GeneralShape) n).getX());
            shapePos.add(((GeneralShape) n).getY());
            PasteCommand paste = new PasteCommand((GeneralShape) n, drawPane, shapePos, selectedShape, this, ct);
            paste.execute();
        }
    }

    
    /** 
     * @param event
     */
    @FXML
    void undoAction(ActionEvent event) {
        ct.revert(this);
    }
    public static class AnchorPaneMemento {
        ObservableList<Node> shapes = FXCollections.observableArrayList();
        public AnchorPaneMemento(ObservableList<Node> observableList){
            this.shapes.clear();
            for (Node node : observableList) {
                if(node instanceof ImageView)
                    continue;
                this.shapes.add((Node) ((GeneralShape) node).cloneShape());
            }
            //this.shapes.clear();
            //this.shapes.addAll(observableList);
        }

        private ObservableList<Node> getShapes(){
            return shapes;
        }

    }
    
    
    @FXML
    void increaseDrawPaneHeight(ActionEvent event) {
        Double newH = Double.parseDouble(drawHeightTf.getText());
        drawPane.setPrefHeight(newH);
        drawPane.setMinHeight(newH);
        
    }

    @FXML
    void increaseDrawPaneWidth(ActionEvent event) {
        Double newW = Double.parseDouble(drawWidthTf.getText());
        drawPane.setPrefWidth(newW);
        drawPane.setMinWidth(newW);

    }

    @FXML
    void newDraw(ActionEvent event) {
        drawPane.getChildren().clear();
    }

}
