package Creators;

import GeneralShapes.GeneralShape;
import GeneralShapes.ElipseShape;
import javafx.scene.paint.Color;

public class CreatorElipse extends CreatorShape{

    
    /** 
     * @param border
     * @param background
     * @return GeneralShape
     * Return a GeneralShape Elipse
     */
    @Override
    public GeneralShape createGeneralShape( Color border, Color background) {
        return new ElipseShape(background,border);
    }
    
}
