package it.unibo.squaresgameteam.squares.view.classes;

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

import it.unibo.squaresgameteam.squares.controller.classes.ShowRankingImpl;

import it.unibo.squaresgameteam.squares.model.enumerations.RankingOption;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.view.interfaces.RankingMenu;
import it.unibo.squaresgameteam.squares.view.interfaces.GUIElements;

public class RankingMenuImpl implements RankingMenu, GUIElements {

	private JFrame frmRankingMenu;
	private JTextArea txtRules;
	private ShowRankingImpl cont;
	private Settings s;

	/**
	 * Create the application.
	 */
	public RankingMenuImpl(Settings s) {
		try {
			cont = new ShowRankingImpl();
		} catch (DuplicatedPlayerStatsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.s = s;
		initialize();
	}
	
	@Override
	public void showGUI()
	{
		frmRankingMenu.setLocationRelativeTo(null);
		frmRankingMenu.setVisible(true);
	}
	
	@Override
	public void hideGUI()
	{
		frmRankingMenu.setVisible(false);
		frmRankingMenu.dispose();
	}
	
	@Override
	public void setBackground(Color c)
	{
		frmRankingMenu.getContentPane().setBackground(c);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRankingMenu = new JFrame();
		frmRankingMenu.addWindowListener(new WindowAdapter() {
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
		frmRankingMenu.setTitle("Squares");
		frmRankingMenu.setResizable(false);
		frmRankingMenu.setBounds(100, 100, 400, 400);
		frmRankingMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmRankingMenu.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 374, 308);
		frmRankingMenu.getContentPane().add(scrollPane);

		txtRules = new JTextArea();
		txtRules.setEditable(false);
		txtRules.setBounds(10, 11, 374, 308);
		txtRules.setWrapStyleWord(true);
		txtRules.setLineWrap(true);
		scrollPane.setViewportView(txtRules);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hideGUI();
				StartMenuImpl sm = new StartMenuImpl(s);
				sm.setBackground(frmRankingMenu.getContentPane().getBackground());
				sm.showGUI();
			}
		});
		btnBack.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		btnBack.setBounds(10, 330, 130, 30);
		frmRankingMenu.getContentPane().add(btnBack);
	}
	
	@Override
	public void orderByWinrate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void orderByTotalWins() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void orderByTotalMatches() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void orderByTotalSquaresCatched() {
		// TODO Auto-generated method stub
		
	}

}
