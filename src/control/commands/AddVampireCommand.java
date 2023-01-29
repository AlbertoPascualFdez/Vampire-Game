package control.commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.DraculaIsAliveException;
import exceptions.NoMoreVampiresException;
import exceptions.UnvalidPositionException;
import exceptions.UnvalidVampireTypeException;
import model.Game;


public class AddVampireCommand extends Command {

	
	final static String NAME = "vampire";
	final static String SHORTCOUT = "v";
	final static String DETAILS = "[v]ampire [<type>] <x> <y>";
	final static String HELP = "Type = {\"\"|\"D\"|\"E\"}: add a vampire in position x, y";
	
	final static String UNVALIDTYPESMSG = "Unvalid type:";
	final static String VALIDTYPESMSG = " Type = {\"\"|\"D\"|\"E\"}";
	final static String FAILED_ADD_VAMPIRE_COMMAND_MSG = "[ERROR]: Failed to add this vampire";
	
	int x;
	int y;
	String type;
	
	private static final String[] VampireTypes = {
			"v",
			"e",
			"d"			
			};
	

	public AddVampireCommand() {
		super(NAME, SHORTCOUT, DETAILS, HELP);
		
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {

		try {
			if(type.equals("v"))
			{
				return game.doUserAddVampire(x, y);
			}
			else if(type.equals("d"))
			{
				return game.doUserAddDracula(x, y);
			}
			else
			{
				return game.doUserAddExplosiveVampire(x, y);
			}
		}
		
		catch(UnvalidPositionException e)
		{
			System.out.println(e.getMessage());
			throw new CommandExecuteException(FAILED_ADD_VAMPIRE_COMMAND_MSG, e);
		}
		catch(DraculaIsAliveException e)
		{
			System.out.println(e.getMessage());
			throw new CommandExecuteException(FAILED_ADD_VAMPIRE_COMMAND_MSG, e);
		}
		catch(NoMoreVampiresException e)
		{
			System.out.println(e.getMessage());
			throw new CommandExecuteException(FAILED_ADD_VAMPIRE_COMMAND_MSG, e);
		}
		
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
	
		if (matchCommandName(commandWords[0])) {
			
			if (commandWords.length == 3)
			{
				
				type = VampireTypes[0];	//posicion 0 vampiro por defecto
				try {
				x = Integer.parseInt(commandWords[1]);
				y = Integer.parseInt(commandWords[2]);	
				}
				catch(NumberFormatException nfe)
				{
					throw new CommandParseException("[ERROR]: Unvalid argument for add vampire command, number expected: [v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}");

				}
				return this;
				
			}
			else if  (commandWords.length == 4)
			{
				if(matchVampireType(commandWords[1]))
				{
					type = commandWords[1].toLowerCase();
					try {
					x = Integer.parseInt(commandWords[2]);
					y = Integer.parseInt(commandWords[3]);	
					}
					catch(NumberFormatException nfe)
					{
						throw new CommandParseException("[ERROR]: Unvalid argument for add vampire command, number expected: [v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}");

					}
					return this;
				}
				else
				{
					throw new UnvalidVampireTypeException("[ERROR]: " + UNVALIDTYPESMSG + " "+ DETAILS + " " + VALIDTYPESMSG);
				}
			}
			else
			{
				throw new CommandParseException("[ERROR]: Command " + name + " :"+ Command.incorrectNumberOfArgsMsg);
			}
		}
	
		return null;
	}
	
	
	
	public boolean matchVampireType(String type)
	{
		for(String t: VampireTypes)
		{
			if(t.equalsIgnoreCase(type))
			{
				return true;
			}
		}	
		return false;
	}
	
		
}
