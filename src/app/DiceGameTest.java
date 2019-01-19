package app;

import javax.swing.SwingUtilities;

import view.AppFrame;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class DiceGameTest
{

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				new AppFrame();				
			}
		});
	}
}
