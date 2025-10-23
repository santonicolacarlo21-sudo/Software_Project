package Creators;

import GeneralShapes.GeneralShape;
import GeneralShapes.LineShape;
import javafx.scene.paint.Color;

public class CreatorLine extends CreatorShape {

    
    /** 
     * @param border
     * @param background
     * @return GeneralShape
     * Return a GeneralShape Line
     */
    @Override
    public GeneralShape createGeneralShape(Color border, Color background) {
        return new LineShape (border, background);
    }
    
}
