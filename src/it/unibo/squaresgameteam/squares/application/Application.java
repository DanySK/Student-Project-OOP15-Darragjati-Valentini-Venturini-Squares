package it.unibo.squaresgameteam.squares.application;

import it.unibo.squaresgameteam.squares.controller.interfaces.Menu;
import it.unibo.squaresgameteam.squares.controller.classes.MusicImpl;
import it.unibo.squaresgameteam.squares.view.classes.StartMenuImpl;

/**
 * This class is used to start the application.
 */
public final class Application {

    private Application() {
    }

    /**
     * Main of the application.
     * 
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws DuplicatedPlayerStatsException
     */
    public static void main(final String[] args) {
    	MusicImpl music = new MusicImpl();
    	music.startMusic();
    	StartMenuImpl sm = new StartMenuImpl(music);
        sm.showGUI();
    }
}
