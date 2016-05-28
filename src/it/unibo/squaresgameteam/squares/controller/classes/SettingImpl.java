package it.unibo.squaresgameteam.squares.controller.classes;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;

import it.unibo.squaresgameteam.squares.controller.interfaces.Setting;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class SettingImpl implements Setting {

    private AudioPlayer player;
    private ContinuousAudioDataStream loop;

    public SettingImpl() {

    }

    @Override
    public void turnVolumeOn() {

        this.player.start(loop);

    }

    @Override
    public void turnVolumeOff() {

        this.player.stop(loop);

    }

    public void createAudio() throws IOException {

        final File audio = new File(ClassLoader.class.getResource("/8_bit_adventure.au").getPath());

        final InputStream in = new FileInputStream(audio);

        try (final AudioStream stream = new AudioStream(in);) {
            this.player = AudioPlayer.player;
            final AudioData data = stream.getData();
            this.loop = new ContinuousAudioDataStream(data);
            this.player.start(loop);
        }

    }

    @Override
    public void setColorPlayer1() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setColorPlayer2() {
        // TODO Auto-generated method stub

    }

}
