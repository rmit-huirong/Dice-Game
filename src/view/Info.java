package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.interfaces.GameEngine;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class Info extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DiceRolledPanel diceResultPanel;
	private CurrentPanel currentPanel;
	private JSplitPane splitPane;
	
	// constants of components
	public static final int XX = 660;
	public static final int YY = 400;
	public static final int XXX = 165;
	public static final int YYY = 160;
	private static final int MIN_XX = 290;
	
	public Info(GameEngine gameEngine, AppFrame appFrame)
	{
		diceResultPanel = new DiceRolledPanel(gameEngine, appFrame);
		currentPanel = new CurrentPanel(gameEngine);
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
	            				   diceResultPanel, currentPanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(XX / 2);
		
		// provide minimum sizes for the two components in the split pane.
		Dimension minimumSize = new Dimension(MIN_XX, YY / 2);
		diceResultPanel.setMinimumSize(minimumSize);
		currentPanel.setMinimumSize(minimumSize);
		splitPane.setPreferredSize(new Dimension(XX, YY));
		add(splitPane);
	}
	
	public CurrentPanel getCurrentPanel()
	{
		return currentPanel;
	}
	
	public DiceRolledPanel getDiceResultPanel()
	{
		return diceResultPanel;
	}
}
