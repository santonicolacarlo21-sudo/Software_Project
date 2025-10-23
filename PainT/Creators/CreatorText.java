package Creators;

import GeneralShapes.GeneralShape;
import GeneralShapes.TextShape;
import javafx.scene.paint.Color;

public class CreatorText extends CreatorShape{

    /** 
     * @param border
     * @param background
     * @return GeneralShape
     * Return a GeneralShape Text
     */
    @Override
    public GeneralShape createGeneralShape(Color border, Color background) {
        return new TextShape (border, background);
    }
    
}
