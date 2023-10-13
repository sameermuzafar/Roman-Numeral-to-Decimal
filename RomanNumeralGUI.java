import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;




public class RomanNumeralGUI {
    private UnsortedRomanNumeralList unsortedList; // declares priv instance variable unsortedList
    private SortedRomanNumeralList sortedList; // declares priv instance variable sortedList
    private JFrame frame; // declares priv instance variable frame & this holds the main application window

    public RomanNumeralGUI() { // constructor for romannumeralgui class using unsortedlist and sortedlist
        unsortedList = new UnsortedRomanNumeralList();
        sortedList = new SortedRomanNumeralList();
    }

    public void createAndShowGUI() { //  This method initializes the frame, sets some properties, attaches handlers for the menu items, and makes the frame visible.
        frame = new JFrame("Roman Numerals Converter"); // Name on top of GUI
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits when pressing close
        frame.setLayout(new BorderLayout()); 

        attachHandlers(); // Call the attachHandlers method to set up the menu bar and menu items with their corresponding action listeners.
        //updateTable();

        frame.pack(); 
        frame.setVisible(true); //sets visibility of gui true
    }

    private void updateTable() {
        JPanel panel = new JPanel(new GridLayout(0, 3));  // 3 columns 
            panel.add(new JLabel("Roman Numerals")); // names on top of the gui to show each different value
            panel.add(new JLabel("Unsorted Arabic Value")); // ^
            panel.add(new JLabel("Sorted Arabic Value")); // ^
        

        RomanNumeralList.Node currentUnsorted = unsortedList.getHead().getNext();
        RomanNumeralList.Node currentSorted = sortedList.getHead().getNext(); // initializing nodes for sorted/unsorted lists
        while (currentUnsorted != null || currentSorted != null) { // add values to the panel with while loop
            JLabel label1 = new JLabel(currentUnsorted == null ? "" : currentUnsorted.getData().getRomanNumeral());
            JLabel label2 = new JLabel(currentUnsorted == null ? "" : String.valueOf(currentUnsorted.getData().getArabicValue()));
            JLabel label3 = new JLabel(currentSorted == null ? "" : String.valueOf(currentSorted.getData().getArabicValue()));
            // JTextArea for each column to make it look neat and organized
            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
            //adds to panels
            

            if (currentUnsorted != null) { // moves to next node
                currentUnsorted = currentUnsorted.getNext();
            }
            if (currentSorted != null) {
                currentSorted = currentSorted.getNext();
            }
        }

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel, BorderLayout.CENTER);  // add the panel to frame and pack the frame
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    private void attachHandlers() {
        JMenuBar menuBar = new JMenuBar(); // creates menu bar instance 
        frame.setJMenuBar(menuBar); // Set the menu bar for the JFrame.

        JMenu fileMenu = new JMenu("File"); // Create a new JMenu instance with the label "File"
        menuBar.add(fileMenu); // adds "File" to menu bar

        JMenuItem openMenuItem = new JMenuItem("Open"); // Create a new JMenuItem instance with the label "Open"
        fileMenu.add(openMenuItem);
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame); // Show the file dialog and store the result of the user's action (approve or cancel) in the result variable.

                if (result == JFileChooser.APPROVE_OPTION) { //Check if the user approved the file selection.
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        displayFileContent(selectedFile.getAbsolutePath());
                        updateTable();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error reading file.", "Error", JOptionPane.ERROR_MESSAGE); // error message shown
                    }
                }
            }
        });

        JMenuItem quitMenuItem = new JMenuItem("Quit"); // quit 
        fileMenu.add(quitMenuItem); // adds quit menu 
        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenu convertMenu = new JMenu("Convert"); // the convert menu option created
        menuBar.add(convertMenu); // adds it 

        JMenuItem romanToArabicMenuItem = new JMenuItem("Roman to Arabic"); // adds roman to arabic to menu option
        convertMenu.add(romanToArabicMenuItem);
        romanToArabicMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String romanNumeralInput = JOptionPane.showInputDialog(frame, "Enter a Roman numeral:"); // shown on the gui option
                if (romanNumeralInput != null && !romanNumeralInput.isEmpty()) {
                    try {
                        RomanNumeral romanNumeral = new RomanNumeral(romanNumeralInput);
                        int arabicValue = romanNumeral.getArabicValue();
                        JOptionPane.showMessageDialog(frame, "The Arabic value for " + romanNumeralInput + " is " + arabicValue, "Result", JOptionPane.INFORMATION_MESSAGE); // shows on screen the results
                    } catch (IllegalRomanNumeralException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid Roman numeral. Please enter a valid Roman numeral.", "Error", JOptionPane.ERROR_MESSAGE); // shows error message
                    }
                }
            }
        });
    }

    private void displayFileContent(String fileName) throws IOException { // already explained previously 
        unsortedList = new UnsortedRomanNumeralList();
        sortedList = new SortedRomanNumeralList();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] romanNumerals = line.split(" ");
                for (String romanNumeral : romanNumerals) {
                    try {
                        RomanNumeral roman = new RomanNumeral(romanNumeral);
                        unsortedList.append(roman);
                        sortedList.add(roman);
                    } catch (IllegalRomanNumeralException e) {
                        System.out.println("Invalid Roman numeral: " + romanNumeral);
                    }
                }
            }
        }
    }

	public Component getFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}
