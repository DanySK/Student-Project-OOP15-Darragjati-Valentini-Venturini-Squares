package it.unibo.squaresgameteam.squares.controller.classes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import it.unibo.squaresgameteam.squares.controller.enumerations.TypeGame;
import it.unibo.squaresgameteam.squares.controller.interfaces.Match;
import it.unibo.squaresgameteam.squares.controller.interfaces.Menu;
import it.unibo.squaresgameteam.squares.controller.interfaces.ShowRanking;

public class MenuImpl implements Menu {

    private String rules;

    public MenuImpl() {
        
    }

    public Match createMatch(int columsNumber, int rowsNumber, String namePlayer1, String namePlayer2, TypeGame mode){
        return  new MatchImpl(columsNumber, rowsNumber, namePlayer1, namePlayer2, mode);
        
    }
    public String showRules() throws IOException {

        final String txtDirectory = ClassLoader.class.getResource("/Rules.txt").getPath();
        //final FileReader in = new FileReader(txtDirectory);
        

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(txtDirectory), "UTF8"))) {
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                this.rules = this.rules + s;

            }
        } catch (IOException ex) {
            throw new IOException();
        }
        
        return this.rules;

    }
    
    public ShowRanking createRankingClass(){     
       return  new ShowRankingImpl();
    }

    public void exitApp() {
        System.exit(0);
    }

}
