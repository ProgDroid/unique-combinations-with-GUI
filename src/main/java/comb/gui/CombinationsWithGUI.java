package comb.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.awt.event.*;
import javax.swing.*;

public class CombinationsWithGUI extends JFrame implements ActionListener
{
	private static final int ASCENDING = 0;
	private static final int DESCENDING = 1;
	
	private static JTextField textInput;
	private static JTextArea textOutput;
	private static JFrame frame;
	private static JButton buttonASC;
	private static JButton buttonDESC;
	private static JButton buttonCompute;
	private static JLabel labelInput;
	private static JLabel labelOutput;
	private static JPanel mainPanel;
	private static JPanel panel0;
	private static JPanel panel1;
	private static JPanel panel2;
	private static JPanel panel3;
	
	// defaults to descending
	private static int order = DESCENDING;
	
	// constructor, sets up GUI
	public CombinationsWithGUI()
	{
		// window
		frame = new JFrame("Combinations");
		
		// input field
		labelInput = new JLabel("Input:");
		textInput = new JTextField(9);
		
		// output field
		labelOutput = new JLabel("Output:");
		textOutput = new JTextArea(20, 30);
		textOutput.setEditable(false);
		textOutput.setLineWrap(true);
		textOutput.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(textOutput);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		// buttons
		buttonASC = new JButton("Ascending Order");
		buttonDESC = new JButton("Descending Order");
		buttonCompute = new JButton("Compute Combinations");
		
		buttonASC.addActionListener(this);
		buttonDESC.addActionListener(this);
		buttonCompute.addActionListener(this);
		
		// panels, main contains every other
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		panel0 = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		panel0.add(labelInput);
		panel0.add(textInput);
		
		panel1.add(buttonASC);
		panel1.add(buttonDESC);
		
		panel2.add(buttonCompute);
		
		panel3.add(labelOutput);
		panel3.add(scrollPane);
		
		mainPanel.add(panel0);
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		
		// add main panel to frame and set properties
		frame.add(mainPanel);
		frame.setSize(500, 500);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	} // CombinationsWithGUI()
	
	// when a button is pressed
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		
		if (command.equals("Ascending Order"))
		{
			order = ASCENDING;
		} // if
		
		else if (command.equals("Descending Order"))
		{
			order = DESCENDING;
		} // else if
		
		else if (command.equals("Compute Combinations"))
		{
			textOutput.setText(computeCombinations(textInput.getText()));
		} // else if
	} // actionPerformed()
	
	// perform binary search to insert number in proper place
	public static int binarySearch(int value, List<Integer> list)
	{
		int start = 0;
		int end = list.size() - 1;
		
		while (start <= end)
		{
			// calculate middle index and get value
			int middle = (start + end) / 2;
 			int middleValue = list.get(middle);
			
			// if ascending order
			if (order == ASCENDING)
			{
				// if middle less than value, take right
				if (middleValue < value)
				{
					start = middle + 1;
				} // if
				
				// if middle greater than value, take left
				else if (middleValue > value)
				{
					end = middle - 1;
				} // else if
				
				// else if value is the same
				else
				{
					return -1;
				} // else
			} // if
			else if (order == DESCENDING)
			{
				// if middle greater than value, take right
				if (middleValue > value)
				{
					start = middle + 1;
				} // if
					
				// if middle less than value, take left 
				else if (middleValue < value)
				{
					end = middle - 1;
				} // else if
				
				// else if value is the same
				else
				{
					return -1;
				} // else
			} // else if
		} // while
		
		return start;		
	} // binarySearch()
	
	// to swap digits in a string
	public static String swap(String input, int first, int second)
	{
		char[] characters = input.toCharArray();
		
		// characters switch place
		char tmp = characters[first];
		characters[first] = characters[second];
		characters[second] = tmp;
		
		// combine array in string
		String output = new String(characters);
		
		return output;
	} // swap()
	
	// to compute all combinations of a string of digits
	public static void combinations(String digits, int start, List<Integer> list)
	{
		// if at the end, add combination to list
		if (start == digits.length() - 1)
		{
			// parse value to int
			int value = Integer.parseInt(digits);
			
			// search in list. if it returns -1, don't add
			int searchVal = binarySearch(value, list);
			if (searchVal != -1)
			{
				list.add(searchVal, value);
			} // if
			return;
		} // if
		
		// else proceed to find combination
		else
		{
			// for all remaining digits
			for (int i = start; i < digits.length(); i++)
			{
				// switch starting with index
				digits = swap(digits, start, i);
				// go down tree
				combinations(digits, start + 1, list);
				// switch back
				digits = swap(digits, start, i);
			} // for
		} // else
		
		return;
	} // combinations()

    public static String computeCombinations(String input) throws NumberFormatException
    {
    	try
    	{
    		List<Integer> combinations = new ArrayList<>();
    		
    		String regEx = "[\\D]";
    		String strippedDigits = input.replaceAll(regEx, "");
    		int parsedInt = Integer.parseInt(strippedDigits);
    		
    		combinations.add(parsedInt);
    		
    		combinations(strippedDigits, 0, combinations);
    		
    		StringBuilder builder = new StringBuilder();
    		
    		Iterator<Integer> iterator = combinations.iterator();
    		
    		while (iterator.hasNext())
    		{
    			builder.append(iterator.next().toString());
    			if (iterator.hasNext())
    			{
    				builder.append(",");
    			} // if
    		} // while
    		
    		String output = builder.toString();
    		
    		return output;
    	} // try
    	catch (Exception e)
    	{
    		System.out.println(e.toString());
    		throw e;
    	} // catch
    } // computeCombinations()

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
		{
        	@Override
        	public void run()
        	{
        		new CombinationsWithGUI();
        	} // run()
		}); // invokeLater()
        
    } // main()

} // class CombinationsWithGUI
