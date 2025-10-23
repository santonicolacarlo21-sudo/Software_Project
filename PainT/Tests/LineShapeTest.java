package Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import GeneralShapes.LineShape;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class LineShapeTest {
    
    private LineShape cloned;
    private LineShape line;
    private ArrayList<Double> positions = new ArrayList<>();
    private ArrayList<Double> newPositions = new ArrayList<>();
    String textFromShape = "LineShape,4,35.345,335.9023,24.466,83.3434,0.0,0.0,5.0,0,0xdeb887ff,0xfff8dcff";

    @Before
    public void setup(){
        this.positions.add(35.345);
        this.positions.add(335.9023);
        this.positions.add(24.466);
        this.positions.add(83.3434);

        this.newPositions.add(53.986);
        this.newPositions.add(235.345);
        this.newPositions.add(4.896);
        this.newPositions.add(17.575);

        this.line = new LineShape(Color.BURLYWOOD, Color.CORNSILK);
        line.setStrokeWidth(5.0);
        line.getPoints().addAll(positions);
    }

    @Test
    public void testCloneShape() {
        cloned = (LineShape) line.cloneShape();
        assertEquals(line, cloned);
    }

    @Test
    public void testGetBackground() {
        Color background = line.getBackground();
        assertEquals(Color.CORNSILK, background);
    }

    @Test
    public void testGetBorder() {
        Color border = line.getBorder();
        assertEquals(Color.BURLYWOOD, border);
    }

    @Test
    public void testGetX() {
        Double firstPointX = line.getX();
        assertEquals(positions.get(0), firstPointX);
    }

    @Test
    public void testGetY() {
        Double firstPointY = line.getY();
        assertEquals(positions.get(1), firstPointY);
    }

    @Test
    public void testLoadShapeFromText() {
        LineShape tempLine = new LineShape();
        tempLine.loadShapeFromText(textFromShape.split(","));
        assertEquals(line, tempLine);
    }

    @Test
    public void testMirror() {
        line.mirror(true);
        assertTrue("Shape not Mirrored", line.getScaleX()<0);
        line.mirror(false);
        assertTrue("Shape not Mirrored", line.getScaleY()<0);
    }

    @Test
    public void testResize() {
       line.resize(135);
       assertEquals(1.35, line.getScaleX(),0);
       assertEquals(1.35, line.getScaleY(),0);
    }

    @Test
    public void testRotate() {
        line.rotate(71);
        assertEquals(71, ((Rotate) line.getTransforms().get(0)).getAngle(),0);
    }

    @Test
    public void testSetBackground() {
        line.setBackground(Color.PINK);
        assertEquals(Color.PINK, line.getBackground());
    }

    @Test
    public void testSetBorder() {
        line.setBorder(Color.YELLOW);
        assertEquals(Color.YELLOW, line.getBorder());
    }

    @Test
    public void testSetX() {
        line.setX(newPositions);
        assertEquals(newPositions.get(0), line.getX(),0);
    }

    @Test
    public void testSetY() {
        line.setY(newPositions);
        assertEquals(newPositions.get(1), line.getY(),0);
    }

    @Test
    public void testStretch() {
        line.stretch(24, 245);
        assertEquals(1, line.getScaleX(),0 );
        assertEquals(1, line.getScaleY(),0 );

    }

    @Test
    public void testStretchX() {
        line.stretchX(453);
        assertEquals(4.53, line.getScaleX(),0 );
    }

    @Test
    public void testStretchY() {
        line.stretchY(18);
        assertEquals(0.18, line.getScaleY(),0 );
    }

    @Test
    public void testToString() {
        assertEquals(textFromShape, line.toString());
    }
}
