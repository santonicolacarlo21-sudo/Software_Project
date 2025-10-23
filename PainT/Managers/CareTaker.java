package Managers;
import java.util.Stack;

import Main.testController;


public class CareTaker {
    
    private Stack<testController.AnchorPaneMemento> history = new Stack<>();
    
    
    /** 
     * @param main
     */
    public void save(testController main){
        history.push(main.save());
    }

    public void reset(){
        this.history.clear();
    }
    
    /** 
     * @param main
     */
    public void revert(testController main){
        if(!history.isEmpty()){
            main.revert(history.pop());
        }
        else
            System.out.println("Cannot Undo");
    }

}
