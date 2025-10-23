package Commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class SaveCommand implements Command {

    private AnchorPane drawPane;

    
    public SaveCommand(AnchorPane drawPane) {
        this.drawPane = drawPane;
    }


    /**
     * It saves all the shapes of the drawing pane and theri properties to the file selected
     * through the file chooser.
     */
    @Override
    public void execute() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(null);

        try (FileWriter out = new FileWriter(file)) {
            for (Node g : drawPane.getChildren()){
                out.write(g.toString()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            
            
        
    }
    
}
