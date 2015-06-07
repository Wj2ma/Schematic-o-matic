package Circuit;

/** Circuit Class
 * The Circuit class is where every object of the circuit package is put
 * together and manipulated. 
 * Date Created: 16/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */

import java.util.*;
import java.awt.Color;

public class Circuit extends Object
{
    //Array lists of each individual part
    //private ArrayList <String> parts;
    private ArrayList <ANDGate> andGates = new ArrayList <ANDGate>();
    private ArrayList <NORGate> norGates = new ArrayList <NORGate>();
    private ArrayList <NANDGate> nandGates = new ArrayList <NANDGate>();
    private ArrayList <ORGate> orGates = new ArrayList <ORGate>();
    private ArrayList <NOTGate> notGates = new ArrayList <NOTGate>();
    private ArrayList <XORGate> xorGates = new ArrayList <XORGate>();
    private ArrayList <FlipFlop> flipFlops = new ArrayList <FlipFlop>();
    private ArrayList <Transistor> transistors = new ArrayList <Transistor>();
    private ArrayList <Capacitor> capacitors = new ArrayList <Capacitor>();
    private ArrayList <Resistor> resistors = new ArrayList <Resistor>();
    private ArrayList <LED> leds = new ArrayList <LED>();
    private ArrayList <PosBattery> posBatteries = new ArrayList <PosBattery>();
    private ArrayList <NegBattery> negBatteries = new ArrayList <NegBattery>();
    private ArrayList <Diode> diodes = new ArrayList <Diode>();
    private ArrayList <Switch> switches = new ArrayList <Switch>();
    
    //Increments for each part to keep track of every single part
    private int andGateInc = 0;
    private int nandGateInc = 0;
    private int orGateInc = 0;
    private int norGateInc = 0;
    private int xorGateInc = 0;
    private int notGateInc = 0;
    private int flipFlopInc = 0;
    private int diodeInc = 0;
    private int ledInc = 0;
    private int capacitorInc = 0;
    private int resistorInc = 0;
    private int transistorInc = 0;
    private int switchInc = 0;
    private int posBatteryInc = 0;
    private int negBatteryInc = 0;
    private int pic24Inc = 0;
    private int hBridgeInc = 0;
    
    //Deleted increments so creating a new part will fill in for the deleted part
    //with this increment
    private ArrayList <Integer> deletedAndInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedNandInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedOrInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedNorInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedXorInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedNotInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedFlipInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedDiodeInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedLedInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedCapInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedResInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedTransInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedSwitchInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedPosInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedNegInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedPicInc = new ArrayList <Integer>();
    private ArrayList <Integer> deletedHInc = new ArrayList <Integer>();
    
    private boolean flag = true;            //For updating power
    private boolean shortCircuit = false;   //if there is a short circuit

