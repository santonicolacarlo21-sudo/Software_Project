package Commands;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Creators.CreatorElipse;
import Creators.CreatorLine;
import Creators.CreatorPolygon;
import Creators.CreatorRectangle;
import Creators.CreatorShape;
import Creators.CreatorText;
import GeneralShapes.GeneralShape;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class OpenCommand implements Command{

    private AnchorPane drawPane;
    private Color border;
    private Color background;
    private ArrayList<GeneralShape> shapesArray = new ArrayList<>();

    public OpenCommand(AnchorPane drawPane, Color border, Color background) {
        this.drawPane = drawPane;
        this.border = border;
        this.background = background;
    }


    @Override
    public void execute() {

        drawPane.getChildren().clear();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt"));
        File file = fileChooser.showOpenDialog(null);

        try (Scanner myScan = new Scanner(file)) {
            CreatorShape shape = null;
            while(myScan.hasNextLine()){
                String lineShape = myScan.nextLine();
                String lineArray[] = lineShape.split(",");
                String tipo = lineArray[0];

                if (tipo.equals("PolygonShape"))
                    shape = new CreatorPolygon();
                else if (tipo.equals("RectangleShape"))
                    shape = new CreatorRectangle();
                else if(tipo.equals("ElipseShape"))
                    shape = new CreatorElipse();
                else if(tipo.equals("LineShape"))
                    shape = new CreatorLine();
                else if(tipo.equals("TextShape"))
                    shape = new CreatorText();

                shape.createShape(border, background);
                shape.figuraGenerica.loadShapeFromText(lineArray);

                shapesArray.add((GeneralShape) shape.figuraGenerica);
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    public ArrayList<GeneralShape> getShapes() {
        return this.shapesArray;
    }

    
    
}
