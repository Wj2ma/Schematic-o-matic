package Circuit;

/** PosBattery Class
 * A Positive Battery CircuitPart that can be used in the circuit
 * Date Created: 14/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */

import java.util.*;

public class PosBattery extends CircuitPart
{  
    private int voltage;    //The volage of the battery
    private boolean state;  //The state of the current battery
    
    /**Constructor for the PosBattery object. Assigns the object its corresponding
     * name.
     * @param n     The name of the object
     */ 
    public PosBattery (String n, int volts)
    {
        super(n);
        voltage = volts;
    }
    
    /**Mutator method to set the state of the battery.
     * @param b     Set the state of the battery to this
     */ 
    public void setState(boolean b)
    {
        state = b;
    }
    
    /**Checks to see if the battery is turned on.
     * @return the state of the battery
     */
    public boolean isOn()
    {
        return state;
    }
    
    /**Accessor method to get the voltage of the object.
     * @return the voltage of the battery
     */ 
    public int getVolatge()
    {
        return voltage;
    }
    
    /** Gets the actual circuit part name.
     * @return the circuit part name
     */
    @Override
    public String getParent()
    {
        return "posbattery";
    }
    
    /** Returns the number of sides for this part.
     * @return the number of sides
     */
    @Override
    public int getSides()
    {
        //This part has 1 side
        return 1;
    }
}
