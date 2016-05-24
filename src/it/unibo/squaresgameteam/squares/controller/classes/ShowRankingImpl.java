package it.unibo.squaresgameteam.squares.controller.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import it.unibo.squaresgameteam.squares.controller.interfaces.ShowRanking;
import it.unibo.squaresgameteam.squares.model.classes.PlayerImpl;
import it.unibo.squaresgameteam.squares.model.classes.RankingImpl;
import it.unibo.squaresgameteam.squares.model.enumerations.RankingOption;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.model.interfaces.Player;
import it.unibo.squaresgameteam.squares.model.interfaces.Ranking;

public class ShowRankingImpl implements ShowRanking {

    private String rankingString;
    private File rankingFile;
    private Ranking rankingList;
    private List<Player> currentRanking;
    private String path;
    
    public ShowRankingImpl() {
        
    }

    @Override
    public String showRanking(RankingOption rankingOrder, boolean reverse)
            throws IOException, DuplicatedPlayerStatsException {
        createRanking();
        this.currentRanking = this.rankingList.orderListBy(rankingOrder, reverse);
        writeRankingFile();
        readRanking();
        return this.rankingString;
    }

    @Override
    public void addPlayer(Player player) throws IOException, DuplicatedPlayerStatsException {
        createRanking();
        rankingList.addPlayerResults(player);

    }

    private void createRanking() throws IOException, DuplicatedPlayerStatsException {
        checkRankingFile();
        this.currentRanking = new ArrayList<>();
       
        InputStream readFile = new FileInputStream(System.getProperty("user.home")+ System.getProperty("file.separator") + "Ranking.txt");
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(readFile, "UTF8"))) {
            String s;
            String playerName;
            String strWonMatches;
            String srtTotalMatches;
            String strTotalPointsScored;
            while ((s = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(s, "\t");
                playerName = st.nextToken();
                strWonMatches = st.nextToken();
                int wonMatches = Integer.parseInt(strWonMatches);
                srtTotalMatches = st.nextToken();
                int totalMatches = Integer.parseInt(srtTotalMatches);
                strTotalPointsScored = st.nextToken();
                int totalPointsScored = Integer.parseInt(strTotalPointsScored);
                Player player = new PlayerImpl.Builder().playerName(playerName).wonMatches(wonMatches)
                        .totalMatches(totalMatches).totalPointsScored(totalPointsScored).build();
                currentRanking.add(player);

            }
        } catch (IOException ex) {
            throw new IOException();
        }

        this.rankingList = new RankingImpl(currentRanking);

    }

    private void readRanking() throws IOException {
        checkRankingFile();
        InputStream readFile = ShowRanking.class.getResourceAsStream(
                System.getProperty("user.home") + System.getProperty("file.separator") + "Ranking.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(readFile, "UTF8"))) {
            String s;
            while ((s = br.readLine()) != null) {
                this.rankingString = this.rankingString + s;

            }
        } catch (IOException ex) {
            throw new IOException();
        }
        readFile.close();

    }

    private void checkRankingFile() throws IOException {

        this.rankingFile = new File(
                System.getProperty("user.home") + System.getProperty("file.separator") + "Ranking.txt");
        if (!this.rankingFile.exists()) {
            try {
                this.rankingFile.createNewFile();
            } catch (IOException ex) {
                throw new IOException();
            }

        }
    }

    private void writeRankingFile() throws IOException {
        checkRankingFile();

        OutputStream writeFile = new FileOutputStream(
                System.getProperty("user.home") + System.getProperty("file.separator") + "Ranking.txt");
        String s;
        String line;
        Double doubleNum;
        int intNum;
        try (PrintStream write = new PrintStream(writeFile, true, "UTF8")) {

            for (Player element : this.currentRanking) {

                line = element.getPlayerName();
                doubleNum = element.getWinRate();
                s = Double.toString(doubleNum);
                line = line + "\t" + s;
                intNum = element.getWonMatches();
                s = Integer.toString(intNum);
                line = line + "\t" + s;
                intNum = element.getTotalMatches();
                s = Integer.toString(intNum);
                line = line + "\t" + s;
                intNum = element.getTotalPointsScored();
                s = Integer.toString(intNum);
                line = line + "\t" + s;
                write.println(line);
            }
        } catch (IOException e) {
            throw new IOException();
        }

    }
}