    /** Constructor for the Circuit object.
     */
    public Circuit()
    {
        super();
    }
    
    
    /** Creates a new part to add to the circuit.
     * @param p      The name of the part that is being added
     * @param extras The value of any extra detail to some parts (eg. ressitor resistance)
     * 
     * @return The name of the part that was just created
     */ 
    public String newPart(String p, int extras)
    {
        if (p.equals("andgate"))
        {
            //If there was a deleted part, this AND gate will use that part's increment
            if (deletedAndInc.size()>0)
            {
                andGates.add(new ANDGate("andgate" + deletedAndInc.get(0)));
                deletedAndInc.remove(0); 
            }
            
            else
            {
                //Otherwise create an ANDGate named andgate with the andgateincrement number
                andGates.add(new ANDGate("andgate" + andGateInc++));
            }        
            
            return andGates.get(andGates.size()-1).getName();
        }
        
        else if (p.equals("nandgate"))
        {
            if (deletedNandInc.size()>0)
            {
                nandGates.add(new NANDGate("nandgate" + deletedNandInc.get(0)));
                deletedNandInc.remove(0); 
            }
            
            else
            {
                nandGates.add(new NANDGate("nandgate" + nandGateInc++));
            }        
            
            return nandGates.get(nandGates.size()-1).getName();
        }
        
        else if (p.equals("norgate"))
        {
            if (deletedNorInc.size()>0)
            {
                norGates.add(new NORGate("norgate" + deletedNorInc.get(0)));
                deletedNorInc.remove(0); 
            }
            
            else
            {
                norGates.add(new NORGate("norgate" + norGateInc++));
            }        
            
            return norGates.get(norGates.size()-1).getName();
        }
        
        else if (p.equals("notgate"))
        {
            if (deletedNotInc.size()>0)
            {
                notGates.add(new NOTGate("notgate" + deletedNotInc.get(0)));
                deletedNotInc.remove(0); 
            }
            
            else
            {
                notGates.add(new NOTGate("notgate" + notGateInc++));
            }        
            
            return notGates.get(notGates.size()-1).getName();
        }
        
        else if (p.equals("orgate"))
        {
            if (deletedOrInc.size()>0)
            {
                orGates.add(new ORGate("orgate" + deletedOrInc.get(0)));
                deletedOrInc.remove(0); 
            }
            
            else
            {
                orGates.add(new ORGate("orgate" + orGateInc++));
            }        
            
            return orGates.get(orGates.size()-1).getName();
        }
        
        else if (p.equals("xorgate")) 
        {
            if (deletedXorInc.size()>0)
            {
                xorGates.add(new XORGate("xorgate" + deletedXorInc.get(0)));
                deletedXorInc.remove(0); 
            }
            
            else
            {
                xorGates.add(new XORGate("xorgate" + xorGateInc++));
            }        
            
            return xorGates.get(xorGates.size()-1).getName();
        }
        
        else if (p.equals("flipflop")) 
        {
            if (deletedFlipInc.size()>0)
            {
                flipFlops.add(new FlipFlop("flipflop" + deletedFlipInc.get(0)));
                deletedFlipInc.remove(0); 
            }
            
            else
            {
                flipFlops.add(new FlipFlop("flipflop" + flipFlopInc++));
            }        
            
            return flipFlops.get(flipFlops.size()-1).getName();
        }
        
        else if (p.equals("diode")) 
        {     
            if (deletedDiodeInc.size()>0)
            {
                diodes.add(new Diode("diode" + deletedDiodeInc.get(0)));
                deletedDiodeInc.remove(0); 
            }
            
            else
            {
                diodes.add(new Diode("diode" + diodeInc++));
            }        
            
            return diodes.get(diodes.size()-1).getName();
        }
        
        else if (p.equals("switch")) 
        {
            if (deletedSwitchInc.size()>0)
            {
                switches.add(new Switch("switch" + deletedSwitchInc.get(0), extras));
                deletedSwitchInc.remove(0); 
            }
            
            else
            {
                switches.add(new Switch("switch" + switchInc++, extras));
            }        
            
            return switches.get(switches.size()-1).getName();
        }
        
        else if (p.equals("negbattery")) 
        {
            if (deletedNegInc.size()>0)
            {
                negBatteries.add(new NegBattery("negbattery" + deletedNegInc.get(0)));
                deletedNegInc.remove(0); 
            }
            
            else
            {
                negBatteries.add(new NegBattery("negbattery" + negBatteryInc++));
            }        
            
            return negBatteries.get(negBatteries.size()-1).getName();
        }
        
        else if (p.equals("capacitor")) 
        {
            if (deletedCapInc.size()>0)
            {
                capacitors.add(new Capacitor("capacitor" + deletedCapInc.get(0), extras));
                deletedCapInc.remove(0); 
            }
            
            else
            {
                capacitors.add(new Capacitor("capacitor" + capacitorInc++, extras));
            }        
            
            return capacitors.get(capacitors.size()-1).getName();
        }
        
        else if (p.equals("resistor")) 
        {
            if (deletedResInc.size()>0)
            {
                resistors.add(new Resistor("resistor" + deletedResInc.get(0), extras));
                deletedResInc.remove(0); 
            }
            
            else
            {
                resistors.add(new Resistor("resistor" + resistorInc++, extras));
            }        
            
            return resistors.get(resistors.size()-1).getName();
        }
        
        else if (p.equals("plusbattery")) 
        {
            if (deletedPosInc.size()>0)
            {
                posBatteries.add(new PosBattery("plusbattery" + deletedPosInc.get(0), extras));
                deletedPosInc.remove(0); 
            }
            
            else
            {
                posBatteries.add(new PosBattery("plusbattery" + posBatteryInc++, extras));
            }        
            
            return posBatteries.get(posBatteries.size()-1).getName();
        }
        
        else if (p.equals("led")) 
        {
            if (deletedLedInc.size()>0)
            {
                leds.add(new LED("led" + deletedLedInc.get(0), extras));
                deletedLedInc.remove(0); 
            }
            
            else
            {
                leds.add(new LED("led" + ledInc++, extras));
            }        
            
            return leds.get(leds.size()-1).getName();
        }
        
        else if (p.equals("transistor")) 
        {
            if (deletedTransInc.size()>0)
            {
                transistors.add(new Transistor("transistor" + deletedTransInc.get(0), extras));
                deletedTransInc.remove(0); 
            }
            
            else
            {
                transistors.add(new Transistor("transistor" + transistorInc++, extras));
            }        
            
            return transistors.get(transistors.size()-1).getName();
        }
        
        else if (p.equals("pic24"))
        {
            //pic24 does not have any function so no need to create a new part
            String name;
            if (deletedPicInc.size()>0)
            {
                name = "pic24" + deletedPicInc.get(0);
                deletedPicInc.remove(0);
            }
            
            else
            {
                name = "pic24" + pic24Inc++;
            }        
            
            return name;
        }
        
        else if (p.equals("hbridge"))
        {
            //hbridge does not have any funciton so no need to create a new part
            String name;
            if (deletedHInc.size()>0)
            {
                name = "hbridge" + deletedHInc.get(0);
                deletedHInc.remove(0);
            }
            
            else
            {
                name = "hbridge" + hBridgeInc++;
            }               
            return name;
        }
        
        return "";
    }
    
    
    /** Connects 2 sides of 2 objects together.
     * @param object1       The first object
     * @param side1         The first object's side
     * @param object2       The second object
     * @param side2         The second object's side
     */
    public void setConnection(CircuitPart object1, int side1, CircuitPart object2, int side2)
    {
        //prevents an object from connecting itself
        if (object1.equals(object2))
            return;
        
        object1.setConnected(object2, side1);
        object2.setConnected(object1,side2);
    } 
    
