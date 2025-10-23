package GeneralShapes;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;


public class RectangleShape extends Rectangle implements GeneralShape{
    private double width = 150, height = 100;
    private Color border, background;
    private double x,y;
    private int counter = 0;
    private int angle;

    public RectangleShape(Color background, Color border) {
        super();
        this.setWidth(width);
        this.setHeight(height);
        this.border = border;
        this.background = background;
        this.angle = 0;
    }

    public RectangleShape() {
    }

    
    /** 
     * @param positions
     * Create a shape setting all the properties and the coordinates at the values specified by @param
     */
    @Override
    public void draw(ArrayList<Double> positions) {
        this.setX(positions);
        this.setY(positions);
        this.setFill(background);
        this.setStroke(border);
    }

    
    /** 
     * @return GeneralShape
     * Create a new shape with the same properties of the selected shape
     */
    @Override
    public GeneralShape cloneShape() {
        RectangleShape rect = new RectangleShape(); 
        rect.setBackground(background);
        rect.setBorder(border);
        rect.setWidth(this.getWidth());
        rect.setTranslateX(this.getTranslateX());
        rect.setTranslateY(this.getTranslateY());
        //rect.getTransforms().addAll(this.getTransforms());
        rect.setHeight(this.getHeight());
        rect.setX(this.getX());
        rect.setY(this.getY()); 
        rect.angle = this.getAngle();       
        return rect;
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
        System.out.println(angle);
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
        this.width = this.getWidth()*percentage/100;
        this.height = this.getHeight()*percentage/100;
        setWidth(width);
        setHeight(height);
       
    }

    
    /** 
     * @param percentage
     * Stretch horizontally the selected shape to the percentage specified by @param
     */
    @Override
    public void stretchX(double percentage) {
        setWidth(this.getWidth()*percentage/100);
    }

    
    /** 
     * @param percentage
     * Stretch vertically the selected shape to the percentage specified by @param
     */
    @Override
    public void stretchY(double percentage) {
        setHeight(this.getHeight()*percentage/100);

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
        return getCounter() == 1;
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
     * @param x
     * Set the X-axis coordinate
     */
    @Override
    public void setX(ArrayList<Double> x) {
        this.setX(x.get(0));  
        this.x = x.get(0);

    }

    
    /** 
     * @param y
     * Set the Y-axis coordinate
     */
    @Override
    public void setY(ArrayList<Double> y) {
        this.setY(y.get(1));        
        this.y = y.get(1);

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
    public void loadShapeFromText(String str []){
        Double width, height, transX, transY;
        String border, background;
        int counter = Integer.parseInt(str[1]);
        int angle;
        ArrayList<Double> points = new ArrayList<>();
            for (int i = 2; i<counter+2; i++){
                points.add(Double.parseDouble(str[i]));
            }
            transX = Double.parseDouble(str[counter+2]);
            transY = Double.parseDouble(str[counter+3]);
            width = Double.parseDouble(str[counter+4]);
            height = Double.parseDouble(str[counter+5]);
            angle = Integer.parseInt(str[counter+6]);
            border = str[counter+7];
            background = str[counter+8];
            this.setX(points.get(0));
            this.setY(points.get(1));
            this.setWidth(width);
            this.setHeight(height);
            this.setBorder(Color.web(border));
            this.setBackground(Color.web(background));
            this.setTranslateX(transX);
            this.setTranslateY(transY);

            this.rotate(angle);
        }

    /** 
     * @return String
     * Returns the string containing all the shape's properties
     */
    @Override
    public String toString() {
        return ("RectangleShape," + 2 + "," + this.getX() + "," + this.getY() + 
        "," + this.getTranslateX() + "," + this.getTranslateY() + "," + this.getWidth() +
        "," + this.getHeight() + "," + this.getAngle() + "," + this.getBorder() + "," + this.getBackground());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RectangleShape other = (RectangleShape) obj;
        if (this.getX() != other.getX())
            return false;
        if (this.getY() != other.getY())
            return false;
        if(this.getTranslateX() != other.getTranslateX())
            return false;
        if(this.getTranslateY() != other.getTranslateY())
            return false;
        if(!this.getBorder().equals(other.getBorder()))
            return false;
        if(!this.getBackground().equals(other.getBackground()))
            return false;
        if(this.getWidth() != other.getWidth())
            return false;
        if(this.getHeight() != other.getHeight())
            return false;
        return true;
    }



}
