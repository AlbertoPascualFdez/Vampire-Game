package exceptions;

public class UnvalidVampireTypeException extends CommandParseException {

	private static final long serialVersionUID = 1L;

	public UnvalidVampireTypeException() {
		super();
	}

	public UnvalidVampireTypeException(String message) {
		super(message);
	}

	public UnvalidVampireTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnvalidVampireTypeException(Throwable cause) {
		super(cause);
	}
}
