package view.interfaces;

/**
 * This interface is used to choose the main options of the appication: to start
 * a new game, to show the rules, to show the ranking, to change the main
 * settings and to quit the application.
 */
public interface StartMenu {

    /**
     * this method shows or not the starting menu.
     */
    void showStartMenu();

    /**
     * this method starts a new match.
     */
    void startNewGame();

    /**
     * this method shows the rules of the game.
     */
    void showRules();

    /**
     * this method shows the ranking of the players.
     */
    void showRanking();

    /**
     * this method shows the options.
     */
    void showSettings();

    /**
     * this method quits the application.
     */
    void quit();
}
