package Commands;

import java.util.ArrayList;

import GeneralShapes.GeneralShape;
import Main.testController;
import Managers.CareTaker;
import Managers.DragMod;
import Managers.SelectMod;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;

public class PasteCommand implements Command{
    AnchorPane ap;
    GeneralShape toPaste;
    DragMod dm;
    SelectMod sm;
    ArrayList<GeneralShape> selectedShape;
    ArrayList<Double> positionsPaste;
    testController test;
    CareTaker ct;
    public PasteCommand(GeneralShape copied, AnchorPane ap, ArrayList<Double> positionsPaste, ArrayList<GeneralShape> selectedShape, testController test, CareTaker ct) {
        this.ap = ap;
        this.selectedShape = selectedShape;
        this.toPaste = copied;
        this.positionsPaste = positionsPaste;
        this.test = test;
        this.ct = ct;
    }

    
    /**
     * It pastes the last copied/cut shape with all its properties
     */
    @Override
    public void execute() {
        
        GeneralShape tempShape = toPaste.cloneShape();

        tempShape.setX(positionsPaste);
        tempShape.setY(positionsPaste);
       
        dm = new DragMod((Node) tempShape, ap,test, ct);
        dm.makeDraggable();
        sm = new SelectMod((Node) tempShape, ap, selectedShape);
        sm.makeSelectable();
        ((Node) tempShape).setCursor(Cursor.CLOSED_HAND);
        ap.getChildren().add((Node) tempShape);
        ((Node) tempShape).getTransforms().add(new Rotate(toPaste.getAngle(), tempShape.getX(), tempShape.getY()));
        positionsPaste.clear();
    }
    
}
