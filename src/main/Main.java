package main;

import controller.interfaces.Menu;

import java.io.IOException;

import controller.classes.MenuImpl;

/**
 * 
 * 
 *
 */
public class Main {
    

    public static void main(String[] args) throws IOException{
        
        Menu newMenu = new MenuImpl();
     
        String print = newMenu.rules();
        System.out.println(print);
       
        
        
    }
}
