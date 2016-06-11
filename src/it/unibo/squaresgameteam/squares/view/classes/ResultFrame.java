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

import it.unibo.squaresgameteam.squares.controller.classes.MusicImpl;

import it.unibo.squaresgameteam.squares.view.interfaces.GUIElements;

public class ResultFrame implements GUIElements {
		
		private JFrame frmMatchSetup;
		private MusicImpl mi;
		
		public ResultFrame(MusicImpl mi)
		{
			this.mi = mi;
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
			frmMatchSetup.getContentPane().setBackground(Color.WHITE);
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
			
			JLabel lblWinner = new JLabel("Winner");
			lblWinner.setBounds(95, 10, 200, 30);
			pane.add(lblWinner);
			lblWinner.setFont(new Font("Sitka Text", Font.PLAIN, 16));
			lblWinner.setHorizontalAlignment(SwingConstants.CENTER);
			
			JLabel lblPlayer = new JLabel("PLAYER");
			lblPlayer.setBounds(95, 40, 200, 30);
			pane.add(lblPlayer);
			lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer.setFont(new Font("Sitka Text", Font.PLAIN, 16));
			
			JLabel lblScore = new JLabel("Score:");
			lblScore.setBounds(75, 90, 240, 30);
			pane.add(lblScore);
			lblScore.setHorizontalAlignment(SwingConstants.LEFT);
			lblScore.setFont(new Font("Sitka Text", Font.PLAIN, 16));
			
			JSeparator separator = new JSeparator();
			separator.setBounds(0, 165, 390, 2);
			pane.add(separator);
			
			JButton btnMenu = new JButton("Menu");
			btnMenu.setBounds(140, 175, 120, 30);
			pane.add(btnMenu);
			btnMenu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					hideGUI();
					StartMenuImpl sm = new StartMenuImpl(mi);
					sm.setBackground(frmMatchSetup.getContentPane().getBackground());
					sm.showGUI();
				}
			});
			btnMenu.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		}
}
