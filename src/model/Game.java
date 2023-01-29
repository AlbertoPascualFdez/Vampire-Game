package model;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import exceptions.DraculaIsAliveException;
import exceptions.NoMoreVampiresException;
import exceptions.NotEnoughCoinsException;
import exceptions.UnvalidPositionException;

import model.GameObjects.BloodBank;
import model.GameObjects.Dracula;
import model.GameObjects.ExplosiveVampire;
import model.GameObjects.Slayer;
import model.GameObjects.Vampire;
import model.lists.GameObjectBoard;


import view.GamePrinter;
import view.IPrintable;


public class Game implements IPrintable{
		
	public static final String NO_MORE_VAMPIRES_LEFT_MSG = "[ERROR]: No more remaining vampires left";
	public static final String DRACULA_ALREADY_ALIVE_MSG = "[ERROR]: Dracula is already on board";
	
	
	final int COST_GARLIC_PUSH = 10;
	final int COST_LIGHT_FLASH = 50;
	
	
	Random rand;
	
	private GameObjectBoard gameObjectBoard;
	
	private GamePrinter gamePrinter;
	
	private Level level;
	
	private Player player;
	
	private int cycles;

	//variables para el rst y el exit
	private long seed;
	private boolean userExit = false;
	
	
	public Game(long seed, Level level)
	{
		this.level = level;
		this.seed = seed;
		gamePrinter = new GamePrinter(this, level.getDimX(), level.getDimY());//x numero columna, y numero de filas
		doReset();//inicializacion del resto de valores				
	}
	
	
	public void addCycles()
	{
		if(!isFinished())//para que en el ultimo ciclo no sume al contador
			cycles++;
	}
	
	
	
