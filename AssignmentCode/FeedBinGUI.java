// FeedBinGUI.java

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

/** 
 * @author  Anne Grundy 
 * Modified by Gary Allen, February 2002 
 * Latest update January 2008 
 * @version 1.1 
 */ 

public class FeedBinGUI extends JFrame { 
    
    private JMenuBar  jmbTop; 
    private JMenu     binMenu; 
    private JMenuItem inspect; 
    private JMenuItem fill; 
    private JMenuItem flush; 
    private JMenuItem exit; 
    
    private FeedBin bin;   // here's the system object behind the interface 
    
    public FeedBinGUI() { 
	 
        bin = new FeedBin(34,"Weety Bits");     //  create a feed bin 
	 
	// Create the menu components 
	jmbTop = new JMenuBar (); 
	binMenu = new JMenu("Bin"); 
	 
	inspect = new JMenuItem("Inspect the Bin ..."); 
        	fill    = new JMenuItem("Add More Product ..."); 
	flush   = new JMenuItem("Flush the Bin ..."); 
	exit    = new JMenuItem("Exit"); 
	 
	binMenu.add(inspect); 
        	binMenu.add(fill); 
	binMenu.add(flush); 
	binMenu.add (new JSeparator()); 
	binMenu.add(exit); 
	 
	jmbTop.add(binMenu); 
	 
	setJMenuBar (jmbTop); 
	 
	exit.addActionListener (new ActionListener() { 
		public void actionPerformed (ActionEvent evt) {		   
		    System.exit(0); 
		} 
	    }		   
				); 
	 
	inspect.addActionListener (new ActionListener() { 
		public void actionPerformed (ActionEvent evt) { 
		    InspectDialog id = new InspectDialog(FeedBinGUI.this,true,bin); 
		    id.setVisible(true);; 
		} 
	    }		   
				   ); 
	 
        fill.addActionListener (new ActionListener() { 
		public void actionPerformed (ActionEvent evt) { 
		    FillDialog fd = new FillDialog(FeedBinGUI.this,true,bin); 
		    fd.setVisible(true);; 
		} 
	    }		   
				); 
	 
	flush.addActionListener (new ActionListener() { 
		public void actionPerformed (ActionEvent evt) { 
		    bin.flush(); 
		    JOptionPane.showMessageDialog(FeedBinGUI.this, 
						  "The bin is now empty", 
						  "Flush Confirmation", 
						  JOptionPane.INFORMATION_MESSAGE, 
						  null); 
		} 
	    }		   
				 ); 
	 
	setTitle ("Feed Bin Controller"); 
        	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); 
	setSize(400, 200);	 
    } 
    
    public static void main (String args[]) { 
	FeedBinGUI demo = new FeedBinGUI(); 
	demo.setLocation(400,400); 
	demo.setVisible(true); 
    } 
    
} // end class FeedBinGUI 



// 
//  Inspect Menu Option Dialog class 
// 
class InspectDialog extends JDialog { 
    
    private FeedBin bin; 
    
    private Box boxInfo; 
    private Box boxButton; 
    
    private JPanel panel; 
    private JLabel label []; 
    
    private JButton OKButton; 
    
