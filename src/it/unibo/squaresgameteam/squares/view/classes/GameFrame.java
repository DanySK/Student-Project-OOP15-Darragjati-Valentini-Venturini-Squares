package it.unibo.squaresgameteam.squares.view.classes;

import it.unibo.squaresgameteam.squares.controller.classes.MatchImpl;
import it.unibo.squaresgameteam.squares.controller.enumerations.TypeGame;
import it.unibo.squaresgameteam.squares.model.enumerations.ListType;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.model.exceptions.NoMovesDoneException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnsupportedSizeException;

import it.unibo.squaresgameteam.squares.view.interfaces.GuiElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Karl Darragjati This class displays and manages the match.
 *
 */
public class GameFrame implements GuiElements {
  private ArrayList<ArrayList<JButton>> btns;
  private ArrayList<JButton> undo;
  private JFrame frmGameFrame;
  private JLabel lblPlaying;
  private JLabel lblScore1;
  private JLabel lblScore2;
  private int rows;
  private int colums;
  private String name1;
  private String name2;
  private Color player1;
  private Color player2;
  private MatchImpl cont;
  private Settings sett;

  /**
   * This constructor initializes the frame and his components.
   * 
   * @param cont
   *          match manager
   * @param sett
   *          settings manager
   */
  public GameFrame(MatchImpl cont, Settings sett) {
    btns = new ArrayList<ArrayList<JButton>>();
    undo = new ArrayList<JButton>();
    this.cont = cont;
    rows = cont.getRowsNumber();
    colums = cont.getColumsNumber();
    name1 = cont.getNamePlayer1();
    name2 = cont.getNamePlayer2();
    this.sett = sett;
    player1 = sett.getPlayer1Color();
    player2 = sett.getPlayer2Color();
    try {
      cont.createGrid();
    } catch (UnsupportedSizeException exc) {
      exc.printStackTrace();
    }
    cont.createNewMatch();

    initialize();
  }
  
  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmGameFrame = new JFrame();
    frmGameFrame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent arg0) {
        String[] objButtons = { "Yes", "No" };
        int promptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
            "Online Examination System", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, objButtons, objButtons[1]);
        if (promptResult == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
      }
    });
    frmGameFrame.setTitle("Squares");
    frmGameFrame.setResizable(false);
    frmGameFrame.setBounds(100, 100, colums * 40 + 200, rows * 40 + 100);
    frmGameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frmGameFrame.getContentPane().setLayout(null);

    JPanel pane = new JPanel();
    pane.setBounds(10, 10, colums * 40 + 10, rows * 40 + 10);
    frmGameFrame.getContentPane().add(pane);
    pane.setLayout(null);

    for (int i = 0; i < rows + 1; i++) {
      if (i == rows) {
        for (int j = 0; j < colums + 1; j++) {
          if (j == colums) {
            JButton btnPoint = new JButton("");
            btnPoint.setBounds(j * 40, i * 40, 10, 10);
            btnPoint.setEnabled(false);
            btnPoint.setBackground(Color.BLACK);
            pane.add(btnPoint);
          } else {
            addHorizontalSide(pane, j * 40, i * 40);
          }
        }
      } else {
        for (int j = 0; j < colums + 1; j++) {
          if (j == colums) {
            addVerticalSide(pane, j * 40, i * 40);
          } else {
            addBasicSquare(pane, j * 40, i * 40);
          }
        }
      }
    }

    if (cont.getCurrentPlayerTurn().equals(name1)) {
      lblPlaying = new JLabel(name1 + "'s turn");
      lblPlaying.setForeground(player1);
    } else {
      lblPlaying = new JLabel(name2 + "'s turn");
      lblPlaying.setForeground(player2);
    }
    lblPlaying.setHorizontalAlignment(SwingConstants.CENTER);
    lblPlaying.setFont(new Font("Sitka Text", Font.BOLD, 14));
    lblPlaying.setBounds(colums * 40 + 30, 10, 150, 30);
    frmGameFrame.getContentPane().add(lblPlaying);

    JLabel lblPlayer1 = new JLabel(name1);
    lblPlayer1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
    lblPlayer1.setBounds(colums * 40 + 30, 50, 150, 30);
    frmGameFrame.getContentPane().add(lblPlayer1);

    lblScore1 = new JLabel("Score: 0");
    lblScore1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
    lblScore1.setBounds(colums * 40 + 30, 80, 150, 30);
    frmGameFrame.getContentPane().add(lblScore1);

    JLabel lblPlayer2 = new JLabel(name2);
    lblPlayer2.setFont(new Font("Sitka Text", Font.PLAIN, 14));
    lblPlayer2.setBounds(colums * 40 + 30, 120, 150, 30);
    frmGameFrame.getContentPane().add(lblPlayer2);

    lblScore2 = new JLabel("Score: 0");
    lblScore2.setFont(new Font("Sitka Text", Font.PLAIN, 14));
    lblScore2.setBounds(colums * 40 + 30, 150, 150, 30);
    frmGameFrame.getContentPane().add(lblScore2);

    JButton btnBack = new JButton("Back");
    btnBack.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        String[] objButtons = { "Yes", "No" };
        int promptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
            "Online Examination System", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, objButtons, objButtons[1]);
        if (promptResult == JOptionPane.YES_OPTION) {
          hideGui();
          StartMenuImpl sm = new StartMenuImpl(sett);
          sm.setBackground(frmGameFrame.getContentPane().getBackground());
          sm.showGui();
        }
      }
    });
    btnBack.setFont(new Font("Sitka Text", Font.PLAIN, 17));
    btnBack.setBounds(10, rows * 40 + 30, 130, 30);
    frmGameFrame.getContentPane().add(btnBack);

    JButton btnUndo = new JButton("Undo");
    btnUndo.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        if (undo.size() != 0) {
          if (cont.getMode() == TypeGame.TRIANGLE) {
            undo.get(undo.size() - 1).setText("");
            undo.get(undo.size() - 1).setBorder(btnUndo.getBorder());
          } else {
            JButton btn = undo.get(undo.size() - 1);
            if (btn.getBounds().getWidth() > btn.getBounds().getHeight()) {
              if (btn.getBounds().y / 40 == 0) {
                btns.get((btn.getBounds().x - 10) / 40
                    + btn.getBounds().y / 40 * colums).get(2).setBackground(null);
              } else if (btn.getBounds().y / 40 == rows - 1) {
                btns.get((btn.getBounds().x - 10) / 40
                    + (btn.getBounds().y / 40 - 1) * colums).get(2).setBackground(null);
              } else {
                btns.get((btn.getBounds().x - 10) / 40
                    + btn.getBounds().y / 40 * colums).get(2).setBackground(null);
                btns.get((btn.getBounds().x - 10) / 40
                    + (btn.getBounds().y / 40 - 1) * colums).get(2).setBackground(null);
              }
            } else {
              if (btn.getBounds().x / 40 == 0) {
                btns.get((btn.getBounds().x / 40 - 1)
                    + (btn.getBounds().y - 10) / 40 * colums).get(2).setBackground(null);
              } else if (btn.getBounds().x / 40 == colums - 1) {
                btns.get((btn.getBounds().x / 40 - 1)
                    + (btn.getBounds().y - 10) / 40 * colums).get(2).setBackground(null);
              } else {
                btns.get(btn.getBounds().x / 40
                    + (btn.getBounds().y - 10) / 40 * colums).get(2).setBackground(null);
                btns.get((btn.getBounds().x / 40 - 1)
                    + (btn.getBounds().y - 10) / 40 * colums).get(2).setBackground(null);
              }
            }
          }

          undo.get(undo.size() - 1).setEnabled(true);
          undo.get(undo.size() - 1).setBackground(null);
          undo.remove(undo.size() - 1);

          try {
            cont.undo();
          } catch (NoMovesDoneException | UnexistentLineListException exc) {
            exc.printStackTrace();
          }

          if (cont.getCurrentPlayerTurn().equals(name1)) {
            lblPlaying.setText(name1 + "'s turn");
            lblPlaying.setForeground(player1);
          } else {
            lblPlaying.setText(name2 + "'s turn");
            lblPlaying.setForeground(player2);
          }

          lblScore1.setText("Score: " + cont.getPlayer1Score());
          lblScore2.setText("Score: " + cont.getPlayer2Score());
        } else {
          JOptionPane.showMessageDialog(null, "No moves to undo.", "InfoBox",
              JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });
    btnUndo.setFont(new Font("Sitka Text", Font.PLAIN, 17));
    btnUndo.setBounds(150, rows * 40 + 30, 130, 30);
    frmGameFrame.getContentPane().add(btnUndo);
  }

  private void addBasicSquare(JPanel pane, int coordX, int coordY) {
    btns.add(new ArrayList<JButton>());

    JButton btnPoint = new JButton("");
    btnPoint.setBounds(coordX, coordY, 10, 10);
    btnPoint.setEnabled(false);
    btnPoint.setBackground(Color.BLACK);
    pane.add(btnPoint);

    JButton btnLine1 = new JButton("");
    btnLine1.setBounds(coordX + 10, coordY, 30, 10);
    btnLine1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        if (btnLine1.isEnabled()) {
          undo.add(btnLine1);
          try {
            cont.addLine(ListType.HORIZONTAL, coordY / 40, coordX / 40);
          } catch (ClassNotFoundException | UnexistentLineListException | IOException
              | DuplicatedPlayerStatsException exc) {
            exc.printStackTrace();
          }

          btnLine1.setEnabled(false);

          if (coordY / 40 != 0) {
            if (!btns.get(coordX / 40 + (coordY / 40 - 1) * colums).get(0).isEnabled()
                && !btns.get(coordX / 40 + (coordY / 40 - 1) * colums).get(1).isEnabled()
                && !btns.get(coordX / 40 + (coordY / 40 - 1) * colums).get(3).isEnabled()
                && !btns.get(coordX / 40 + (coordY / 40 - 1) * colums).get(4).isEnabled()) {
              btns.get(coordX / 40 + (coordY / 40 - 1) * colums).get(2)
                .setBackground(lblPlaying.getForeground());
            }
          }
          if (!btns.get(coordX / 40 + coordY / 40 * colums).get(0).isEnabled()
              && !btns.get(coordX / 40 + coordY / 40 * colums).get(1).isEnabled()
              && !btns.get(coordX / 40 + coordY / 40 * colums).get(3).isEnabled()
              && !btns.get(coordX / 40 + coordY / 40 * colums).get(4).isEnabled()) {
            btns.get(coordX / 40 + coordY / 40 * colums).get(2)
              .setBackground(lblPlaying.getForeground());
          }

          btnLine1.setBackground(lblPlaying.getForeground());

          if (cont.isEnded()) {
            ResultFrame rf = new ResultFrame(frmGameFrame, sett, cont);
            rf.showGui();
          } else {
            if (cont.getCurrentPlayerTurn().equals(name1)) {
              lblPlaying.setText(name1 + "'s turn");
              lblPlaying.setForeground(player1);
            } else {
              lblPlaying.setText(name2 + "'s turn");
              lblPlaying.setForeground(player2);
            }

            lblScore1.setText("Score: " + cont.getPlayer1Score());
            lblScore2.setText("Score: " + cont.getPlayer2Score());
          }
        }
      }
    });
    if ((coordY / 40) != 0) {
      btns.get(coordX / 40 + (coordY / 40 - 1) * colums).add(btnLine1);
    }
    btns.get(coordX / 40 + coordY / 40 * colums).add(btnLine1);
    pane.add(btnLine1);

    JButton btnLine2 = new JButton("");
    btnLine2.setBounds(coordX, coordY + 10, 10, 30);
    btnLine2.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        if (btnLine2.isEnabled()) {
          undo.add(btnLine2);
          try {
            cont.addLine(ListType.VERTICAL, coordX / 40, coordY / 40);
          } catch (ClassNotFoundException | UnexistentLineListException | IOException
              | DuplicatedPlayerStatsException exc) {
            exc.printStackTrace();
          }

          btnLine2.setEnabled(false);

          if (coordX / 40 != 0) {
            if (!btns.get((coordX / 40 - 1) + coordY / 40 * colums).get(0).isEnabled()
                && !btns.get((coordX / 40 - 1) + coordY / 40 * colums).get(1).isEnabled()
                && !btns.get((coordX / 40 - 1) + coordY / 40 * colums).get(3).isEnabled()
                && !btns.get((coordX / 40 - 1) + coordY / 40 * colums).get(4).isEnabled()) {
              btns.get((coordX / 40 - 1) + coordY / 40 * colums).get(2)
                .setBackground(lblPlaying.getForeground());
            }
          }
          if (!btns.get(coordX / 40 + coordY / 40 * colums).get(0).isEnabled()
              && !btns.get(coordX / 40 + coordY / 40 * colums).get(1).isEnabled()
              && !btns.get(coordX / 40 + coordY / 40 * colums).get(3).isEnabled()
              && !btns.get(coordX / 40 + coordY / 40 * colums).get(4).isEnabled()) {
            btns.get(coordX / 40 + coordY / 40 * colums).get(2)
              .setBackground(lblPlaying.getForeground());
          }

          btnLine2.setBackground(lblPlaying.getForeground());

          if (cont.isEnded()) {
            ResultFrame rf = new ResultFrame(frmGameFrame, sett, cont);
            rf.setBackground(frmGameFrame.getContentPane().getBackground());
            rf.showGui();
          } else {
            if (cont.getCurrentPlayerTurn().equals(name1)) {
              lblPlaying.setText(name1 + "'s turn");
              lblPlaying.setForeground(player1);
            } else {
              lblPlaying.setText(name2 + "'s turn");
              lblPlaying.setForeground(player2);
            }

            lblScore1.setText("Score: " + cont.getPlayer1Score());
            lblScore2.setText("Score: " + cont.getPlayer2Score());
          }
        }
      }
    });
    if ((coordX / 40) != 0) {
      btns.get((coordX / 40 - 1) + coordY / 40 * colums).add(btnLine2);
    }
    btns.get(coordX / 40 + coordY / 40 * colums).add(btnLine2);
    pane.add(btnLine2);

    JButton btnBox = new JButton("");
    if (cont.getMode() == TypeGame.TRIANGLE) {
      btnBox.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent arg0) {
          if (!btnBox.getText().equals("\u2572")) {
            undo.add(btnBox);
            try {
              cont.addLine(ListType.DIAGONAL, coordY / 40, coordX / 40);
            } catch (ClassNotFoundException | UnexistentLineListException | IOException
                | DuplicatedPlayerStatsException exc) {
              exc.printStackTrace();
            }

            btnBox.setForeground(lblPlaying.getForeground());
            btnBox.setBorder(null);
            btnBox.setText("\u2572");

            if (cont.isEnded()) {
              ResultFrame rf = new ResultFrame(frmGameFrame, sett, cont);
              rf.showGui();
            } else {
              if (cont.getCurrentPlayerTurn().equals(name1)) {
                lblPlaying.setText(name1 + "'s turn");
                lblPlaying.setForeground(player1);
              } else {
                lblPlaying.setText(name2 + "'s turn");
                lblPlaying.setForeground(player2);
              }

              lblScore1.setText("Score: " + cont.getPlayer1Score());
              lblScore2.setText("Score: " + cont.getPlayer2Score());
            }
          }
        }
      });
    } else {
      btnBox.setEnabled(false);
    }
    btnBox.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 28));
    btnBox.setBounds(coordX + 10, coordY + 10, 30, 30);
    btns.get(coordX / 40 + coordY / 40 * rows).add(btnBox);
    pane.add(btnBox);
  }

  private void addVerticalSide(JPanel pane, int coordX, int coordY) {
    JButton btnPoint = new JButton("");
    btnPoint.setBounds(coordX, coordY, 10, 10);
    btnPoint.setEnabled(false);
    btnPoint.setBackground(Color.BLACK);
    pane.add(btnPoint);

    JButton btnLine = new JButton("");
    btnLine.setBounds(coordX, coordY + 10, 10, 30);
    btnLine.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        if (btnLine.isEnabled()) {
          undo.add(btnLine);
          try {
            cont.addLine(ListType.VERTICAL, coordX / 40, coordY / 40);
          } catch (ClassNotFoundException | UnexistentLineListException | IOException
              | DuplicatedPlayerStatsException exc) {
            exc.printStackTrace();
          }

          btnLine.setEnabled(false);

          if (!btns.get((coordX / 40 - 1) + coordY / 40 * colums).get(0).isEnabled()
              && !btns.get((coordX / 40 - 1) + coordY / 40 * colums).get(1).isEnabled()
              && !btns.get((coordX / 40 - 1) + coordY / 40 * colums).get(3).isEnabled()
              && !btns.get((coordX / 40 - 1) + coordY / 40 * colums).get(4).isEnabled()) {
            btns.get((coordX / 40 - 1) + coordY / 40 * colums).get(2)
              .setBackground(lblPlaying.getForeground());
          }

          btnLine.setBackground(lblPlaying.getForeground());

          if (cont.isEnded()) {
            ResultFrame rf = new ResultFrame(frmGameFrame, sett, cont);
            rf.showGui();
          } else {
            if (cont.getCurrentPlayerTurn().equals(name1)) {
              lblPlaying.setText(name1 + "'s turn");
              lblPlaying.setForeground(player1);
            } else {
              lblPlaying.setText(name2 + "'s turn");
              lblPlaying.setForeground(player2);
            }

            lblScore1.setText("Score: " + cont.getPlayer1Score());
            lblScore2.setText("Score: " + cont.getPlayer2Score());
          }
        }
      }
    });
    btns.get((coordX / 40 - 1) + coordY / 40 * colums).add(btnLine);
    pane.add(btnLine);
  }

  private void addHorizontalSide(JPanel pane, int coordX, int coordY) {
    JButton btnPoint = new JButton("");
    btnPoint.setBounds(coordX, coordY, 10, 10);
    btnPoint.setEnabled(false);
    btnPoint.setBackground(Color.BLACK);
    pane.add(btnPoint);

    JButton btnLine = new JButton("");
    btnLine.setBounds(coordX + 10, coordY, 30, 10);
    btnLine.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        if (btnLine.isEnabled()) {
          undo.add(btnLine);
          try {
            cont.addLine(ListType.HORIZONTAL, coordY / 40, coordX / 40);
          } catch (ClassNotFoundException | UnexistentLineListException | IOException
              | DuplicatedPlayerStatsException exc) {
            exc.printStackTrace();
          }

          btnLine.setEnabled(false);

          if (!btns.get(coordX / 40 + (coordY / 40 - 1) * colums).get(0).isEnabled()
              && !btns.get(coordX / 40 + (coordY / 40 - 1) * colums).get(1).isEnabled()
              && !btns.get(coordX / 40 + (coordY / 40 - 1) * colums).get(3).isEnabled()
              && !btns.get(coordX / 40 + (coordY / 40 - 1) * colums).get(4).isEnabled()) {
            btns.get(coordX / 40 + (coordY / 40 - 1) * colums).get(2)
              .setBackground(lblPlaying.getForeground());
          }

          btnLine.setBackground(lblPlaying.getForeground());

          if (cont.isEnded()) {
            ResultFrame rf = new ResultFrame(frmGameFrame, sett, cont);
            rf.showGui();
          } else {
            if (cont.getCurrentPlayerTurn().equals(name1)) {
              lblPlaying.setText(name1 + "'s turn");
              lblPlaying.setForeground(player1);
            } else {
              lblPlaying.setText(name2 + "'s turn");
              lblPlaying.setForeground(player2);
            }

            lblScore1.setText("Score: " + cont.getPlayer1Score());
            lblScore2.setText("Score: " + cont.getPlayer2Score());
          }
        }
      }
    });
    btns.get(coordX / 40 + (coordY / 40 - 1) * colums).add(btnLine);
    pane.add(btnLine);
  }

  @Override
  public void showGui() {
    frmGameFrame.setLocationRelativeTo(null);
    frmGameFrame.setVisible(true);
  }

  @Override
  public void hideGui() {
    frmGameFrame.setVisible(false);
    frmGameFrame.dispose();
  }

  @Override
  public void setBackground(Color color) {
    frmGameFrame.getContentPane().setBackground(color);
  }
}
