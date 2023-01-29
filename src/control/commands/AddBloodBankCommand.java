package control.commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.NotEnoughCoinsException;
import exceptions.UnvalidPositionException;
import model.Game;

public class AddBloodBankCommand extends Command {

	final static String NAME = "bank";
	final static String SHORTCOUT = "b";
	final static String DETAILS = "[b]ank <x> <y> <z>";
	final static String HELP = "add a blood bank with cost z in position x, y";
	
	final static String FAILED_EXECUTE_COMMAND_MSG = "[ERROR]: Failed to add blood bank";
	
	int x;
	int y;
	int z;
	
	public AddBloodBankCommand() {
		super(NAME, SHORTCOUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			return game.doAddBloodBank(x, y, z); //solo si se añade correctamente devuelve true para que se actualice la pantalla
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

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length != 4) {
			//System.err.println (incorrectArgsMsg);
			//return null;
			throw new CommandParseException("[ERROR]: Command " + name + " :"+ Command.incorrectNumberOfArgsMsg);
			}
			
			x = Integer.parseInt(commandWords[1]);
			y = Integer.parseInt(commandWords[2]);
			z = Integer.parseInt(commandWords[3]);
			return this;
		}

		return null;
	}

}
