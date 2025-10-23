package Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import GeneralShapes.TextShape;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;

public class TextShapeTest {

    private TextShape cloned;
    private TextShape text;
    private ArrayList<Double> positions = new ArrayList<>();
    private ArrayList<Double> newPositions = new ArrayList<>();
    private Color background = Color.LIGHTGOLDENRODYELLOW;
    private Color border = Color.ROSYBROWN;
    String textFromShape = "TextShape,2,270.0,126.0,0.0,0.0,20.0,ciao,0,0xbc8f8fff,0xfafad2ff";


    @Before
    public void setup(){
 
        this.positions.add(270.0);
        this.positions.add(126.0);

        this.newPositions.add(33.33);
        this.newPositions.add(15.0);

        this.text = new TextShape(border, background);
        text.setX(positions);
        text.setY(positions);
        text.setFill(background);
        text.setStroke(border);
        text.setText("ciao");
        text.setFont(new Font(20));
    }
    
    @Test
    public void testCloneShape() {
        cloned = (TextShape) text.cloneShape();
        assertEquals(text, cloned);
    }

    @Test
    public void testDraw() {
        TextShape tempText =  new TextShape(border, background);
        tempText.draw(positions);
        tempText.setText("ciao");
        assertEquals(text, tempText);
        assertEquals(positions.get(0), tempText.getX(), 0);
        assertEquals(positions.get(1), tempText.getY(), 0);
    }

    @Test
    public void testGetBackground() {
        Color backgroundText = text.getBackground();
        assertEquals(background, backgroundText);
    }

    @Test
    public void testGetBorder() {
        Color borderText = text.getBorder();
        assertEquals(border, borderText);
    }


    @Test
    public void testLoadShapeFromText() {
        TextShape tempText = new TextShape();
        tempText.loadShapeFromText(textFromShape.split(","));
        assertEquals(text, tempText);
    
    }
    

    @Test
    public void testMirror() {
        text.mirror(true);
        assertTrue("Shape not Mirrored", text.getScaleX()<0);
        text.mirror(false);
        assertTrue("Shape not Mirrored", text.getScaleY()<0);
    }

    @Test
    public void testResize() {
        double percentage = 563.124;
        Double newFontSize = text.getFont().getSize()*percentage/100;

        text.resize(percentage);

        assertEquals(newFontSize, text.getFont().getSize(), 0);
    }

    @Test
    public void testRotate() {
        text.rotate(56);
        assertEquals(56, ((Rotate) text.getTransforms().get(0)).getAngle(), 0);
    }


    @Test
    public void testSetBackground() {
        text.setBackground(Color.LEMONCHIFFON);
        assertEquals(Color.LEMONCHIFFON, text.getBackground());
    }

    @Test
    public void testSetBorder() {
        text.setBackground(Color.LAWNGREEN);
        assertEquals(Color.LAWNGREEN, text.getBackground());
    }


    @Test
    public void testSetTextString() {
        text.setTextString("PAPA",34);
        text.draw(positions);
        assertEquals("PAPA", text.getText());
        assertEquals(34, text.getFont().getSize(), 0);
    }

    @Test
    public void testSetX() {
        text.setX(newPositions);
        assertEquals(newPositions.get(0),text.getX(),0);
    }

    @Test
    public void testSetY() {
        text.setY(newPositions);
        assertEquals(newPositions.get(1),text.getY(),0);
    }

    @Test
    public void testStretch() {
        double percentageX = 870;
        double percentageY = 30;
        Double newScaleX = percentageX/100;
        Double newScaleY = percentageY/100;

        text.stretch(percentageX, -1);
        text.stretch(-1, percentageY);

        assertEquals(newScaleX, text.getScaleX(),0 );
        assertEquals(newScaleY,text.getScaleY(),0 );
    }

    @Test
    public void testStretchX() {
        double percentageX = 42;
        Double newScaleX = percentageX/100;

        text.stretchX(percentageX);

        assertEquals(newScaleX, text.getScaleX(),0 );
    }

    @Test
    public void testStretchY() {
        double percentageY = 42;
        Double newScaleY = percentageY/100;

        text.stretchY(percentageY);

        assertEquals(newScaleY, text.getScaleY(),0 );
    }

    @Test
    public void testToString() {
        assertEquals(textFromShape, text.toString());
    }
}
