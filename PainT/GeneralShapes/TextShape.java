package GeneralShapes;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class TextShape extends Text implements GeneralShape{

    private double fontSize= 20;
    private Color border, background;
    private double x,y;
    private String text;
    private int angle;

    
    public TextShape( Color border, Color background) {
        super();
        this.border = border;
        this.background = background;
        this.angle = 0;
    }

    public TextShape(){

    }

    
    /** 
     * @param positions
     * Create a shape setting all the properties and the coordinates at the values specified by @param
     */
    @Override
    public void draw(ArrayList<Double> positions) {
        this.setFont(new Font(fontSize));
        this.setText(text);
        this.setStroke(border);
        this.setFill(background);
        this.setX(positions.get(0));
        this.setY(positions.get(1));
    }

    
    /** 
     * @return GeneralShape
     * Create a new shape with the same properties of the selected shape
     */
    @Override
    public GeneralShape cloneShape() {
        TextShape text = new TextShape(); 
        text.setBackground(background);
        text.setBorder(border);
        text.setScaleX(this.getScaleX());
        text.setScaleY(this.getScaleY());
        text.setTranslateX(this.getTranslateX());
        text.setTranslateY(this.getTranslateY());
        text.setFont(this.getFont());
        text.setText(this.getText());
        text.setX(this.getX());
        text.setY(this.getY());
        text.angle = this.getAngle();
        
        return text;
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
       this.setFont(new Font(fontSize*percentage/100));
       this.setFont(new Font(fontSize*percentage/100));

    }

    
    /** 
     * @param percentage
     * Stretch horizontally the selected shape to the percentage specified by @param
     */
    @Override
    public void stretchX(double percentage) {
       this.setScaleX(percentage/100); 
       
    }

    
    /** 
     * @param percentage
     * Stretch vertically the selected shape to the percentage specified by @param
     */
    @Override
    public void stretchY(double percentage) {
        this.setScaleY(percentage/100);
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
        this.text = textShape;
        this.fontSize = textFontSize;
        
    }

    
    /** 
     * @return String
     * Returns the string containing all the shape's properties
     */
    @Override
    public String toString() {
        return "TextShape,2," + this.getX() + "," + this.getY() + "," + this.getTranslateX() 
        + "," + this.getTranslateY() + "," + this.getFont().getSize() + "," + this.getText()
        + "," + this.getAngle() + "," + this.getBorder() + "," + this.getBackground() ;
    }

    
    /** 
     * @param str
     * Load a shape with all its properties on the anchorPane
     */
    @Override
    public void loadShapeFromText(String[] str){
        Double transX, transY;
        String border, background, text;
        Double fontSize;
        int counter = Integer.parseInt(str[1]);
        int angle;

        ArrayList<Double> points = new ArrayList<>();
            for (int i = 2; i<counter+2; i++){
                points.add(Double.parseDouble(str[i]));
            }
            transX = Double.parseDouble(str[counter+2]);
            transY = Double.parseDouble(str[counter+3]);
            fontSize = Double.parseDouble(str[counter+4]);
            text = str[counter+5];
            angle = Integer.parseInt(str[counter+6]);
            border = str[counter+7];
            background = str[counter+8];
            this.setX(points.get(0));
            this.setY(points.get(1));
            this.setBorder(Color.web(border));
            this.setBackground(Color.web(background));
            this.setFont(new Font(fontSize));
            this.setTranslateX(transX);
            this.setTranslateY(transY);     
            this.setText(text);   

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
        TextShape other = (TextShape) obj;
        if (this.getX() != other.getX())
            return false;
        if (this.getY() != other.getY())
            return false;
        if (this.getTranslateX() != other.getTranslateX())
            return false;
        if (this.getTranslateY() != other.getTranslateY())
            return false;
        if (this.getFont().getSize() != other.getFont().getSize())
            return false;
        if (!this.getText().equals(other.getText()))
            return false;
        if (this.getAngle() != other.getAngle())
            return false;
        if(!this.getBorder().equals(other.getBorder()))
            return false;
        if(!this.getBackground().equals(other.getBackground()))
            return false;
        return true;
    }  

    
}
