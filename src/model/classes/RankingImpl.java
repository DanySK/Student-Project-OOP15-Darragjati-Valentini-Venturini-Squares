package model.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.enumerations.RankingOption;
import model.interfaces.Ranking;

public class RankingImpl implements Ranking {

    private List<Player> playerList = new ArrayList<>();

    public RankingImpl(List<Player> playerList) {
        this.playerList = playerList;
    }

    @Override
    public void addPlayerResults(String playerName, boolean victory, Integer totalSquaresCatched) {

        for (Player p : playerList) {
            if (p.getPlayerName().equals(playerName)) {
                p.addLastMatchResults(victory, totalSquaresCatched);
                return;
            }
        }

        Player newPlayer = new Player(playerName);
        newPlayer.addLastMatchResults(victory, totalSquaresCatched);
        playerList.add(newPlayer);
    }

    public List<Player> orderListBy(RankingOption option) {

        
        switch (option) {
        case WINRATE:
            playerList.sort(new WinRateComparator());
            break;
        case TOTAL_WINS:
            playerList.sort(new TotalWinsComparator());
            break;
        case TOTAL_MATCHES:
            playerList.sort(new TotalMatchesComparator());
            break;
        case TOTAL_SQUARES_CATCHED:
            playerList.sort(new TotalSquaresCatchedComparator());
            break;
        default:
            throw new IllegalStateException("There is a bug here.");
        }

        return Collections.unmodifiableList(playerList);
    }
    
    public List<Player> reverseRanking(RankingOption option){
        
        List<Player> reverseList = new ArrayList<>();
        
        for(int i = orderListBy(option).size()-1; i > 0; i-- ){
            reverseList.add(orderListBy(option).get(i));
        }
        return reverseList;
    }
    
}
