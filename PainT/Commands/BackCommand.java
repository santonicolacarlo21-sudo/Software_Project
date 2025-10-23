package Commands;

import GeneralShapes.GeneralShape;

public class BackCommand implements Command{

    GeneralShape selectedShape;

    

    public BackCommand(GeneralShape selectedShape) {
        this.selectedShape = selectedShape;
    }
    
    /**
     * Puts the shape in the attribute to the back of other overlapped shapes.
     */
    @Override
    public void execute() {
        selectedShape.sendToBack();
        
    }
    
}
