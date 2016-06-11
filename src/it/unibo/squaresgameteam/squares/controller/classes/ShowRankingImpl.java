package it.unibo.squaresgameteam.squares.controller.classes;

import java.io.BufferedInputStream;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.List;

import it.unibo.squaresgameteam.squares.controller.interfaces.ShowRanking;

import it.unibo.squaresgameteam.squares.model.classes.RankingImpl;
import it.unibo.squaresgameteam.squares.model.enumerations.RankingOption;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.model.interfaces.Player;
import it.unibo.squaresgameteam.squares.model.interfaces.Ranking;

/**
 * 
 * @author Licia Valentini
 * 
 * This class manages the ranking
 *
 */

public class ShowRankingImpl implements ShowRanking {

    private String rankingString;
    private final File rankingFile;
    private Ranking rankingList;
    private List<Player> currentRanking;
    
    /**
     * 
     * @throws DuplicatedPlayerStatsException
     * Constructor of the class.
     */

    public ShowRankingImpl() throws DuplicatedPlayerStatsException {
        this.currentRanking = new ArrayList<>();
        this.rankingList = new RankingImpl(currentRanking);
        this.rankingFile = new File(
                System.getProperty("user.home") + System.getProperty("file.separator") + "Ranking.obj");

    }

    @Override
    public String showRanking(final RankingOption rankingOrder, final boolean reverse)
            throws IOException, DuplicatedPlayerStatsException, ClassNotFoundException {
        createRanking();
        this.rankingList.orderListBy(rankingOrder, reverse);
        convertRanking();
        return this.rankingString;
    }

    private void convertRanking() {

        this.currentRanking = rankingList.getPlayerList();
        String s;
        String line;
        this.rankingString = "";
        Double doubleNum;
        int intNum;

        for (final Player element : this.currentRanking) {

            line = element.getPlayerName();
            doubleNum = element.getWinRate();
            s = Double.toString(doubleNum);
            line = line.concat("\t").concat(s);
            intNum = element.getWonMatches();
            s = Integer.toString(intNum);
            line = line.concat("\t").concat(s);
            intNum = element.getTotalMatches();
            s = Integer.toString(intNum);
            line = line.concat("\t").concat(s);
            intNum = element.getTotalPointsScored();
            s = Integer.toString(intNum);
            line = line.concat("\t").concat(s);

            this.rankingString = this.rankingString.concat(line).concat("\n");
        }

    }

    @Override
    public void addPlayer(final Player player) throws IOException, DuplicatedPlayerStatsException, ClassNotFoundException {
        createRanking();
        deleteRankingFile();
        rankingList.addPlayerResults(player);
        writeRankingFile();

    }

    private void createRanking() throws IOException, ClassNotFoundException, DuplicatedPlayerStatsException {
        checkRankingFile();

        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(
                    System.getProperty("user.home") + System.getProperty("file.separator") + "Ranking.obj")));
            this.rankingList = (Ranking) ois.readObject();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();

        } catch (IOException e) {
            throw new IOException();
        }
        ois.close();

    }

    private void checkRankingFile() throws IOException {

        if (!this.rankingFile.exists()) {
            try {
                this.rankingFile.createNewFile();

            } catch (IOException ex) {
                throw new IOException();
            }
            writeRankingFile();
        }

    }

    private void writeRankingFile() throws IOException {

        ObjectOutputStream oss;
        try {
            oss = new ObjectOutputStream(new FileOutputStream(
                    System.getProperty("user.home") + System.getProperty("file.separator") + "Ranking.obj"));

            oss.writeObject(this.rankingList);
            oss.close();
        } catch (IOException e) {
            throw new IOException();
        }

    }

    private void deleteRankingFile() {
        if (this.rankingFile.exists()) {
            try {
                this.rankingFile.delete();
            } catch (SecurityException e) {
                throw new SecurityException();
            }

        }
    }
}
