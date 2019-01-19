package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.interfaces.GameEngine;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class DiceRolledPanel extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PlayerDiceResultPanel playerDiceResultPanel;
	private HouseDiceResultPanel houseDiceResultPanel;
	private JSplitPane splitPane;
	
	public DiceRolledPanel(GameEngine gameEngine, AppFrame appFrame)
	{
		playerDiceResultPanel = new PlayerDiceResultPanel();
		houseDiceResultPanel = new HouseDiceResultPanel();
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
								   playerDiceResultPanel, houseDiceResultPanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(Info.YY / 2);
		
		// provide minimum sizes for the two components in the split pane.
		Dimension minimumSize = new Dimension(Info.XXX, Info.YYY);
		playerDiceResultPanel.setMinimumSize(minimumSize);
		houseDiceResultPanel.setMinimumSize(minimumSize);
		splitPane.setPreferredSize(new Dimension(Info.XX / 2, Info.YY));
		add(splitPane);
	}
	
	public PlayerDiceResultPanel getPlayerDiceResultPanel()
	{
		return playerDiceResultPanel;
	}
	
	public HouseDiceResultPanel getHouseDiceResultPanel()
	{
		return houseDiceResultPanel;
	}
}
