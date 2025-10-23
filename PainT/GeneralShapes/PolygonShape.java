package GeneralShapes;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class PolygonShape extends Polygon implements GeneralShape{

    private Color border, background;
    private ArrayList<Double> points;
    private int angle;

    public PolygonShape(Color background, Color border) {
        super();
        this.border = border;
        this.background = background;
        this.setStrokeWidth(3);
        this.angle += 0;
    }

    public PolygonShape() {
        super();
    }


    /** 
     * @return int
     * Return the angle value of the shape
     */
    public int getAngle(){
        return angle;
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
        return "PolygonShape,"+this.getPoints().size()+","+this.getPointsWithoutParenthesis()+","+this.getTranslateX()+","+this.getTranslateY()+","+ this.getAngle() + "," + this.getBorder()+","+this.getBackground();
    }

    /** 
     * @param positions
     * Create a shape setting all the properties and the coordinates at the values specified by @param
     */
    @Override
    public void draw(ArrayList<Double> positions) {
        this.getPoints().addAll(positions);
        this.setBorder(border);
        this.setBackground(background);
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
        for(int i = 1; i<this.getPoints().size(); i+=2){
            this.getPoints().set(i, yPaste + (this.getPoints().get(i)-anchorY));
        }
    }

    
    /** 
     * @return GeneralShape
     * Create a new shape with the same properties of the selected shape
     */
    @Override
    public GeneralShape cloneShape() {
        PolygonShape poly = new PolygonShape();
        poly.setBackground(background);
        poly.setBorder(border);
        poly.setScaleX(this.getScaleX());
        poly.setTranslateX(this.getTranslateX());
        poly.setTranslateY(this.getTranslateY());
        poly.setScaleY(this.getScaleY());
        poly.setStrokeWidth(3);
        poly.getPoints().addAll(this.getPoints());
        poly.angle = this.getAngle();
        return poly;
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
     * @param c
     * Set the border color of the selected shape
     */
    @Override
    public void setBorder(Color c) {
        this.border = c;
        this.setStroke(c);
    }

    
    /** 
     * @param c
     * Set the background color of the selected shape
     */
    @Override
    public void setBackground(Color c) {
        this.background = c;
        this.setFill(c);        
    }

    
    /** 
     * @param degree
     * Rotate the selected shape to the arbitrary angle specified by @param
     */
    @Override
    public void rotate(int degree) {
        Rotate rotateTransf = new Rotate(degree,this.getX(),this.getY());
        this.getTransforms().add(rotateTransf);     
        this.angle = degree;    
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
     * @param percentage
     * Resize the selected shape to the percentage specified by @param
     */
    @Override
    public void resize(double percentage) {
        this.stretchX(percentage);
        this.stretchY(percentage);   
        this.setStrokeWidth(5);    
    }

    
    /** 
     * @param percentage
     * Stretch horizontally the selected shape to the percentage specified by @param
     */
    @Override
    public void stretchX(double percentage) {
        this.setScaleX(getScaleX()*percentage/100);
    }

    
    /** 
     * @param percentage
     * Stretch vertically the selected shape to the percentage specified by @param
     */
    @Override
    public void stretchY(double percentage) {
        this.setScaleY(getScaleY()*percentage/100);
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
     * @return boolean
     * Return true if the shape can be drawn 
     */
    @Override
    public boolean isDrawable() {
        return false;
    }

    
    /** 
     * @param counter
     * Set the click counter of the selected shape
     */
    @Override
    public void setCounter(int counter) {
        return;        
    }

    
    /** 
     * @return int
     * Get the click counter of the selected shape
     */
    @Override
    public int getCounter() {
        return this.getCounter();
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
     * @param str
     * Load a shape with all its properties on the anchorPane
     */
    @Override
    public void loadShapeFromText(String str []) {
        String border, background;
        Double transX, transY;
        int angle;
        int counter = Integer.parseInt(str[1]);
        ArrayList<Double> points = new ArrayList<>();
            for (int i = 2; i<counter+2; i++){
                points.add(Double.parseDouble(str[i]));
            }
            transX = Double.parseDouble(str[counter+2]);
            transY = Double.parseDouble(str[counter+3]);
            angle = Integer.parseInt(str[counter+4]);
            border = str[counter+5];
            background = str[counter+6];
            this.getPoints().addAll(points);
            this.setBorder(Color.web(border));
            this.setBackground(Color.web(background));
            this.setTranslateX(transX);
            this.setTranslateY(transY);

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
        PolygonShape other = (PolygonShape) obj;
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
        if (points == null) {
            if (other.points != null)
                return false;
        } else if (!points.equals(other.points))
            return false;
        return true;
    }     

        
}
    

