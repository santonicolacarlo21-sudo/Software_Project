package Commands;

import GeneralShapes.GeneralShape;

public class ResizeCommand implements Command{
    private GeneralShape shape;
    private double percentage;

    public ResizeCommand(GeneralShape shape, double percentage) {
        this.shape = shape;
        this.percentage = percentage;
    }

    /**
     * Call the effective resize method of the selected Shape
     * It calculates the new charateristic of the shape based on the percentage inserted and then updates
     * the shape displayed.
     */
    @Override
    public void execute() {
        shape.resize(percentage);
    }
    
}
