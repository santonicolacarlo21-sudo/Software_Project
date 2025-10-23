package Creators;

import GeneralShapes.GeneralShape;
import GeneralShapes.PolygonShape;
import javafx.scene.paint.Color;

public class CreatorPolygon extends CreatorShape{

    /** 
     * @param border
     * @param background
     * @return GeneralShape
     * Return a GeneralShape Polygon
     */
    @Override
    public GeneralShape createGeneralShape(Color border, Color background) {
        return new PolygonShape(background, border);
    }
    
}
