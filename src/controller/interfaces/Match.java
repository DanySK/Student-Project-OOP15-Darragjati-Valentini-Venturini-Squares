package controller.interfaces;

import model.enumerations.ListType;
/**
 * Interfaccia della classe MatchImpl.
 * @author Licia Valentini
 *
 */
public interface Match {

    /**
     * Metodo che crea la griglia di gioco, a seconda della tipologia di gioco scelta.
     */
    void createGrid();
    
    /**
     * Metodo che genera una nuova partita e fa partire il tempo di gioco.
     * @return Nome del giocatore che deve fare la prima mossa
     */
    String createNewMatch();
    
    /**
     * Metodo che permette di aggiungere una mossa e controlla se la partita � finita.
     * @param direction Verticale, orizzontale o diagonale (se la griglia � triangolare).
     * @param numLine Numero linea in cui � posta la mossa.
     * @param position Posizione nella linea della mossa.
     * @return Nome del giocatore che deve fare la prossima mossa
     */
    String addLine(ListType direction, int numLine, int position);
    
    /**
     * Metodo che fa l'undo dell'ultima mossa.
     */
    void undo();
    
}
