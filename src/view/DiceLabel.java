package view;

import javax.swing.JLabel;

import view.factory.DiceFaceFactory;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class DiceLabel extends JLabel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int DICE_SIZE = 30;
	
	public DiceLabel(int i)
	{
		setIcon(DiceFaceFactory.getImageIcon(i));
	}
}
