package Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import GeneralShapes.PolygonShape;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class PolygonShapeTest {
    
    private PolygonShape cloned;
    private PolygonShape poly;
    private ArrayList<Double> positions = new ArrayList<>();
    private ArrayList<Double> newPositions = new ArrayList<>();
    String textFromShape = "PolygonShape,8,165.0,51.0,156.0,312.0,489.0,284.0,512.0,58.0,0.0,0.0,0,0xfaebd7ff,0xf0f8ffff";

    @Before
    public void setup(){
        this.positions.add(165.0);
        this.positions.add(51.0);
        this.positions.add(156.0);
        this.positions.add(312.0);
        this.positions.add(489.0);
        this.positions.add(284.0);
        this.positions.add(512.0);
        this.positions.add(58.0);

        this.newPositions.add(88.0);
        this.newPositions.add(106.0);
        this.newPositions.add(88.0);
        this.newPositions.add(243.0);
        this.newPositions.add(286.0);
        this.newPositions.add(284.0);
        this.newPositions.add(238.0);

        this.poly = new PolygonShape(Color.ALICEBLUE, Color.ANTIQUEWHITE);
        poly.getPoints().addAll(positions);
    }

    @Test
    public void testCloneShape() {
        cloned = (PolygonShape) poly.cloneShape();
        assertEquals(poly, cloned);
    }

    @Test
    public void testGetBackground() {
        Color background = poly.getBackground();
        assertEquals(background, Color.ALICEBLUE);
    }

    @Test
    public void testGetBorder() {
        Color border = poly.getBorder();
        assertEquals(border, Color.ANTIQUEWHITE);
    }

    @Test
    public void testGetX() {
        Double firstPointX = poly.getX();
        assertEquals(firstPointX, positions.get(0));
    }

    @Test
    public void testGetY() {
        Double firstPointY = poly.getY();
        assertEquals(firstPointY, positions.get(1));
    }

    @Test
    public void testLoadShapeFromText() {
        PolygonShape tempPoly = new PolygonShape();
        tempPoly.loadShapeFromText(textFromShape.split(","));
        assertEquals(poly, tempPoly);
    }

    @Test
    public void testMirror() {
        poly.mirror(true);
        assertTrue("Shape not Mirrored", poly.getScaleX()<0);
        poly.mirror(false);
        assertTrue("Shape not Mirrored", poly.getScaleY()<0);
    }

    @Test
    public void testResize() {
       poly.resize(200);
       Double t = poly.getScaleX();
       assertEquals(poly.getScaleX(), 2,0);
       assertEquals(poly.getScaleY(), 2,0);
    }

    @Test
    public void testRotate() {
        poly.rotate(45);
        assertEquals(((Rotate) poly.getTransforms().get(0)).getAngle(), 45.0 ,0);
    }

    /*
    @Test
    public void testSendToBack() {
        poly.toBack();
    }

    @Test
    public void testSendToFront() {

    }
    */

    @Test
    public void testSetBackground() {
        poly.setBackground(Color.RED);
        assertEquals(poly.getBackground(), Color.RED);
    }

    @Test
    public void testSetBorder() {
        poly.setBorder(Color.RED);
        assertEquals(poly.getBorder(), Color.RED);
    }

    @Test
    public void testSetX() {
        poly.setX(newPositions);
        assertEquals(poly.getX(), newPositions.get(0),0);
    }

    @Test
    public void testSetY() {
        poly.setY(newPositions);
        assertEquals(poly.getY(), newPositions.get(1),0);
    }

    @Test
    public void testStretch() {

        poly.stretch(200, 400);
        assertEquals(poly.getScaleX(),1,0 );
        assertEquals(poly.getScaleY(),1,0 );

    }

    @Test
    public void testStretchX() {
        poly.stretchX(200);
        assertEquals(poly.getScaleX(),2,0 );
    }

    @Test
    public void testStretchY() {
        poly.stretchY(200);
        assertEquals(poly.getScaleY(),2,0 );
    }

    @Test
    public void testToString() {
        assertEquals(poly.toString(),"PolygonShape,8,165.0,51.0,156.0,312.0,489.0,284.0,512.0,58.0,0.0,0.0,0,0xfaebd7ff,0xf0f8ffff" );
    }
}
