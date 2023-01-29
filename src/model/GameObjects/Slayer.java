package model.GameObjects;

import model.Game;
import model.IAttack;

public class Slayer extends GameObject{

	public static final int COST_SLAYER = 50;
	
	final static int INITIAL_LIVE = 3;

	final static int DAMAGE_SLAYER = 1;
	
	final static String SYMBOLSLAYER = "S";


	public Slayer(int x, int y, Game game)
	{
		super(x, y, INITIAL_LIVE, DAMAGE_SLAYER, SYMBOLSLAYER, game);

	}

	public Slayer(int x, int y, int vida, String symbol, Game game)
	{
		super(x, y, vida, DAMAGE_SLAYER, symbol, game);

	}

	@Override
	public void move() {
			
	}
	
	
	public void attack()
	{
		if(isAlive())
		{
			IAttack other;
			for(int i = x+1; i < game.getDimX(); i++)
			{
				other = game.getAttackableInPosition(i, y);
				if(other != null) 
				{
					if(other.receiveSlayerAttack(damage))//solo daña al primero que encuentra
					break;
				}
			}
			
		}
	}
	
	 public boolean receiveVampireAttack(int damage)
	 {
		 life -= damage;
		 return true;
	 }
	 
	 public boolean receiveDraculaAttack()
	 {
		 life = 0;
		 return true;
	 }



	
	/*public static boolean isValidPosition(int x, int y)
	{
		return (x < game.getDimX()-1 && y < game.getDimY());
	}*/
	
	
}
