package GeneralShapes;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public interface GeneralShape {
    public void draw(ArrayList<Double> positions);
    public void setX(ArrayList<Double> x);
    public void setY(ArrayList<Double> y);
    public GeneralShape cloneShape();
    public Color getBorder();
    public Color getBackground();
    public double getX();
    public double getY();
    public void setBorder(Color c);
    public void setBackground(Color c);
    public void rotate(int degree);
    public void sendToBack();
    public void sendToFront();
    public double getScaleX();
    public double getScaleY();
    public void resize(double percentage);
    public void stretchX(double percentage);
    public void stretchY(double percentage);
    public void stretch(double percentageX, double percentageY);
    public boolean isDrawable();
    public void setCounter(int counter);
    public int getCounter();
    public void mirror(boolean byX);
    //AHAHAHHAHAH ANTONIO DICE DI TOGLIERLO DALL'INTERFACCIA AHAHAH.                CIAO LUCIA
    public void setTextString(String textShape, double textFontSize);
    public void loadShapeFromText(String str[]);
    public int getAngle();
}
