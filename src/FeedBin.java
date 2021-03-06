//   FeedBin.java 

//   A R Grundy 
//   Modified by Gary Allen, February 2002 
//   Last update January 2008. 

/*   A class for modelling the state and operation of an 'animal feed bin' 
     as used in the factory production of animal feedstuffs 
*/ 

public class FeedBin { 
    
    // 
    // FeedBin instance variables 
    // 
    private int    binNumber; 
    private String productName; 
    private double maxVolume;               
    private double currentVolume;  
    
    // 
    // method FeedBin : constructor 
    // 
    public FeedBin(int binNo, String prodName) { 
	 
	binNumber = binNo;          // bin identifier 
	productName = prodName;     // product in bin 
	maxVolume = 40.0;           // maximum capacity in cubic metres 
	currentVolume = 0.0;        // bin starts in the empty state 
	 
    } // end method FeedBin 
    
    
    // 
    // method setProductName - used to change the product assigned to the bin 
    //                         can only do this if the bin is empty 
    //                         ie use flush() method first 
    public boolean setProductName(String newName) { 
	if (currentVolume == 0.0) { 
	    productName = newName; 
	    return true; 
	} else 
	    return false; 
    } 
    
    
    // 
    // method flush - used to completely empty the bin 
    // 
    public void flush() { 
	currentVolume = 0.0; 
    } 
    
    // 
    // method addProduct - can only add if there is sufficient room 
    // 
    public boolean addProduct(double volume) { 
        if ( maxVolume >= currentVolume  + volume ) { 
	    currentVolume +=  volume; 
	    return true; 
        } else 
	    return false; 
	 
    } 
    
    // 
    // method removeProduct - returns actual volume removed if insufficient 
    //                        in bin to remove full amount requested 
    public double removeProduct(double volume) { 
	if (currentVolume >= volume) { 
	    currentVolume -= volume; 
	} 
	else { 
	    volume = currentVolume; 
	    currentVolume = 0.0; 
	} 
	return volume;  // actual amount removed - may be less than requested 
	 
    } 

    // 
    // accessor methods for each FeedBin instance variable 
    // 
    public int    getBinNumber()    { return binNumber;     } 
    public String getProductName()  { return productName;   } 
    
    public double getMaxVolume()    { return maxVolume;     } 
    public double getcurrentVolume(){ return currentVolume; } 
    
} // class FeedBin 


