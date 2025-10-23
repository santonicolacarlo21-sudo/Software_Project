package GeneralShapes;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.transform.Rotate;

public class LineShape extends Polyline implements GeneralShape{
    private Color border, background;
    private int counter = 0;
    private int angle;
    private ArrayList<Double> points;
    
    public LineShape(Color border, Color background) {
        super();
        this.border = border;
        this.background = background;
        this.angle = 0;
    }

    public LineShape() {
    }

    
    /** 
     * @param positions
     * Create a shape setting all the properties and the coordinates at the values specified by @param
     */
    @Override
    public void draw(ArrayList<Double> positions) {
        this.getPoints().addAll(positions);
        this.setStrokeWidth(5);
        this.setFill(background);
        this.setStroke(border);
        points = (ArrayList<Double>) positions.clone();  
    }

    
    /** 
     * @param x
     * Set the points X-axis coordinates
     */
    @Override
    public void setX(ArrayList<Double> x) {
        double xPaste = x.get(0);
        double anchorX = this.getPoints().get(0);
        for(int i = 0; i<this.getPoints().size(); i+=2)
            this.getPoints().set(i, xPaste + (this.getPoints().get(i)-anchorX));
        
    }

    
    /** 
     * @param y
     * Set the points Y-axis coordinates
     */
    @Override
    public void setY(ArrayList<Double> y) {
        double yPaste = y.get(1);
        double anchorY = this.getPoints().get(1);
        
        for(int i = 1; i<this.getPoints().size(); i+=2)
            this.getPoints().set(i, yPaste + (this.getPoints().get(i)-anchorY));
        
    }

    
    /** 
     * @return GeneralShape
     * Create a new shape with the same properties of the selected shape
     */
    @Override
    public GeneralShape cloneShape() {
        LineShape newLine = new LineShape();
        newLine.setBackground(background);
        newLine.setBorder(border);
        newLine.setTranslateX(this.getTranslateX());
        newLine.setTranslateY(this.getTranslateY());
        newLine.setScaleX(this.getScaleX());
        newLine.setScaleY(this.getScaleY());
        newLine.setStrokeWidth(5);
        newLine.getPoints().addAll(this.getPoints());
        newLine.angle = this.getAngle();
        return newLine;
    }

    
    /** 
     * @return Color
     * Return the border color of the selected shape
     */
    @Override
    public Color getBorder() {
        return this.border;
    }

    
    /** 
     * @return Color
     * Return the background color of the selected shape
     */
    @Override
    public Color getBackground() {
        return this.background;
    }

    
    /** 
     * @return double
     * Return the first point X-axis coordinate
     */
    @Override
    public double getX() {
        return this.getPoints().get(0);
    }

    
    /** 
     * @return double
     * Return the first point Y-axis coordinate
     */
    @Override
    public double getY() {
        return this.getPoints().get(1);
    }

    
    /** 
     * @param percentage
     * Resize the selected shape to the percentage specified by @param
     */
    @Override
    public void resize(double percentage) {
        
        this.stretchX(percentage);
        this.stretchY(percentage);      
    }

    
    /** 
     * @param percentage
     * Stretch horizontally the selected shape to the percentage specified by @param
     */
    @Override
    public void stretchX(double percentage) {
        this.setScaleX(this.getScaleX()*percentage/100);
    }

    
    /** 
     * @param percentage
     * Stretch vertically the selected shape to the percentage specified by @param
     */
    @Override
    public void stretchY(double percentage) {
        this.setScaleY(this.getScaleY()*percentage/100);
    }

    
    /** 
     * @param percentageX
     * @param percentageY
     * Stretch the selected shape to the percentages specified by @param
     */
    @Override
    public void stretch(double percentageX, double percentageY) {
        if(percentageY == -1)
            stretchX(percentageX);
        if(percentageX == -1)
            stretchY(percentageY);        
    }

    
    /** 
     * @param c
     * Set the border color of the selected shape
     */
    @Override
    public void setBorder(Color c) {
        this.setStroke(c);     
        this.border = c;   
    }

    
    /** 
     * @param c
     * Set the background color of the selected shape
     */
    @Override
    public void setBackground(Color c) {
        this.setFill(c);   
        this.background = c;    
    }


