package Circuit;

/** NegBattery Class
 * A Negative Battery CircuitPart that can be used in the circuit
 * Date Created: 14/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */

import java.util.*;

public class NegBattery extends CircuitPart
{    
    /**Constructor for the PosBattery object. Assigns the object its corresponding
     * name.
     * @param n     The name of the object
     */ 
    public NegBattery (String n)
    {
        super(n);
    } 
    
    /** Gets the actual circuit part name.
     * @return the circuit part name
     */
    @Override
    public String getParent()
    {
        return "negbattery";
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