	public void addVampire()
	{
		
		if(Vampire.vampRemaining > 0)
		{			
			if(rand.nextDouble() < level.getVampireFrequency())
			{
				try {
					int y = rand.nextInt(level.getDimY());//fila
					int x = level.getDimX()-1;//columna
					if(gameObjectBoard.isPositionEmpty(x,y))
					{
						gameObjectBoard.addGameObject(new Vampire(x, y, this));
					}
					else {
						throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y +"): Unvalid position");
					}
				}
				catch(UnvalidPositionException e)
				{
					//no se hace nada ya que es una excepcion generada por el funcionamiento normal del programa y no por algun error del usuario
				}
			}
		}	
	}
	
	
	public boolean doUserAddVampire(int x, int y) throws UnvalidPositionException, NoMoreVampiresException
	{
		if(Vampire.vampRemaining > 0)
		{
			if(gameObjectBoard.isPositionEmpty(x,y) && positionInsideLimits(x, y)) 
			{
				gameObjectBoard.addGameObject(new Vampire(x, y, this));
				return true;
			}
			else {
				throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y +"): Unvalid position");
			}
		}
		else {
			throw new NoMoreVampiresException(NO_MORE_VAMPIRES_LEFT_MSG);
		}
	}
	
	
	public void addDracula()
	{
		if(Vampire.vampRemaining > 0)
		{		
			if(!Dracula.DraculaAlive)
			{
				if(rand.nextDouble() < level.getVampireFrequency())
				{
					try {
						int y = rand.nextInt(level.getDimY());
						int x = level.getDimX()-1;
						if(gameObjectBoard.isPositionEmpty(x,y))
						{
							gameObjectBoard.addGameObject(new Dracula(x, y, this));
						}	
						else {
							throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y +"): Unvalid position");
						}
					}
					catch(UnvalidPositionException e){
						//no se hace nada ya que es una excepcion generada por el funcionamiento normal del programa y no por algun error del usuario
					}
				}
			}
		}	
	}
	
	
	
	public boolean doUserAddDracula(int x, int y) throws UnvalidPositionException, DraculaIsAliveException, NoMoreVampiresException
	{
		if(Vampire.vampRemaining > 0)
		{
			if(!Dracula.DraculaAlive)
			{
				if(gameObjectBoard.isPositionEmpty(x,y) && positionInsideLimits(x, y))
				{
					gameObjectBoard.addGameObject(new Dracula(x, y, this));
					return true;
				}
				else {
					throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y +"): Unvalid position");
				}
			}
			else {
				throw new DraculaIsAliveException(DRACULA_ALREADY_ALIVE_MSG);
			}
		}
		else {
			throw new NoMoreVampiresException(NO_MORE_VAMPIRES_LEFT_MSG);
		}
			
	}
	
	public void addExplosiveVampire()
	{
		
		if(Vampire.vampRemaining > 0)
		{			
			if(rand.nextDouble() < level.getVampireFrequency())
			{
				try {
					int y = rand.nextInt(level.getDimY());
					int x = level.getDimX()-1;
					if(gameObjectBoard.isPositionEmpty(x,y))
					{
						gameObjectBoard.addGameObject(new ExplosiveVampire(x, y, this));
					}
					else {
						throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y +"): Unvalid position");
					}
				}
				catch(UnvalidPositionException e){
					//no se hace nada ya que es una excepcion generada por el funcionamiento normal del programa y no por algun error del usuario
				}
			}
			
		}	
	}
	
	
	
	public boolean doUserAddExplosiveVampire(int x, int y) throws UnvalidPositionException, NoMoreVampiresException
	{
		if(Vampire.vampRemaining > 0)
		{
			if(gameObjectBoard.isPositionEmpty(x,y) && positionInsideLimits(x, y)) 
			{
				gameObjectBoard.addGameObject(new ExplosiveVampire(x, y, this));
				return true;
			}
			else {
				throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y +"): Unvalid position");
			}
		}
		else {
			throw new NoMoreVampiresException(NO_MORE_VAMPIRES_LEFT_MSG);
		}
	
	}
	
	
	public boolean isFinished()
	{
		if(Vampire.vampRemaining == 0 && Vampire.vampOnBoard == 0)
		{
			return true;
		}
		if(Vampire.arriveFinal)
		{
			return true;
		}
		if(userExit)
		{
			return true;
		}
		
		return false;
	}
	
	//dada una posicion x, y devolvera el toString del objeto en dicha posicion
	public String getPositionToString(int x, int y)
	{		
		return gameObjectBoard.getPositionToString(x,y);
	}
	
	
	public String toString()
	{
		return gamePrinter.toString();
	}
	
	public String getMensajeGameOver()
	{
		if(Vampire.arriveFinal)
		{
			return "Vampires win!";
		}
		if(userExit)
		{
			return "Nobody wins...";
		}
		
		return "Player wins";
	}
	
	
	public void doExit() {
		
		userExit = true;
	}

	public void doReset() {
		
		Vampire.setVampRemaining(level.getNumberOfVampires());
		Vampire.setVampOnBoard(0);
		rand = new Random(seed);//creamos otra vez la misma secuencia de numeros aleatorios
		gameObjectBoard = new GameObjectBoard();
		player = new Player(rand);
		cycles = 0;	
		Dracula.setDraculaAlive(false);
	}

	public void doUpdate() {
			
		player.addAleatCoins();
		
		gameObjectBoard.moveObjects();
		gameObjectBoard.attack();
		
		addVampire();
		addDracula();
		addExplosiveVampire();
		
		
		gameObjectBoard.removeDeadObjects();
		addCycles();
		
	}


	public boolean doAddSlayer(int x, int y) throws UnvalidPositionException, NotEnoughCoinsException {
		
		if(!positionInsideLimits(x, y) || x == level.getDimX()-1 || !isPositionEmpty(x, y))
		{
			throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y +"): Unvalid position");
		}
		
		if(!player.enoughCoins(Slayer.COST_SLAYER))
		{
			throw new NotEnoughCoinsException("[ERROR]: Defender cost is " + Slayer.COST_SLAYER + ": Not enough coins");	
		}
			
		gameObjectBoard.addGameObject(new Slayer(x, y, this));
		player.subCoins(Slayer.COST_SLAYER);
			
		doUpdate();
		return true;
			
	}


	public boolean isPositionEmpty(int x, int y) {		
		return gameObjectBoard.isPositionEmpty(x,y);
		
	}


	public boolean positionInsideLimits(int x, int y) {
		return ( x < level.getDimX() && x >= 0 && y < level.getDimY() && y >= 0);
	}


