package Commands;

import GeneralShapes.GeneralShape;

public class MirrorCommand implements Command {

    private GeneralShape shape;
    private boolean byX;

    
    public MirrorCommand(GeneralShape shape, boolean byX) {
        this.shape = shape;
        this.byX = byX;
    }


    /**
     * Call the effective mirror method of the selected Shape
     * It mirrors the shape along the X or Y axis according to the byX value
     */
    @Override
    public void execute() {
         shape.mirror(byX);
    }
    
}
