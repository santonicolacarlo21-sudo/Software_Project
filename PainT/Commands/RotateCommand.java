package Commands;

import GeneralShapes.GeneralShape;

public class RotateCommand implements Command{

    GeneralShape shape;
    int degree;

    
    public RotateCommand(GeneralShape shape, int degree) {
        this.shape = shape;
        this.degree = degree;
    }


    /** 
     * 
     * Call the effective rotate method of the selected Shape.
     * Used to rotate of a specified angle the shape about the first corner
     */
    @Override
    public void execute() {
        
        shape.rotate(degree);
        
        
    }
    
}