    /** Connects one object to another object (used for loading).
     * @param object1   The first object
     * @param side1     The first object's side
     * @param object2   The second object
     */
    public void connect(CircuitPart object1, int side1, CircuitPart object2)
    {
        object1.setConnected(object2, side1);
    }
    
    /** Removes a connection of 2 sides of 2 objects.
     * @param object1       The first object
     * @param side1         The first object's side
     * @param object2       The second object
     * @param side2         The second object's side
     */ 
    public void removeConnection(CircuitPart object1, int side1, CircuitPart object2, int side2)
    {
        object1.removeConnection(object2, side1);
        object2.removeConnection(object1, side2);
    }
    
    /** Removes a whole part from the circuit.
     * @param object    The object that is being removed
     */ 
    public void removePart(CircuitPart object)
    {
        if (object.getParent().equals("andgate")) 
        {
            //Adds the increment to the deleted increments list
            deletedAndInc.add(Integer.parseInt(object.getName().substring(7))); //cuts off the word "andgate" leaving the increment left
            //removes the part
            andGates.remove(object);
        }
        
        else if (object.getParent().equals("norgate"))
        {
            deletedNorInc.add(Integer.parseInt(object.getName().substring(7)));
            norGates.remove(object);
        }
        
        else if (object.getParent().equals("nandgate")) 
        {
            deletedNandInc.add(Integer.parseInt(object.getName().substring(8)));
            nandGates.remove(object);
        }
        
        else if (object.getParent().equals("orgate"))
        {
            deletedOrInc.add(Integer.parseInt(object.getName().substring(6)));
            orGates.remove(object);
        }
        
        else if (object.getParent().equals("notgate"))
        {
            deletedNotInc.add(Integer.parseInt(object.getName().substring(7)));
            notGates.remove(object);
        }
        
        else if (object.getParent().equals("xorgate"))
        {
            deletedXorInc.add(Integer.parseInt(object.getName().substring(7)));
            xorGates.remove(object);
        }
        
        else if (object.getParent().equals("flipflop")) 
        {
            deletedFlipInc.add(Integer.parseInt(object.getName().substring(8)));
            flipFlops.remove(object);
        }
        
        else if (object.getParent().equals("transistor")) 
        {
            deletedTransInc.add(Integer.parseInt(object.getName().substring(10)));
            transistors.remove(object);
        }
        
        else if (object.getParent().equals("capacitor"))
        {
            deletedCapInc.add(Integer.parseInt(object.getName().substring(9)));
            capacitors.remove(object);
        }
        
        else if (object.getParent().equals("resistor")) 
        {
            deletedResInc.add(Integer.parseInt(object.getName().substring(8)));
            resistors.remove(object);
        }
        
        else if (object.getParent().equals("led")) 
        {
            deletedLedInc.add(Integer.parseInt(object.getName().substring(3)));
            leds.remove(object);
        }
        
        else if (object.getParent().equals("plusbattery"))
        {
            deletedPosInc.add(Integer.parseInt(object.getName().substring(11)));
            posBatteries.remove(object);
        }
        
        else if (object.getParent().equals("negbattery"))
        {
            deletedNegInc.add(Integer.parseInt(object.getName().substring(10)));
            negBatteries.remove(object);
        }
        
        else if (object.getParent().equals("diode")) 
        {
            deletedDiodeInc.add(Integer.parseInt(object.getName().substring(5)));
            diodes.remove(object);
        }
        
        else if (object.getParent().equals("switch"))
        {
            deletedSwitchInc.add(Integer.parseInt(object.getName().substring(6)));
            switches.remove(object);
        }    
        
        else if (object.getParent().equals("pic24")) 
        {
            //Only sets the deleted increment because there is no function of pic
            deletedPicInc.add(Integer.parseInt(object.getName().substring(5)));
        }   
        
        else if (object.getParent().equals("hbridge")) 
        {
            //Only sets the deleted increment because there is no function of h bridge
            deletedHInc.add(Integer.parseInt(object.getName().substring(6)));
        }    
    }
    
