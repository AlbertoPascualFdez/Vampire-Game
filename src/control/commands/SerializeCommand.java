package control.commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import model.Game;

public class SerializeCommand extends Command{
	
	final static String NAME = "serialize";
	final static String SHORTCOUT = "z";
	final static String DETAILS = "Seriali[z]e";
	final static String HELP = "Serializes the board.";
	
	public SerializeCommand() {
		super(NAME, SHORTCOUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.viewSerialize();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}
