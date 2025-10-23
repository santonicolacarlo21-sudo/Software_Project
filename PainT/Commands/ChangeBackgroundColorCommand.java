package Commands;

import GeneralShapes.GeneralShape;
import javafx.scene.paint.Color;

public class ChangeBackgroundColorCommand implements Command{

    GeneralShape shape;
    Color color;

    public ChangeBackgroundColorCommand(GeneralShape shapeCreation, Color c) {
        this.color = c;
        this.shape = shapeCreation;
    }
    
    
    /** 
     * Get function for the Shape selected for the creation
     * @return Shape
     */
    public GeneralShape getShape() {
        return shape;
    }

    
    /** 
     * Set function for the Shape selected for the creation.
     * @param shape
     */
    public void setShape(GeneralShape shape) {
        this.shape = shape;
    }

    
    /** 
     * Get function for the color of the shape.
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    
    /** 
     * Set function for the color of the shape.
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Fills the shape with the color attribute
     */
    @Override
    public void execute() {
        shape.setBackground(color);
        
    }
    
}