    // 
    //  InspectDialog constructor 
    // 
    InspectDialog(FeedBinGUI parent, boolean modal, FeedBin binObject) { 
	 
	super (parent, "Bin Inspection", modal); // call superclass constructor 

	int i; 
	bin = binObject; 

	getContentPane ().setLayout 
	    (new BoxLayout (getContentPane (), BoxLayout.Y_AXIS)); 
	 
	panel = new JPanel(); 
	panel.setLayout(new GridLayout(4,2) ); 
	 
	label = new JLabel[8]; 
	 
	label[0] = new JLabel("Bin Number"); 
	label[2] = new JLabel("Contains"); 
	label[4] = new JLabel("Maximum Volume"); 
	label[6] = new JLabel("Current Volume"); 
	 
	label[1] = new JLabel(String.valueOf(bin.getBinNumber())); 
	label[3] = new JLabel(bin.getProductName());   
	label[5] = new JLabel(String.valueOf(bin.getMaxVolume()));  
	label[7] = new JLabel(String.valueOf(bin.getcurrentVolume())); 
	 
	OKButton = new JButton("OK"); 
	 
	OKButton.addActionListener (new ActionListener () { 
		public void actionPerformed (ActionEvent evt) { 
		    dispose();  
		} 
	    } 
				    ); 
	 
	for (i = 0; i< 8 ; i++)  // labels go into a 4 x 2 grid 
	    panel.add(label[i]); 
        
	boxInfo   = new Box(BoxLayout.Y_AXIS); 
	boxButton = new Box(BoxLayout.Y_AXIS); 
	 
	boxInfo.add(panel); 
	 
	boxButton.add(OKButton); 
	 
	getContentPane().add(boxInfo); 
	getContentPane().add(boxButton);     
	 
	pack(); 
	 
    } // end InspectDialog constructor 
    
} // end InspectDialog class 



class FillDialog extends JDialog implements ActionListener{ 
    
    private FeedBin bin; 
    private JTextField input; 
    
    private JPanel panel1; 
    private JPanel panel2; 
    private JLabel label; 
    
    private JButton applyButton; 
    private JButton clearButton; 
    private JButton cancelButton; 
    
    
    // 
    //  FillDialog constructor 
    // 
    FillDialog(FeedBinGUI parent, boolean modal, FeedBin binObject) { 
	 
	super (parent, "Bin Filling", modal); // call superclass constructor 
	 
	bin = binObject; 
	 
	panel1 = new JPanel(); 
	panel2 = new JPanel(); 
	 
	label = new JLabel("How much is to be added?"); 
	input = new JTextField(10); 
	 
	applyButton  = new JButton("Apply");  
	clearButton  = new JButton("Clear"); 
	cancelButton = new JButton("Cancel"); 
	 
	applyButton.addActionListener(this); 
	clearButton.addActionListener(this);  
	cancelButton.addActionListener(this); 
	 
	panel1.add(label); 
	panel1.add(input); 
	getContentPane().add(panel1,"North"); 
	 
	panel2.add(applyButton); 
	panel2.add(clearButton); 
	panel2.add(cancelButton); 
	getContentPane().add(panel2,"South"); 
	 
	pack(); 
	 
    } 
    
    // 
    // button event handler 
    // 
    
    public void actionPerformed(ActionEvent event) { 
	 
	double volume; 
	String outcome;  
	 
	String butLabel = event.getActionCommand(); 
        
	if (butLabel.equals("Apply")) {             
	    try { 
		volume = Double.parseDouble(input.getText()); 
		if (volume < 0.0) 
		    throw new NumberFormatException(); 
		if (!bin.addProduct(volume))       // interact with the bin object here 
		    throw new FillException(); 
		JOptionPane.showMessageDialog(this, 
					      "Addition Confirmed", 
					      "Fill Report", 
					      JOptionPane.INFORMATION_MESSAGE, 
					      null); 
		dispose(); 
	    } 
	    catch (NumberFormatException e) {      // may be thrown by parseDouble() 
		JOptionPane.showMessageDialog(this, 
					      "Not a valid number", 
					      "Fill Report", 
					      JOptionPane.WARNING_MESSAGE, 
					      null); 
	    } 
	    catch (FillException e) { 
		JOptionPane.showMessageDialog(this, 
					      "Not enough room in the bin", 
					      "Fill Report", 
					      JOptionPane.WARNING_MESSAGE, 
					      null); 
	    }         
	    
	}  // end of Apply button handling 
	 
	else if(butLabel.equals("Clear"))     
	    input.setText("");                                              
	 
	else // must have pressed the cancel button 
	    dispose();                                
	 
    }  // method actionPerformed 
    
} // class AddDialog 

class FillException extends RuntimeException {} 




