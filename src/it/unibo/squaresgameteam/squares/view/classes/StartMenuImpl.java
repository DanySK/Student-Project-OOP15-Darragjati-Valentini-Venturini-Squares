package it.unibo.squaresgameteam.squares.view.classes;

import it.unibo.squaresgameteam.squares.controller.interfaces.Music;
import it.unibo.squaresgameteam.squares.view.interfaces.GuiElements;
import it.unibo.squaresgameteam.squares.view.interfaces.StartMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author Karl Darragjati This class displays the application main menu.
 *
 */
public class StartMenuImpl implements StartMenu, GuiElements {

  private JFrame frmStartMenu;
  private Settings sett;

  /**
   * This constructor initializes the frame and his components.
   * 
   * @param mi
   *          music manager
   */
  public StartMenuImpl(Music mi) {
    frmStartMenu = new JFrame();
    sett = new Settings(mi, frmStartMenu.getBackground(), Color.RED, Color.BLUE);
    initialize();
  }

  /**
   * This constructor initializes the frame and his components.
   * 
   * @param sett
   *          settings manager
   */
  public StartMenuImpl(Settings sett) {
    frmStartMenu = new JFrame();
    this.sett = sett;
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmStartMenu.addWindowListener(new WindowAdapter() {
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
    frmStartMenu.setTitle("Squares");
    frmStartMenu.setResizable(false);
    frmStartMenu.setBounds(100, 100, 400, 400);
    frmStartMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frmStartMenu.getContentPane().setLayout(null);

    JButton btnNewGame = new JButton("New Game");
    btnNewGame.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        startNewGame();
      }
    });
    btnNewGame.setFont(new Font("Sitka Text", Font.PLAIN, 17));
    btnNewGame.setBounds(135, 70, 130, 30);
    frmStartMenu.getContentPane().add(btnNewGame);

    JButton btnSettings = new JButton("Settings");
    btnSettings.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        showSettings();
      }
    });
    btnSettings.setFont(new Font("Sitka Text", Font.PLAIN, 17));
    btnSettings.setBounds(135, 125, 130, 30);
    frmStartMenu.getContentPane().add(btnSettings);

    JButton btnRules = new JButton("Rules");
    btnRules.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        showRules();
      }
    });
    btnRules.setFont(new Font("Sitka Text", Font.PLAIN, 17));
    btnRules.setBounds(135, 180, 130, 30);
    frmStartMenu.getContentPane().add(btnRules);

    JButton btnStats = new JButton("Stats");
    btnStats.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        showRanking();
      }
    });
    btnStats.setFont(new Font("Sitka Text", Font.PLAIN, 17));
    btnStats.setBounds(135, 235, 130, 30);
    frmStartMenu.getContentPane().add(btnStats);
  }

  @Override
  public void showGui() {
    frmStartMenu.setLocationRelativeTo(null);
    frmStartMenu.setVisible(true);
  }

  @Override
  public void hideGui() {
    frmStartMenu.setVisible(false);
    frmStartMenu.dispose();
  }

  @Override
  public void setBackground(Color color) {
    frmStartMenu.getContentPane().setBackground(color);
  }

  @Override
  public void startNewGame() {
    MatchSetupImpl ms = new MatchSetupImpl(frmStartMenu, sett);
    ms.setBackground(frmStartMenu.getContentPane().getBackground());
    ms.showGui();
  }

  @Override
  public void showRules() {
    hideGui();
    RulesMenuImpl rm = new RulesMenuImpl(sett);
    rm.setBackground(frmStartMenu.getContentPane().getBackground());
    rm.showGui();
  }

  @Override
  public void showRanking() {
    hideGui();
    RankingMenuImpl rankm = new RankingMenuImpl(sett);
    rankm.setBackground(frmStartMenu.getContentPane().getBackground());
    rankm.showGui();
  }

  @Override
  public void showSettings() {
    hideGui();
    OptionsMenuImpl om = new OptionsMenuImpl(sett);
    om.setBackground(frmStartMenu.getContentPane().getBackground());
    om.showGui();
  }
}
