package Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import javafx.scene.transform.Rotate;

import org.junit.Before;
import org.junit.Test;

import GeneralShapes.ElipseShape;
import javafx.scene.paint.Color;

public class ElipseShapeTest {

    private ElipseShape cloned;
    private ElipseShape ely;
    private ArrayList<Double> positions = new ArrayList<>();
    private ArrayList<Double> newPositions = new ArrayList<>();
    private Color background = Color.BLUEVIOLET;
    private Color border = Color.LIMEGREEN;
    String textFromShape = "ElipseShape,2,732.0,350.0,0.0,0.0,100.0,50.0,0,0x32cd32ff,0x8a2be2ff";

    
    @Before
    public void setup(){
 
        this.positions.add(732.0);
        this.positions.add(350.0);

        this.newPositions.add(850.0);
        this.newPositions.add(150.0);

        this.ely = new ElipseShape(background, border);
        ely.setCenterX(positions.get(0));
        ely.setCenterY(positions.get(1));
        ely.setFill(background);
        ely.setStroke(border);
    }

    @Test
    public void testDraw() {
        ElipseShape tempElipse =  new ElipseShape(background, border);
        tempElipse.draw(positions);
        assertEquals(ely, tempElipse);
        assertEquals(positions.get(0), tempElipse.getCenterX(), 0);
        assertEquals(positions.get(1), tempElipse.getCenterY(), 0);
    }

    @Test
    public void testCloneShape() {
        cloned = (ElipseShape) ely.cloneShape();
        assertEquals(ely, cloned);
    }

    @Test
    public void testGetBackground() {
        Color backgroundEly = ely.getBackground();
        assertEquals(background, backgroundEly);
    }

    @Test
    public void testGetBorder() {
        Color borderEly = ely.getBorder();
        assertEquals(border, borderEly);
    }

    @Test
    public void testGetX() {
        Double centerX = ely.getX();
        assertEquals(positions.get(0), centerX);
    }

    @Test
    public void testGetY() {
        Double centerY = ely.getY();
        assertEquals(positions.get(1), centerY);
    }

    @Test
    public void testLoadShapeFromText() {
        ElipseShape tempEly = new ElipseShape();
        tempEly.loadShapeFromText(textFromShape.split(","));
        assertEquals(ely, tempEly);
    }

    @Test
    public void testMirror() {
        ely.mirror(true);
        assertTrue("Shape not Mirrored", ely.getScaleX()<0);
        ely.mirror(false);
        assertTrue("Shape not Mirrored", ely.getScaleY()<0);
    }

    @Test
    public void testResize() {
        double percentage = 200;
        Double newRadiusX = ely.getRadiusX()*percentage/100;
        Double newRadiusY = ely.getRadiusY()*percentage/100;

        ely.resize(percentage);
        
        assertEquals(newRadiusX, ely.getRadiusX(),0);
        assertEquals(newRadiusY, ely.getRadiusY(),0);
    }

    @Test
    public void testRotate() {
        ely.rotate(84);
        assertEquals(84, ((Rotate) ely.getTransforms().get(0)).getAngle(), 0);
    }

    /* @Test
    public void testSendToBack() {

    }

    @Test
    public void testSendToFront() {

    } */

    @Test
    public void testSetBackground() {
        ely.setBackground(Color.RED);
        assertEquals(Color.RED, ely.getBackground());
    }

    @Test
    public void testSetBorder() {
        ely.setBorder(Color.YELLOW);
        assertEquals(Color.YELLOW, ely.getBorder());
    }


    @Test
    public void testSetX() {
        ely.setX(newPositions);
        assertEquals(newPositions.get(0),ely.getX(),0);
    }

    @Test
    public void testSetY() {
        ely.setY(newPositions);
        assertEquals(newPositions.get(1),ely.getY(),0);
    }

    @Test
    public void testStretch() {
        double percentageX = 80;
        double percentageY = 300;
        Double newRadiusX = ely.getRadiusX()*percentageX/100;
        Double newRadiusY = ely.getRadiusY()*percentageY/100;

        ely.stretch(percentageX, -1);
        ely.stretch(-1, percentageY);

        assertEquals(newRadiusX, ely.getRadiusX(),0 );
        assertEquals(newRadiusY,ely.getRadiusY(),0 );

    }

    @Test
    public void testStretchX() {
        double percentage = 356.494;
        Double newRadiusX = ely.getRadiusX()*percentage/100;

        ely.stretchX(percentage);
        assertEquals(newRadiusX,ely.getRadiusX(),0 );
    }

    @Test
    public void testStretchY() {
        double percentage = 56.494;
        Double newRadiusY = ely.getRadiusY()*percentage/100;

        ely.stretchY(percentage);
        assertEquals(newRadiusY,ely.getRadiusY(),0 );
    }

    @Test
    public void testToString() {
        assertEquals(textFromShape, ely.toString());
    }
}