    /** Updates the positive and negative power of each part for the circuit.
     */
    public void updatePower()
    {
        resetAllPower();                       //Resets all power first
        updatePower(true, false);              //Updates all positive power excluding the flipflop
        for (FlipFlop p: flipFlops)            //Checks if there has been a change in the hold for flip flops
            if (p.getChecked() && p.getHold()) //If a hold has been turned off and was on before
                p.setHold(false);              //Turn off hold    

        updatePower(false, true);  //updates all the negative power 
        updatePower(true, true);   //updates all the positive power including the flipflop
    }
    
    /**Resets all of the power for each part
     */
    private void resetAllPower()
    {
        //Goes through each list of parts and calls their resetPower() method
        for (ANDGate p: andGates)
            p.resetPower();
        for (NORGate p: norGates)
            p.resetPower();
        for (NANDGate p: nandGates)
            p.resetPower();
        for (ORGate p: orGates)
            p.resetPower();
        for (NOTGate p: notGates)
            p.resetPower();
        for (XORGate p: xorGates)
            p.resetPower();
        for (Transistor p: transistors)
            p.resetPower();
        for (Capacitor p: capacitors)
            p.resetPower();
        for (Resistor p: resistors)
            p.resetPower();
        for (LED p: leds)
            p.resetPower();
        for (Diode p: diodes)
            p.resetPower();
        for (Switch p: switches)
            p.resetPower();
        for (FlipFlop p: flipFlops)
        {
            //flipflop resets the checkedpower as well
            p.resetPower();
            p.resetChecked();
        }
        
        shortCircuit = false;
    }   
    
    /** Updates the power of each part of the circuit
     * @param p     Update either the positive power or negative power
     * @param flip  Excludes updating the flip flop if this is false
     */ 
    private void updatePower(boolean p, boolean flip)
    {
        flag = true;
        //Updates the positive or negative batteries first
        if (p)
            updatePosBattery(posBatteries);
        else
            updateNegBattery(negBatteries);
        
        while (flag)
        {
            //keeps updating each part while a change has occured
            flag = false;
            updateSwitch(switches, p);
            updateTransistor(transistors, p);
            updateANDGate(andGates, p);
            updateNANDGate(nandGates, p);
            updateORGate(orGates, p);
            updateNORGate(norGates, p);
            updateXORGate(xorGates, p);
            updateNOTGate(notGates, p);
            updateResistor(resistors, p);
            updateCapacitor(capacitors, p);           
            updateDiode(diodes, p);
            updateLED(leds, p);
            if (flip)
                updateFlipFlop(flipFlops, p);          
        }
    }
   
    /**Sets the powers of the connecting pins to a part that has power
     * @param part      The part that has power
     * @param side      The side of the part that has power
     * @param type      The type of power (positive or negative)
     */
    private void setPowers(CircuitPart part, int side, boolean type)
    {
        boolean[] checks = new boolean[2];
        //Goes through every single part that is connected to this side
        for (CircuitPart p: part.getConnections(side))
        {
            if (p.equals(part));
            else
            {
                ArrayList <Integer> connections = p.isConnected(part);
                //Set the power of each connection side to true
                if (type)                                       //Positive powers
                    //for each side that is connected, set the power of that side to true
                    for (Integer i: connections)
                        checks = p.setPower(true, i); 
                else                                            //Negative powers
                    //for each side that is connected, set the negpower of that side to true
                    for (Integer i: connections)
                        checks = p.setNegPower(true, i); 

                if (checks[0])                  //If there is a short circuit
                    shortCircuit = true;
                if (checks[1])                  //If there has been a change in power
                    flag = true;
            }
        }            
    }
    
    /**Updates all the powers of the connections to the positive batteries
     * @param list      The list of positive batteries
     */
    private void updatePosBattery(ArrayList <PosBattery> list)
    {
        //Cycles through each battery
        for (CircuitPart part: list)
        {           
            //Cycles through each connection to the battery
            for (CircuitPart p: part.getConnections(1))
            {
                //Sets all of the connections to have positive power
                ArrayList <Integer> connections = p.isConnected(part);
                for (Integer i: connections)
                    p.setPower(true, i);  
            }
        }
    }
    
    
    /**Updates all the powers of the connections to the negative batteries
     * @param list      The list of negative batteries
     */
    private void updateNegBattery(ArrayList <NegBattery> list)
    {
        //Cycles through each battery
        for (CircuitPart part: list)
        {         
            //Cycles through each connection to the battery
            for (CircuitPart p: part.getConnections(1))
            {
                //Sets all of the connections to have positive power
                ArrayList <Integer> connections = p.isConnected(part);
                for (Integer i: connections)
                    p.setNegPower(true, i);  
            }              
        }
    }
    
