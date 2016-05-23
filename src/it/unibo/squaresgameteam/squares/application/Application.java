package it.unibo.squaresgameteam.squares.application;

import java.io.IOException;

import it.unibo.squaresgameteam.squares.controller.classes.MenuImpl;
import it.unibo.squaresgameteam.squares.controller.interfaces.Menu;

public class Application {  

    public static void main(String[] args) throws IOException{
        
        Menu newMenu = new MenuImpl();
     
        String print = newMenu.rules();
        System.out.println(print);
       
        
        
    }
}
