package it.unibo.squaresgameteam.squares.controller.classes;



import java.io.IOException;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import it.unibo.squaresgameteam.squares.controller.interfaces.Music;

public class MusicImpl implements Music {
    
    private Clip clip;
    private AudioInputStream audioIn;
    private boolean started;

    // Constructor
    public MusicImpl() {
        try {
            // Open an audio input stream.
            final URL url = ClassLoader.class.getResource("/8_bit_adventure.wav");
            this.audioIn = AudioSystem.getAudioInputStream(url);

            // Get a sound clip resource.
            this.clip = AudioSystem.getClip();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void startMusic() {
        try {
            // Open the audio.
            this.clip.open(this.audioIn);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Loop the audio.
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        
        started = true;
    }

    public void stopMusic() {
        // Stop the audio
        this.clip.stop();

        started = false;
    }
    
    public boolean isStarted()
    {
    	return started;
    }

    
}



