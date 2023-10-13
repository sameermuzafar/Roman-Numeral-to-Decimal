public class RomanNumeralList {
    private Node head;
    private int size;

    public RomanNumeralList() { // constructor
        head = new Node(null);
        size = 0; // empty start node with size 0
    }

    public int getSize() { // get method
        return size;
    }

    public void setSize(int size) { // set method 
        this.size = size;
    }

    public void append(RomanNumeral roman) {
        Node newNode = new Node(roman); // new node with roman numeral 
        Node current = head;  // initialized reference to current node to head node
        while (current.getNext() != null) { // until last node is hit
            current = current.getNext();
        }
        current.setNext(newNode);
        size++;
    }

    public Node getHead() { // get method for head node
        return head;
    }

    public void setHead(Node head) { // set method for starting node
        this.head = head;
    }

    public class Node {
        private RomanNumeral data;
        private Node next;

        public Node(RomanNumeral data) {
            this.data = data;
            this.next = null;
        }

        public RomanNumeral getData() { // get method for Roman Numeral data
            return data;
        }
        
        public Node getNext() { // get method for the next node
            return next;
        }
        
        public void setData(RomanNumeral data) { // set method for Roman Numeral data
            this.data = data;
        }

        public void setNext(Node next) { // set method for the next node
            this.next = next;
        }
    }
}
