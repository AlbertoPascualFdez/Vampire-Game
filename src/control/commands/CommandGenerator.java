package control.commands;

import exceptions.CommandParseException;

public class CommandGenerator {//clase utilidad: todos los metodos son estaticos

	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),
			new AddBloodBankCommand(),			
			new SuperCoinsCommand(),
			new AddVampireCommand(),
			new SaveCommand(),
			new SerializeCommand()
			
			};

	
	public static Command parse(String[] commandWords) throws CommandParseException//dado un comando escrito por el usuario devuelve el comando con el que coincide (parseando la entrada respecto a cada comando concreto). Si no hay ninguno, null
	{
		Command parsedCommand = null;// = c.parse(commandWords);
		for(Command c: availableCommands)
		{
			parsedCommand = c.parse(commandWords);
			if(parsedCommand != null)
			{
				break;
			}
		}	
			if(parsedCommand == null)
			{
				throw new CommandParseException("[ERROR]: " + Command.unknownCommandMsg);

			}
			return parsedCommand;
	}
	
	public static String commandHelp()
	{
		String helpText= "";
		for(Command c: availableCommands)
		{
			helpText = helpText + c.helpText();			
		}
		return "Available commands:"+ "\n" + helpText;
	}
	
}
