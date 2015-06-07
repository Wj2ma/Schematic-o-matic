package Circuit;

/** NOTGate Class
 * A NOT gate CircuitPart that can be used in the circuit
 * Date Created: 14/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.util.*;

public class NOTGate extends CircuitPart
{
    private boolean powerS2;    //Positive power of side 2
    private boolean negPowerS2; //Negative power of side 2
    
    private ArrayList <CircuitPart> connectionsSide2 = new ArrayList<CircuitPart>();    //All connections of side 2
    
    /**Constructor for the NOTGate CircuitPart. Assigns the object its corresponding
     * name.
     * @param n     The name of the object
     */ 
    public NOTGate(String n)
    {
        super(n);
    }   
    
    /**Sets one of the sides for this object to be connected to another object.
     * @param object    The object
     * @param side      The side of this object that will be connected to the other object
     */
    @Override
    public void setConnected(CircuitPart object, int side)
    {
        switch (side)
        {
            //Adds the object to the corresponding side
            case 1:
                connectionsSide1.add(object);
                break;
            case 2:
                connectionsSide2.add(object);
                break;
        }              
    }

    
    /**Checks to see if this object is connected to the other object at a 
     * specific side.
     * @param object    The object
     * @return the side of this object that is connected to the other object 
     *         or 0 if it is not connected 
     */
    @Override
    public ArrayList <Integer> isConnected(CircuitPart object)
    {
        ArrayList <Integer> list = new ArrayList <Integer>();
        //Looks through each name in the connections to check if it is
        //connected to the object
        for (CircuitPart temp: connectionsSide1)
            if (object.getName().equals(temp.getName()))
                list.add(1);
        for (CircuitPart temp: connectionsSide2)
            if (object.getName().equals(temp.getName()))
                list.add(2);
        return list;
    }
    
    /** Removes a connection to this object.
     * @param object    The other object
     * @param side      The side of this object
     */ 
    @Override
    public void removeConnection(CircuitPart object, int side)
    {
        switch (side)
        {
            //Removes the object from the corresponding side
            case 1:
                connectionsSide1.remove(object);
                break;
            case 2:
                connectionsSide2.remove(object);
                break;
        }
    }
    
    /**Sets the positive power of a certain side of this object.
     * @param p     Sets power to on or off
     * @param side  The side that this method is working on
     * @return an array of 2 values: The first one is to check whether there is 
     *         a short circuit or not, and the second is to check whether there
     *         has been a change in power
     */
    @Override
    public boolean[] setPower(boolean p, int side)
    {
        //make change = false
        //Change will be used in a recursion method in circuit
        boolean change = false;   
        switch (side)
        {
            //If the power of side 1 isn't already equal to p, set it to p
            //and make change equal to true
            case 1:
                if (powerS1 != p)
                {
                    powerS1 = p;
                    change = true;
                }
                break;
            //If the power of side 2 isn't already equal to p, set it to p
            //and make change equal to true
            case 2:
                if (powerS2 != p)
                {
                    powerS2 = p;
                    change = true;
                }
                break;
        }
        boolean checks[] = {shortCircuit(), change};
        return checks;
    }
    
    /**Checks to see if a certain side of this object has positive power.
     * @param side      The side the method is checking
     * @return the current state of the power
     */
    @Override
    public boolean isPower(int side)
    {
        switch (side)
        {
            //Returns the power of the side
            case 1:
                return powerS1;
            case 2:
                return powerS2;
        }
        return false;
    }
    
     /**Sets the negative power of a certain side of this object.
     * @param p     Sets negative power to on or off
     * @param side  The side that this method is working on
     * @return an array of 2 values: The first one is to check whether there is 
     *         a short circuit or not, and the second is to check whether there
     *         has been a change in power
     */
    @Override
    public boolean[] setNegPower(boolean p, int side)
    {
        //Same thing as setPower except it is negative power
        boolean change = false;
        switch (side)
        {
            case 1:
                if (negPowerS1 != p)
                {
                    change = true;
                    negPowerS1 = p;
                }
                break;
            case 2:
                if (negPowerS2 != p)
                {
                    change = true;
                    negPowerS2 = p;
                }
                break;
        }
        boolean checks[] = {shortCircuit(), change};
        return checks;
    }
    
    /**Checks to see if a certain side of this object has negative power.
     * @param side      The side the method is checking
     * @return the current state of the power
     */
    @Override
    public boolean isNegPower(int side)
    {
        //Same thing as isPower but negative
        switch (side)
        {
            case 1:
                return negPowerS1;
            case 2:
                return negPowerS2;
        }
        return false;
    }
    
    /**Resets all the power for the current part
     */
    @Override
    public void resetPower()
    {
        //Make all powers off
        powerS1 = false;
        negPowerS1 = false;
        powerS2 = false;
        negPowerS2 = false;
    }
    
    /**Checks to see if there is a short circuit in the circuit.
     * @return true or false depending on if there is a short circuit or not
     */ 
    private boolean shortCircuit()
    {
        //There is a short circuit if there is a positive and negative power
        //going through one side of the object
        if ((powerS1 == true && negPowerS1 == true) || (powerS2 == true && negPowerS2 == true))
            return true;
        else
            return false;
    }   
    
    /** Gets the actual circuit part name.
     * @return the circuit part name
     */
    @Override
    public String getParent()
    {
        return "notgate";
    }
    
    /** Accessor method to return the arraylist of connections for a side.
     * @param side      The side the method is working with
     * @return the arraylist of connections to that side
     */
    @Override
    public ArrayList <CircuitPart> getConnections(int side)
    {
        //Returns the corresponding arraylist of connections
        switch (side)
        {
            case 1:
                return connectionsSide1;
            case 2:
                return connectionsSide2;
        }
        return null;
    }
    
    /** Returns the number of sides for this part.
     * @return the number of sides
     */
    @Override
    public int getSides()
    {
        //This part has 2 sides
        return 2;
    }
}