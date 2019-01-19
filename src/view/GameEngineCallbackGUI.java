package view;

import javax.swing.SwingUtilities;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class GameEngineCallbackGUI implements GameEngineCallback
{

	private AppFrame appFrame;
	
	public GameEngineCallbackGUI(AppFrame appFrame)
	{
		this.appFrame = appFrame;
	}
	
	@Override
	public void intermediateResult(final Player player, final DicePair dicePair, GameEngine gameEngine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				// update rolling dices
				appFrame.getInfo().getCurrentPanel().getDiceRollingPanel().update(dicePair.getDice1(), dicePair.getDice2());
				appFrame.getInfo().getCurrentPanel().getDiceRollingPanel().getResult().setText("Dice 1: " + dicePair.getDice1() + ",  Dice 2: " + dicePair.getDice2() + " .. Total: " + (dicePair.getDice1() + dicePair.getDice2()));
				
				//update game status
				appFrame.getStatusBar().getGameStatus().setText("GAME STATUS: " + player.getPlayerName() + " ROLLING...");
			}
		});
	}

	@Override
	public void result(final Player player, final DicePair result, final GameEngine gameEngine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				// update rolling dices
				appFrame.getInfo().getCurrentPanel().getDiceRollingPanel().update(result.getDice1(), result.getDice2());	
				appFrame.getInfo().getDiceResultPanel().getPlayerDiceResultPanel().update(result.getDice1(), result.getDice2());
				
				// check if the selected player has rolled
				if(gameEngine.getPlayer(appFrame.getPlayerID()).getRollResult()!=null)
				{
					// update rolling dices
					appFrame.getInfo().getCurrentPanel().getDiceRollingPanel().getResult().setText("Dice 1: " + result.getDice1() + ",  Dice 2: " + result.getDice2() + " .. Total: " + (result.getDice1() + result.getDice2()));
					
					// update player rolled dices
					appFrame.getInfo().getDiceResultPanel().getPlayerDiceResultPanel().getResult().setText(gameEngine.getPlayer(appFrame.getPlayerID()).getRollResult().toString());
				}
				else
				{
					appFrame.getInfo().getDiceResultPanel().getPlayerDiceResultPanel().getResult().setText("Oops! Haven't rolled yet.");
				}
				
				// update game status
				appFrame.getStatusBar().getGameStatus().setText("GAME STATUS: " + player.getPlayerName() + " HAS FINISHED ROLLING!");
			}
		});
	}

	@Override
	public void intermediateHouseResult(final DicePair dicePair, GameEngine gameEngine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				// update rolling dices
				appFrame.getInfo().getCurrentPanel().getDiceRollingPanel().update(dicePair.getDice1(), dicePair.getDice2());	
				appFrame.getInfo().getCurrentPanel().getDiceRollingPanel().getResult().setText("Dice 1: " + dicePair.getDice1() + ",  Dice 2: " + dicePair.getDice2() + " .. Total: " + (dicePair.getDice1() + dicePair.getDice2()));
				
				// update game status
				appFrame.getStatusBar().getGameStatus().setText("GAME STATUS: HOUSE ROLLING...");
			}
		});
	}

	@Override
	public void houseResult(final DicePair result, final GameEngine gameEngine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{				
				// update rolling dices
				appFrame.getInfo().getCurrentPanel().getDiceRollingPanel().update(result.getDice1(), result.getDice2());	
				appFrame.getInfo().getCurrentPanel().getDiceRollingPanel().getResult().setText("Dice 1: " + result.getDice1() + ",  Dice 2: " + result.getDice2() + " .. Total: " + (result.getDice1() + result.getDice2()));
				
				//update house rolled dices
				appFrame.getInfo().getDiceResultPanel().getHouseDiceResultPanel().update(result.getDice1(), result.getDice2());
				appFrame.getInfo().getDiceResultPanel().getHouseDiceResultPanel().getResult().setText("Dice 1: " + result.getDice1() + ",  Dice 2: " + result.getDice2() + " .. Total: " + (result.getDice1() + result.getDice2()));
				
				// update game status
				appFrame.getStatusBar().getGameStatus().setText("GAME STATUS: HOUSE HAS FINISHED ROLLING!");
			}
		});
	}
}
