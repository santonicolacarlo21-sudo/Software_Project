package Commands;

import GeneralShapes.GeneralShape;

public class CopyCommand implements Command{

    GeneralShape selectedShape = null;
    GeneralShape newShape = null;

    public CopyCommand(GeneralShape selectedShape) {
        this.selectedShape = selectedShape;
    }

    /**
     * It creates a new shape that is a copy of the selected shape passed in the constructor.
     */
    @Override
    public void execute() {
        newShape = selectedShape.cloneShape();
    }

    
    /** 
     * @return GeneralShape
     * Return the selected shape copy
     */
    public GeneralShape getCopy(){
        return newShape;
    }

}

