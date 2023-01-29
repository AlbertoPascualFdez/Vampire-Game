package model.GameObjects;

import model.Game;

public class BloodBank extends Slayer{

	final static String SYMBOLBLOODBANK = "B";
	final static int INITIAL_LIVE = 1;
	int costCoins;
	
	
	public BloodBank(int x, int y, int costCoins, Game game) {
		super(x, y, INITIAL_LIVE, SYMBOLBLOODBANK, game);
		this.costCoins = costCoins;

	}
	
	
	@Override
	public void move() {
			game.addCoins((int) (costCoins * 0.1f));
	}
	
	@Override
	public void attack()
	{
		
	}
	

	//al tener life y CostCoins, y mostrara su CostCoins en vez de su vida, necesita un toString propio
	@Override
	public String toString()
	{
		return (symbol + "[" + costCoins +"]");
		
	}
	public String serialize()
	{
		return super.serialize() + ";" + costCoins;
	}
	

}
