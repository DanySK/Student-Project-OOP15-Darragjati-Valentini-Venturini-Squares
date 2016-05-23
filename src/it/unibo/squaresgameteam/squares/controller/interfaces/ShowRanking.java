package it.unibo.squaresgameteam.squares.controller.interfaces;

import java.io.IOException;

import it.unibo.squaresgameteam.squares.model.enumerations.RankingOption;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.model.interfaces.Player;
/**
 * 
 * @author Licia Valentini
 * 
 * Interfaccia della classe dedicata alla gestione della classifica.
 *
 */
public interface ShowRanking {
     /**
     * Metodo per la visualizzazione della lista, i parametri servono per modificare il criterio d'ordine.
     * @param rankingOrder
     * @param reverse
     * @return classifica sotto forma di stringa.
     * @throws IOException 
     * @throws DuplicatedPlayerStatsException 
     */
    String showRanking(RankingOption rankingOrder, boolean reverse) throws IOException, DuplicatedPlayerStatsException;
    
    void addPlayer(String player) throws IOException, DuplicatedPlayerStatsException;
   
    

}
