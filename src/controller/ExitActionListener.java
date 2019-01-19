package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AppFrame;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class ExitActionListener implements ActionListener
{

	private AppFrame appFrame;
	
	public ExitActionListener(AppFrame appFrame)
	{	
		this.appFrame = appFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// exit the game
		appFrame.exit();
	}
}
