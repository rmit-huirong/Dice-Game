package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AppFrame;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class AddPlayerActionListener implements ActionListener
{
	
	private AppFrame appFrame;
	
	public AddPlayerActionListener(AppFrame appFrame)
	{
		this.appFrame = appFrame;		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// get details of the player and add the player
		appFrame.addPlayer();
	}
}