    /** Updates all the powers of the connections to the AND Gates
     * @param list      The list of AND gates
     * @param type      The type of power to update
     */
    private void updateANDGate(ArrayList <ANDGate> list, boolean type)
    {
        for (CircuitPart part: list)
        {
            if (type)
                //If both side 1 and 2 are positive, all connections to side 3
                //will get positive power
                if (part.isPower(1) && part.isPower(2))
                    setPowers(part, 3, type);
                else;
            else
                //If side 3 has negative power, all connections to side 1 and 2
                //will get negative power
                if (part.isNegPower(3))
                {
                    setPowers(part, 1, type);
                    setPowers(part, 2, type);
                }
        }
    }
    
    /** Updates all the powers of the connections to the NAND Gates
     * @param list      The list of NAND gates
     * @param type      The type of power to update
     */
    private void updateNANDGate(ArrayList <NANDGate> list, boolean type)
    {
        for (CircuitPart part: list)
        {
            if (type)
                //If sides 1 and 2 do not have positive power, all connections
                //to side 3 will get positive power
                if (!(part.isPower(1) && part.isPower(2)))
                    setPowers(part, 3, type);
                else;
            else
                //If side 3 has negative power, all connections to side 1 and 2
                //will get negative power
                if (part.isNegPower(3))
                {
                    setPowers(part, 1, type);
                    setPowers(part, 2, type);
                }
        }
    }
    
    /** Updates all the powers of the connections to the OR Gates
     * @param list      The list of OR gates
     * @param type      The type of power to update
     */
    private void updateORGate(ArrayList <ORGate> list, boolean type)
    {
        for (CircuitPart part: list)
        {
            if (type)
                //If side 1 or 2 is positive, all connections to side 3
                //will get positive power
                if (part.isPower(1) || part.isPower(2))
                    setPowers(part, 3, type);
                else;
            else
                //If side 3 has negative power, all connections to side 1 and 2
                //will get negative power
                if (part.isNegPower(3))
                {
                    setPowers(part, 1, type);
                    setPowers(part, 2, type); 
                }
        }
    }
    
    /** Updates all the powers of the connections to the NOR Gates
     * @param list      The list of NOR gates
     * @param type      The type of power to update
     */
    private void updateNORGate(ArrayList <NORGate> list, boolean type)
    {
        for (CircuitPart part: list)
        {
            if (type)
                //If side 1 and side 2 are not positive, all connections to side 3
                //will get positive power
                if (!(part.isPower(1) || part.isPower(2)))
                    setPowers(part, 3, type);
                else;
            else
                //If side 3 has negative power, all connections to side 1 and 2
                //will get negative power
                if (part.isNegPower(3))
                {
                    setPowers(part, 1, type);
                    setPowers(part, 2, type); 
                }
        }
    }
    
    /** Updates all the powers of the connections to the XOR Gates
     * @param list      The list of XOR gates
     * @param type      The type of power to update
     */
    private void updateXORGate(ArrayList <XORGate> list, boolean type)
    {
        for (CircuitPart part: list)
        {
            if (type)
                //If both side 1 or 2 are positive but not both, all connections
                //to side 3 will get positive power
                if (part.isPower(1) ^ part.isPower(2))
                    setPowers(part, 3, type);
                else;
            else
                //If side 3 has negative power, all connections to side 1 and 2
                //will get negative power
                if (part.isNegPower(3))
                {
                    setPowers(part, 1, type);
                    setPowers(part, 2, type); 
                }
        }
    }
    
    /** Updates all the powers of the connections to the NOT Gates
     * @param list      The list of NOT gates
     * @param type      The type of power to update
     */
    private void updateNOTGate(ArrayList <NOTGate> list, boolean type)
    {
        for (CircuitPart part: list)
        {
            if (type)
                //If side 1 is not positive, all connections to side 2
                //will get positive power
                if (!part.isPower(1))
                    setPowers(part, 2, type);
                else;
            else
                //If side 3 has negative power, all connections to side 1 and 2
                //will get negative power
                if (part.isNegPower(2))
                {
                    setPowers(part, 1, type);
                }
        }
    }
    
    /** Updates all the powers of the connections to the LEDs
     * @param list      The list of LEDs
     * @param type      The type of power to update
     */
    private void updateLED(ArrayList <LED> list, boolean type)
    {
        for (LED part: list)
        {
            if (type)
                //If side 1 is positive, all connections to side 2 will get
                //positive power
                if (part.isPower(1))
                    setPowers(part, 2, type);
                else;
            else
                //If side 2 has negative power, all connections to side 1
                //will get negative power
                if (part.isNegPower(2))
                {
                    setPowers(part, 1, type);
                }
        }
    }
    
