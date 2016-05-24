package it.unibo.squaresgameteam.squares.controller.interfaces;

import java.io.IOException;

import it.unibo.squaresgameteam.squares.controller.enumerations.TypeGame;

public interface Menu {
    
    String showRules() throws IOException;
    
    Match createMatch(int columsNumber, int rowsNumber, String namePlayer1, String namePlayer2, TypeGame mode);
    
    ShowRanking createRankingClass();
    
    void exitApp();

}
