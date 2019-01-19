package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class RollHouseActionListener implements ActionListener
{
	private GameEngine gameEngine;
	private AppFrame appFrame;
	
	public RollHouseActionListener(GameEngine gameEngine, AppFrame appFrame)
	{
		this.gameEngine = gameEngine;
		this.appFrame = appFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		boolean canRoll = false;
		
		// check if at least one player has already placed valid bet
		for(Player player : gameEngine.getAllPlayers())
		{
			if(player.getRollResult() != null)
			{
				canRoll = true;
			}
		}
		
		// check if we can roll house
		if(canRoll)
		{
			// update house message in the area of player details
			appFrame.getStatusBar().getPlayerDetail().setText("THIS IS HOUSE:)");
			new Thread()
			{
				public void run()
				{
					// roll house in gameEngine and settle bet
					gameEngine.rollHouse(AppFrame.INITIAL_DELAY, AppFrame.FINAL_DELAY, AppFrame.DELAY_INCREMENT);
					
					// update battle results
					appFrame.getInfo().getCurrentPanel().getBattleResultPanel().update();
					
					// reset all player bets to 0s after bet settled
					for(Player player : gameEngine.getAllPlayers())
					{
						gameEngine.placeBet(player, 0);
						player.setRollResult(null);
					}
				}
			}.start();
			
			// update battle results
			appFrame.getInfo().getCurrentPanel().getBattleResultPanel().update();	
		}
		else
		{
			System.out.println("*Warning*: You must roll at least one player before you can roll House.");
		}
	}
}
