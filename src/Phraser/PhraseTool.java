package Phraser;

	import java.io.*;
	import java.awt.Container;
	import java.awt.Frame;
	import java.awt.GridLayout;
	import java.awt.Label;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyEvent;
	import java.io.File;

	import javax.swing.JButton;
	import javax.swing.JDialog;
	import javax.swing.JFileChooser;
	import javax.swing.JFrame;
	import javax.swing.JMenu;
	import javax.swing.JMenuBar;
	import javax.swing.JMenuItem;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTextArea;

import Phraser.PhraseTest;


	/*
	 * This tool accesses the pangram tests to create an interface to check files of pangram delimited by end of lines
	 */
	public class PhraseTool implements ActionListener {
		static JFrame frame;
	    JTextArea output;

		
	    JScrollPane scrollPane;
	    
	    String newline = "\n";
	    
	    public JMenuBar createMenuBar() {
	        JMenuBar menuBar;
	        JMenu menu;
	        JMenuItem menuItem;


	        //Create the menu bar.
	        menuBar = new JMenuBar();

	        //Build the first menu.
	        menu = new JMenu("Main");
	        menu.setMnemonic(KeyEvent.VK_A);
	        menu.getAccessibleContext().setAccessibleDescription(
	                "Contains the main options for using the program");
	        menuBar.add(menu);

	        //a group of JMenuItems
	        menuItem = new JMenuItem("Pangram Test");
	        menuItem.getAccessibleContext().setAccessibleDescription(
	                "load a file containing strings to test for pangrams");
	        menuItem.addActionListener(this);
	        menu.add(menuItem);

	        menuItem = new JMenuItem("Palingram Test");
	        menuItem.getAccessibleContext().setAccessibleDescription(
	                "load a file containing strings to test for pangrams");
	        menuItem.addActionListener(this);
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Palindrome Test");
	        menuItem.getAccessibleContext().setAccessibleDescription(
	                "load a file containing strings to test for pangrams");
	        menuItem.addActionListener(this);
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Exit");
	        menuItem.addActionListener(this);
	        menu.add(menuItem);

	        
	        return menuBar;
	    }

	    public Container createContentPane() {
	        JPanel DisplayPane = new JPanel();
	        
	        DisplayPane.setOpaque(true);
	        
	        Label welcomelabel1;
	        
	        welcomelabel1 = new Label("Phrase Test Tool. Use the dropdown menu to continue.");
	        DisplayPane.add(welcomelabel1);
	        
	        //Create a scrolled text area.
	        output = new JTextArea(20,50);
	        output.setEditable(false);
	        scrollPane = new JScrollPane(output);

	        //Add the text area to the content pane.
	        DisplayPane.add(scrollPane);

	        return DisplayPane;
	    }

	     
	    

	    public void actionPerformed(ActionEvent e) {
	        JMenuItem source = (JMenuItem)(e.getSource());
	        //String s = source.getText();
	        output.setText(""); 
	        //output.setCaretPosition(output.getDocument().getLength());
	        
	        if (source.getText()=="Pangram Test") {
	            final JFileChooser fc = new JFileChooser();
	            fc.setCurrentDirectory(new File("./"));
	            
	            fc.showOpenDialog(frame);

	            // Get the selected file
	            File selFile = fc.getSelectedFile();
	            //System.out.print(selFile);
	            if (selFile!=null){
	            	//here we will do something will the file
	            	System.out.print("openning " + selFile.getAbsolutePath());
	                try{
	                    // Open the file that is the first 
	                    // command line parameter
	                    FileInputStream fstream = new FileInputStream(selFile.getAbsolutePath());
	                    // Get the object of DataInputStream
	                    DataInputStream in = new DataInputStream(fstream);
	                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	                    String strLine;
	                    //Read File Line By Line
	                    String sMissing="";
	                    while ((strLine = br.readLine()) != null)   {
	                      // test each line to see if it is a pangram
	                      // if yes then say so if no then report the letters which are missing
	                    	//System.out.println (strLine);
	                    	
	                    	//-//changed from a check-test to test because it could cut down on processing as we won't have to go over those letters we already checked to get the check for return false
	                    	//-//if (PhraseTest.checkPangram(strLine)) {
	                    	sMissing = PhraseTest.getMissingLetters(strLine );
	                    	if (sMissing.length()==0) {
		        				//System.out.println("'" + strLine + "' is a pangram\n");
		        				output.append("'" + strLine + "' is a pangram\n" + newline);
		        		        output.setCaretPosition(output.getDocument().getLength());
		        				
		        			} else {
		        				//-//sMissing = PhraseTest.getMissingLetters(strLine );
		        				//System.out.println("'" + strLine + "' is not a pangram");
		        				//System.out.println(sMissing + " " + ((sMissing.length()==1)?"is":"are") + " missing\n");
		        				output.append("'" + strLine + "' is not a pangram" + newline);
		        				output.append(sMissing + " " + ((sMissing.length()==1)?"is":"are") + " missing\n" + newline);
		        		        output.setCaretPosition(output.getDocument().getLength());
		        			}
	                      
	                    }
	                    //Close the input stream
	                    in.close();
                    }catch (Exception exc){//Catch exception if any
                      System.err.println("Error: " + exc.getMessage());
                    }
	            }
	        }
	        if (source.getText()=="Palingram Test") {
	            final JFileChooser fc = new JFileChooser();
	            fc.setCurrentDirectory(new File("./"));
	            
	            fc.showOpenDialog(frame);

	            // Get the selected file
	            File selFile = fc.getSelectedFile();
	            //System.out.print(selFile);
	            if (selFile!=null){
	            	//here we will do something will the file
	            	System.out.print("openning " + selFile.getAbsolutePath());
	                try{
	                    // Open the file that is the first 
	                    // command line parameter
	                    FileInputStream fstream = new FileInputStream(selFile.getAbsolutePath());
	                    // Get the object of DataInputStream
	                    DataInputStream in = new DataInputStream(fstream);
	                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	                    String strLine;
	                    //Read File Line By Line
	                    String sMissing="";
	                    while ((strLine = br.readLine()) != null)   {
	                      // test each line to see if it is a palingram
	                      // if yes then say so if no then report the letters which are missing
	                    	//System.out.println (strLine);
		        			if (PhraseTest.checkPalingram(strLine)) {
		        				//System.out.println("'" + strLine + "' is a pangram\n");
		        				output.append("'" + strLine + "' is a palingram\n" + newline);
		        		        output.setCaretPosition(output.getDocument().getLength());
		        				
		        			} else {
		        				sMissing = PhraseTest.getMissingLetters(strLine );
		        				//System.out.println("'" + strLine + "' is not a pangram");
		        				//System.out.println(sMissing + " " + ((sMissing.length()==1)?"is":"are") + " missing\n");
		        				output.append("'" + strLine + "' is not a palingram" + newline);
		        				//output.append(sMissing + " " + ((sMissing.length()==1)?"is":"are") + " missing\n" + newline);
		        		        output.setCaretPosition(output.getDocument().getLength());
		        			}
	                      
	                    }
	                    //Close the input stream
	                    in.close();
                    }catch (Exception exc){//Catch exception if any
                      System.err.println("Error: " + exc.getMessage());
                    }
	            }
	        }       

	        if (source.getText()=="Palindrome Test") {
	            final JFileChooser fc = new JFileChooser();
	            fc.setCurrentDirectory(new File("./"));
	            
	            fc.showOpenDialog(frame);

	            // Get the selected file
	            File selFile = fc.getSelectedFile();
	            //System.out.print(selFile);
	            if (selFile!=null){
	            	//here we will do something will the file
	            	System.out.print("openning " + selFile.getAbsolutePath());
	                try{
	                    // Open the file that is the first 
	                    // command line parameter
	                    FileInputStream fstream = new FileInputStream(selFile.getAbsolutePath());
	                    // Get the object of DataInputStream
	                    DataInputStream in = new DataInputStream(fstream);
	                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	                    String strLine;
	                    //Read File Line By Line
	                    String sMissing="";
	                    while ((strLine = br.readLine()) != null)   {
	                      // test each line to see if it is a palingram
	                      // if yes then say so if no then report the letters which are missing
	                    	//System.out.println (strLine);
		        			if (PhraseTest.checkPalindrome(strLine)) {
		        				//System.out.println("'" + strLine + "' is a pangram\n");
		        				output.append("'" + strLine + "' is a palindrome\n" + newline);
		        		        output.setCaretPosition(output.getDocument().getLength());
		        				
		        			} else {
		        				sMissing = PhraseTest.getMissingLetters(strLine );
		        				//System.out.println("'" + strLine + "' is not a pangram");
		        				//System.out.println(sMissing + " " + ((sMissing.length()==1)?"is":"are") + " missing\n");
		        				output.append("'" + strLine + "' is not a palindrome" + newline);
		        				//output.append(sMissing + " " + ((sMissing.length()==1)?"is":"are") + " missing\n" + newline);
		        		        output.setCaretPosition(output.getDocument().getLength());
		        			}
	                      
	                    }
	                    //Close the input stream
	                    in.close();
                    }catch (Exception exc){//Catch exception if any
                      System.err.println("Error: " + exc.getMessage());
                    }
	            }
	        }       
	        
	        if (source.getText()=="Exit") {
	        	System.exit(0);
	        }
	    }

	   /**
	     * Create the GUI and show it.  For thread safety,
	     * this method should be invoked from the
	     * event-dispatching thread.
	     */
	    private static  void createAndShowGUI() {
	        //Create and set up the window.
	        frame = new JFrame("Pangragm Tool");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Create/set menu bar and content pane.
	        PhraseTool demo = new PhraseTool();
	        frame.setJMenuBar(demo.createMenuBar());
	        frame.setContentPane(demo.createContentPane());
	        

	        //Display the window.
	        frame.setSize(600, 500);
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }

	    	    
	public static void ShowMessgaeBox(String msg) {
		 final Frame f = new Frame();
		 //f.setSize(300, 300);
		 f.setVisible (false);
		 
	      final JDialog jd = new JDialog (f, "Info", true);
	      jd.setSize(300, 100);
	      jd.setLayout (new GridLayout (0, 1));
	      jd.setLocationRelativeTo(null);
	      Label jl = new Label (msg); 
	      jd.add (jl);

	      JButton jb = new JButton ("OK");
	      jd.add (jb);
	      jb.addActionListener (new ActionListener() {
	          public void actionPerformed (ActionEvent e) {
	              jd.dispose();
	              f.dispose();
	            }
	          });
	      jd.pack();
	      jd.setVisible(true);
		
	}
	    




}
