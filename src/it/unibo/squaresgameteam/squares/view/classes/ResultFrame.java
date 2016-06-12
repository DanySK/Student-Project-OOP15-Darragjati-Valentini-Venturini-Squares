package it.unibo.squaresgameteam.squares.view.classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import it.unibo.squaresgameteam.squares.controller.classes.MatchImpl;

import it.unibo.squaresgameteam.squares.view.interfaces.GUIElements;

public class ResultFrame implements GUIElements {
		
		private JFrame frmMatchSetup, f;
		private Settings s;
		private MatchImpl cont;
		
		public ResultFrame(JFrame f, Settings s, MatchImpl cont)
		{
			this.f = f;
			this.s = s;
			this.cont = cont;
			initialize();
		}
		
		@Override
		public void showGUI()
		{
			frmMatchSetup.setLocationRelativeTo(null);
			frmMatchSetup.setVisible(true);
		}
		
		@Override
		public void hideGUI()
		{
			frmMatchSetup.setVisible(false);
			frmMatchSetup.dispose();
		}
		
		@Override
		public void setBackground(Color c)
		{
			frmMatchSetup.getContentPane().setBackground(c);
		}
		
		private void initialize() {
			frmMatchSetup = new JFrame();
			frmMatchSetup.setUndecorated(true);
			frmMatchSetup.setTitle("Squares");
			frmMatchSetup.setResizable(false);
			frmMatchSetup.setBounds(100, 100, 400, 225);
			frmMatchSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmMatchSetup.getContentPane().setLayout(null);
			
			JPanel pane = new JPanel();
			pane.setBounds(5, 5, 390, 215);
			frmMatchSetup.getContentPane().add(pane);
			pane.setLayout(null);
			
			JLabel lblWinner;
			JLabel lblPlayer;
			
			if(cont.isPar())
			{
				lblWinner = new JLabel("NO WINNER");
				lblPlayer = new JLabel("It's a draw!");
			}
			else
			{
				lblWinner = new JLabel("Winner");
				lblPlayer = new JLabel(cont.getCurrentPlayerTurn());
			}
			
			lblWinner.setBounds(95, 10, 200, 30);
			pane.add(lblWinner);
			lblWinner.setFont(new Font("Sitka Text", Font.PLAIN, 16));
			lblWinner.setHorizontalAlignment(SwingConstants.CENTER);
			
			lblPlayer.setBounds(95, 40, 200, 30);
			pane.add(lblPlayer);
			lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer.setFont(new Font("Sitka Text", Font.PLAIN, 16));
			
			JLabel lblScore = new JLabel("Score: " + cont.getCurrentPlayerScore());
			lblScore.setBounds(75, 70, 240, 30);
			pane.add(lblScore);
			lblScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblScore.setFont(new Font("Sitka Text", Font.PLAIN, 16));
			
			JLabel lblTime = new JLabel("Played time: " + cont.getMatchTime() + "s");
			lblTime.setHorizontalAlignment(SwingConstants.CENTER);
			lblTime.setFont(new Font("Sitka Text", Font.PLAIN, 16));
			lblTime.setBounds(75, 131, 240, 30);
			pane.add(lblTime);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(0, 165, 390, 2);
			pane.add(separator);
			
			JButton btnMenu = new JButton("Menu");
			btnMenu.setBounds(140, 175, 120, 30);
			pane.add(btnMenu);
			btnMenu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					f.setVisible(false);
					f.dispose();
					hideGUI();
					StartMenuImpl sm = new StartMenuImpl(s);
					sm.setBackground(frmMatchSetup.getContentPane().getBackground());
					sm.showGUI();
				}
			});
			btnMenu.setFont(new Font("Sitka Text", Font.PLAIN, 17));

			JButton btnColor = new JButton("");
			btnColor.setBounds(0, 0, 400, 225);
			frmMatchSetup.getContentPane().add(btnColor);
			btnColor.setEnabled(false);
			btnColor.setBackground(Color.WHITE);
		}
}
