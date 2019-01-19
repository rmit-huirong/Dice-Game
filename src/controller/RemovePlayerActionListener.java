package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AppFrame;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class RemovePlayerActionListener implements ActionListener
{

	private AppFrame appFrame;
	
	public RemovePlayerActionListener(AppFrame appFrame)
	{
		this.appFrame = appFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{	
		// check if playerID is valid
		try
		{
			// get details of the player to be removed
			appFrame.removePlayer();
			
			// update playerBox
			appFrame.getToolBar().getPlayerBox().update();
		}
		catch (NullPointerException npe)
		{
			System.out.println("*Error*: Please enter a valid playerID.");
		}
	}
}
