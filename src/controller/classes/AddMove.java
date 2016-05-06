package controller.classes;

import model.enumerations.GridOption;
import model.enumerations.ListType;

public class AddMove {
    private ListType direction;
    private int position;
    private int numLine;
    
    public AddMove(ListType direction, int numLine, int position){
        this.direction = direction;
        this.numLine = numLine;
        this.position = position;
                
    }
    
    public void addLine(){
        
    }
}
