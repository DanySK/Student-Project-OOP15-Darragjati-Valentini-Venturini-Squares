package it.unibo.squaresgameteam.squares.application;

import java.io.IOException;

import it.unibo.squaresgameteam.squares.controller.classes.MenuImpl;
import it.unibo.squaresgameteam.squares.controller.classes.ShowRankingImpl;
import it.unibo.squaresgameteam.squares.controller.interfaces.Menu;
import it.unibo.squaresgameteam.squares.controller.interfaces.ShowRanking;
import it.unibo.squaresgameteam.squares.model.enumerations.RankingOption;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;

public class Application {  

    public static void main(String[] args) throws IOException, DuplicatedPlayerStatsException{
        String s;
        ShowRanking ranking = new ShowRankingImpl();
        s = ranking.showRanking(RankingOption.TOTAL_MATCHES, false);
       System.out.println(s);
        
        
    }
}
