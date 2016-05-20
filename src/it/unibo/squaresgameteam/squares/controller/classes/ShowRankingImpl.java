package it.unibo.squaresgameteam.squares.controller.classes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import it.unibo.squaresgameteam.squares.controller.interfaces.ShowRanking;
import it.unibo.squaresgameteam.squares.model.classes.PlayerImpl;
import it.unibo.squaresgameteam.squares.model.classes.RankingImpl;
import it.unibo.squaresgameteam.squares.model.enumerations.RankingOption;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.model.interfaces.Player;

public class ShowRankingImpl implements ShowRanking {

    private String ranking;

    @Override
    public String showRanking(RankingOption rankingOrder, boolean reverse) {

        return null;
    }

    private void createRanking() throws IOException, DuplicatedPlayerStatsException {

        List<Player> currentRanking = new ArrayList<>();
        InputStream readFile = ShowRankingImpl.class.getResourceAsStream("Ranking.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(readFile, "UTF8"))) {
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
                Player player = new PlayerImpl.Builder().playerName(playerName).wonMatches(wonMatches).totalMatches(totalMatches).totalPointsScored(totalPointsScored).build();
                currentRanking.add(player);

            }
        } catch (IOException ex) {
            throw new IOException();
        }

        RankingImpl ranking = new RankingImpl(currentRanking);

    }

    private void readRanking() throws IOException {
        InputStream readFile = ShowRanking.class.getResourceAsStream("Ranking.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(readFile, "UTF8"))) {
            String s;
            while ((s = br.readLine()) != null) {
                this.ranking = this.ranking + s;

            }
        } catch (IOException ex) {
            throw new IOException();
        }
        readFile.close();

    }
    
    

}
