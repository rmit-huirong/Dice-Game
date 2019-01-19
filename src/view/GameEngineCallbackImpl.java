package view;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see model.interfaces.GameEngineCallback
 * 
 */

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
	private Logger logger = Logger.getLogger("assignment1");

	public GameEngineCallbackImpl()
	{
		// FINE shows rolling output, INFO only shows result
		logger.setLevel(Level.FINE);
	}

	@Override
	public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
	{
		
		// intermediate results logged at Level.FINE
		displayRoll(player, dicePair, gameEngine, false);
	}

	@Override
	public void result(Player player, DicePair result, GameEngine gameEngine)
	{
		// final results logged at Level.INFO
		displayRoll(player, result, gameEngine, true);
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine)
	{
		// intermediate house results logged at Level.FINE
		displayRoll(null, dicePair, gameEngine, false);
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine)
	{
		// final house result logged at Level.INFO
		displayRoll(null, result, gameEngine, true);
		
		// final bet result logged at Level.INFO
		displayResult(gameEngine.getAllPlayers(), gameEngine);
	}

	/**
	 * Common progress of logging the results of player and house
	 * 
	 * @param player
	 *            the Player who rolled, it is null if house rolled
	 * @param dicePair
	 *            the current (upfacing) values of the rolling dice
	 * @param gameEngine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * @param isResult
	 *            true if it is the result
	 * 
	 * @see model.interfaces.GameEngine
	 * 
	 */
	private void displayRoll(Player player, DicePair dicePair, GameEngine gameEngine, boolean isResult)
	{
		if(player != null)
		{
			if(!isResult)
			{
				// intermediate results logged at Level.FINE
				logger.log(Level.FINE, player.getPlayerName() + ": ROLLING " + dicePair.toString());
			}
			else
			{
				// final results logged at Level.INFO
				logger.log(Level.INFO, player.getPlayerName() + ": *RESULT* " + dicePair.toString());
			}
		}
		else
		{
			if(!isResult)
			{
				// intermediate house results logged at Level.FINE
				logger.log(Level.FINE,"House: ROLLING " + dicePair.toString());		
			}
			else
			{
				// final house result logged at Level.INFO
				logger.log(Level.INFO, "House: *RESULT* " + dicePair.toString());	
			}
		}
	}
	
	/**
	 * logging the final bet result
	 * 
	 * @param players
	 *            an unmodifiable collection of all Players
	 * @param gameEngine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * 
	 * @see model.interfaces.GameEngine
	 * 
	 */
	private void displayResult(Collection<Player> players, GameEngine gameEngine)
	{
		// final bet result logged at Level.INFO
		for(Player player : players)
		{
			
			logger.log(Level.INFO, player.toString());
		}
	}
}
