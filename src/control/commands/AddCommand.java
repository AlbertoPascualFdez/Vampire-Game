package control.commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.NotEnoughCoinsException;
import exceptions.UnvalidPositionException;
import model.Game;

public class AddCommand extends Command {

	final static String NAME = "add";
	final static String SHORTCOUT = "a";
	final static String DETAILS = "[a]dd <x> <y>";
	final static String HELP = "add a slayer in position x, y";
	
	final static String FAILED_EXECUTE_COMMAND_MSG = "[ERROR]: Failed to add slayer";
	
	int x;
	int y;
	
	public AddCommand()
	{
		super(NAME, SHORTCOUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		try {
			return	game.doAddSlayer(x,y);
		}
		catch(UnvalidPositionException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(FAILED_EXECUTE_COMMAND_MSG, e);
			
		}
		catch(NotEnoughCoinsException e)
		{
			System.out.println(e.getMessage());
			throw new CommandExecuteException(FAILED_EXECUTE_COMMAND_MSG, e);
		}

	}

	//NumberFormatException nfe
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length != 3) {
			//System.err.println (incorrectArgsMsg);
			//return null;
			throw new CommandParseException("[ERROR]: Incorrect number of arguments for add command: [a]dd <x> <y>");
			
			}
			try {
			x = Integer.parseInt(commandWords[1]);
			y = Integer.parseInt(commandWords[2]);
			}
			catch(NumberFormatException nfe)
			{
				throw new CommandParseException("[ERROR]: Unvalid argument for add slayer command, number expected: [a]dd <x> <y>");//[ERROR]: Unvalid argument for add slayer command, number expected: [a]dd <x> <y>

			}
			
			return this;
		}

		return null;
	}

}
