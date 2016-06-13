package it.unibo.squaresgameteam.squares.application;

import it.unibo.squaresgameteam.squares.view.classes.StartMenuImpl;
import it.unibo.squaresgameteam.squares.view.interfaces.StartMenu;
import it.unibo.squaresgameteam.squares.controller.classes.MusicImpl;
import it.unibo.squaresgameteam.squares.controller.interfaces.Music;

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
        final Music music = new MusicImpl();
        music.startMusic();
        final StartMenu sm = new StartMenuImpl((MusicImpl) music);
        ((StartMenuImpl) sm).showGui();
    }
}
