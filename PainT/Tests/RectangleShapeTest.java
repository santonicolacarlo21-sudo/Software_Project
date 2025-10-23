package Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import javafx.scene.transform.Rotate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import GeneralShapes.RectangleShape;
import javafx.scene.paint.Color;

public class RectangleShapeTest {

    private RectangleShape r;
    private RectangleShape cloned;
    private ArrayList<Double> positions = new ArrayList<>();
    private ArrayList<Double> newPositions = new ArrayList<>();
    private Color background = Color.DARKGREEN;
    private Color border = Color.LAVENDER;
    String textFromShape = "RectangleShape,2,165.0,51.0,0.0,0.0,150.0,100.0,0,0xe6e6faff,0x006400ff";

    @Before
    public void setup(){
        r = new RectangleShape(background, border);

        positions.add(165.0);
        positions.add(51.0);
        newPositions.add(333.3);
        newPositions.add(213.34);

        r.setX(positions.get(0));
        r.setY(positions.get(1));
        r.setFill(background);
        r.setStroke(border);
    }

    @After
    public void tearDown(){
        r = null;
    }

    @Test
    public void testCloneShape() {
        cloned = (RectangleShape) r.cloneShape();
        assertEquals(r, cloned);
    }

    @Test
    public void testDraw() {
        RectangleShape tempRect = new RectangleShape(background, border);
        tempRect.draw(positions);
        assertEquals(r, tempRect);
        assertEquals(positions.get(0), tempRect.getX(), 0);
        assertEquals(positions.get(1), tempRect.getY(), 0);
    }

    @Test
    public void testGetBackground() {
        assertEquals(Color.DARKGREEN, r.getBackground());
    }

    @Test
    public void testGetBorder() {
        assertEquals(Color.LAVENDER, r.getBorder());
    }


    @Test
    public void testLoadShapeFromText() {
        RectangleShape tempRect = new RectangleShape();
        tempRect.loadShapeFromText(textFromShape.split(","));
        assertEquals(r, tempRect);
    }

    @Test
    public void testMirror() {
        r.mirror(true);
        assertTrue("Shape not Mirrored", r.getScaleX()<0);
        r.mirror(false);
        assertTrue("Shape not Mirrored", r.getScaleY()<0);
    }

    @Test
    public void testResize() {
        Double percentage = 17.8;
        Double newWidth = r.getWidth()*percentage/100;
        Double newHeight = r.getHeight()*percentage/100;
        r.resize(percentage);
        assertEquals(newWidth, r.getWidth(),0);
        assertEquals(newHeight, r.getHeight(),0);
    }

    @Test
    public void testRotate() {
        r.rotate(84);
        assertEquals(84, ((Rotate) r.getTransforms().get(0)).getAngle(),0);
    }

    @Test
    public void testSetBackground() {
        r.setBackground(Color.BLUE);
        assertEquals(Color.BLUE, r.getBackground());
    }

    @Test
    public void testSetBorder() {
        r.setBorder(Color.BLANCHEDALMOND);
        assertEquals(Color.BLANCHEDALMOND, r.getBorder());
    }

    @Test
    public void testSetX() {
        r.setX(newPositions);
        assertEquals(newPositions.get(0), r.getX(), 0);
    }

    @Test
    public void testSetY() {
        r.setY(newPositions);
        assertEquals(newPositions.get(1), r.getY(), 0);
    }

    @Test
    public void testStretch() {
        Double percentageX = 343.4;
        Double percentageY = 56.2444;
        Double newWidth = r.getWidth()*percentageX/100;
        Double newHeight = r.getHeight()*percentageY/100;
        r.stretch(percentageX, -1);
        assertEquals(newWidth, r.getWidth(),0 );
        r.stretch(-1, percentageY);
        assertEquals(newHeight, r.getHeight(),0 );
    }

    @Test
    public void testStretchX() {
        Double percentageX = 7.324;
        Double newWidth = r.getWidth()*percentageX/100;
        r.stretchX(percentageX);
        assertEquals(newWidth, r.getWidth(),0 );
    }

    @Test
    public void testStretchY() {
        Double percentageY = 343.4;
        Double newHeight = r.getHeight()*percentageY/100;
        r.stretchY(percentageY);
        assertEquals(newHeight, r.getHeight(),0 );
    }

    @Test
    public void testToString() {
        assertEquals(textFromShape, r.toString());
    }
}
