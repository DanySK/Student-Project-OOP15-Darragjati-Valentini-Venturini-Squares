package it.unibo.squaresgameteam.squares.view.classes;

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
import javax.swing.border.Border;

import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.model.exceptions.NoMovesDoneException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnsupportedSizeException;
import it.unibo.squaresgameteam.squares.model.enumerations.ListType;
import it.unibo.squaresgameteam.squares.controller.enumerations.TypeGame;
import it.unibo.squaresgameteam.squares.controller.classes.MatchImpl;

import it.unibo.squaresgameteam.squares.view.interfaces.GUIElements;

/**
 * 
 * @author Karl Darragjati This class displays and manage the match.
 *
 */
public class GameFrame implements GUIElements {
	private ArrayList<JButton> btns;
	private JFrame frmGameFrame;
	private JLabel lblPlaying, lblScore1, lblScore2;
	private int rows, colums;
	private String name1, name2, previous;
	private Color player1, player2;
	private MatchImpl cont;
	private Settings s;

	/**
     * 
     * @param cont
     *            match manager
     * @param s
     *            settings manager
     */
	public GameFrame(MatchImpl cont, Settings s) {
		btns = new ArrayList<JButton>();
		this.cont = cont;
		rows = cont.getRowsNumber();
		colums = cont.getColumsNumber();
		name1 = cont.getNamePlayer1();
		name2 = cont.getNamePlayer2();
		this.s = s;
		previous = "";
		player1 = s.getPlayer1Color();
		player2 = s.getPlayer2Color();
		try {
			cont.createGrid();
		} catch (UnsupportedSizeException e) {
			e.printStackTrace();
		}
		cont.createNewMatch();
		
		initialize();
	}
	
	@Override
	public void showGUI()
	{
		frmGameFrame.setLocationRelativeTo(null);
		frmGameFrame.setVisible(true);
	}
	
	@Override
	public void hideGUI()
	{
		frmGameFrame.setVisible(false);
		frmGameFrame.dispose();
	}
	