    /** Updates all the powers of the connections to the diodes
     * @param list      The list of diodes
     * @param type      The type of power to update
     */
    private void updateDiode(ArrayList <Diode> list, boolean type)
    {
        for (CircuitPart part: list)
        {
            if (type)
                //If side 1 is positive, all connections to side 2 will get
                //positive power
                if (part.isPower(1))
                    setPowers(part, 2, type);
                else;
            else
                //If side 2 has negative power, all connections to side 1
                //will get negative power
                if (part.isNegPower(2))
                {
                    setPowers(part, 1, type);
                }
        }
    }
    
    /** Updates all the powers of the connections to the switches
     * @param list      The list of switches
     * @param type      The type of power to update
     */
    private void updateSwitch(ArrayList <Switch> list, boolean type)
    {
        for (Switch part: list)
        {
            //If this is a positive switch, then only update all the connections
            //on side 2 if the part is connected
            if (part.isPositiveSwitch())
            {
                if (type)
                {
                    if (part.isConnect())
                        setPowers(part, 1, type);
                    else;
                }
            }
            
            else
            {
                if (type)
                {
                    //If side 1 is positive and the switch is on, all connections
                    //to side 2 will get positive power
                    if (part.isPower(1) && part.isConnect())
                        setPowers(part, 2, type);
                    //If side 2 is positive, all connections to side 2 will get positive power
                    if (part.isPower(2))
                        setPowers(part, 1, type);
                }
                
                else
                {
                    //If side 2 has negative power and the switch is on, all 
                    //connections to side 1 will get negative power
                    if (part.isNegPower(2) && part.isConnect())
                        setPowers(part, 1, type);
                    //If side 1 is negative, all connections to side 2 will get negative power
                    if (part.isNegPower(1))
                        setPowers(part, 2, type);
                }
            }
        }
    }
    
    /** Updates all the powers of the connections to the transistors
     * @param list      The list of transistors
     * @param type      The type of power to update
     */
    private void updateTransistor(ArrayList <Transistor> list, boolean type)
    {
        for (Transistor part: list)
        {
            if (type)
                //If side 3 is positive and the switch is on, all connections
                //to side 1 will get positive power
                if (part.isPower(3) && part.isConnect())
                    setPowers(part, 1, type);
                else;
            else
                //If side 1 has negative power and the switch is on, all 
                //connections to side 3 will get negative power
                if (part.isNegPower(1) && part.isConnect())
                {
                    setPowers(part, 3, type);
                }
        }
    }
    
    /** Updates all the powers of the connections to the capacitors
     * @param list      The list of capacitors
     * @param type      The type of power to update
     */
    private void updateCapacitor(ArrayList <Capacitor> list, boolean type)
    {
        for (Capacitor part: list)
        {
            if (type)
            {
                //If side 1 is positive, all connections to side 2 will get
                //positive power
                if (part.isPower(1))
                    setPowers(part, 2, type);
                //If side 2 is positive, all connections to side 2 will get positive power
                if (part.isPower(2))
                    setPowers(part, 1, type);       
            }
            else
            {
                //If side 2 has negative power, all connections to side 1
                //will get negative power
                if (part.isNegPower(2))
                    setPowers(part, 1, type);
                //If side 1 is negative, all connections to side 2 will get negative power
                if (part.isNegPower(1))
                    setPowers(part, 2, type);
            }
        }
    }
    
    /** Updates all the powers of the connections to the resistors
     * @param list      The list of resistors
     * @param type      The type of power to update
     */
    private void updateResistor(ArrayList <Resistor> list, boolean type)
    {
        for (Resistor part: list)
        {
            //If updating positive power
            if (type)
            {
                //If side 1 is positive, all connections to side 2 will get 
                //positive power
                if (part.isPower(1))
                    setPowers(part, 2, type);
                //If side 2 is positive, all connections to side 2 will get positive power
                if (part.isPower(2))
                    setPowers(part, 1, type);
                else;
            }
            else
            {
                //If side 2 has negative power, all connections to side 1
                //will get negative power
                if (part.isNegPower(2))
                    setPowers(part, 1, type);
                
                //If side 1 has negative power, all connections to side 2 will get negative power
                if (part.isNegPower(1))
                    setPowers(part, 2, type);
            }
        }
    }
    
