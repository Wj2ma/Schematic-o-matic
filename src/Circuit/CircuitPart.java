package Circuit;

/**CircuitPart Class
 * The Circuit Part is the basis of all of the parts in the circuit. It includes
 * methods used by all of the parts of the circuit.
 * Date Created: 15/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */ 

import java.util.*;

public class CircuitPart extends Object
{
    protected boolean powerS1=false;    //Positive power on side 1
    protected boolean negPowerS1=false; //Negative power on side 1
    
    protected ArrayList <CircuitPart> connectionsSide1 = new ArrayList<CircuitPart>();    //All connections to this side   
    
    protected String name;    //Name of the object
    
    /**Constructor for the CircuitPart object. Assigns the object its corresponding
     * name.
     * @param n     The name of the object
     */ 
    public CircuitPart (String n)
    {
        super();
        name = n;
    }
    
    /**Accessor method to get the name of the object.
     * @return the name of the object
     */ 
    public String getName()
    {
        return name;
    }
    
    /**Sets this object to be connected to another object.
     * @param object    The other object
     * @param side      The side of this object that will be connected to the other object
     */
    public void setConnected(CircuitPart object, int side)
    {
        //Adds the object to the corresponding side
        connectionsSide1.add(object);
    }
    
    /**Sets the positive power of a certain side of this object.
     * @param p     Sets power to on or off
     * @param side  The side that this method is working on
     * @return an array of 2 values: The first one is to check whether there is 
     *         a short circuit or not, and the second is to check whether there
     *         has been a change in power
     */
    public boolean[] setPower(boolean p, int side)
    {
        powerS1 = p;
        boolean checks[] = {false,false};
        return checks;
    }
    
    /**Checks to see if the positive side connects with this.
     * @param side  The side this method is working with
     * @return the current state of the negative power
     */
    public boolean isPower(int side)
    {
        //Returns the power of the side
        return powerS1;
    }
    
     /**Sets the negative power of a certain side of this object.
     * @param p     Sets negative power to on or off
     * @param side  The side that this method is working on
     * @return an array of 2 values: The first one is to check whether there is 
     *         a short circuit or not, and the second is to check whether there
     *         has been a change in power
     */
    public boolean[] setNegPower(boolean p, int side)
    {
        //Same thing as setPower but negative
        negPowerS1 = p;
        boolean checks[] = {false,false};
        return checks;
    }
    
    /**Checks to see if the negative side connects with this.
     * @param side  The side this method is working with
     * @return the current state of the negative power
     */
    public boolean isNegPower(int side)
    {
        return negPowerS1;
    }
    
    /**Resets all the power for the current part
     */
    public void resetPower()
    {
        //Make all powers off
        powerS1 = false;
        negPowerS1 = false;
    }
    
     /**Checks to see if this object is connected to the other object at a 
     * specific side.
     * @param object    The other object
     * @param side      The side of the other object its checking
     * @return the side of this object that is connected to the other object 
     *         or 0 if it is not connected 
     */
    public ArrayList <Integer> isConnected(CircuitPart object)
    {
        ArrayList <Integer> list = new ArrayList <Integer>();
        //Looks through each name in the connections to check if it is
        //connected to the object
        for (CircuitPart temp: connectionsSide1)
            if (object.getName().equals(temp.getName()))
                list.add(1);
        return list;
    }
    
    /** Removes a connection to this object.
     * @param object    The other object
     * @param side      The side of this object
     */ 
    public void removeConnection(CircuitPart object, int side)
    {
        //Removes the object from the corresponding side
        connectionsSide1.remove(object);
    }
    
    /** Gets the actual circuit part name.
     * @return the circuit part name
     */
    public String getParent()
    {
        return null;
    }
    
    /** Accessor method to return the arraylist of connections for a side.
     * @param side      The side the method is working with
     * @return the arraylist of connections to that side
     */
    public ArrayList <CircuitPart> getConnections(int side)
    {
        //Returns the corresponding arraylist of connections
        return connectionsSide1;
    }
    
    /** Returns the number of sides for this part.
     * @return the number of sides
     */
    public int getSides()
    {
        //This part has 0 sides
        return 0;
    }
}