    /** 
     * @return int
     * Return the angle value of the shape
     */
    @Override
    public int getAngle(){
        return this.angle;
    }

    
    /** 
     * @param degree
     * Rotate the selected shape to the arbitrary angle specified by @param
     */
    @Override
    public void rotate(int degree) {
        Rotate rotateTransf = new Rotate(degree,this.getX(),this.getY());
        this.getTransforms().add(rotateTransf);     
        this.angle += degree;   
    }

    
    /** 
     * Set the selected shape in background
     */
    @Override
    public void sendToBack() {
        this.toBack();        
    }


    /** 
     * Set the selected shape in foreground
     */
    @Override
    public void sendToFront() {
        this.toFront();        
        
    }

    
    /** 
     * @return boolean
     * Return true if the shape can be drawn
     */
    @Override
    public boolean isDrawable() {
        return getCounter() == 2;
    }

    
    /** 
     * @param counter
     * Set the click counter of the selected shape
     */
    @Override
    public void setCounter(int counter) {
        this.counter = counter;        
    }

    
    /** 
     * @return int
     * Get the click counter of the selected shape
     */
    @Override
    public int getCounter() {
        return this.counter;
    }

    
    /** 
     * @param byX
     * Mirror the selected shape along the X-axis or the Y-axis according to the @param value 
     */
    @Override
    public void mirror(boolean byX) {
        if(byX)
            this.setScaleX(-1*this.getScaleX());
        else if (!byX)
            this.setScaleY(-1*this.getScaleY());         
    }

    
    /** 
     * @param textShape
     * @param textFontSize
     * Set the value of the text and fontsize attributes
     */
    @Override
    public void setTextString(String textShape, double textFontSize) {
        return;
        
    }


    /** 
     * @return String
     * Returns the points string without the square bracket  
     */
    private String getPointsWithoutParenthesis(){
        String s = super.getPoints().toString();
        s = s.replace("[", "");
        s = s.replace("]", "");
        s = s.replace(" ", "");
        return s;
    }

    
    /** 
     * @return String
     * Returns the string containing all the shape's properties
     */
    @Override
    public String toString() {
        return "LineShape,4," + this.getPointsWithoutParenthesis() + "," 
        + this.getTranslateX() + "," + this.getTranslateY() + "," + this.getStrokeWidth() 
        + ","+ this.getAngle() + "," + this.getBorder() + "," + this.getBackground();
    }


    /** 
     * @param str
     * Load a shape with all its properties on the anchorPane
     */
    @Override
    public void loadShapeFromText(String[] str) {
        String border, background;
        Double transX, transY, strokeWidth;
        int counter = Integer.parseInt(str[1]);
        int angle;

        ArrayList<Double> points = new ArrayList<>();
            for (int i = 2; i<counter+2; i++){
                points.add(Double.parseDouble(str[i]));
            }
            transX = Double.parseDouble(str[counter+2]);
            transY = Double.parseDouble(str[counter+3]);
            strokeWidth = Double.parseDouble(str[counter+4]);
            angle = Integer.parseInt(str[counter+5]);
            border = str[counter+6];
            background = str[counter+7];
            this.getPoints().addAll(points);
            this.setBorder(Color.web(border));
            this.setBackground(Color.web(background));
            this.setTranslateX(transX);
            this.setTranslateY(transY);
            this.setStrokeWidth(strokeWidth);  
            
            this.rotate(angle);
            
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LineShape other = (LineShape) obj;
        if (border == null) {
            if (other.border != null)
                return false;
        } else if (!border.equals(other.border))
            return false;
        if (background == null) {
            if (other.background != null)
                return false;
        } else if (!background.equals(other.background))
            return false;
        if (counter != other.counter)
            return false;
        if (points == null) {
            if (other.points != null)
                return false;
        } else if (!points.equals(other.points))
            return false;
        return true;
    }
    
    
    
}
