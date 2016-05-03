package controller.classes;

import java.io.BufferedReader;
import java.io.FileReader;

public class Menu {

    private String lineRead;

    public String getLineRead() {
        return this.lineRead;
    }

    // public Menu(String directory) {
    // this.directory = directory;
    // }

    public void startGame(int numColonne, int numRighe) {
        StartGame game = new StartGame(numColonne, numRighe);
        
    }

    public String showRules() {

        FileReader f = new FileReader("");

        BufferedReader b = new BufferedReader(f);

        return getLineRead();

    }
    
    public String showRanking(){
        return ;
    }
    
    public void setting(){
        
    }
    
    public void exit(){
    }
}
