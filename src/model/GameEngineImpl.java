package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class GameEngineImpl implements GameEngine
{

	private Map<Integer, Player>players = new HashMap<>();
	
	// a collection of GameEngineCallbacks
	private List<GameEngineCallback> gecbs = new ArrayList<GameEngineCallback>();
	
	@Override
	public boolean placeBet(Player player, int bet)
	{
		if(player.placeBet(bet))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement)
	{
		// dices rolled by a player
		boolean isPlayer = true;
		
		// log the result of a player
		rollAll(isPlayer, player, initialDelay, finalDelay, delayIncrement);

	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement)
	{
		// dices rolled by the house
		boolean isPlayer = false;
		
		// log the result of the house
		DicePair houseResult = rollAll(isPlayer, null, initialDelay, finalDelay, delayIncrement);
		
		// settle the bets
		betSettle(getAllPlayers(), houseResult);
		
		// display the house result and final players' details
		for(GameEngineCallback gecb : gecbs)
		{
			gecb.houseResult(houseResult, this);
		}
	}

	@Override
	public void addPlayer(Player player)
	{
		players.put(new Integer(player.getPlayerId()), player);
	}

	@Override
	public Player getPlayer(String id)
	{
		for(Player player : getAllPlayers())
		{
			if(player.getPlayerId().equals(id))
			{
				return player;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player)
	{
		return players.remove(new Integer(player.getPlayerId()), player);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		gecbs.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		gecbs.remove(gameEngineCallback);
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers()
	{
		return Collections.unmodifiableCollection(players.values());
	}
	
	/**
	 * Common progress of rolling the dice by player and house
	 * 
	 * @param isPlayer
	 *            true if the player is rolling, false if the house is rolling
	 * @param player
	 *            the player who is rolling and will have their result set
	 *            at the end of the roll, it is null if house is rolling
	 * @param initialDelay
	 *            the starting delay in ms between updates (based on how fast
	 *            dice are rolling)
	 * @param finalDelay
	 *            the final delay in ms between updates when the dice stop
	 *            rolling
	 * @param delayIncrement
	 *            how much the dice slow down (delay gets longer) after each
	 *            roll/tumble
	 * @return a DicePair containing both dice values
	 * 
	 * 
	 * @see view.interfaces.GameEngineCallback
	 * 
	 */
	private DicePair rollAll(boolean isPlayer, Player player, int initialDelay, int finalDelay, int delayIncrement)
	{
		int delay = initialDelay;
		DicePair dicePair = null;
		while (delay < finalDelay)
		{
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			dicePair = diceRoll();
			
			if(isPlayer)
			{
				for(GameEngineCallback gecb : gecbs)
				{
					gecb.intermediateResult(player, dicePair, this);
				}
				//gecb.intermediateResult(player, dicePair, this);???
			}
			else
			{
				for(GameEngineCallback gecb : gecbs)
				{
					gecb.intermediateHouseResult(dicePair, this);
				}
			}
			delay += delayIncrement;
		}
		
		dicePair = diceRoll();
		
		if(isPlayer)
		{
			player.setRollResult(dicePair);
			for(GameEngineCallback gecb : gecbs)
			{
				gecb.result(player, dicePair, this);
			}
		}
		return dicePair;
	}

	/**
	 * 
	 * @return a DicePair containing both dice values which are random
	 * @see model.interfaces.DicePair
	 */
	private DicePair diceRoll()
	{
		Random random = new Random();
		int dice1 =  random.nextInt(NUM_FACES) + 1;
		int dice2 =  random.nextInt(NUM_FACES) + 1;
		return new DicePairImpl(dice1, dice2, NUM_FACES);
	}
	
	/**
	 * Settle the bet
	 * 
	 * @param players
	 *            an unmodifiable collection of all Players
	 * @param houseDicePair
	 * 			  A DicePair containing both dice values rolled by house
	 * 
	 * 
	 * @see model.interfaces.DicePair
	 * 
	 */
	private void betSettle(Collection<Player> players, DicePair houseDicePair)
	{
		int houseDiceTotal = houseDicePair.getDice1() + houseDicePair.getDice2();
		int playerDiceTotal = 0;
		for (Player player : players)
		{
			if(player.getRollResult() != null)
			{
				playerDiceTotal = player.getRollResult().getDice1() + player.getRollResult().getDice2();
				
				// player wins
				if (playerDiceTotal > houseDiceTotal)
				{
					// bet added
					player.setPoints((player.getPoints() + player.getBet()));
				}
				
				// house wins
				else if (playerDiceTotal < houseDiceTotal)
				{
					// bet subtracted
					player.setPoints(player.getPoints() - player.getBet());
				}
				
				// draw, no change
				else {}
			}
		}
	}
}
