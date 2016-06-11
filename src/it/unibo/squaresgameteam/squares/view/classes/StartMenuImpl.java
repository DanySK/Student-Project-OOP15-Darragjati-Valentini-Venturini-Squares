package it.unibo.squaresgameteam.squares.view.classes;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import it.unibo.squaresgameteam.squares.controller.classes.MusicImpl;

import it.unibo.squaresgameteam.squares.view.interfaces.StartMenu;
import it.unibo.squaresgameteam.squares.view.interfaces.GUIElements;

public class StartMenuImpl implements StartMenu, GUIElements {
	
	private JFrame frmStartMenu;
	private MusicImpl mi;
	private Settings s;
	

	/**
	 * Create the application.
	 */
	public StartMenuImpl(MusicImpl mi) {
		frmStartMenu = new JFrame();
		s = new Settings(mi, frmStartMenu.getBackground(), Color.RED, Color.BLUE);
		initialize();
	}
	
	public StartMenuImpl(Settings s)
	{
		frmStartMenu = new JFrame();
		this.s = s;
		initialize();
	}
	
	@Override
	public void showGUI()
	{
		frmStartMenu.setLocationRelativeTo(null);
		frmStartMenu.setVisible(true);
	}
	
	@Override
	public void hideGUI()
	{
		frmStartMenu.setVisible(false);
		frmStartMenu.dispose();
	}
	
	@Override
	public void setBackground(Color c)
	{
		frmStartMenu.getContentPane().setBackground(c);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmStartMenu.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				String ObjButtons[] = {"Yes","No"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","Squares",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        {
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
	public void startNewGame() {
		MatchSetupImpl ms = new MatchSetupImpl(frmStartMenu, s);
		ms.setBackground(frmStartMenu.getContentPane().getBackground());
		ms.showGUI();
	}

	@Override
	public void showRules() {
		hideGUI();
		RulesMenuImpl rm = new RulesMenuImpl(s);
		rm.setBackground(frmStartMenu.getContentPane().getBackground());
		rm.showGUI();
	}

	@Override
	public void showRanking() {
		hideGUI();
		RankingMenuImpl rankm = new RankingMenuImpl(s);
		rankm.setBackground(frmStartMenu.getContentPane().getBackground());
		rankm.showGUI();
	}

	@Override
	public void showSettings() {
		hideGUI();
		OptionsMenuImpl om = new OptionsMenuImpl(s);
		om.setBackground(frmStartMenu.getContentPane().getBackground());
		om.showGUI();
	}

}
