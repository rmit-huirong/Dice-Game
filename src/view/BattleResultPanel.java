package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class BattleResultPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameEngine gameEngine;
	
	private JLabel title = new JLabel("---------------BATTLE RESULTS---------------");
	private JTextArea result = new JTextArea();
	private JScrollPane scrollPane;
	
	public BattleResultPanel(GameEngine gameEngine)
	{
		this.gameEngine = gameEngine;
		
		// make title be in center
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title);
		
		scrollPane = new JScrollPane(result, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	}
	
	public void update()
	{
		// reset battle results
		result.setText("");
		
		for(Player player : gameEngine.getAllPlayers())
		{
			// check if the player rolled
			if(player.getRollResult() != null)
			{
				// print out all battle results including playerName, playerRollResult, points
				result.setText(result.getText() + player.getPlayerName() + "(" + player.getRollResult().toString() +") points: " + player.getPoints() + "\n");
			}
			else
			{
				result.setText(result.getText() + player.getPlayerName() + "(Hasn't roll for last round) points: " + player.getPoints() + "\n");
			}
		}
		add(scrollPane);
	}
}
