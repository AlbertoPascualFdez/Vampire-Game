package model.GameObjects;

import model.Game;
import model.IAttack;

public class Dracula extends Vampire {

	final static String SYMBOLDRACULA = "D";
	
	public static boolean DraculaAlive;
	
	
	public Dracula(int x, int y, Game game) {
		super(x, y, SYMBOLDRACULA, game);
		DraculaAlive = true;
	}
	
	
	@Override
	public void attack()
	{
		if(isAlive())
		{

			IAttack other = game.getAttackableInPosition(x-1,y);
			if(other != null)
			{
				other.receiveDraculaAttack();
			}
		}
	}
	
	@Override 
	public boolean receiveSlayerAttack(int damage)
	{
		if(super.receiveSlayerAttack(damage))
		{
			if(!isAlive())
			{
				DraculaAlive = false;
			}		
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean receiveLightFlash()
	{
		return false;
	}
	
	@Override
	public boolean receiveGarlicPush()
	{
		super.receiveGarlicPush();
		if(x == game.getDimX())
		{
			DraculaAlive = false;
		}
		return true;
	}
		


	public static void setDraculaAlive(boolean value) {
		DraculaAlive = value;
		
	}

}
