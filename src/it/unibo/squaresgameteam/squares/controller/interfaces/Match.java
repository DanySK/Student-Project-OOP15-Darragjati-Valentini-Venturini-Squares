package it.unibo.squaresgameteam.squares.controller.interfaces;

import java.io.IOException;

import it.unibo.squaresgameteam.squares.model.enumerations.ListType;
import it.unibo.squaresgameteam.squares.model.exceptions.UnsupportedSizeException;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.model.exceptions.NoMovesDoneException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.Move;
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
     * Metodo che permette di aggiungere una mossa e controlla se la partita � finita.
     * @param direction Verticale, orizzontale o diagonale (se la griglia � triangolare).
     * @param numLine Numero linea in cui � posta la mossa.
     * @param position Posizione nella linea della mossa.
     * @return Nome del giocatore che deve fare la prossima mossa
     * @throws UnexistentLineListException Eccezione in caso di lista inesistente
     * @throws DuplicatedPlayerStatsException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    String addLine(ListType direction, int numLine, int position) throws UnexistentLineListException, IOException, DuplicatedPlayerStatsException, ClassNotFoundException;
    
    
    
    /**
     * Metodo che fa l'undo dell'ultima mossa.
     * @return l'oggetto contenente la posizione dell'ultima mossa.
     * @throws NoMovesDoneException 
     * @throws UnexistentLineListException 
     */
    Move undo() throws NoMovesDoneException, UnexistentLineListException;
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