	@Override
	public void setBackground(Color c)
	{
		frmGameFrame.getContentPane().setBackground(c);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGameFrame = new JFrame();
		frmGameFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				String ObjButtons[] = {"Yes","No"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","Online Examination System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        {
		            System.exit(0);
		        }
			}
		});
		frmGameFrame.setTitle("Squares");
		frmGameFrame.setResizable(false);
		frmGameFrame.setBounds(100, 100, colums*40+200, rows*40+100);
		frmGameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGameFrame.getContentPane().setLayout(null);
		
		JPanel pane = new JPanel();
		pane.setBounds(10, 10, colums*40+10, rows*40+10);
		frmGameFrame.getContentPane().add(pane);
		pane.setLayout(null);
		
		for(int i = 0; i<rows+1;i++)
		{
			if(i==rows)
				for(int j = 0; j<colums+1;j++)
				{
					if(j==colums)
					{
						JButton btnPoint = new JButton("");
						btnPoint.setBounds(j*40,i*40,10,10);
						btnPoint.setEnabled(false);
						btnPoint.setBackground(Color.BLACK);
						pane.add(btnPoint);
					}
					else
						addHorizontalSide(pane, j*40, i*40);
				}
			else
				for(int j = 0; j<colums+1;j++)
				{
					if(j==colums)
						addVerticalSide(pane, j*40, i*40);
					else
						addBasicSquare(pane, j*40, i*40);
				}
		}
		
		if(cont.getCurrentPlayerTurn().equals(name1))
		{
			lblPlaying = new JLabel(name1+"'s turn");
			lblPlaying.setForeground(player1);
		}
		else
		{
			lblPlaying = new JLabel(name2+"'s turn");
			lblPlaying.setForeground(player2);
		}
		lblPlaying.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaying.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblPlaying.setBounds(colums*40+30, 10, 150, 30);
		frmGameFrame.getContentPane().add(lblPlaying);
		
		JLabel lblPlayer1 = new JLabel(name1);
		lblPlayer1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblPlayer1.setBounds(colums*40+30, 50, 150, 30);
		frmGameFrame.getContentPane().add(lblPlayer1);
		
		lblScore1 = new JLabel("Score: ");
		lblScore1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblScore1.setBounds(colums*40+30, 80, 150, 30);
		frmGameFrame.getContentPane().add(lblScore1);
		
		JLabel lblPlayer2 = new JLabel(name2);
		lblPlayer2.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblPlayer2.setBounds(colums*40+30, 120, 150, 30);
		frmGameFrame.getContentPane().add(lblPlayer2);
		
		lblScore2 = new JLabel("Score: ");
		lblScore2.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblScore2.setBounds(colums*40+30, 150, 150, 30);
		frmGameFrame.getContentPane().add(lblScore2);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String ObjButtons[] = {"Yes","No"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","Online Examination System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        {
					hideGUI();
					StartMenuImpl sm = new StartMenuImpl(s);
					sm.setBackground(frmGameFrame.getContentPane().getBackground());
					sm.showGUI();
		        }
			}
		});
		btnBack.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		btnBack.setBounds(10, rows*40+30, 130, 30);
		frmGameFrame.getContentPane().add(btnBack);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btns.size()!=0)
				{
					if(cont.getMode()==TypeGame.TRIANGLE)
					{
						btns.get(btns.size()-1).setText("");
						btns.get(btns.size()-1).setBorder(btnUndo.getBorder());
					}
					btns.get(btns.size()-1).setEnabled(true);
					btns.get(btns.size()-1).setBackground(null);
					btns.remove(btns.size()-1);
					try {
						cont.undo();
					} catch (NoMovesDoneException | UnexistentLineListException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(cont.getCurrentPlayerTurn().equals(name1))
					{
						lblPlaying.setText(name1+"'s turn");
						lblPlaying.setForeground(player1);
						if(previous.equals(cont.getCurrentPlayerTurn()))
							lblScore2.setText("Score: " + cont.getCurrentPlayerScore());
						else
							lblScore1.setText("Score: " + cont.getCurrentPlayerScore());
					}
					else
					{
						lblPlaying.setText(name2+"'s turn");
						lblPlaying.setForeground(player2);
						if(previous.equals(cont.getCurrentPlayerTurn()))
							lblScore1.setText("Score: " + cont.getCurrentPlayerScore());
						else
							lblScore2.setText("Score: " + cont.getCurrentPlayerScore());
					}
				}
				else
					JOptionPane.showMessageDialog(null, "No moves to undo.", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnUndo.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		btnUndo.setBounds(150, rows*40+30, 130, 30);
		frmGameFrame.getContentPane().add(btnUndo);
	}
	
	private void addBasicSquare(JPanel pane, int x, int y)
	{
		JButton btnPoint = new JButton("");
		btnPoint.setBounds(x,y,10,10);
		btnPoint.setEnabled(false);
		btnPoint.setBackground(Color.BLACK);
		pane.add(btnPoint);
		
		JButton btnLine1 = new JButton("");
		btnLine1.setBounds(x+10,y,30,10);
		btnLine1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnLine1.isEnabled())
				{
					btns.add(btnLine1);
					try {
						cont.addLine(ListType.HORIZONTAL, y/40, x/40);
					} catch (ClassNotFoundException | UnexistentLineListException | IOException
							| DuplicatedPlayerStatsException e) {
						e.printStackTrace();
					}
					
					btnLine1.setBackground(lblPlaying.getForeground());
					btnLine1.setEnabled(false);
					
					if(cont.isEnded())
					{
						ResultFrame rf = new ResultFrame(frmGameFrame, s, cont);
						rf.showGUI();
					}
					else
					{
						if(cont.getCurrentPlayerTurn().equals(name1))
						{
							lblPlaying.setText(name1+"'s turn");
							lblPlaying.setForeground(player1);
							if(previous.equals(cont.getCurrentPlayerTurn()))
								lblScore1.setText("Score: " + cont.getCurrentPlayerScore());
							else
								lblScore2.setText("Score: " + cont.getCurrentPlayerScore());
						}
						else
						{
							lblPlaying.setText(name2+"'s turn");
							lblPlaying.setForeground(player2);
							if(previous.equals(cont.getCurrentPlayerTurn()))
								lblScore2.setText("Score: " + cont.getCurrentPlayerScore());
							else
								lblScore1.setText("Score: " + cont.getCurrentPlayerScore());
						}
					}
					
					previous = cont.getCurrentPlayerTurn();
				}
			}
		});
		pane.add(btnLine1);
		
		JButton btnLine2 = new JButton("");
		btnLine2.setBounds(x,y+10,10,30);
		btnLine2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnLine2.isEnabled())
				{
					btns.add(btnLine2);
					try {
						cont.addLine(ListType.VERTICAL, x/40, y/40);
					} catch (ClassNotFoundException | UnexistentLineListException | IOException
							| DuplicatedPlayerStatsException e) {
						e.printStackTrace();
					}
					
					btnLine2.setBackground(lblPlaying.getForeground());
					btnLine2.setEnabled(false);
					
					if(cont.isEnded())
					{
						ResultFrame rf = new ResultFrame(frmGameFrame, s, cont);
						rf.setBackground(frmGameFrame.getContentPane().getBackground());
						rf.showGUI();
					}
					else
					{
						if(cont.getCurrentPlayerTurn().equals(name1))
						{
							lblPlaying.setText(name1+"'s turn");
							lblPlaying.setForeground(player1);
							if(previous.equals(cont.getCurrentPlayerTurn()))
								lblScore1.setText("Score: " + cont.getCurrentPlayerScore());
							else
								lblScore2.setText("Score: " + cont.getCurrentPlayerScore());
						}
						else
						{
							lblPlaying.setText(name2+"'s turn");
							lblPlaying.setForeground(player2);
							if(previous.equals(cont.getCurrentPlayerTurn()))
								lblScore2.setText("Score: " + cont.getCurrentPlayerScore());
							else
								lblScore1.setText("Score: " + cont.getCurrentPlayerScore());
						}
					}
					
					previous = cont.getCurrentPlayerTurn();
				}
			}
		});
		pane.add(btnLine2);
		
		JButton btnBox = new JButton("");
		if(cont.getMode()==TypeGame.TRIANGLE)
			btnBox.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(!btnBox.getText().equals("\u2572"))
					{
						btns.add(btnBox);
						try {
							cont.addLine(ListType.DIAGONAL, y/40, x/40);
						} catch (ClassNotFoundException | UnexistentLineListException | IOException
								| DuplicatedPlayerStatsException e) {
							e.printStackTrace();
						}
						
						btnBox.setForeground(lblPlaying.getForeground());
						btnBox.setBorder(null);
						btnBox.setText("\u2572");
						
						if(cont.isEnded())
						{
							ResultFrame rf = new ResultFrame(frmGameFrame, s, cont);
							rf.showGUI();
						}
						else
						{
							if(cont.getCurrentPlayerTurn().equals(name1))
							{
								lblPlaying.setText(name1+"'s turn");
								lblPlaying.setForeground(player1);
								if(previous.equals(cont.getCurrentPlayerTurn()))
									lblScore1.setText("Score: " + cont.getCurrentPlayerScore());
								else
									lblScore2.setText("Score: " + cont.getCurrentPlayerScore());
							}
							else
							{
								lblPlaying.setText(name2+"'s turn");
								lblPlaying.setForeground(player2);
								if(previous.equals(cont.getCurrentPlayerTurn()))
									lblScore2.setText("Score: " + cont.getCurrentPlayerScore());
								else
									lblScore1.setText("Score: " + cont.getCurrentPlayerScore());
							}
						}
						
						previous = cont.getCurrentPlayerTurn();
					}
				}
			});
		else
			btnBox.setVisible(false);
		btnBox.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 28));
		btnBox.setBounds(x+10,y+10,30,30);
		pane.add(btnBox);
	}
	
	private void addVerticalSide(JPanel pane, int x, int y)
	{
		JButton btnPoint = new JButton("");
		btnPoint.setBounds(x,y,10,10);
		btnPoint.setEnabled(false);
		btnPoint.setBackground(Color.BLACK);
		pane.add(btnPoint);
		
		JButton btnLine = new JButton("");
		btnLine.setBounds(x,y+10,10,30);
		btnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnLine.isEnabled())
				{
					btns.add(btnLine);
					try {
						cont.addLine(ListType.VERTICAL, x/40, y/40);
					} catch (ClassNotFoundException | UnexistentLineListException | IOException
							| DuplicatedPlayerStatsException e) {
						e.printStackTrace();
					}
					
					btnLine.setBackground(lblPlaying.getForeground());
					btnLine.setEnabled(false);
					
					if(cont.isEnded())
					{
						ResultFrame rf = new ResultFrame(frmGameFrame, s, cont);
						rf.showGUI();
					}
					else
					{
						if(cont.getCurrentPlayerTurn().equals(name1))
						{
							lblPlaying.setText(name1+"'s turn");
							lblPlaying.setForeground(player1);
							if(previous.equals(cont.getCurrentPlayerTurn()))
								lblScore1.setText("Score: " + cont.getCurrentPlayerScore());
							else
								lblScore2.setText("Score: " + cont.getCurrentPlayerScore());
						}
						else
						{
							lblPlaying.setText(name2+"'s turn");
							lblPlaying.setForeground(player2);
							if(previous.equals(cont.getCurrentPlayerTurn()))
								lblScore2.setText("Score: " + cont.getCurrentPlayerScore());
							else
								lblScore1.setText("Score: " + cont.getCurrentPlayerScore());
						}
					}
					
					previous = cont.getCurrentPlayerTurn();
				}
			}
		});
		pane.add(btnLine);
	}
	
	private void addHorizontalSide(JPanel pane, int x, int y)
	{
		JButton btnPoint = new JButton("");
		btnPoint.setBounds(x,y,10,10);
		btnPoint.setEnabled(false);
		btnPoint.setBackground(Color.BLACK);
		pane.add(btnPoint);
		
		JButton btnLine = new JButton("");
		btnLine.setBounds(x+10,y,30,10);
		btnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnLine.isEnabled())
				{
					btns.add(btnLine);
					try {
						cont.addLine(ListType.HORIZONTAL, y/40, x/40);
					} catch (ClassNotFoundException | UnexistentLineListException | IOException
							| DuplicatedPlayerStatsException e) {
						e.printStackTrace();
					}
					
					btnLine.setBackground(lblPlaying.getForeground());
					btnLine.setEnabled(false);
					
					if(cont.isEnded())
					{
						ResultFrame rf = new ResultFrame(frmGameFrame, s, cont);
						rf.showGUI();
					}
					else
					{
						if(cont.getCurrentPlayerTurn().equals(name1))
						{
							lblPlaying.setText(name1+"'s turn");
							lblPlaying.setForeground(player1);
							if(previous.equals(cont.getCurrentPlayerTurn()))
								lblScore1.setText("Score: " + cont.getCurrentPlayerScore());
							else
								lblScore2.setText("Score: " + cont.getCurrentPlayerScore());
						}
						else
						{
							lblPlaying.setText(name2+"'s turn");
							lblPlaying.setForeground(player2);
							if(previous.equals(cont.getCurrentPlayerTurn()))
								lblScore2.setText("Score: " + cont.getCurrentPlayerScore());
							else
								lblScore1.setText("Score: " + cont.getCurrentPlayerScore());
						}
					}
					
					previous = cont.getCurrentPlayerTurn();
				}
			}
		});
		pane.add(btnLine);
	}
}
