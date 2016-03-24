package model.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.enumerations.RankingOption;
import model.interfaces.Ranking;

public class RankingImpl implements Ranking {

    private List<PlayerImpl> playerList = new ArrayList<>();

    public RankingImpl(List<PlayerImpl> playerList) {
        this.playerList = playerList;
    }

    @Override
    public void addPlayerResults(String playerName, boolean victory, Integer totalSquaresCatched) {

        for (PlayerImpl p : playerList) {
            if (p.getPlayerName().equals(playerName)) {
                p.addLastMatchResults(victory, totalSquaresCatched);
                return;
            }
        }

        PlayerImpl newPlayer = new PlayerImpl(playerName);
        newPlayer.addLastMatchResults(victory, totalSquaresCatched);
        playerList.add(newPlayer);
    }

    @Override
    public List<PlayerImpl> orderListBy(RankingOption option) {

        
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
    
    @Override
    public List<PlayerImpl> reverseRanking(RankingOption option){
        
        List<PlayerImpl> reverseList = new ArrayList<>();
        
        for(int i = orderListBy(option).size()-1; i > 0; i-- ){
            reverseList.add(orderListBy(option).get(i));
        }
        return Collections.unmodifiableList(reverseList);
    }
    
}