public IAttack getAttackableInPosition(int x, int y) {
		
		return gameObjectBoard.getObjectInPosition(x, y); //"Cast" de gameObject a IAttack
	}


	public int getDimX() {
		
		return level.getDimX();
	}
	public int getDimY()
	{
		return level.getDimY();
	}


	@Override
	public String getInfo() {
		String auxEnter= System.lineSeparator();
		
		String info = "Number of cycles: " + cycles + auxEnter + "Coins: " + player.getNumOfCoins() + auxEnter + "Remaining vampires: " + Vampire.vampRemaining + auxEnter + "Vampires on the board: " + Vampire.vampOnBoard + auxEnter;
		if(Dracula.DraculaAlive)
		{
			info += "Dracula is alive" + auxEnter;
		}
		return  info;
	}

	//necesario para el BloodBank
	public void addCoins(int coins) {
		player.addCoins(coins);
		
	}


	public boolean doAddBloodBank(int x, int y, int cost) throws UnvalidPositionException, NotEnoughCoinsException {
		
		
		if(!positionInsideLimits(x, y) || x == level.getDimX() -1 || !isPositionEmpty(x, y))
		{
			throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y +"): Unvalid position");
		}
		
		if(!player.enoughCoins(cost))
		{
			throw new NotEnoughCoinsException("[ERROR]: Blood bank cost is " + cost + ": Not enough coins");	
		}
			
		gameObjectBoard.addGameObject(new BloodBank(x, y, cost, this));
		player.subCoins(cost);
			
		doUpdate();
		return true;
				
	}



	public boolean doLightFlash() throws NotEnoughCoinsException {
			
		if(!player.enoughCoins(COST_LIGHT_FLASH))
		{
			throw new NotEnoughCoinsException("[ERROR]: Light Flash cost is 50: Not enough coins");
		}
		
		gameObjectBoard.lightFlash();
		player.subCoins(COST_LIGHT_FLASH);
		doUpdate();
		return true;

	}


	//throws del tipo CommandExecuteException o del tipo NotEnoughCoinsException
	public boolean doGarlicPush() throws NotEnoughCoinsException {
			
		if(!player.enoughCoins(COST_GARLIC_PUSH))
		{
			throw new NotEnoughCoinsException("[ERROR]: Garlic Push cost is 10: Not enough coins");
		}
			
		gameObjectBoard.GarlicPush();
		player.subCoins(COST_GARLIC_PUSH);		
		doUpdate();	
		return true;
			
	}
	


	public void doSuperCoins(int coins) {
		player.addCoins(coins);	
	}


	public void viewSerialize() {	
		System.out.println(serialize());
	}
	
	public String serialize()
	{
		String serializedGame = "Cycles: " + cycles + "\n" +
								"Coins: " + player.getNumOfCoins() + "\n" +
								"Level: " + level.getNivel().toUpperCase() + "\n" +
								"Remaining Vampires: " + Vampire.vampRemaining + "\n" +
								"Vampires on Board: " + Vampire.vampOnBoard + "\n" + "\n" +
								"Game Object List: " + "\n";
		serializedGame += gameObjectBoard.serializeGameObjects();
		
		return serializedGame;
	}


	public void save(String fileName) throws IOException{

		try(BufferedWriter myWriter = new BufferedWriter(new FileWriter(fileName)))
		{
			myWriter.write(serialize());
			System.out.println("Game successfully saved to file "+ fileName + ".");
		}
		
	}
	


}
