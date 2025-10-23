package GeneralShapes;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Rotate;

public class ElipseShape extends Ellipse implements GeneralShape{

    Color border, background;
    double radiusX = 100, radiusY = 50;
    int angle;

    public ElipseShape( Color background, Color border){
        super();
        this.setRadiusX(radiusX);
        this.setRadiusY(radiusY);
        this.border = border;
        this.background = background;
        this.angle = 0;
    }

    public ElipseShape() {
    }

    
    /** 
     * @param positions
     * Create a shape setting all the properties and the coordinates at the values specified by @param
     */
    @Override
    public void draw(ArrayList<Double> positions){
        this.setX(positions);
        this.setY(positions);
        this.setFill(background);
        this.setStroke(border);
    }

    
    /** 
     * @param x
     * Set the X-axis coordinate
     */
    @Override
    public void setX(ArrayList<Double> x){
        this.setCenterX(x.get(0));
        
    }

    
    /** 
     * @param y
     * Set the Y-axis coordinate
     */
    @Override
    public void setY(ArrayList<Double> y){
        this.setCenterY(y.get(1));
        
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
     * @return GeneralShape
     * Create a new shape with the same properties of the selected shape
     */
    @Override
    public GeneralShape cloneShape() {
        ElipseShape el = new ElipseShape(); 
        el.setBackground(background);
        el.setBorder(border);
        el.setScaleX(this.getScaleX());
        el.setScaleY(this.getScaleY());
        el.setTranslateX(this.getTranslateX());
        el.setTranslateY(this.getTranslateY());
        el.setRadiusX(this.getRadiusX());
        el.setRadiusY(this.getRadiusY());
        el.setCenterX(this.getCenterX());
        el.setCenterY(this.getCenterY());
        el.angle = this.getAngle();
        return el;
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
     * Return the center X-coordinate
     */
    @Override
    public double getX() {
        return this.getCenterX();
    }

    
    /** 
     * @return double
     * Return the center Y-coordinate
     */
    @Override
    public double getY() {
        return this.getCenterY();
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
     * @param percentage
     * Resize the selected shape to the percentage specified by @param
     */
    @Override
    public void resize(double percentage) {
        this.radiusX = this.getRadiusX()*percentage/100;
        this.radiusY = this.getRadiusY()*percentage/100;
        setRadiusX(radiusX);
        setRadiusY(radiusY);
    }

    
    /** 
     * @param percentage
     * Stretch horizontally the selected shape to the percentage specified by @param
     */
    @Override
    public void stretchX(double percentage) {
        setRadiusX(this.getRadiusX()*percentage/100);

    }

    
    /** 
     * @param percentage
     * Stretch vertically the selected shape to the percentage specified by @param
     */
    @Override
    public void stretchY(double percentage) {
        setRadiusY(this.getRadiusY()*percentage/100);

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
     * @param byX
     * Mirror the selected shape along the X-axis or the Y-axis according to the @param value
     */
    @Override
    public void mirror(boolean byX) {
        
        if(byX && this.getScaleX()==1)
            this.setScaleX(-1);
        else if (byX && this.getScaleX()==-1)
            this.setScaleX(1);
        else if (!byX && this.getScaleY()==1)
            this.setScaleY(-1);
        else 
            this.setScaleY(1);
        
    }

    
    /** 
     * @return boolean
     * Return true if the shape can be drawn
     */
    @Override
    public boolean isDrawable() {
        return true;
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
        return 0;
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
     * Returns the string containing all the shape's properties
     */
    @Override
    public String toString() {
        return ("ElipseShape," + 2 + "," + this.getX() + "," + this.getY() + "," 
        + this.getTranslateX() + "," + this.getTranslateY() + "," + this.getRadiusX() 
        + "," + this.getRadiusY() + "," + this.getAngle() + "," + this.getBorder() + "," 
        + this.getBackground());
    }


    /** 
     * @param str
     * Load a shape with all its properties on the anchorPane
     */
    @Override
    public void loadShapeFromText(String[] str) {
        Double radiusX, radiusY, transX, transY;
        String border, background;
        int counter = Integer.parseInt(str[1]);
        int angle;

        ArrayList<Double> points = new ArrayList<>();
            for (int i = 2; i<counter+2; i++){
                points.add(Double.parseDouble(str[i]));
            }

            transX = Double.parseDouble(str[counter+2]);
            transY = Double.parseDouble(str[counter+3]);
            radiusX = Double.parseDouble(str[counter+4]);
            radiusY = Double.parseDouble(str[counter+5]);
            angle = Integer.parseInt(str[counter+6]);
            border = str[counter+7];
            background = str[counter+8];
            this.setCenterX((points.get(0)));
            this.setCenterY((points.get(1)));
            this.setRadiusX(radiusX);
            this.setRadiusY(radiusY);
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
        ElipseShape other = (ElipseShape) obj;
        if(this.getX() != other.getX())
            return false;
        if(this.getY() != other.getY())
            return false;
        if(this.getTranslateX() != other.getTranslateX())
            return false;
        if(this.getRadiusX() != other.getRadiusX())
            return false;
        if(this.getRadiusY() != other.getRadiusY())
            return false;
        if(!this.getBorder().equals(other.getBorder()))
            return false;
        if(!this.getBackground().equals(other.getBackground()))
            return false;
        return true;
    }

   
    

    
}
