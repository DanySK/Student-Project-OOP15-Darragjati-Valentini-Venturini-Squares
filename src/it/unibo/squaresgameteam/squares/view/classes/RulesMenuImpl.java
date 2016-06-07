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
import javax.swing.JTextPane;

import it.unibo.squaresgameteam.squares.view.interfaces.*;

public class RulesMenuImpl implements RulesMenu {

private JFrame frmRulesMenu;
	
	public RulesMenuImpl()
	{
		initialize();
	}
	
	public void showGUI()
	{
		frmRulesMenu.setLocationRelativeTo(null);
		frmRulesMenu.setVisible(true);
	}
	
	private void hideGUI()
	{
		frmRulesMenu.setVisible(false);
		frmRulesMenu.dispose();
	}
	
	public void setBackground(Color c)
	{
		frmRulesMenu.getContentPane().setBackground(c);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRulesMenu = new JFrame();
		frmRulesMenu.addWindowListener(new WindowAdapter() {
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
		frmRulesMenu.setTitle("Squares");
		frmRulesMenu.setResizable(false);
		frmRulesMenu.setBounds(100, 100, 400, 400);
		frmRulesMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmRulesMenu.getContentPane().setLayout(null);

		JTextPane txtRules = new JTextPane();
		txtRules.setBounds(10, 11, 374, 308);
		frmRulesMenu.getContentPane().add(txtRules);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hideGUI();
				StartMenuImpl sm = new StartMenuImpl();
				sm.setBackground(frmRulesMenu.getContentPane().getBackground());
				sm.showGUI();
			}
		});
		btnBack.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		btnBack.setBounds(10, 330, 130, 30);
		frmRulesMenu.getContentPane().add(btnBack);
	}
	
	@Override
	public void showRules() {
		// TODO Auto-generated method stub
		
	}

}
