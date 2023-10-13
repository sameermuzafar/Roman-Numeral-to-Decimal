import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileMenuHandler implements ActionListener { // FileMenuHandler class implements the ActionListener 
    private RomanNumeralGUI gui;

    public FileMenuHandler(RomanNumeralGUI gui) { // constructor 
        this.gui = gui; // sets gui instance 
    }

    
    public void actionPerformed(ActionEvent e) { // The actionPerformed method is called when a item is selected
        String command = e.getActionCommand();

        if (command.equals("Open")) { // when the "Open" menu item is selected, it opens a file chooser, reads the file, and loads the Roman numerals
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(gui.getFrame());
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                loadRomanNumeralsFromFile(selectedFile);
            }
        } else if (command.equals("Quit")) { // quits the application when 
            System.exit(0); // ends program with 0
        }
    }

    private void loadRomanNumeralsFromFile(File file) { // takes File instance as parameter, then loads & parses the Roman numerals
    	UnsortedRomanNumeralList unsortedList = new UnsortedRomanNumeralList();
        SortedRomanNumeralList sortedList = new SortedRomanNumeralList();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] romanNumerals = line.split(" ");
                for (String romanNumeral : romanNumerals) {
                    try {
                        RomanNumeral roman = new RomanNumeral(romanNumeral);
                        unsortedList.append(roman);
                        sortedList.add(roman);
                    } catch (IllegalRomanNumeralException e) {
                        System.err.println("Invalid Roman Numeral: " + romanNumeral);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1); // ends program with 1
        }

        //gui.updateLists(unsortedList, sortedList);
    }
}
