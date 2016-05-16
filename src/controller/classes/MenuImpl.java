package controller.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.interfaces.Menu;
import controller.interfaces.ShowRanking;

public class MenuImpl implements Menu {
    
    private String rules;
    
    public MenuImpl() {
        
    }
    
    public String rules() throws IOException{
        
        
        InputStream readFile = ClassLoader.class.getResourceAsStream("res/Rules.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(readFile, "UTF8"))) {
            String s;
            while ((s = br.readLine()) != null) {
                this.rules = this.rules.concat(s);

            }
        } catch (IOException ex) {
            throw new IOException();
        }
        readFile.close(); 
        return this.rules;
        
    }
    
    public void exitApp(){
        System.exit(0);
    }   
    

}
