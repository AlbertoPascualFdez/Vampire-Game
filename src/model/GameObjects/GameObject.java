package model.GameObjects;

import model.Game;
import model.IAttack;

public abstract class GameObject implements IAttack {
	
	protected int x;
	protected int y;
	
	protected int life;
	protected int damage;
	protected String symbol;
	protected Game game; //necesario static para el cambio: añadir funciones de posicion valida propias para slayer y vampire
	
	public GameObject(int x, int y, int life, int damage, String symbol, Game game)
	{
		this.x = x;
		this.y = y;
		this.life = life;
		this.damage = damage;
		this.symbol = symbol;
		this.game = game;
		
	}
	
	public abstract void move();
	
	
	public boolean isObjectInPosition(int x2, int y2)
	{
		if(x == x2 && y == y2)
		{
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		return (symbol + "[" + life +"]");
		
	}

	public boolean isAlive()
	{
		return life > 0;
	}
	
	public String serialize()
	{
		return symbol + ";" + x + ";" + y + ";" + life;
	}
	
	
	

}
