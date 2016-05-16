package controller.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import controller.interfaces.ShowRanking;
import model.classes.PlayerImpl;
import model.classes.RankingImpl;
import model.enumerations.RankingOption;
import model.exceptions.DuplicatedPlayerStatsException;
import model.interfaces.Player;

public class ShowRankingImpl implements ShowRanking {

    private String ranking;

    @Override
    public String showRanking(RankingOption rankingOrder, boolean reverse) {

        return null;
    }

    private void createRanking() throws IOException, DuplicatedPlayerStatsException {

        List<Player> currentRanking = new ArrayList<>();
        InputStream readFile = ShowRanking.class.getResourceAsStream("Ranking.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(readFile))) {
            String s = null;
            String playerName;
            String strWonMatches;
            String srtTotalMatches;
            String strTotalPointsScored;
            StringTokenizer st = new StringTokenizer(s, "  ");
            while ((s = br.readLine()) != null) {
                playerName = st.nextToken();
                strWonMatches = st.nextToken();
                int wonMatches = Integer.parseInt(strWonMatches);
                srtTotalMatches = st.nextToken();
                int totalMatches = Integer.parseInt(srtTotalMatches);
                strTotalPointsScored = st.nextToken();
                int totalPointsScored = Integer.parseInt(strTotalPointsScored);
                Player player = new PlayerImpl(playerName, wonMatches, totalMatches, totalPointsScored);
                currentRanking.add(player);

            }
        } catch (IOException ex) {
            throw new IOException();
        }

        RankingImpl ranking = new RankingImpl(currentRanking);

    }

    private void readRanking() throws IOException {
        InputStream readFile = ShowRanking.class.getResourceAsStream("Ranking.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(readFile))) {
            String s;
            while ((s = br.readLine()) != null) {
                this.ranking = this.ranking.concat(s);
               
            }
        } catch (IOException ex) {
            throw new IOException();
        }
        readFile.close();
       

    }

}
