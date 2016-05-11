package controller.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.classes.RankingImpl;
import model.enumerations.RankingOption;
import model.interfaces.Player;

public class Menu {

    private String linesRead;
    

    public Menu(){
        
    }
    
    

    public String showRules() throws IOException {

        InputStream readFile = Menu.class.getResourceAsStream("Rules.txt");
        

        try (BufferedReader br = new BufferedReader(new InputStreamReader(readFile))) {
            String s;
            while ((s = br.readLine()) != null) {
                 this.linesRead = this.linesRead.concat(s);
                 //System.out.println(s);
            }
        } catch (IOException ex) {
            throw new IOException();
        }
        readFile.close();
        //System.out.println(this.linesRead);
        return this.linesRead;
    }

    public String showRanking(RankingOption rankingOrder, boolean reverse) throws IOException {
        List<Player> currentRanking = new ArrayList<>();
        InputStream readFile = Menu.class.getResourceAsStream("Ranking.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(readFile))) {
            String s;
            while ((s = br.readLine()) != null) {
                 currentRanking.add(null);
                 try (BufferedReader br2 = new BufferedReader(new InputStreamReader(readFile))){
                     String r;
                     while (true){
                        
                         
                     }
                 } catch(IOException ex) {
                     throw new IOException();
                 }
                 
            }
        } catch (IOException ex) {
            throw new IOException();
        }
        
        RankingImpl ranking = new RankingImpl(currentRanking);

        return null;
    }

    public void setting() {
        

    }

    public void exit() {
        System.exit(0);
    }
    
   
}
