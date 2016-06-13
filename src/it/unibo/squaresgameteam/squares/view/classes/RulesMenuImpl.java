package it.unibo.squaresgameteam.squares.view.classes;

import it.unibo.squaresgameteam.squares.controller.classes.MenuImpl;

import it.unibo.squaresgameteam.squares.view.interfaces.GuiElements;
import it.unibo.squaresgameteam.squares.view.interfaces.RulesMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Karl Darragjati This class displays the game rules.
 *
 */
public class RulesMenuImpl implements RulesMenu, GuiElements {

  private JFrame frmRulesMenu;
  private JTextArea txtRules;
  private MenuImpl cont;
  private Settings sett;

  /**
   * This constructor initializes the frame and his components.
   * 
   * @param sett
   *          settings manager
   */
  public RulesMenuImpl(Settings sett) {
    this.sett = sett;
    cont = new MenuImpl();
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmRulesMenu = new JFrame();
    frmRulesMenu.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent arg0) {
        String[] objButtons = { "Yes", "No" };
        int promptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
            "Squares", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, objButtons, objButtons[1]);
        if (promptResult == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
      }
    });
    frmRulesMenu.setTitle("Squares");
    frmRulesMenu.setResizable(false);
    frmRulesMenu.setBounds(100, 100, 400, 400);
    frmRulesMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frmRulesMenu.getContentPane().setLayout(null);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 10, 374, 308);
    frmRulesMenu.getContentPane().add(scrollPane);

    txtRules = new JTextArea();
    txtRules.setEditable(false);
    txtRules.setBounds(10, 11, 374, 308);
    txtRules.setWrapStyleWord(true);
    txtRules.setLineWrap(true);
    showRules();
    scrollPane.setViewportView(txtRules);

    JButton btnBack = new JButton("Back");
    btnBack.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        hideGui();
        StartMenuImpl sm = new StartMenuImpl(sett);
        sm.setBackground(frmRulesMenu.getContentPane().getBackground());
        sm.showGui();
      }
    });
    btnBack.setFont(new Font("Sitka Text", Font.PLAIN, 17));
    btnBack.setBounds(10, 330, 130, 30);
    frmRulesMenu.getContentPane().add(btnBack);
  }

  @Override
  public void showGui() {
    frmRulesMenu.setLocationRelativeTo(null);
    frmRulesMenu.setVisible(true);
  }

  @Override
  public void hideGui() {
    frmRulesMenu.setVisible(false);
    frmRulesMenu.dispose();
  }

  @Override
  public void setBackground(Color color) {
    frmRulesMenu.getContentPane().setBackground(color);
  }

  @Override
  public void showRules() {
    try {
      txtRules.setText(cont.showRules());
    } catch (IOException exc) {
      exc.printStackTrace();
    }
  }

}
