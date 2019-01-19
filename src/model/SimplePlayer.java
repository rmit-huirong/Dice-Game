package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class SimplePlayer implements Player
{

	private String playerId = null;
	private String playerName = null;
	private int points = 0;
	private int bet = 0;
	private DicePair rollResult = null;
	
	public SimplePlayer(String playerId, String playerName, int initialPoints)
	{
		this.playerId = playerId;
		this.playerName = playerName;
		points = initialPoints;
	}

	@Override
	public String getPlayerName()
	{
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;		
	}

	@Override
	public int getPoints()
	{
		return points;
	}

	@Override
	public void setPoints(int points)
	{
		this.points = points;		
	}

	@Override
	public String getPlayerId()
	{
		return playerId;
	}

	@Override
	public boolean placeBet(int bet)
	{
		if(points >= bet)
		{
			this.bet = bet;
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int getBet()
	{
		return bet;
	}

	@Override
	public DicePair getRollResult()
	{
		return rollResult;
	}

	@Override
	public void setRollResult(DicePair rollResult)
	{
		this.rollResult = rollResult;
	}
	
	@Override
	public String toString()
	{
		return String.format("Player: id=" + playerId + ", name=" + playerName + ", points=" + points);
	}
}
