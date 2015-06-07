package Schematicomatic;

/**SchematicomaticModel class
 * The model for the Schematic-o-matic program. Helps simulate the schematic
 * the user creates.
 * Date Created: 24/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */ 
import Circuit.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class SchematicomaticModel extends Object
{
    private Circuit circuit;    //The circuit that has the function of every single part
    
    private static JFileChooser chooser = new JFileChooser();
    private static PrintWriter chartPrinter;
    
    //The prices of each part
    private final static double ANDGATEPRICE = 1.0;
    private final static double NORGATEPRICE = 1.0;
    private final static double ORGATEPRICE = 1.0;
    private final static double NOTGATEPRICE = 1.0;
    private final static double XORGATEPRICE = 1.0;
    private final static double FLIPFLOPPRICE = 1.0;
    private final static double NPNPRICE  = 0.5;
    private final static double PNPPRICE  = 0.5;
    private final static double ONEMFPRICE = 0.5;
    private final static double TENMFPRICE = 0.5;
    private final static double ONEHUNDREDMFPRICE = 0.5;
    private final static double ONEKRESPRICE = 0.05;
    private final static double TENKRESPRICE = 0.05;
    private final static double FOURSEVENTYRESPRICE = 0.05;
    private final static double PLUSBATTERYPRICE = 2.0;
    private final static double GREENLEDPRICE = 0.25;
    private final static double REDLEDPRICE = 0.25;
    private final static double YELLOWLEDPRICE = 0.25;
    private final static double BLUELEDPRICE = 0.5;
    private final static double DIODEPRICE = 0.1;
    private final static double SWITCHPRICE = 1.5;
    private final static double HBRIDGEPRICE = 3.0;
    private final static double PIC24FV32KA302PRICE = 5.0;
    
    //The format each price should look like
    private DecimalFormat format = new DecimalFormat("$0.00");
    
    /**Creates a new circuit for the user to use.
     */ 
    public SchematicomaticModel()
    {
        super();
        circuit = new Circuit();
    }
    
    /** Creates a new part in the circuit.
     * @param part      The parent name of the part (Ex. andgate)
     * @param extra     The value of any additional info the part has (Ex. 6V battery)
     * @return          The name of the part that is created
     */ 
    public String createPart(String part, int extra)
    {
        return circuit.newPart(part, extra);
    }
    
    /** Sets a connection between 2 part sides
     * @param part1     The name of the first part
     * @param side1     The side of the first part
     * @param part2     The name of the second part
     * @param side2     The side of the second part
     */ 
    public void setConnection(String part1, int side1, String part2, int side2)
    {
        CircuitPart p1, p2;
        //Calls the findPart method to find the real part
        p1 = findPart(part1);
        p2 = findPart(part2);
        //Calls the circuit to connect the two parts together
        circuit.setConnection(p1,side1,p2,side2);
    }
    
    /** Removes all the connections of a particular part
     * @param part      The part that will get removed from all connections
     */
    public void removeAllConnections(String part)
    {
        CircuitPart p1;
        //Calls the findPart method to find the real part
        p1 = findPart(part);
        //Tries to go through each of the part's connections and making the connected part
        //remove its connection to this part
        try{
            for (int counter=1; counter<=p1.getSides(); counter++)
            {                   
                for (CircuitPart p: p1.getConnections(counter))
                {    
                    //if the part equals this part, do nothing
                    if (p.equals(p1));
                    
                    else
                        //While the other part is still connected to this part,
                        //remove a connection to this part
                        while (p.isConnected(p1).size()>0)
                        {
                            int side = p.isConnected(p1).get(0);
                            p.removeConnection(p1, side);   
                        }
                }
            }     
        } catch (NullPointerException ex){} //if there is no part selected, do nothing
    }
    
    /** Deletes a part from the circuit
     * @param part      the part that will be deleted
     */ 
    public void deletePart(String part)
    {
        //calls circuit to remove the part after finding the real part first
        circuit.removePart(findPart(part));
    }
    
    /** Finds the real CircuitPart from getting the name of the part
     * @param part      the name of the CircuitPart
     * @return the circuitpart that has that name
     */ 
    private CircuitPart findPart(String part)
    {
        //Goes through each arraylist of parts to see if the name equals
        //this circuitpart. If it does, return that circuitpart
        for (ANDGate gate: circuit.getANDGates())
            if (gate.getName().equals(part))
                return (CircuitPart) gate;
        for (NANDGate gate: circuit.getNANDGates())
            if (gate.getName().equals(part))
                return (CircuitPart) gate;
        for (ORGate gate: circuit.getORGates())
            if (gate.getName().equals(part))
                return (CircuitPart) gate;
        for (NORGate gate: circuit.getNORGates())
            if (gate.getName().equals(part))
                return (CircuitPart) gate;
        for (XORGate gate: circuit.getXORGates())
            if (gate.getName().equals(part))
                return (CircuitPart) gate;
        for (NOTGate gate: circuit.getNOTGates())
            if (gate.getName().equals(part))
                return (CircuitPart) gate;
        for (Capacitor cap: circuit.getCapacitors())
            if (cap.getName().equals(part))
                return (CircuitPart) cap;
        for (Diode diode: circuit.getDiodes())
            if (diode.getName().equals(part))
                return (CircuitPart) diode;
        for (FlipFlop flipFlop: circuit.getFlipFlops())
            if (flipFlop.getName().equals(part))
                return (CircuitPart) flipFlop;
        for (LED led: circuit.getLEDs())
            if (led.getName().equals(part))
                return (CircuitPart) led;
        for (NegBattery negBat: circuit.getNegBatteries())
            if (negBat.getName().equals(part))
                return (CircuitPart) negBat;
        for (PosBattery posBat: circuit.getPosBatteries())
            if (posBat.getName().equals(part))
                return (CircuitPart) posBat;
        for (Resistor resistor: circuit.getResistors())
            if (resistor.getName().equals(part))
                return (CircuitPart) resistor;
        for (Switch switch0: circuit.getSwitches())
            if (switch0.getName().equals(part))
                return (CircuitPart) switch0;
        for (Transistor trans: circuit.getTransistors())
            if (trans.getName().equals(part))
                return (CircuitPart) trans;
        return null;
    }
    
   /**
     * Exports a bill of all the components required for the schematic diagram
     * @param numHBridge number of HBridge components in the schematic
     * @param numPic number of Pic components in the schematic 
     */
    public void exportToChart(int numHBridge, int numPic){
    
        //Opens dialog box to get the user's name
        String userName = (String) JOptionPane.showInputDialog(null, "Please Inupt disired name:"
                , "User Name Input");
        
        
        //Opens the save dialog
        if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            try
              {
                chartPrinter = new PrintWriter(chooser.getSelectedFile()); //Gives the printwriter the file
              } catch (FileNotFoundException ex) //Should not be needed
              {
                System.out.println(ex.getMessage());
              }
        }
        
        //Prints chart to a text file
        chartPrinter.println("\t\t\tBill of Materials");
        chartPrinter.println();
        chartPrinter.println("Username - " + userName);
        chartPrinter.println("Date: " + new SimpleDateFormat("yyyy/MM/dd_HH:mm").format(Calendar.getInstance().getTime()));
        chartPrinter.println();
        chartPrinter.println("Name:\t\t\t" + "Quantity:\t" + " Cost/Unit:\t" + "Total Cost:");
        chartPrinter.println("-------------------------------------------------------------------------------");
        chartPrinter.println("7408 (AND)\t\t\t" + getNumANDGates()+ "\t\t$1.00"  +"\t\t" +priceANDGates((int)getNumANDGates()));
        chartPrinter.println("7432 (OR)\t\t\t" + getNumORGates()+ "\t\t$1.00"  + "\t\t" + priceORGates((int)getNumORGates()));
        chartPrinter.println("7408 (NOR)\t\t\t" + getNumNORGates()+ "\t\t$1.00"  + "\t\t" + priceNORGates((int)getNumNORGates()));
        chartPrinter.println("7408 (NOT)\t\t\t" + getNumNOTGates()+ "\t\t$1.00"  + "\t\t" + priceNOTGates((int)getNumNOTGates()));
        chartPrinter.println("7408 (XOR)\t\t\t" + getNumXORGates()+ "\t\t$1.00"  + "\t\t" + priceXORGates((int)getNumXORGates()));
        chartPrinter.println("7474 (D FF)\t\t\t" + getNumFlipFlops()+ "\t\t$1.00" + "\t\t" + priceFlipFlop((int)getNumFlipFlops()));
        chartPrinter.println("NPN (PN2222A)\t\t\t" + getNumNPN()+ "\t\t$0.50"  + "\t\t" + priceNPN((int)getNumNPN()));
        chartPrinter.println("PNP (PN2907A)\t\t\t" + getNumPNP()+ "\t\t$0.50" + "\t\t" + pricePNP((int)getNumPNP()));
        chartPrinter.println("Capacitor 1uF\t\t\t" + getNum1MF()+ "\t\t$0.50"  + "\t\t" + price1MF((int)getNum1MF()));
        chartPrinter.println("Capacitor 10uF\t\t\t" + getNum10MF()+ "\t\t$0.50"  + "\t\t" + price10MF((int)getNum10MF()));
        chartPrinter.println("Capacitor 100uF\t\t\t" + getNum100MF()+ "\t\t$0.50"  + "\t\t" + price100MF((int)getNum100MF()));
        chartPrinter.println("1k Resistor\t\t\t" + getNum1kRes()+ "\t\t$0.05"  + "\t\t" + price1kRes((int)getNum1kRes()));
        chartPrinter.println("10k Resistor\t\t\t" + getNum10kRes()+ "\t\t$0.05"  + "\t\t" + price10kRes((int)getNum10kRes()));
        chartPrinter.println("470 Resistor\t\t\t" + getNum470Res()+ "\t\t$0.05" + "\t\t" + price470Res((int)getNum470Res()));
        chartPrinter.println("AA' Batteries\t\t\t" + getNumBattery()+ "\t\t$2.00" + "\t\t" + priceBattery((int)getNumBattery()));
        chartPrinter.println("3mm Red LED\t\t\t" + getNumRedLED()+ "\t\t$0.25" + "\t\t" + priceRedLED((int)getNumRedLED()));
        chartPrinter.println("3mm Green LED\t\t\t" + getNumGreenLED()+ "\t\t$0.25" + "\t\t" + priceGreenLED((int)getNumGreenLED()));
        chartPrinter.println("3mm Yellow LED \t\t\t" + getNumYellowLED()+ "\t\t$0.25" + "\t\t" + priceYellowLED((int)getNumYellowLED()));
        chartPrinter.println("3mm Blue LED\t\t\t" + getNumBlueLED()+ "\t\t$0.50" + "\t\t" + priceBlueLED((int)getNumBlueLED()));
        chartPrinter.println("Switch\t\t\t\t" + getNumSwitches() + "\t\t$1.50" + "\t\t" + priceSwitch((int) getNumSwitches()));
        chartPrinter.println("PIC24FV32KA302 \t\t\t" + numPic + "\t\t$5.00" + "\t\t" + pricePic24((int) numPic));
        chartPrinter.println("H-Bridge \t\t\t" + numHBridge + "\t\t$3.00" + "\t\t" + priceHBridge((int) numHBridge));
        //Get the total cost of all the parts by adding them all up
        double totalCost = (Math.ceil(getNumANDGates()/4.0)*ANDGATEPRICE) +  (Math.ceil(getNumORGates()/4.0)*ORGATEPRICE) + (Math.ceil(getNumNORGates()/4.0)*NORGATEPRICE)  +
                           (Math.ceil(getNumNOTGates()/4.0)*NOTGATEPRICE)+ (Math.ceil(getNumXORGates()/4.0)*XORGATEPRICE) +  (Math.ceil(getNumFlipFlops()/2)*FLIPFLOPPRICE) +
                           (getNumNPN()*NPNPRICE)+(getNumPNP()*PNPPRICE) +  (getNum1MF()*ONEMFPRICE) +(getNum10MF()*TENMFPRICE) + ((getNum100MF()*ONEHUNDREDMFPRICE)        +  
                           (getNum1kRes()*ONEKRESPRICE) + (getNum10kRes()*TENKRESPRICE) +  (getNum470Res()*FOURSEVENTYRESPRICE) + (getNumBattery()*PLUSBATTERYPRICE))       + 
                           (getNumRedLED()*REDLEDPRICE) +  (getNumGreenLED()*GREENLEDPRICE) + (getNumYellowLED()*YELLOWLEDPRICE) +  (getNumBlueLED()*BLUELEDPRICE)          + 
                           (getNumSwitches()*SWITCHPRICE) + (numPic*PIC24FV32KA302PRICE) + (numHBridge*HBRIDGEPRICE);
        String totalCostString = format.format(totalCost);
        chartPrinter.println("-------------------------------------------------------------------------------");
        chartPrinter.println("Total Cost:\t\t\t\t\t\t\t" + totalCostString);
        chartPrinter.close();
        
        
    }
    
    /**Gets the number of AND Gates in the diagram
     * @return returns the number of AND Gates
     */
    private int getNumANDGates()
    {
        return circuit.getANDGates().size();
    }
    
    /**
     * Returns the price of all the AND Gates in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceANDGates(int n)
    {
        double b = Math.ceil(n/4.0)*ANDGATEPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of NOR Gates in the diagram
     * @return returns the number of NOR Gates
     */
    private int getNumNORGates()
    {
        return circuit.getNORGates().size();
    }
    
    /**
     * Returns the price of all the NOR Gates in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceNORGates(int n)
    {
        double b = Math.ceil(n/4.0)*NORGATEPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of OR Gates in the diagram
     * @return returns the number of OR Gates
     */
    private int getNumORGates()
    {
        return circuit.getORGates().size();
    }
    
    /**
     * Returns the price of all the OR Gates in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceORGates(int n)
    {
        double b = Math.ceil(n/4.0)*ORGATEPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of NOT Gates in the diagram
     * @return returns the number of NOT Gates
     */
    private int getNumNOTGates()
    {
        return circuit.getNOTGates().size();
    }
    
    /**
     * Returns the price of all the NOT Gates in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceNOTGates(int n)
    {
        double b = Math.ceil(n/6.0)*NOTGATEPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of XOR Gates in the diagram
     * @return returns the number of XOR Gates
     */
    private int getNumXORGates()
    {
        return circuit.getXORGates().size();
    }
    
    /**
     * Returns the price of all the XOR Gates in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceXORGates(int n)
    {
        double b = Math.ceil(n/4.0)*XORGATEPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of Flip Flops in the diagram
     * @return returns the number of Flip Flops
     */
    private int getNumFlipFlops()
    {
        return circuit.getFlipFlops().size();
    }
    
    /**
     * Returns the price of all the Flip Flops in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceFlipFlop(int n)
    {
        double b = Math.ceil(n/2)*FLIPFLOPPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of NPN Transistors in the diagram
     * @return returns the number of NPN Transistors
     */
    private int getNumNPN()
    {
        int x = 0;
        for(Transistor t: circuit.getTransistors()){
            if(t.getType().equals("NPN"))
                x++;
        }
        return x;
    }
    
    /**
     * Returns the price of all the NPN Transistors in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceNPN(int n)
    {
        double b = n*NPNPRICE; 
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of PNP Transistors in the diagram
     * @return returns the number of PNP Transistors
     */
    private int getNumPNP()
    {
        int x = 0;
        for(Transistor t: circuit.getTransistors()){
            if(t.getType().equals("PNP"))
                x++;
        }
        return x;
    }
    
    /**
     * Returns the price of all the PNP Transistors in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String pricePNP(int n)
    {
        double b = n*PNPPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of 1mf Capacitors in the diagram
     * @return returns the number of 1mf Capacitors
     */
    private int getNum1MF()
    {
        int x = 0;
        for(Capacitor r : circuit.getCapacitors()){
            if(r.getPicoFerets()== 1)
                x++;
        }
        return x;
    }
    
    /**
     * Returns the price of all the 1mF Capacitors in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String price1MF(int n)
    {
        double b = n*ONEMFPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of 10mf Capacitors in the diagram
     * @return returns the number of 10mf Capacitors
     */
    private int getNum10MF()
    {
        int x = 0;
        for(Capacitor r : circuit.getCapacitors()){
            if(r.getPicoFerets()== 10)
                x++;
        }
        return x;
    }
    
    /**
     * Returns the price of all the 10mF Capacitors in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String price10MF(int n)
    {
        double b = n*TENMFPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of 100mf Capacitors in the diagram
     * @return returns the number of 100mf Capacitors
     */
    private int getNum100MF()
    {
        int x = 0;
        for(Capacitor r : circuit.getCapacitors()){
            if(r.getPicoFerets()== 100)
                x++;
        }
        return x;
    }
    
    /**
     * Returns the price of all the 100mF Capacitors in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String price100MF(int n)
    {
        double b = n*ONEHUNDREDMFPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of 1k Resistors in the diagram
     * @return returns the number of 1k Resistors Capacitors
     */
    private int getNum1kRes()
    {
        int x = 0;
        for(Resistor r : circuit.getResistors()){
            if(r.getResistance() == 1000)
                x++;
        }
        return x;
    }
    
    /**
     * Returns the price of all the 1k Resistors in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String price1kRes(int n)
    {
        double b = n*ONEKRESPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of 10k Resistors in the diagram
     * @return returns the number of 10k Resistors Capacitors
     */
    private int getNum10kRes()
    {
        int x = 0;
        for(Resistor r : circuit.getResistors()){
            if(r.getResistance() == 10000)
                x++;
        }
        return x;
    }
    
    /**
     * Returns the price of all the 10k Resistors in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String price10kRes(int n)
    {
        double b = n*TENKRESPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of 470 Resistors in the diagram
     * @return returns the number of 470 Resistors Capacitors
     */
    private int getNum470Res()
    {
        int x = 0;
        for(Resistor r : circuit.getResistors()){
            if(r.getResistance() == 470)
                x++;
        }
        return x;
    }
    
    /**
     * Returns the price of all the 470 Resistors in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String price470Res(int n)
    {
        double b = n*FOURSEVENTYRESPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of Batteries in the diagram
     * @return returns the number of Batteries
     */
    private int getNumBattery()
    {
        if (circuit.getPosBatteries().size()>0)
            return 4;
        else
            return 0;
    }
    
    /**
     * Returns the price of all the Batteries in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceBattery(int n)
    {
        double b = n*PLUSBATTERYPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of Red LEDs in the diagram
     * @return returns the number of Red LEDs
     */
    private int getNumRedLED()
    {
        int x = 0;
        for(LED r : circuit.getLEDs()){
            if(r.getColour() == 1)
                x++;
        }
        return x;
    }
    
    /**
     * Returns the price of all the Red LEDs in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceRedLED(int n)
    {
        double b = n*REDLEDPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of Blue LEDs in the diagram
     * @return returns the number of Blue LEDs
     */
    private int getNumBlueLED()
    {
        int x = 0;
        for(LED r : circuit.getLEDs()){
            if(r.getColour() == 2)
                x++;
        }
        return x;
    }
    
    /**
     * Returns the price of all the Blue LEDs in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceBlueLED(int n)
    {
        double b = n*BLUELEDPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of Yellow LEDs in the diagram
     * @return returns the number of Yellow LEDs
     */
    private int getNumYellowLED()
    {
        int x = 0;
        for(LED r : circuit.getLEDs()){
            if(r.getColour() == 3)
                x++;
        }
        return x;
    }
    
    /**
     * Returns the price of all the Yellow LEDs in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceYellowLED(int n)
    {
        double b = n*YELLOWLEDPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of Green LEDs in the diagram
     * @return returns the number of Green LEDs
     */
    private int getNumGreenLED()
    {
        int x = 0;
        for(LED r : circuit.getLEDs()){
            if(r.getColour() == 4)
                x++;
        }
        return x;
    }
    
    /**
     * Returns the price of all the Green LEDs in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceGreenLED(int n)
    {
        double b = n*GREENLEDPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of Diodes in the diagram
     * @return returns the number of Diodes
     */
    private int getNumDiode()
    {
        return circuit.getDiodes().size();
    }
    
    /**
     * Returns the price of all the Diodes in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceDiode(int n)
    {
        double b = n*DIODEPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of switches in the diagram
     * @return returns the number of switches
     */
    private int getNumSwitches()
    {
        return circuit.getSwitches().size();
    }
    
    /**
     * Returns the price of all the switches in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceSwitch(int n)
    {
        double b = n*SWITCHPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of HGridges in the diagram
     * @return returns the number of HBridges
     */
    private int getNumHBridge()
    {
        return circuit.getSwitches().size();
    }
    
    /**
     * Returns the price of all the HBridges in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String priceHBridge(int n)
    {
        double b = n*HBRIDGEPRICE;
        String price = format.format(b);
        return price;
    }
    
    /**Gets the number of Pic24 in the diagram
     * @return returns the number of Pic24
     */
    private int getNumPic24()
    {
        return circuit.getSwitches().size();
    }
    
    /**
     * Returns the price of all the Pic24 in the circuit
     * @param n number of parts
     * @return String formatted to $0.00
     */
    private String pricePic24(int n)
    {
        double b = n*PIC24FV32KA302PRICE;
        String price = format.format(b);
        return price;
    }
    
    /** Starts the simulation of the schematic
     */ 
    public void simulate()
    {
        //Calls circuit to update the power of each part
        circuit.updatePower();
    }
    
    /** Changes a switch to on or off
     * @param part      The name of the switch
     * @param b         The true/false value to which the switch should be turned to
     */ 
    public void changeSwitch(String part, boolean b)
    {
        //Look through the circuit's switches and if the part equals that switch's name,
        //set the switch to the b value. Update the power afterwards and break the loop
        for (Switch s: circuit.getSwitches())
        {
            if (s.getName().equals(part))
            {
                s.setSwitch(b);
                circuit.updatePower();
                break;
            }
        }
    }
    
    /**Accessor method to get the arraylist of LEDs
     * @return the arraylist of LEDs
     */ 
    public ArrayList<LED> getLights()
    {
        return circuit.getLEDs();
    }
    
    /**Determines whether there is a shortCircuit or not
     * @return a boolean value that represents a short circuit is present or not
     */ 
    public boolean isShortCircuit()
    {
        //Go into the circuit's isShortCircuit() method that actually finds out
        //whether there is a short circuit or not
        return circuit.isShortCircuit();
    }
    
    /**Resets the flipflop checked and hold values
     */ 
    public void resetFlipFlops()
    {
        //Goes through each flipflop and resets the checked and hold values
        for (FlipFlop f: circuit.getFlipFlops())
        {
            f.resetChecked();
            f.resetHold();
        }
    }
    
    /**Gets the connections of a certain part's side. If there are no connections
     * add a No. into the arraylist and return it. Used for saving a file
     * @param part      the part that the method is going to look into
     * @param side      this side's connections that will be returned
     * @return an arraylist of the connections connected to this part's side
     */ 
    public ArrayList <String> getConnections(String part, int side)
    {
        //Create new arraylist for the connections to be stored in
        ArrayList <String> connections = new ArrayList <String>();
        
        //If the part is either a pic or an hbridge, there are automatically no
        //connections to it, so add a No. and return the connections
        if (part.contains("pic24") || part.contains("hbridge"))
        {
            connections.add("No.");
            return connections;
        }
        
        //Find the actual part from the part name
        CircuitPart p1 = findPart(part);
        
        //If the side is greater than the amount of sides the part has, put a 
        //No. in the arraylist and return it
        if (side > p1.getSides())
            connections.add("No.");
        
        else
        {
            //For each of the connections to that side, add the connected part's
            //name into the arraylist
            for (CircuitPart p2: p1.getConnections(side))
                connections.add(p2.getName());
            
            //If nothing has been added in, put in a No. in the arraylist
            if (connections.size() == 0)
                connections.add("No.");
        }
        
        return connections;
    }
    
    /** A special connect only used for loading a file. Only connects one part to
     * another part but not the second part to that one part.
     * @param part      The name of the part getting the connection
     * @param side      The side of the part that is getting the connection
     * @param part2     The name of the part that will be connected to part
     */
    public void connect(String part, int side, String part2)
    {
        //Calls the circuit's special connect using the 2 name's real part and the one side
        circuit.connect(findPart(part), side, findPart(part2));
    }
    
    /** Deletes every single part to clear the schematic
     */ 
    public void clearAll()
    {
        circuit.clearAll();
    }
    
    /**Fills in any deleted part's increments that are leftover when the user
     * wants to save. This prevents any misconnections when loading a file.
     * @return the names that are needed to be used to fill up the deleted parts.
     */ 
    public String[] fill()
    {
        return circuit.fill();
    }
}
