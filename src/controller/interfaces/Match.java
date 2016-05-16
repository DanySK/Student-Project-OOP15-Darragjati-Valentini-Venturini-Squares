package controller.interfaces;

import model.enumerations.ListType;
import model.exceptions.UnsupportedSizeException;
import model.exceptions.NoMovesDoneException;
import model.interfaces.Move;
/**
 * Interfaccia della classe MatchImpl.
 * @author Licia Valentini
 *
 */
public interface Match {

    /**
     * Metodo che crea la griglia di gioco, a seconda della tipologia di gioco scelta.
     * @throws UnsupportedSizeException 
     */
    void createGrid() throws UnsupportedSizeException;
    
    /**
     * Metodo che genera una nuova partita e fa partire il tempo di gioco.
     * @return Nome del giocatore che deve fare la prima mossa
     */
    String createNewMatch();
    
    /**
     * Metodo che permette di aggiungere una mossa e controlla se la partita è finita.
     * @param direction Verticale, orizzontale o diagonale (se la griglia è triangolare).
     * @param numLine Numero linea in cui è posta la mossa.
     * @param position Posizione nella linea della mossa.
     * @return Nome del giocatore che deve fare la prossima mossa
     */
    String addLine(ListType direction, int numLine, int position);
    
    
    
    /**
     * Metodo che fa l'undo dell'ultima mossa.
     * @return 
     * @throws NoMovesDoneException 
     */
    Move undo() throws NoMovesDoneException;
    /**
     * 
     * @return ritorna il punteggio del giocatore corrente.
     */
    int getPlayerScore();
    /**
     * 
     * @return il tempo totale di gioco a fine partita.
     */
    Double getMatchTime();
    
}
