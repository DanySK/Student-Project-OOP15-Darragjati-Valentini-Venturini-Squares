package it.unibo.squaresgameteam.squares.application;


import java.io.IOException;

import it.unibo.squaresgameteam.squares.controller.classes.MusicImpl;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
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
    public static void main(final String[] args)
            throws IOException, ClassNotFoundException, DuplicatedPlayerStatsException {
        
        final StartMenuImpl start = new StartMenuImpl();
        final MusicImpl music = new MusicImpl();
        start.showGUI();
        music.startMusic();       
    }
}
