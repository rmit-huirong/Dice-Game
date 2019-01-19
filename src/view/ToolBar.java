package view;

import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controller.PlaceBetActionListener;
import controller.RollHouseActionListener;
import controller.RollPlayerActionListener;
import controller.SelectPlayerActionListener;
import model.interfaces.GameEngine;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class ToolBar extends JToolBar
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AbstractButton rollPlayer = new JButton("Roll Selected Player");
	private AbstractButton rollHouse = new JButton("Roll House");
	private AbstractButton placeBet = new JButton("Place Bet");
	private JTextField betAmount = new JTextField(10);
	private ComboBox playerBox;
	
	public ToolBar(GameEngine gameEngine, AppFrame appFrame)
	{		
		setLayout(new FlowLayout());
		
		playerBox = new ComboBox(gameEngine);
				
		// add components
		add(rollPlayer);
		add(rollHouse);
		add(playerBox);
		add(placeBet);
		add(betAmount);
		
		// add action listener to buttons
		placeBet.addActionListener(new PlaceBetActionListener(gameEngine, appFrame, betAmount));
		rollPlayer.addActionListener(new RollPlayerActionListener(gameEngine, appFrame));
		rollHouse.addActionListener(new RollHouseActionListener(gameEngine, appFrame));
		playerBox.addActionListener(new SelectPlayerActionListener(gameEngine, appFrame));
	}
	
	public ComboBox getPlayerBox()
	{
		return playerBox;
	}
}
