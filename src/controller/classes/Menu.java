package controller.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Menu {

    private String linesRead;

    // public String getLineRead() {
    // return this.lineRead;
    // }

    // public Menu(String directory) {
    // this.directory = directory;
    // }

    public void startGame(final int numColonne, final int numRighe, final String namePlayer1,
            final String namePlayer2) {
        StartGame game = new StartGame(numColonne, numRighe, namePlayer1, namePlayer2);

    }

    public String showRules() throws IOException {

        String fileName = System.getProperty("user.home") + System.getProperty("file.separator") System.getProperty("")+ "SquareRules.txt";
        File file = new File(fileName); // fileName è di tipo String ed è il
                                        // percorso del file

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;
            while ((s = br.readLine()) != null) {
                 this.linesRead.concat(s);
                 //System.out.println(s);
            }
        } catch (IOException ex) {
            throw new IOException();
        }
        System.out.println(this.linesRead);
        return this.linesRead;
    }

    public String showRanking() {
        return;
    }

    public void setting() {

    }

    public void exit() {
        System.exit(0);
    }
}
