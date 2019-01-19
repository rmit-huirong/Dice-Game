package model;

import model.interfaces.DicePair;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class DicePairImpl implements DicePair
{

	final private int dice1;
	final private int dice2;
	final private int numFaces;
	
	public DicePairImpl(int dice1, int dice2, int numFaces)
	{
		this.dice1 = dice1;
		this.dice2 = dice2;
		this.numFaces = numFaces;
	}
	
	@Override
	public int getDice1()
	{
		return dice1;
	}

	@Override
	public int getDice2()
	{
		return dice2;
	}

	@Override
	public int getNumFaces()
	{
		return numFaces;
	}

	@Override
	public String toString()
	{
		return  String.format("Dice 1: %d,  Dice 2: %d .. Total: %d", dice1, dice2, dice1 + dice2);
	}
}
