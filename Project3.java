import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;
/**
 * 
 * @author shmeez
 *
 */
public class Project3 {
    public static void main(String[] args) {
        RomanNumeralGUI gui = new RomanNumeralGUI(); //new instance of RomanNumerGUI class
        gui.createAndShowGUI(); // shows gui
    }
    public static void displayFileContent(String fileName, JTextArea displayArea) { // displays content using JTextArea
        UnsortedRomanNumeralList unsortedList = new UnsortedRomanNumeralList(); // new instance of UnsortedRomanNumeralList class
        SortedRomanNumeralList sortedList = new SortedRomanNumeralList(); //new instance of SsortedRomanNumeralList class

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) { // opens a BufferedReader to read the file 
            String line; // holds each line read from file
            while ((line = reader.readLine()) != null) { // stops when no more lines are available
                String[] romanNumerals = line.split(" "); // splits lines into array of strings
                for (String romanNumeral : romanNumerals) { // iterates thru
                    try {
                        RomanNumeral roman = new RomanNumeral(romanNumeral);
                        unsortedList.append(roman); // appends the Roman numeral to the unsorted list
                        sortedList.add(roman); // adds the Roman numeral to the sorted list
                    } catch (IllegalRomanNumeralException e) { // catches illegal error
                        System.out.println(e.getMessage()); // Prints the error message to the console
                    }
                }
            }
        } catch (IOException e) { //if there is an issue reading the file.
            e.printStackTrace(); // prints the exception stack trace to the console.
            System.exit(1); // terminates code 
        }
        StringBuilder unsortedArabicValues = new StringBuilder(); // creates a StringBuilder for the unsorted Arabic values
        StringBuilder sortedArabicValues = new StringBuilder(); // creates a StringBuilder for the sorted Arabic values
        RomanNumeralList.Node currentUnsorted = unsortedList.getHead().getNext(); // gets the first node in the unsorted list
        RomanNumeralList.Node currentSorted = sortedList.getHead().getNext(); // gets the first node in the sorted list
        while (currentUnsorted != null || currentSorted != null) { // it loops until both lists have been used 
            if (currentUnsorted != null) { // checks if current node in unsorted list is null. If its not null, it means there are still elements in the list,
                unsortedArabicValues.append(currentUnsorted.getData().getArabicValue()).append("\n"); // retrieves roman numeral data and appends it, new line to show arabic value 
                currentUnsorted = currentUnsorted.getNext(); 
            }
            if (currentSorted != null) {
                sortedArabicValues.append(currentSorted.getData().getArabicValue()).append("\n");
                currentSorted = currentSorted.getNext();
            }
        }

        displayArea.setText("Unsorted Arabic Values:\n" + unsortedArabicValues.toString() +
                "\nSorted Arabic Values:\n" + sortedArabicValues.toString()); // displays the text 
    }
}

//Sameer Muzafar
//Lab Professor: Zhujun Li - Lab section: 22A [34711]
