package it.unibo.squaresgameteam.squares.application;

import java.io.IOException;

import it.unibo.squaresgameteam.squares.controller.classes.ShowRankingImpl;
import it.unibo.squaresgameteam.squares.model.enumerations.RankingOption;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;



public class Application {  

    public static void main(String[] args) throws IOException, ClassNotFoundException, DuplicatedPlayerStatsException {
        String s; 
       
       ShowRankingImpl ranking = new ShowRankingImpl();
       s = ranking.showRanking(RankingOption.WINRATE, false);
       System.out.println(s);
       
       
//       Player player1 = new PlayerImpl.Builder().playerName("Ciaone").wonMatches(15).totalMatches(24).totalPointsScored(68).build();
//
//       Player player2 = new PlayerImpl.Builder().playerName("Ciaone2").wonMatches(32).totalMatches(52).totalPointsScored(103).build();
//
//       Player player3 = new PlayerImpl.Builder().playerName("Ciaone3").wonMatches(4).totalMatches(30).totalPointsScored(36).build();
//       ranking.addPlayer(player1);
//       ranking.addPlayer(player2);
//       ranking.addPlayer(player3);
//      
//       s = ranking.showRanking(RankingOption.TOTAL_WINS, false);
//       System.out.println(s);
//       Menu rules = new MenuImpl();
//       s = rules.showRules();
//       System.out.println(s);
       System.exit(0);
       
       
        
       
        
    }
}
