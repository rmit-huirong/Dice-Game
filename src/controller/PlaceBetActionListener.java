package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import model.interfaces.GameEngine;
import view.AppFrame;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class PlaceBetActionListener implements ActionListener
{

	private GameEngine gameEngine;
	private AppFrame appFrame;
	private JTextField textField;
	
	public PlaceBetActionListener(GameEngine gameEngine, AppFrame appFrame, JTextField textField)
	{
		this.gameEngine = gameEngine;
		this.appFrame = appFrame;
		this.textField = textField;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			gameEngine.getPlayer(appFrame.getPlayerID());
			try
			{
				// get bet amount
				String text = textField.getText();
				int bet = Integer.valueOf(text);
				
				// check if bet amount is valid
				if(bet > 0)
				{
					if(gameEngine.getPlayer(appFrame.getPlayerID()).getBet() == 0)
					{
						appFrame.placeBet(bet);
					}
					else
					{
						System.out.println("*Warning*: You can't place more than one bet in one around.");
					}
				}
				else
				{
					System.out.println("*Warning*: Bet can't be 0.");
				}
			}
			catch (NumberFormatException nfe)
			{
				System.out.println("*Error*: Bet is not a number.");
			}
		}
		catch (NullPointerException npe)
		{
			System.out.println("*Error*: No player yet.");
		}
	}
}