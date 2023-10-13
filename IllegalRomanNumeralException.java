public class IllegalRomanNumeralException extends IllegalArgumentException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalRomanNumeralException(String message) { // when roman numeral is invalid, the message shows whats wrong
        super(message);
    }
}
