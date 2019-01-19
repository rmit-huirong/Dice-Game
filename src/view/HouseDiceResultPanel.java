package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.factory.DiceFaceFactory;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class HouseDiceResultPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel title = new JLabel("---------------HOUSE ROLLED DICES---------------");
	private JLabel result;
	private JLabel dice1;
	private JLabel dice2;
	
	public HouseDiceResultPanel()
	{
		result = new JLabel();
		dice1 = new DiceLabel(0);
		dice2 = new DiceLabel(0);
		dice1.setSize(DiceLabel.DICE_SIZE, DiceLabel.DICE_SIZE);
		dice2.setSize(DiceLabel.DICE_SIZE, DiceLabel.DICE_SIZE);
		
		// add components
		add(title);
		add(dice1);
		add(dice2);
		add(result);
	}
	
	public JLabel getResult()
	{
		return result;
	}
	
	// update dices faces
	public void update(int dice1, int dice2)
	{
		this.dice1.setIcon(DiceFaceFactory.getImageIcon(dice1));
		this.dice2.setIcon(DiceFaceFactory.getImageIcon(dice2));
	}
}
