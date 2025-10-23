package Creators;

import GeneralShapes.GeneralShape;
import GeneralShapes.RectangleShape;
import javafx.scene.paint.Color;

public class CreatorRectangle extends CreatorShape{

    /** 
     * @param border
     * @param background
     * @return GeneralShape
     * Return a GeneralShape Rectangle
     */
    @Override
    public GeneralShape createGeneralShape(Color border, Color background) {
        return new RectangleShape(background, border);
    }

    

    
    
}
