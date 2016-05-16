package controller.interfaces;

import model.enumerations.RankingOption;
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
     */
    String showRanking(RankingOption rankingOrder, boolean reverse);
   
    

}
