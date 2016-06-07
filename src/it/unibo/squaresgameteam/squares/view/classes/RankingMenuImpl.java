package it.unibo.squaresgameteam.squares.view.classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.unibo.squaresgameteam.squares.view.interfaces.*;

public class RankingMenuImpl implements RankingMenu {

	private JFrame frmRankingMenu;

	/**
	 * Create the application.
	 */
	public RankingMenuImpl() {
		initialize();
	}
	
	public void showGUI()
	{
		frmRankingMenu.setLocationRelativeTo(null);
		frmRankingMenu.setVisible(true);
	}
	
	private void hideGUI()
	{
		frmRankingMenu.setVisible(false);
		frmRankingMenu.dispose();
	}
	
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
		        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","Online Examination System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
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
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hideGUI();
				StartMenuImpl sm = new StartMenuImpl();
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
