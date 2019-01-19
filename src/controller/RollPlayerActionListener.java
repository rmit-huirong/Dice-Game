package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.AppFrame;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class RollPlayerActionListener implements ActionListener
{

	private GameEngine gameEngine;
	private AppFrame appFrame;
	
	public RollPlayerActionListener(GameEngine gameEngine, AppFrame appFrame)
	{
		this.gameEngine = gameEngine;
		this.appFrame = appFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// check if a valid player has been selected
		try
		{
			// check if it is a valid player
			gameEngine.getPlayer(appFrame.getPlayerID());
		
			// check if the player has rolled for current round
			if(gameEngine.getPlayer(appFrame.getPlayerID()).getRollResult() == null)
			{
				// check if valid bet has already been placed
				if(gameEngine.getPlayer(appFrame.getPlayerID()).getBet() != 0)
				{
					// update player details
					appFrame.getStatusBar().getPlayerDetail().setText("PLAYER ID: " + gameEngine.getPlayer(appFrame.getPlayerID()).getPlayerId() + " POINTS: " + gameEngine.getPlayer(appFrame.getPlayerID()).getPoints() + " BET: " + gameEngine.getPlayer(appFrame.getPlayerID()).getBet());
					
					new Thread()
					{
						public void run()
						{
							// roll player in gameEngine
							gameEngine.rollPlayer(gameEngine.getPlayer(appFrame.getPlayerID()), AppFrame.INITIAL_DELAY, AppFrame.FINAL_DELAY, AppFrame.DELAY_INCREMENT);
						}
					}.start();
					
					// update player details
					appFrame.getStatusBar().getPlayerDetail().setText("PLAYER ID: " + gameEngine.getPlayer(appFrame.getPlayerID()).getPlayerId() + " POINTS: " + gameEngine.getPlayer(appFrame.getPlayerID()).getPoints() + " BET: " + gameEngine.getPlayer(appFrame.getPlayerID()).getBet());
				}
				else
				{
					System.out.println("Warning*: You must place a valid bet before you can roll.");
				}
			}
			else
			{
				System.out.println("*Warning*: A player can only roll one time for each round.");
			}
		}
		catch (NullPointerException npe)
		{
			System.out.println("*Error*: No player yet.");
		}
	}
}
