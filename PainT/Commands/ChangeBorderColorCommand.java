package Commands;

import GeneralShapes.GeneralShape;
import javafx.scene.paint.Color;

public class ChangeBorderColorCommand implements Command {

    GeneralShape shape;
    Color color;

    public ChangeBorderColorCommand(GeneralShape shape, Color color) {
        this.color = color;
        this.shape = shape;
    }
    
    
    /** 
     * Get for the shape attribute.
     * @return Shape
     */
    public GeneralShape getShape() {
        return shape;
    }

    
    /** 
     * Set for the shape attribute
     * @param shape
     */
    public void setShape(GeneralShape shape) {
        this.shape = shape;
    }

    
    /** 
     * Get for the color attribute
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    
    /** 
     * Set for the Color attribute
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the stroke of the shape present in attribute
     */
    @Override
    public void execute() {
        shape.setBorder(color);
        
    }
    
}