    /** Updates all the powers of the connections to the flip flops
     * @param list      The list of flip flops
     * @param type      The type of power to update
     */
    private void updateFlipFlop(ArrayList <FlipFlop> list, boolean type)
    {
        for (FlipFlop part: list)
        {
            //If updating positive power
            if (type)
                //If side 1 is positive and hold is off, all connections to side
                //2 will get positive power
                if (part.isPower(1) && !part.getHold())
                    setPowers(part, 2, type);
                //Or if side 1 is negative and hold is off, all connections to
                //side 3 will get positive power
                else if (!part.isPower(1) && !part.getHold())
                    setPowers(part, 3, type);
                //Or if hold is on, all connections to the stored value will
                //get positive power
                else if (part.getHold())
                    if (part.getStore())
                        setPowers(part, 2, type);
                    else
                        setPowers(part, 3, type);
                else;
            else
                //If side 3 or side 2 has negative power and hold is off, all
                //connections to side 1 will get negative power
                if ((part.isNegPower(3) || part.isNegPower(2)) && !part.getHold())
                    setPowers(part, 1, type);
            
            //If side1's power is off and hold is off, set the stored value to false
            if (part.getPowerS1() == false && !part.getHold())
                part.setStore(false);
        }
    }
    
    /** Accessor method to get the list of AND gates
     * @return arraylist of AND gates
     */
    public ArrayList <ANDGate> getANDGates()
    {
        return andGates;
    }
    
    /** Accessor method to get the list of NAND gates
     * @return arraylist of NAND gates
     */
    public ArrayList <NANDGate> getNANDGates()
    {
        return nandGates;
    }
    
    /** Accessor method to get the list of OR gates
     * @return arraylist of OR gates
     */
    public ArrayList <ORGate> getORGates()
    {
        return orGates;
    }
    
    /** Accessor method to get the list of NOR gates
     * @return arraylist of NOR gates
     */
    public ArrayList <NORGate> getNORGates()
    {
        return norGates;
    }
    
    /** Accessor method to get the list of XOR gates
     * @return arraylist of XOR gates
     */
    public ArrayList <XORGate> getXORGates()
    {
        return xorGates;
    }
    
    /** Accessor method to get the list of NOT gates
     * @return arraylist of NOT gates
     */
    public ArrayList <NOTGate> getNOTGates()
    {
        return notGates;
    }
    
    /** Accessor method to get the list of flip flops
     * @return arraylist of flip flops
     */
    public ArrayList <FlipFlop> getFlipFlops()
    {
        return flipFlops;
    }
    
    /** Accessor method to get the list of LEDs
     * @return arraylist of LEDs
     */
    public ArrayList <LED> getLEDs()
    {
        return leds;
    }
    
    /** Accessor method to get the list of diodes
     * @return arraylist of diodes
     */
    public ArrayList <Diode> getDiodes()
    {
        return diodes;
    }
    
    /** Accessor method to get the list of capacitors
     * @return arraylist of capacitors
     */
    public ArrayList <Capacitor> getCapacitors()
    {
        return capacitors;
    }
    
    /** Accessor method to get the list of resistors
     * @return arraylist of resistors
     */
    public ArrayList <Resistor> getResistors()
    {
        return resistors;
    }
    
    /** Accessor method to get the list of transistors
     * @return arraylist of transistors
     */
    public ArrayList <Transistor> getTransistors()
    {
        return transistors;
    }
    
    /** Accessor method to get the list of switches
     * @return arraylist of switches
     */
    public ArrayList <Switch> getSwitches()
    {
        return switches;
    }
    
    /** Accessor method to get the list of positive batteries
     * @return arraylist of positive batteries
     */
    public ArrayList <PosBattery> getPosBatteries()
    {
        return posBatteries;
    }
    
    /** Accessor method to get the list of negative batteries
     * @return arraylist of negative batteries
     */
    public ArrayList <NegBattery> getNegBatteries()
    {
        return negBatteries;
    }
    
    /** Accessor method to check if there is a short circuit
     * @return shortCircuit
     */
    public boolean isShortCircuit()
    {
        return shortCircuit ;
    }
    
    /** Clears every single part off the circuit.
     */
    public void clearAll()
    {
        //clear every single arraylist of parts
        andGates.clear();
        norGates.clear();
        nandGates.clear();
        orGates.clear();
        notGates.clear();
        xorGates.clear();
        flipFlops.clear();
        transistors.clear();
        capacitors.clear();
        resistors.clear();
        leds.clear();
        posBatteries.clear();
        negBatteries.clear();
        diodes.clear();
        switches.clear();
        
        //Resets all increments for naming
        andGateInc = 0;
        nandGateInc = 0;
        orGateInc = 0;
        norGateInc = 0;
        xorGateInc = 0;
        notGateInc = 0;
        flipFlopInc = 0;
        diodeInc = 0;
        ledInc = 0;
        capacitorInc = 0;
        resistorInc = 0;
        transistorInc = 0;
        switchInc = 0;
        posBatteryInc = 0;
        negBatteryInc = 0;
    }
    
