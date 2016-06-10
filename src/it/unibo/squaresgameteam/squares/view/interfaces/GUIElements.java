package it.unibo.squaresgameteam.squares.view.interfaces;

import java.awt.Color;

/**
 * This interface is used to set all the needed methods used by all the frames.
 */
public interface GUIElements {
	
	/**
	 * This method sets the frame visible and locates it on the center of the screen.
	 */
	public void showGUI();
	
	/**
	 * This method hides the frame.
	 */
	public void hideGUI();
	
	/**
	 * This method sets the frame background.
	 */
	public void setBackground(Color c);
}
