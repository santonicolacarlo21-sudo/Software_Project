package Commands;

import GeneralShapes.GeneralShape;

public class FrontCommand implements Command{
    
    GeneralShape generalShape;
    //Shape generalShape;

    public FrontCommand(GeneralShape generalShape) {
        this.generalShape = generalShape;
    }

    /**
     * Puts the selected shape to the front of any overlapped shapes
     */
    @Override
    public void execute() {
        generalShape.sendToFront();
        
    }
    
}
