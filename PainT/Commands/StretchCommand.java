package Commands;

import GeneralShapes.GeneralShape;

public class StretchCommand implements Command{
    private GeneralShape shape;
    private double percentageX;
    private double percentageY;

    public StretchCommand(GeneralShape shape, double percentageX, double percentageY) {
        this.shape = shape;
        this.percentageX = percentageX;
        this.percentageY = percentageY;
    }

    /** 
     * Call the effective stretch method of the selected Shape
     * It calculates the new charateristic of the shape based on the percentage inserted and then updates
     * the shape displayed.
     */
    @Override
    public void execute() {
        shape.stretch(percentageX, percentageY);
    }

    

}
