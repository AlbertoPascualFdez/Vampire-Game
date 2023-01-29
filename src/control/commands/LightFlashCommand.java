package control.commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.NotEnoughCoinsException;
import model.Game;

public class LightFlashCommand extends Command {
	
	final static String NAME = "light";
	final static String SHORTCOUT = "l";
	final static String DETAILS = "[l]ight";
	final static String HELP = "kills all the vampires";

	final static String FAILED_EXECUTE_COMMAND_MSG = "[ERROR]: Failed to light flash";
	
	public LightFlashCommand() {
		super(NAME, SHORTCOUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {		
		try {
		return game.doLightFlash();
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
