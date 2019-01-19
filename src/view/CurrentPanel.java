package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.interfaces.GameEngine;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class CurrentPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DiceRollingPanel diceRollingPanel;
	private BattleResultPanel battleResultPanel;
	private JSplitPane splitPane;
	
	public CurrentPanel(GameEngine gameEngine)
	{
		diceRollingPanel = new DiceRollingPanel();
		battleResultPanel = new BattleResultPanel(gameEngine);
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
								   diceRollingPanel, battleResultPanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(Info.YY / 2);
		
		// provide minimum sizes for the two components in the split pane.
		Dimension minimumSize = new Dimension(Info.XXX, Info.YYY);
		diceRollingPanel.setMinimumSize(minimumSize);
		battleResultPanel.setMinimumSize(minimumSize);
		splitPane.setPreferredSize(new Dimension(Info.XX / 2, Info.YY));
		add(splitPane);
	}
	
	public DiceRollingPanel getDiceRollingPanel()
	{
		return diceRollingPanel;
	}
	
	public BattleResultPanel getBattleResultPanel()
	{
		return battleResultPanel;
	}
}
