package it.unibo.squaresgameteam.squares.view.classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import it.unibo.squaresgameteam.squares.controller.classes.MenuImpl;
import it.unibo.squaresgameteam.squares.controller.classes.MatchImpl;
import it.unibo.squaresgameteam.squares.controller.enumerations.TypeGame;

import it.unibo.squaresgameteam.squares.view.interfaces.MatchSetup;
import it.unibo.squaresgameteam.squares.view.interfaces.GUIElements;

public class MatchSetupImpl implements MatchSetup, GUIElements {
	
	private JFrame frmMatchSetup;
	private JFrame frame;
	private JTextField txtPlayer1;
	private JTextField txtPlayer2;
	private JTextField txtRows;
	private JTextField txtColums;
	private JComboBox<String> cmbGameMode;
	private MenuImpl cont;
	
	public MatchSetupImpl(JFrame f)
	{
		cont = new MenuImpl();
		initialize();
		frame = f;
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
		
		JLabel lblPlayer1 = new JLabel("PLAYER 1");
		lblPlayer1.setBounds(30, 10, 200, 30);
		pane.add(lblPlayer1);
		lblPlayer1.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lblPlayer1.setHorizontalAlignment(SwingConstants.LEFT);
		
		txtPlayer1 = new JTextField();
		txtPlayer1.setBounds(230, 10, 130, 25);
		pane.add(txtPlayer1);
		txtPlayer1.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		txtPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer1.setColumns(10);
		
		JLabel lblPlayer2 = new JLabel("PLAYER 2");
		lblPlayer2.setBounds(30, 40, 200, 30);
		pane.add(lblPlayer2);
		lblPlayer2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer2.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		
		txtPlayer2 = new JTextField();
		txtPlayer2.setBounds(230, 40, 130, 25);
		pane.add(txtPlayer2);
		txtPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer2.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		txtPlayer2.setColumns(10);
		
		JLabel lblRows = new JLabel("ROWS");
		lblRows.setBounds(30, 70, 200, 30);
		pane.add(lblRows);
		lblRows.setHorizontalAlignment(SwingConstants.LEFT);
		lblRows.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		
		txtRows = new JTextField();
		txtRows.setBounds(310, 70, 50, 25);
		pane.add(txtRows);
		txtRows.setText("6");
		txtRows.setToolTipText("A number between 4 and 10");
		txtRows.setHorizontalAlignment(SwingConstants.CENTER);
		txtRows.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		txtRows.setColumns(10);
		
		JLabel lblColums = new JLabel("COLUMS");
		lblColums.setBounds(30, 100, 200, 30);
		pane.add(lblColums);
		lblColums.setHorizontalAlignment(SwingConstants.LEFT);
		lblColums.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		
		txtColums = new JTextField();
		txtColums.setBounds(310, 100, 50, 25);
		pane.add(txtColums);
		txtColums.setText("6");
		txtColums.setHorizontalAlignment(SwingConstants.CENTER);
		txtColums.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		txtColums.setColumns(10);
		
		JLabel lblGameMode = new JLabel("GAME MODE");
		lblGameMode.setBounds(30, 130, 200, 30);
		pane.add(lblGameMode);
		lblGameMode.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameMode.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		
		cmbGameMode = new JComboBox<String>();
		cmbGameMode.setBounds(230, 130, 130, 27);
		cmbGameMode.addItem("SQUARE");
		cmbGameMode.addItem("TRIANGLE");
		pane.add(cmbGameMode);
		cmbGameMode.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 165, 390, 2);
		pane.add(separator);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(10, 175, 130, 30);
		pane.add(btnStart);
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				startMatch();
			}
		});
		btnStart.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(250, 175, 130, 30);
		pane.add(btnCancel);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hideGUI();
			}
		});
		btnCancel.setFont(new Font("Sitka Text", Font.PLAIN, 17));
	}
	
	@Override
	public void startMatch() {
		// TODO Auto-generated method stub
		hideGUI();
		frame.setVisible(false);
		frame.dispose();
		GameFrame gf;
		if(cmbGameMode.getSelectedIndex()==0)
			gf = new GameFrame((MatchImpl) cont.createMatch(Integer.parseInt(txtColums.getText()), Integer.parseInt(txtRows.getText()), txtPlayer1.getText(), txtPlayer2.getText(), TypeGame.SQUARE));
		else
			gf = new GameFrame((MatchImpl) cont.createMatch(Integer.parseInt(txtColums.getText()), Integer.parseInt(txtRows.getText()), txtPlayer1.getText(), txtPlayer2.getText(), TypeGame.TRIANGLE));
		gf.setBackground(frmMatchSetup.getContentPane().getBackground());
		gf.showGUI();
	}

	@Override
	public void borderless() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBoardType() {
		// TODO Auto-generated method stub
		
	}

}
