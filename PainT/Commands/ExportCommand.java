package Commands;


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class ExportCommand implements Command{

    AnchorPane drawPane;
    
    public ExportCommand(AnchorPane drawPane) {
        this.drawPane = drawPane;
    }
    

    /**
     * It creates the snapshot of the drawing pane and the save it to the file selected
     * through the file chooser.
     */
    @Override
    public void execute() {
        
        WritableImage image = drawPane.snapshot(new SnapshotParameters(), null);
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
        File file = fileChooser.showSaveDialog(null);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        
    }
    
}
