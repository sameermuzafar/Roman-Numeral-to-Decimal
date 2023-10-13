public class RomanNumeralListNode {
    private RomanNumeral data;
    private RomanNumeralListNode next;

    public RomanNumeralListNode(RomanNumeral data) {
        this.data = data;
        this.next = null; // null "next" reference 
    }

    public RomanNumeral getData() {  // get method for Roman Numeral data 
        return data;
    }
    
    public RomanNumeralListNode getNext() { // get method for the next node
        return next;
    }

    public void setData(RomanNumeral data) { // set method for Roman Numeral data
        this.data = data;
    }

    
    public void setNext(RomanNumeralListNode next) { //set method for the next node
        this.next = next;
    }

    public int getArabicValue() {
        return data.getArabicValue(); // gets the Arabic value of the Roman numeral data stored in the node
    }
}
