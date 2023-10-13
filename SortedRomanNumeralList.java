public class SortedRomanNumeralList extends RomanNumeralList {
	public void add(RomanNumeral add) {
        Node newNode = new Node(add); // Create a new node with the given Roman numeral
        Node current = getHead(); //initialize a reference to the current node with start node
        while (current.getNext() != null && current.getNext().getData().compareTo(add) < 0) {
            current = current.getNext(); //  Go thru list until a node with a >= value is found, or hit the end of the list
        }
        newNode.setNext(current.getNext()); // set "next" of new node to "next" of current node
        current.setNext(newNode);// set "next" of current node to new node
        setSize(getSize() + 1); // like size++
    }
}

