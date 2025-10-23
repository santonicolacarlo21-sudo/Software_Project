package Commands;

import GeneralShapes.GeneralShape;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;


public class DeleteCommand implements Command{

    AnchorPane drawPane;
    GeneralShape shape;
    

    public DeleteCommand(AnchorPane drawPane, GeneralShape shape) {
        this.drawPane = drawPane;
        this.shape = shape;
    }

    /**
     * Delete the selected shape
     */
    @Override
    public void execute() {
        System.out.println("Sto rimuovendo "+ shape);
        drawPane.getChildren().remove((Node) shape);
    }
    
}
