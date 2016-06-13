package it.unibo.squaresgameteam.squares.view.classes;

import java.awt.Color;

import it.unibo.squaresgameteam.squares.controller.classes.MusicImpl;

/**
 * 
 * @author Karl Darragjati This class contains the application settings.
 *
 */
public class Settings {
	private MusicImpl mi;
	private Color background, player1, player2;
	
	/**
	 * This constructor initializes the settings.
     * 
     * @param mi
     *            music manager
     * @param background
     *            background color
     * @param player1
     *            player1 color
     * @param player2
     *            player2 color
     */
	Settings(MusicImpl mi, Color background, Color player1, Color player2)
	{
		this.mi = mi;
		this.background = background;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public MusicImpl getMusic()
	{
		return mi;
	}
	
	public Color getBackground()
	{
		return background;
	}

	public Color getPlayer1Color()
	{
		return player1;
	}

	public Color getPlayer2Color()
	{
		return player2;
	}
}
