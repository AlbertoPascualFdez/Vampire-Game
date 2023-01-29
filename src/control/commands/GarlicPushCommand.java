package control.commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.NotEnoughCoinsException;
import model.Game;

public class GarlicPushCommand extends Command{

	final static String NAME = "garlic";
	final static String SHORTCOUT = "g";
	final static String DETAILS = "[g]arlic";
	final static String HELP = "Pushes back vampires";
	
	final static String FAILED_EXECUTE_COMMAND_MSG = "[ERROR]: Failed to garlic push";
	
	public GarlicPushCommand() {
		super(NAME, SHORTCOUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
		return game.doGarlicPush();
		}
		catch(NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(FAILED_EXECUTE_COMMAND_MSG, e);
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}
