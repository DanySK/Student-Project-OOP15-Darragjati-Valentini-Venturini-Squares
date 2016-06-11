package it.unibo.squaresgameteam.squares.view.classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.squaresgameteam.squares.controller.classes.MusicImpl;
import it.unibo.squaresgameteam.squares.controller.classes.MatchImpl;

import it.unibo.squaresgameteam.squares.view.interfaces.GUIElements;

public class GameFrame implements GUIElements {
	private ArrayList<ArrayList<JButton>> btns;
	private JFrame frmGameFrame;
	private JLabel lblPlaying;
	private int rows, colums;
	private boolean player = true, change = true;
	private String name1, name2;
	private Color player1=Color.CYAN, player2=Color.ORANGE;
	private MusicImpl mi;

	public GameFrame(MatchImpl cont, MusicImpl mi) {
		rows = cont.getRowsNumber();
		colums = cont.getColumsNumber();
		name1 = cont.getNamePlayer1();
		name2 = cont.getNamePlayer2();
		this.mi = mi;
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
		btns = new ArrayList<ArrayList<JButton>>();
		
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
		
		
		lblPlaying = new JLabel(name1+"'s turn");
		lblPlaying.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaying.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblPlaying.setBounds(colums*40+30, 10, 150, 30);
		lblPlaying.setForeground(player1);
		frmGameFrame.getContentPane().add(lblPlaying);
		
		JLabel lblPlayer1 = new JLabel(name1);
		lblPlayer1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblPlayer1.setBounds(colums*40+30, 50, 150, 30);
		frmGameFrame.getContentPane().add(lblPlayer1);
		
		JLabel lblScore1 = new JLabel("Score: ");
		lblScore1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblScore1.setBounds(colums*40+30, 80, 150, 30);
		frmGameFrame.getContentPane().add(lblScore1);
		
		JLabel lblPlayer2 = new JLabel(name2);
		lblPlayer2.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblPlayer2.setBounds(colums*40+30, 120, 150, 30);
		frmGameFrame.getContentPane().add(lblPlayer2);
		
		JLabel lblScore2 = new JLabel("Score: ");
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
					StartMenuImpl sm = new StartMenuImpl(mi);
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
				
			}
		});
		btnUndo.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		btnUndo.setBounds(150, rows*40+30, 130, 30);
		frmGameFrame.getContentPane().add(btnUndo);
	}
	
	private void addBasicSquare(JPanel pane, int x, int y)
	{
		btns.add(new ArrayList<JButton>());
		
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
				if(y!=0)
				{
					if(!btns.get(x/40+(y/40-1)*colums).get(0).isEnabled() &&
							!btns.get(x/40+(y/40-1)*colums).get(1).isEnabled() &&
							!btns.get(x/40+(y/40-1)*colums).get(3).isEnabled() &&
							!btns.get(x/40+(y/40-1)*colums).get(4).isEnabled())
					{
						btns.get(x/40+(y/40-1)*colums).get(2).setVisible(true);
						btns.get(x/40+(y/40-1)*colums).get(2).setBackground(lblPlaying.getForeground());
						
						change = false;
					}
				}
				if(!btns.get(x/40+y/40*colums).get(0).isEnabled() &&
						!btns.get(x/40+y/40*colums).get(1).isEnabled() &&
						!btns.get(x/40+y/40*colums).get(3).isEnabled() &&
						!btns.get(x/40+y/40*colums).get(4).isEnabled())
				{
					btns.get(x/40+y/40*colums).get(2).setVisible(true);
					btns.get(x/40+y/40*colums).get(2).setBackground(lblPlaying.getForeground());
					
					change = false;
				}
				
				btnLine1.setBackground(lblPlaying.getForeground());
				btnLine1.setEnabled(false);
				
				if(change==true)
				{
					if(player==true)
					{
						lblPlaying.setForeground(player2);
						lblPlaying.setText(name2+"'s turn");
					}
					else
					{
						lblPlaying.setForeground(player1);
						lblPlaying.setText(name1+"'s turn");
					}
					player=!player;
				}
				else
					change = true;
			}
		});
		if(y!=0)
			btns.get(x/40+(y/40-1)*colums).add(btnLine1);
		btns.get(x/40+y/40*colums).add(btnLine1);
		pane.add(btnLine1);
		
		JButton btnLine2 = new JButton("");
		btnLine2.setBounds(x,y+10,10,30);
		btnLine2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(y!=0)
				{
					if(!btns.get((x/40-1)+y/40*colums).get(0).isEnabled() &&
							!btns.get((x/40-1)+y/40*colums).get(1).isEnabled() &&
							!btns.get((x/40-1)+y/40*colums).get(3).isEnabled() &&
							!btns.get((x/40-1)+y/40*colums).get(4).isEnabled())
					{
						btns.get((x/40-1)+y/40*colums).get(2).setVisible(true);
						btns.get((x/40-1)+y/40*colums).get(2).setBackground(lblPlaying.getForeground());
						
						change = false;
					}
				}
				if(!btns.get(x/40+y/40*colums).get(0).isEnabled() &&
						!btns.get(x/40+y/40*colums).get(1).isEnabled() &&
						!btns.get(x/40+y/40*colums).get(3).isEnabled() &&
						!btns.get(x/40+y/40*colums).get(4).isEnabled())
				{
					btns.get(x/40+y/40*colums).get(2).setVisible(true);
					btns.get(x/40+y/40*colums).get(2).setBackground(lblPlaying.getForeground());
					
					change = false;
				}
				
				btnLine2.setBackground(lblPlaying.getForeground());
				btnLine2.setEnabled(false);
				
				if(change==true)
				{
					if(player==true)
					{
						lblPlaying.setForeground(player2);
						lblPlaying.setText(name2+"'s turn");
					}
					else
					{
						lblPlaying.setForeground(player1);
						lblPlaying.setText(name1+"'s turn");
					}
					player=!player;
				}
				else
					change = true;
			}
		});
		if(x!=0)
			btns.get((x/40-1)+y/40*colums).add(btnLine1);
		btns.get(x/40+y/40*colums).add(btnLine2);
		pane.add(btnLine2);
		
		JButton btnBox = new JButton("");
		btnBox.setBounds(x+10,y+10,30,30);
		btnBox.setEnabled(false);
		btnBox.setVisible(false);
		btns.get(x/40+y/40*colums).add(btnBox);
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
				if(!btns.get((x/40-1)+y/40*colums).get(0).isEnabled() &&
						!btns.get((x/40-1)+y/40*colums).get(1).isEnabled() &&
						!btns.get((x/40-1)+y/40*colums).get(3).isEnabled() &&
						!btns.get((x/40-1)+y/40*colums).get(4).isEnabled())
				{
					btns.get((x/40-1)+y/40*colums).get(2).setVisible(true);
					btns.get((x/40-1)+y/40*colums).get(2).setBackground(lblPlaying.getForeground());
					
					change = false;
				}
				
				btnLine.setBackground(lblPlaying.getForeground());
				btnLine.setEnabled(false);
				
				if(change==true)
				{
					if(player==true)
					{
						lblPlaying.setForeground(player2);
						lblPlaying.setText(name2+"'s turn");
					}
					else
					{
						lblPlaying.setForeground(player1);
						lblPlaying.setText(name1+"'s turn");
					}
					player=!player;
				}
				else
					change = true;
			}
		});
		btns.get((x/40-1)+y/40*colums).add(btnLine);
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
				if(!btns.get(x/40+(y/40-1)*colums).get(0).isEnabled() &&
						!btns.get(x/40+(y/40-1)*colums).get(1).isEnabled() &&
						!btns.get(x/40+(y/40-1)*colums).get(3).isEnabled() &&
						!btns.get(x/40+(y/40-1)*colums).get(4).isEnabled())
				{
					btns.get(x/40+(y/40-1)*colums).get(2).setVisible(true);
					btns.get(x/40+(y/40-1)*colums).get(2).setBackground(lblPlaying.getForeground());
					
					change = false;
				}
				
				btnLine.setBackground(lblPlaying.getForeground());
				btnLine.setEnabled(false);
				
				if(change==true)
				{
					if(player==true)
					{
						lblPlaying.setForeground(player2);
						lblPlaying.setText(name2+"'s turn");
					}
					else
					{
						lblPlaying.setForeground(player1);
						lblPlaying.setText(name1+"'s turn");
					}
					player=!player;
				}
				else
					change = true;
			}
		});
		btns.get(x/40+(y/40-1)*colums).add(btnLine);
		pane.add(btnLine);
	}
}
