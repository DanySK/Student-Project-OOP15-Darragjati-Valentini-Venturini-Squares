package it.unibo.squaresgameteam.squares.view.classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		scrollPane.setBounds(10, 10, 374, 270);
		frmRankingMenu.getContentPane().add(scrollPane);

		txtRules = new JTextArea();
		txtRules.setEditable(false);
		txtRules.setBounds(10, 11, 374, 308);
		txtRules.setWrapStyleWord(true);
		txtRules.setLineWrap(true);
		scrollPane.setViewportView(txtRules);
		
		JLabel lblOrder = new JLabel("Order by:");
		lblOrder.setBounds(10, 285, 81, 14);
		frmRankingMenu.getContentPane().add(lblOrder);
		
		JRadioButton rdbtnWinrate = new JRadioButton("Winrate");
		rdbtnWinrate.setSelected(true);
		rdbtnWinrate.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		rdbtnWinrate.setBounds(6, 300, 70, 23);
		frmRankingMenu.getContentPane().add(rdbtnWinrate);
		
		JRadioButton rdbtnTotalWins = new JRadioButton("Total wins");
		rdbtnTotalWins.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		rdbtnTotalWins.setBounds(82, 299, 85, 23);
		frmRankingMenu.getContentPane().add(rdbtnTotalWins);
		
		JRadioButton rdbtnTotalMatches = new JRadioButton("Total matches");
		rdbtnTotalMatches.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		rdbtnTotalMatches.setBounds(169, 299, 105, 23);
		frmRankingMenu.getContentPane().add(rdbtnTotalMatches);
		
		JRadioButton rdbtnSquaresCatched = new JRadioButton("Squares catched");
		rdbtnSquaresCatched.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		rdbtnSquaresCatched.setBounds(276, 299, 115, 23);
		frmRankingMenu.getContentPane().add(rdbtnSquaresCatched);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnWinrate);
		bg.add(rdbtnTotalWins);
		bg.add(rdbtnTotalMatches);
		bg.add(rdbtnSquaresCatched);
		
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
		
		JButton btnApply = new JButton("Apply");
		btnApply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbtnWinrate.isSelected())
				{
					txtRules.setText("1");
					orderByWinrate();
				}
				else if(rdbtnTotalWins.isSelected())
				{
					txtRules.setText("2");
					orderByTotalWins();
				}
				else if(rdbtnTotalMatches.isSelected())
				{
					txtRules.setText("3");
					orderByTotalMatches();
				}
				else
				{
					txtRules.setText("4");
					orderByTotalSquaresCatched();
				}
			}
		});
		btnApply.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		btnApply.setBounds(254, 329, 130, 30);
		frmRankingMenu.getContentPane().add(btnApply);
	}
	
	@Override
	public void orderByWinrate() {
		try {
			txtRules.setText(cont.showRanking(RankingOption.WINRATE, true));
		} catch (ClassNotFoundException | IOException | DuplicatedPlayerStatsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void orderByTotalWins() {
		try {
			txtRules.setText(cont.showRanking(RankingOption.TOTAL_WINS, true));
		} catch (ClassNotFoundException | IOException | DuplicatedPlayerStatsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void orderByTotalMatches() {
		try {
			txtRules.setText(cont.showRanking(RankingOption.TOTAL_MATCHES, true));
		} catch (ClassNotFoundException | IOException | DuplicatedPlayerStatsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void orderByTotalSquaresCatched() {
		try {
			txtRules.setText(cont.showRanking(RankingOption.TOTAL_POINTS_SCORED, true));
		} catch (ClassNotFoundException | IOException | DuplicatedPlayerStatsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
