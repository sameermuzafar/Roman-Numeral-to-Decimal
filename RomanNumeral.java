



public class RomanNumeral implements Comparable<RomanNumeral> { // using comparable interface
	private String romanNumeral;
	private int arabicValue; // instance variables 
	/**
	 * 
	 * @param romanNumeral takes roman numeral and coverts to arabic value
	 * 
	 */
	public RomanNumeral(String romanNumeral) { //constructor that takes roman numeral as argument and 
		this.romanNumeral = romanNumeral; // sets the instance var to string and stores & converts it to arabic value
		arabicValue = convertToArabic(romanNumeral);
    }

	public String getRomanNumeral() {
		return romanNumeral; 
    }
	
	public void setRomanNumeral(String romanNumeral) {
        this.romanNumeral = romanNumeral;
        this.arabicValue = convertToArabic(romanNumeral);
    }
	
    public int getArabicValue() {
        return arabicValue; 
    }

    public boolean equals(Object object1) { // compares two RomanNumeral objects for equality
    	if (object1 instanceof RomanNumeral) { 
    		RomanNumeral other = (RomanNumeral) object1; // True if both the Roman numeral string and Arabic values strings are equal
    		return this.arabicValue == other.arabicValue && this.romanNumeral.equals(other.romanNumeral);
        }
    	return false;
    }
    
    public String toString() {
    	return romanNumeral;
    }
   
    public int compareTo(RomanNumeral other) {
        return Integer.compare(this.arabicValue, other.arabicValue); // compare two RomanNumeral objects based on their Arabic values
    }

    private int convertToArabic(String romanNumeral) { // converts to arabic value 
        int result = 0;
        int lastValue = 0;
        for (int i = romanNumeral.length() - 1; i >= 0; i--) { // goes thru roman numeral sting right to left
            int currentValue;
            char c = romanNumeral.charAt(i);

            // Use a switch statement to determine the Arabic value of the current Roman numeral character
            switch (c) {
                case 'I':
                    currentValue = 1;
                    break;
                case 'V':
                    currentValue = 5;
                    break;
                case 'X':
                    currentValue = 10;
                    break;
                case 'L':
                    currentValue = 50;
                    break;
                case 'C':
                    currentValue = 100;
                    break;
                case 'D':
                    currentValue = 500;
                    break;
                case 'M':
                    currentValue = 1000;
                    break;
                default: // default is when input character isn't a roman numeral so it throws IllegalArgumentException
                    throw new IllegalArgumentException("Invalid Roman Numeral character: ");
            }

            if (currentValue < lastValue) { // checks roman numerals and sees if its valid or not
                result -= currentValue; // checks if last value less than current, if it is, subtract, if not, then add
            } else {
                result += currentValue;
            }
            lastValue = currentValue; // sets it as the current value after the statements
        }
        return result;
    }
}
