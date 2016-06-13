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
import javax.swing.SwingConstants;

import it.unibo.squaresgameteam.squares.controller.classes.ShowRankingImpl;

import it.unibo.squaresgameteam.squares.model.enumerations.RankingOption;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.view.interfaces.RankingMenu;
import it.unibo.squaresgameteam.squares.view.interfaces.GUIElements;

/**
 * 
 * @author Karl Darragjati This class displays and orders the game ranking.
 *
 */
public class RankingMenuImpl implements RankingMenu, GUIElements {

	private JFrame frmRankingMenu;
	private JTextArea txtRules;
	private ShowRankingImpl cont;
	private Settings s;
	private JRadioButton rdtbnAscending, rdbtnDescending;

	/**
	 * This constructor initializes the frame and his components.
     * 
     * @param s
     *            settings manager
     */
	public RankingMenuImpl(Settings s) {
		try {
			cont = new ShowRankingImpl();
		} catch (DuplicatedPlayerStatsException e) {
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
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(10, 10, 75, 14);
		frmRankingMenu.getContentPane().add(lblName);
		
		JLabel lblWinrate = new JLabel("Winrate");
		lblWinrate.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblWinrate.setHorizontalAlignment(SwingConstants.CENTER);
		lblWinrate.setBounds(85, 10, 75, 14);
		frmRankingMenu.getContentPane().add(lblWinrate);
		
		JLabel lblTotalWins = new JLabel("Total wins");
		lblTotalWins.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblTotalWins.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalWins.setBounds(160, 10, 75, 14);
		frmRankingMenu.getContentPane().add(lblTotalWins);
		
		JLabel lblTotalMatches = new JLabel("Total matches");
		lblTotalMatches.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblTotalMatches.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalMatches.setBounds(235, 10, 75, 14);
		frmRankingMenu.getContentPane().add(lblTotalMatches);
		
		JLabel lblPointsScored = new JLabel("Points scored");
		lblPointsScored.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblPointsScored.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointsScored.setBounds(310, 10, 75, 14);
		frmRankingMenu.getContentPane().add(lblPointsScored);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 30, 385, 250);
		frmRankingMenu.getContentPane().add(scrollPane);

		txtRules = new JTextArea();
		txtRules.setEditable(false);
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
		
		JRadioButton rdbtnSquaresCatched = new JRadioButton("Points scored");
		rdbtnSquaresCatched.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		rdbtnSquaresCatched.setBounds(276, 299, 115, 23);
		frmRankingMenu.getContentPane().add(rdbtnSquaresCatched);
		
		rdtbnAscending = new JRadioButton("Ascending");
		rdtbnAscending.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		rdtbnAscending.setBounds(70, 280, 85, 23);
		frmRankingMenu.getContentPane().add(rdtbnAscending);
		
		rdbtnDescending = new JRadioButton("Descending");
		rdbtnDescending.setSelected(true);
		rdbtnDescending.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		rdbtnDescending.setBounds(152, 280, 90, 23);
		frmRankingMenu.getContentPane().add(rdbtnDescending);

		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rdbtnWinrate);
		bg1.add(rdbtnTotalWins);
		bg1.add(rdbtnTotalMatches);
		bg1.add(rdbtnSquaresCatched);
		
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(rdtbnAscending);
		bg2.add(rdbtnDescending);
		
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
					orderByWinrate();
				}
				else if(rdbtnTotalWins.isSelected())
				{
					orderByTotalWins();
				}
				else if(rdbtnTotalMatches.isSelected())
				{
					orderByTotalMatches();
				}
				else
				{
					orderByTotalPointsScored();
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
			if(rdtbnAscending.isSelected())
				txtRules.setText(cont.showRanking(RankingOption.WINRATE, true));
			else
				txtRules.setText(cont.showRanking(RankingOption.WINRATE, false));
		} catch (ClassNotFoundException | IOException | DuplicatedPlayerStatsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void orderByTotalWins() {
		try {
			if(rdtbnAscending.isSelected())
				txtRules.setText(cont.showRanking(RankingOption.TOTAL_WINS, true));
			else
				txtRules.setText(cont.showRanking(RankingOption.TOTAL_WINS, false));
		} catch (ClassNotFoundException | IOException | DuplicatedPlayerStatsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void orderByTotalMatches() {
		try {
			if(rdtbnAscending.isSelected())
				txtRules.setText(cont.showRanking(RankingOption.TOTAL_MATCHES, true));
			else
				txtRules.setText(cont.showRanking(RankingOption.TOTAL_MATCHES, false));
		} catch (ClassNotFoundException | IOException | DuplicatedPlayerStatsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void orderByTotalPointsScored() {
		try {
			if(rdtbnAscending.isSelected())
				txtRules.setText(cont.showRanking(RankingOption.TOTAL_POINTS_SCORED, true));
			else
				txtRules.setText(cont.showRanking(RankingOption.TOTAL_POINTS_SCORED, false));
		} catch (ClassNotFoundException | IOException | DuplicatedPlayerStatsException e) {
			e.printStackTrace();
		}
	}

}
