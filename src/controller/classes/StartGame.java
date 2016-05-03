package controller.classes;

import model.classes.BaseGridImpl;
import model.classes.PlayerImpl;
import model.classes.TurnImpl;

public class StartGame {

    private final int numColonne;
    private int numRighe;
    private String namePlayer1;
    private String namePlayer2;

    public StartGame(int numColonne, int numRighe, String namePlayer1, String namePlayer2) {
        this.numColonne = numColonne;
        this.numRighe = numRighe;
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
    }

    public void createGrid(int numColonne, int numRighe){
        BaseGridImpl newGrid = new BaseGridImpl(numRighe, numColonne);
    
    }
    
    public void addPlayer(String namePlayer1, String namePlayer2) {
        PlayerImpl player1 = new PlayerImpl(namePlayer1);
        PlayerImpl player2 = new PlayerImpl(namePlayer2);        
        
    }
    
    public int getNumColonne(){
        return this.numColonne;
    }
    
    public int getNumRighe(){
        return this.numRighe;
    }
    
    public String getNamePlayer1() {
        return this.namePlayer1;
    }
    
    public String getNamePlayer2() {
        return this.namePlayer2;
    }
}
