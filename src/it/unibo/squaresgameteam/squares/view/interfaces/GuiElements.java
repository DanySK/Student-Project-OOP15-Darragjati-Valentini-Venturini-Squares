package it.unibo.squaresgameteam.squares.view.interfaces;

import java.awt.Color;

/**
 * This interface is used to set all the needed methods used by all the frames.
 */
public interface GuiElements {

  /**
   * This method sets the frame visible and locates it on the center of the
   * screen.
   */
  void showGui();

  /**
   * This method hides the frame.
   */
  void hideGui();

  /**
   * This method sets the frame background.
   */
  void setBackground(Color color);
}
