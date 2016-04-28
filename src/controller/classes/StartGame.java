package controller.classes;

import model.classes.TurnImpl;

public class StartGame {

    private int numColonne;
    private int numRighe;

    public StartGame(int numColonne, int numRighe) {
        this.numColonne = numColonne;
        this.numRighe = numRighe;
    }

    public void createGrid(int numColonne, int numRighe){
        TurnImpl newBaseGrid = new TurnImpl(numRighe, numColonne);
    }
}
