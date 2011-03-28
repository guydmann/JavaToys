package ParticklerPolar;

import java.io.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
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

public class AnimTool implements ActionListener {
	static JFrame frame;
    JTextArea output;
    Board AnimPane;
    JScrollPane scrollPane;
    static ParticleDataPolar DataModule; 
    
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
        menuItem = new JMenuItem("Load Polar Particle Data");
        menuItem.getAccessibleContext().setAccessibleDescription(
                "load a file containing strings to test for pangrams");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        /*
        menuItem = new JMenuItem("Load Polar Particle Data");
        menuItem.getAccessibleContext().setAccessibleDescription(
                "load a file containing strings to test for pangrams");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        */
        
        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        
        return menuBar;
    }

    public Container createContentPane() {
        JPanel DisplayPane = new JPanel();
        DisplayPane.setLayout(new BorderLayout());
        DisplayPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        DisplayPane.setOpaque(true);
        
        Label welcomelabel1;
        
        welcomelabel1 = new Label("Particle Animation Tool. Use the dropdown menu to continue.");
        DisplayPane.add(welcomelabel1, BorderLayout.PAGE_START);
        
        //Create a scrolled text area.
        output = new JTextArea(8,50);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        DisplayPane.add(scrollPane,  BorderLayout.PAGE_END);
        AnimPane = new Board(DataModule);
        //AnimPane.setPreferredSize(280, 240);
        DisplayPane.add(AnimPane , BorderLayout.CENTER);
        //DisplayPane.setSize(280, 240);

        return DisplayPane;
    }

     
    

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        //String s = source.getText();
        output.setText(""); 
        //output.setCaretPosition(output.getDocument().getLength());
        
        if (source.getText()=="Load Polar Particle Data") {
        	if(AnimPane.isRunning()) {
        		AnimPane.toggleRun();
        	}
            final JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("./"));
            
            fc.showOpenDialog(frame);

        	boolean runnable = false;
            // Get the selected file
            File selFile = fc.getSelectedFile();
            //System.out.print(selFile);
            if (selFile!=null){

            	DataModule.clear();
            	//here we will do something will the file
            	System.out.println("openning " + selFile.getAbsolutePath());
                try{
                    // Open the file that is the first 
                    // command line parameter
                    FileInputStream fstream = new FileInputStream(selFile.getAbsolutePath());
                    // Get the object of DataInputStream
                    DataInputStream in = new DataInputStream(fstream);
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String strLine;            	
                    int line_num = 1;
                    //Read File Line By Line
                    while ((strLine = br.readLine()) != null)   {
                    	//all data for a set should be contained in a single line
                    	// the file could contain multiple sets on multiple lines
                    	//  if an error occur need to report the line it occurred on
                    	// data in format "0,90,180,270","1,5,1,2","0,90,180,270","1,2,3,4"
                    	// parse data into direction_double_array, v_double_array, pos0_direction_double_array, pos0_distance_double_array
                    	double[] adD = null,adV = null,adP_dir = null,adP_dis = null;
                    	Pattern pattern = Pattern.compile("\"([0-9\\.,\\-]+)\",\"([0-9\\.,\\-]+)\",\"([0-9\\.,\\-]+)\",\"([0-9\\.,\\-]+)\"");
                        Matcher matcher = pattern.matcher(strLine);
                        if(matcher.matches()) {
                        	System.out.println("Parsing line " + line_num );
                        	//System.out.println(matcher.group(1));
                        	//System.out.println(matcher.group(2));
                        	//System.out.println(matcher.group(3));
                        	//System.out.println(matcher.group(4));
                        	adD = convertStringArraytoDoubleArray(matcher.group(1).split(","));
                        	adV = convertStringArraytoDoubleArray(matcher.group(2).split(",")); 
                        	adP_dir = convertStringArraytoDoubleArray(matcher.group(3).split(","));
                        	adP_dis = convertStringArraytoDoubleArray(matcher.group(4).split(","));
                        	
                        } else {
                        	System.out.println("Parse failure at line " + line_num);
                        	return ;
                        }
                    	// test if LR_string.length == v_double_array.length == pos0_double_array.length
                    	//		if not fail and throw error
                    	// also test that they are all nonzero
                    	// pass all data to the ParticleData object to be appended
                    	if (DataModule.append(adD,adV,adP_dir,adP_dis)) {
                        	// toggle rendering on by
                        	// set runnable = true
                    		runnable = true;
                    		System.out.println("data imported successfully");
                    	} else {
                    		runnable = false;
                    	}
                    	line_num++;
                    }
                    //Close the input stream
                    in.close();
                }catch (Exception exc){//Catch exception if any
                  System.err.println("Error: " + exc.getMessage());
                }
                if (runnable) {
                	AnimPane.toggleRun();                    	
                }
                
            }
        }
                
        if (source.getText()=="Exit") {
        	System.exit(0);
        }
    }

    public static double[] convertStringArraytoDoubleArray(String[] sarray) throws Exception {
    	if (sarray != null) {
    		double dblarray[] = new double[sarray.length];
    		for (int i = 0; i < sarray.length; i++) {
    			dblarray[i] = Double.parseDouble(sarray[i]);
    		}
    		return dblarray;
    	}
    	return null;
    }
    
    
   /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static  void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Particle Animation Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create/set menu bar and content pane.
        AnimTool demo = new AnimTool();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());
        

        //Display the window.
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
    	//setup data module
    	DataModule = new ParticleDataPolar();
    	
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
