package it.unibo.squaresgameteam.squares.view.classes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import javax.swing.*;
import it.unibo.squaresgameteam.squares.view.interfaces.*;

public class MatchSetupImpl implements MatchSetup{
	
	private JFrame frmMatchSetup;
	private JTextField txtPlayer1;
	private JTextField txtPlayer2;
	private JTextField txtRows;
	private JTextField txtColums;
	
	public MatchSetupImpl()
	{
		initialize();
	}
	
	public void showGUI()
	{
		frmMatchSetup.setLocationRelativeTo(null);
		frmMatchSetup.setVisible(true);
	}
	
	private void hideGUI()
	{
		frmMatchSetup.setVisible(false);
		frmMatchSetup.dispose();
	}
	
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
		
		JLabel lblPlayer1 = new JLabel("PLAYER 1");
		lblPlayer1.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lblPlayer1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer1.setBounds(50, 11, 200, 30);
		frmMatchSetup.getContentPane().add(lblPlayer1);
		
		txtPlayer1 = new JTextField();
		txtPlayer1.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		txtPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer1.setBounds(221, 14, 130, 25);
		frmMatchSetup.getContentPane().add(txtPlayer1);
		txtPlayer1.setColumns(10);
		
		JLabel lblPlayer2 = new JLabel("PLAYER 2");
		lblPlayer2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer2.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lblPlayer2.setBounds(50, 39, 200, 30);
		frmMatchSetup.getContentPane().add(lblPlayer2);
		
		txtPlayer2 = new JTextField();
		txtPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer2.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		txtPlayer2.setColumns(10);
		txtPlayer2.setBounds(221, 42, 130, 25);
		frmMatchSetup.getContentPane().add(txtPlayer2);
		
		JLabel lblRows = new JLabel("ROWS");
		lblRows.setHorizontalAlignment(SwingConstants.LEFT);
		lblRows.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lblRows.setBounds(50, 65, 200, 30);
		frmMatchSetup.getContentPane().add(lblRows);
		
		txtRows = new JTextField();
		txtRows.setText("6");
		txtRows.setToolTipText("A number between 4 and 10");
		txtRows.setHorizontalAlignment(SwingConstants.CENTER);
		txtRows.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		txtRows.setColumns(10);
		txtRows.setBounds(301, 68, 50, 25);
		frmMatchSetup.getContentPane().add(txtRows);
		
		JLabel lblColums = new JLabel("COLUMS");
		lblColums.setHorizontalAlignment(SwingConstants.LEFT);
		lblColums.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lblColums.setBounds(50, 95, 200, 30);
		frmMatchSetup.getContentPane().add(lblColums);
		
		txtColums = new JTextField();
		txtColums.setText("6");
		txtColums.setHorizontalAlignment(SwingConstants.CENTER);
		txtColums.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		txtColums.setColumns(10);
		txtColums.setBounds(301, 98, 50, 25);
		frmMatchSetup.getContentPane().add(txtColums);
		
		JLabel lblGameMode = new JLabel("GAME MODE");
		lblGameMode.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameMode.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lblGameMode.setBounds(50, 125, 200, 30);
		frmMatchSetup.getContentPane().add(lblGameMode);
		
		JComboBox<String> cmbGameMode = new JComboBox<String>();
		cmbGameMode.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		cmbGameMode.setBounds(221, 127, 130, 27);
		frmMatchSetup.getContentPane().add(cmbGameMode);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 166, 400, 2);
		frmMatchSetup.getContentPane().add(separator);
		
		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hideGUI();
			}
		});
		btnStart.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		btnStart.setBounds(20, 179, 130, 30);
		frmMatchSetup.getContentPane().add(btnStart);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hideGUI();
				StartMenuImpl sm = new StartMenuImpl();
				sm.setBackground(frmMatchSetup.getContentPane().getBackground());
				sm.showGUI();
			}
		});
		btnCancel.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		btnCancel.setBounds(250, 179, 130, 30);
		frmMatchSetup.getContentPane().add(btnCancel);
	}
	
	@Override
	public void startMatch() {
		// TODO Auto-generated method stub
		
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
