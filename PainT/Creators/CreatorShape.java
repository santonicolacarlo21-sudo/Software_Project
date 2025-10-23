package Creators;



import GeneralShapes.GeneralShape;
import javafx.scene.paint.Color;

public abstract class CreatorShape {
    public abstract GeneralShape createGeneralShape( Color border, Color background);
    public GeneralShape figuraGenerica;

    
    /** 
     * @param border
     * @param background
     * Constructor
     */
    public void createShape( Color border, Color background){
        figuraGenerica = createGeneralShape( border, background);
        
    }

    

}
