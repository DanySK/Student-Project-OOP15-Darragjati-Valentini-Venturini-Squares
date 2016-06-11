package it.unibo.squaresgameteam.squares.view.classes;

import java.awt.Color;

import it.unibo.squaresgameteam.squares.controller.classes.MusicImpl;

public class Settings {
	private MusicImpl mi;
	private Color background, player1, player2;
	
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
