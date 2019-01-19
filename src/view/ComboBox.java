package view;

import javax.swing.JComboBox;

import model.PlayerDecorator;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class ComboBox extends JComboBox<PlayerDecorator>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameEngine gameEngine;

	public ComboBox(GameEngine gameEngine)
	{
		this.gameEngine = gameEngine;
		update();
	}
	
	public void update()
	{
		// reset playerBox
		removeAllItems();
		
		// add player name to playerBox
		for(Player player : gameEngine.getAllPlayers())
		{
			addItem(new PlayerDecorator(player));
		}
	}
}
