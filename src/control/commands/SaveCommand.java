package control.commands;

import java.io.IOException;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import model.Game;

public class SaveCommand extends Command{

	final static String NAME = "save";
	final static String SHORTCOUT = "s";
	final static String DETAILS = "[S]ave <filename>";
	final static String HELP = "Save the state of the game to a file.";
	
	final static String FAILED_EXECUTE_COMMAND_MSG = "[ERROR]: Failed to save";
	
	String fileName;
	
	public SaveCommand() {
		super(NAME, SHORTCOUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
		game.save(fileName);
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			throw new CommandExecuteException(FAILED_EXECUTE_COMMAND_MSG, e);
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0]))
		{
		if(commandWords.length != 2)
		{
			throw new CommandParseException("[ERROR]: Command " + name + " :"+ incorrectNumberOfArgsMsg);
		}
		fileName = commandWords[1] + ".dat";
		return this;
		}
		
		return null;
	}
	
}