    /** Fills in any empty increments that have been deleted and not replaced
     * For the save/load function
     */ 
    public String[] fill()
    {
        //While there are still deleted increments not yet used
        //create an ANDGate that will be used just to take up the increment
        String []n = {"",""};
        
        if (deletedAndInc.size()>0)
        {
            //Creates a new andgate to take up the deleted increment
            andGates.add(new ANDGate("andgate" + deletedAndInc.get(0)));
            //n[0] is the name with the increment
            n[0] = "andgate" + deletedAndInc.get(0);
            //n[1] is the parent value
            n[1] = "andgate";
            //remove the deletedincrement from the list
            deletedAndInc.remove(0); 
        }
        
        //refer to the and gate section
        if (deletedNandInc.size()>0)
        {
            nandGates.add(new NANDGate("nandgate" + deletedNandInc.get(0)));
            n[0] = "nandgate" + deletedNandInc.get(0);
            n[1] = "nandgate";
            deletedNandInc.remove(0); 
        }

 
        if (deletedNorInc.size()>0)
        {
            norGates.add(new NORGate("norgate" + deletedNorInc.get(0)));
            n[0] = "norgate" + deletedNorInc.get(0);
            n[1] = "norgate";
            deletedNorInc.remove(0); 
        }

        if (deletedNotInc.size()>0)
        {
            notGates.add(new NOTGate("notgate" + deletedNotInc.get(0)));
            n[0] = "notgate" + deletedNotInc.get(0);
            n[1] = "notgate";
            deletedNotInc.remove(0); 
        }
            
        if (deletedOrInc.size()>0)
        {
            orGates.add(new ORGate("orgate" + deletedOrInc.get(0)));
            n[0] = "orgate" + deletedOrInc.get(0);
            n[1] = "orgate";
            deletedOrInc.remove(0); 
        }
            
        if (deletedXorInc.size()>0)
        {
            xorGates.add(new XORGate("xorgate" + deletedXorInc.get(0)));
            n[0] = "xorgate" + deletedXorInc.get(0);
            n[1] = "xorgate";
            deletedXorInc.remove(0); 
        }

        if (deletedFlipInc.size()>0)
        {
            flipFlops.add(new FlipFlop("flipflop" + deletedFlipInc.get(0)));
            n[0] = "flipflop" + deletedFlipInc.get(0);
            n[1] = "flipflop";
            deletedFlipInc.remove(0); 
        }
            
        if (deletedDiodeInc.size()>0)
        {
            diodes.add(new Diode("diode" + deletedDiodeInc.get(0)));
            n[0] = "diode" + deletedDiodeInc.get(0);
            n[1] = "diode";
            deletedDiodeInc.remove(0); 
        }
             
        if (deletedSwitchInc.size()>0)
        {
            switches.add(new Switch("switch" + deletedSwitchInc.get(0), 0));
            n[0] = "switch" + deletedSwitchInc.get(0);
            n[1] = "switch";
            deletedSwitchInc.remove(0); 
        }

        if (deletedNegInc.size()>0)
        {
            negBatteries.add(new NegBattery("negbattery" + deletedNegInc.get(0)));
            n[0] = "negbattery" + deletedNegInc.get(0);
            n[1] = "negbattery";
            deletedNegInc.remove(0); 
        }
            
        if (deletedCapInc.size()>0)
        {
            capacitors.add(new Capacitor("capacitor" + deletedCapInc.get(0), 0));
            n[0] = "capacitor" + deletedCapInc.get(0);
            n[1] = "capacitor";
            deletedCapInc.remove(0); 
        }

        if (deletedResInc.size()>0)
        {
            resistors.add(new Resistor("resistor" + deletedResInc.get(0), 0));
            n[0] = "resistor" + deletedResInc.get(0);
            n[1] = "resistor";
            deletedResInc.remove(0); 
        }  

        if (deletedPosInc.size()>0)
        {
            posBatteries.add(new PosBattery("plusbattery" + deletedPosInc.get(0), 0));
            n[0] = "plusbattery" + deletedPosInc.get(0);
            n[1] = "plusbattery";
            deletedPosInc.remove(0); 
        }

        if (deletedLedInc.size()>0)
        {
            leds.add(new LED("led" + deletedLedInc.get(0), 0));
            n[0] = "led" + deletedLedInc.get(0);
            n[1] = "led";
            deletedLedInc.remove(0); 
        }

        if (deletedTransInc.size()>0)
        {
            transistors.add(new Transistor("transistor" + deletedTransInc.get(0), 0));
            n[0] = "transistor" + deletedTransInc.get(0);
            n[1] = "transistor";
            deletedTransInc.remove(0); 
        }
        
        return n;
    }
}
