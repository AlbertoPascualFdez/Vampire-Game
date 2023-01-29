package model.GameObjects;

import model.Game;
import model.IAttack;

public class ExplosiveVampire extends Vampire{

	final static String SYMBOLEXPLOSIVE = "EV";
	
	public ExplosiveVampire(int x, int y, Game game) {
		super(x, y, SYMBOLEXPLOSIVE, game);

	}
	
	
	public boolean receiveSlayerAttack(int damage)
	{
		if(super.receiveSlayerAttack(damage))//solo se comprueba si explota si ha recibido el ataque. Si devuelve false, es que ya estaba muerto y le han vuelto a disparar
		{
			if(!isAlive())
			{
				explosion();
			}	
			return true;
		}
		return false;
	}
	
	
	void explosion()
	{
		IAttack other;
		for(int i = x-1; i <= x +1; i++)
		{
			for(int j = y -1; j <= y +1; j++)
			{
				other = game.getAttackableInPosition(i, j);
				if(other != null && other != this) //other != this, para evitar que se intente atacar asi mismo. Sino entrario en un bucle infinito, porque estaria explotando infinitamente
				{
					other.receiveSlayerAttack(damage);	
				}
			}
		}
	}
	


}
