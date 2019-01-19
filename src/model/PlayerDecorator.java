package model;

import model.interfaces.Player;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class PlayerDecorator
{

	private Player player;
	
	public PlayerDecorator(Player player)
	{
		this.player = player;
	}

	public String toString()
	{
		return String.format(player.getPlayerName());
	}
	
	public String getPlayerID()
	{
		return player.getPlayerId();
	}
}
