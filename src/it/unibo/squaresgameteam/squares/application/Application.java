package it.unibo.squaresgameteam.squares.application;

import it.unibo.squaresgameteam.squares.controller.classes.MusicImpl;
import it.unibo.squaresgameteam.squares.view.classes.StartMenuImpl;

/**
 * This class is used to start the application.
 */
public final class Application {

    private Application() {
    }

    /**
     * 
     * @param args
     *            necessary arguments
     *
     */
    public static void main(final String[] args) {
        final MusicImpl music = new MusicImpl();
        music.startMusic();
        final StartMenuImpl sm = new StartMenuImpl(music);
        sm.showGUI();
    }
}
