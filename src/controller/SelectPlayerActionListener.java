package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PlayerDecorator;
import model.interfaces.GameEngine;
import view.AppFrame;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class SelectPlayerActionListener implements ActionListener
{

	private GameEngine gameEngine;
	private AppFrame appFrame;
	
	public SelectPlayerActionListener(GameEngine gameEngine, AppFrame appFrame)
	{
		this.gameEngine = gameEngine;
		this.appFrame = appFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(appFrame.getToolBar().getPlayerBox().getSelectedItem() != null)
		{
			// set the current player to be the selected player
			appFrame.setPlayer(((PlayerDecorator)appFrame.getToolBar().getPlayerBox().getSelectedItem()).getPlayerID());
			
			// check if selected player has finished rolling
			if(gameEngine.getPlayer(appFrame.getPlayerID()).getRollResult() != null)
			{			
				// update the player dice result
				appFrame.getInfo().getDiceResultPanel().getPlayerDiceResultPanel().getResult().setText(gameEngine.getPlayer(appFrame.getPlayerID()).getRollResult().toString());
				appFrame.getInfo().getDiceResultPanel().getPlayerDiceResultPanel().update(gameEngine.getPlayer(appFrame.getPlayerID()).getRollResult().getDice1(), gameEngine.getPlayer(appFrame.getPlayerID()).getRollResult().getDice2());
			}
			else
			{
				// reset the player dice result
				appFrame.getInfo().getDiceResultPanel().getPlayerDiceResultPanel().getResult().setText("Oops! Haven't rolled yet.");
				appFrame.getInfo().getDiceResultPanel().getPlayerDiceResultPanel().update(0, 0);
			}
			
			// update the player details
			appFrame.getStatusBar().getPlayerDetail().setText("PLAYER ID: " + appFrame.getPlayerID() + " POINTS: " + appFrame.getPoints() + " BET: " + gameEngine.getPlayer(appFrame.getPlayerID()).getBet());
		}
	}

}
