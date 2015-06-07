package Schematicomatic;

/** Wire Class
 * A Wire object that is used to draw wire in the schematicPanel of the SchematicomaticView
 * Date Created: 28/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Wire extends JComponent
{
    private boolean first = false;      //a check to see if it is called already(for drawing the crosshair first, then the wire)
    private Point firstPoint;           //First point of where the wire is situated
    private Point secondPoint;          //Second point of where the wire goes to
    private Point bounds;               //The width and height of the wire
    private int colour;                 //An integer for a specific wire colour
    private String name;                //Name of the wire. Will be used to determine this wire from another
    
    private ArrayList <Wire> wireConnections = new ArrayList <Wire>();          //All of the wire that is connected to this wire
    private ArrayList <Drawing> partConnections = new ArrayList <Drawing>();    //All of the parts that are connected to this wire. will be aligned with partSides
    private ArrayList <Integer> partSides = new ArrayList <Integer>();          //All of the part sides that are connected to this wire
    
    //Wire checks when finding connections, this keeps track if the wire has been already checked or not
    private boolean checked = false;    //First check used to see if this wire was checked already
    private boolean checked2 = false;   //Second check
    private boolean checked3 = false;   //Third check
    
    private boolean highlighted = false;    //determines whether the wire is highlighted or not
    private boolean special;                //in special cases (loading the wire), the wire can be drawn in one go instead of 2
    
    /** Constructor for wire class. Gives it a bunch of attributes
     * 
     * @param x     the first x point of the line
     * @param y     the second x point of the line
     * @param c     the integer value of the colour of the wire
     * @param n     the name of the wire
     * @param s     special case for loading
     * @param x2    used with the special case, or 0 if nothing  (the second x point of the line)
     * @param y2    used with the special case, or 0 if nothing  (the second y point of the line)
     */
    public Wire(int x, int y, int c, String n, boolean s, int x2, int y2)
    {
        super();
        //convert the x and y value into a new point called firstPoint
        firstPoint = new Point(x,y);
        //set the colour to c
        colour = c;
        //set the name to n
        name = n;
        //set the special to s
        special = s;
        //make new bounds using x2 and y2
        bounds = new Point(x2, y2);
    }
    
    /** Overrides the super paintcomponent method. Paints the wire.
     * @param g     the drawing
     */ 
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //create new graphics 2D so the thickness of a line can be changed
        Graphics2D g2 = (Graphics2D) g;   
        
        //If first has been clicked or special is true, draw the line
        if (first || special)
        {      
            g2.setColor(Color.DARK_GRAY);
            //Draws a square at the first point of the wire
            g2.drawRect(0,0,5,5);
            //Draws a square at the second point of the wire
            g2.drawRect(Math.abs(bounds.x),Math.abs(bounds.y),5,5);
            
            //Sets the colour of the wire based on the integer value of colour
            switch (colour)
            {
                case 0:
                    g2.setColor(Color.BLACK);
                    break;
                case 1:
                    g2.setColor(Color.BLUE);
                    break;
                case 2:
                    g2.setColor(Color.RED);
                    break;
                case 3:
                    g2.setColor(Color.YELLOW);
                    break;
                case 4:
                    g2.setColor(Color.GREEN);
                    break;
                case 5:
                    //This is purple
                    g2.setColor(new Color(145,44,248));
                    break;
                case 6:
                    g2.setColor(Color.ORANGE);
                    break;
            }
            //Set the line thickness to 2 pixels thick
            g2.setStroke(new BasicStroke(2));
            //Draws the line
            g2.drawLine(3,3,Math.abs(bounds.x)+3,Math.abs(bounds.y)+3);         
        }
        
        else
        {
            //If this is the first time this method is called, draw a red
            //crosshair to show where the first point of the wire is going to 
            //be placed
            g2.setColor(Color.RED);
            g2.drawLine(10,0,10,8);
            g2.drawLine(0,10,8,10);
            g2.drawLine(20,10,12,10);
            g2.drawLine(10,20,10,12);
            first = true;
        }                   
    }
    
    /**Accessor method to get the first point
     * @return the first point of where the wire will be placed
     */ 
    public Point getFirstPoint()
    {
        return firstPoint;
    }
    
    /**Mutator method to set the second point of the wire
     * @param s     the second point
     */ 
    public void setSecondPoint(Point s)
    {
        secondPoint = s;
    }
    
    /** Gets the width of the line.
     */ 
    public int getLineWidth()
    {
        //Returns the absolute value of the difference between 2 X values to get the width of the line
        return (int)Math.abs(secondPoint.x-firstPoint.x);
    }
    
    /** Gets the height of the line.
     */ 
    public int getLineHeight()
    {
        //Returns the absolute value of the difference between the 2 Y values to get the height of the line
        return (int)Math.abs(secondPoint.y-firstPoint.y);
    }
    
    /** Mutator method to set the bounds of this wire
     *  @param b    the bounds point
     */ 
    public void setThisBounds(Point b)
    {
        bounds = b;
    }
    
    /** Sets this wire to be highlighted or unhighlighted based on the condition
     * @param b     true to highlight the wire and false to unhighlight it
     */ 
    public void setHighlighted(boolean b)
    {
        //Change this highlighted variable to b
        highlighted = b;
        if (b)
            //create a magenta coloured border around the line
            this.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        else
            //remove the border
            this.setBorder(null);
    }
    
    /**Accessor method to tell if this wire is highlighted
     * @return a boolean value to represent highlighted or unhighlighted
     */ 
    public boolean isHighlighted()
    {
        return highlighted;
    }
    
    /**Accessor method to get the name of this wire
     * @return the name of this wire
     */ 
    public String getName()
    {
        return name;
    }
    
    /** Sets a wire connection to this wire
     * @param w     the wire that will be connected to this wire
     */ 
    public void setConnection(Wire w)
    {
        //If this wire does not have w in it, then add it to the list of connections
        if (!wireConnections.contains(w))
            wireConnections.add(w);
    }
    
    /** Sets a drawing connection to this wire.
     * 
     * @param d     The drawing that will be connected to this wire
     * @param side  The side of the drawing that will be connected to this wire
     */
    public void setConnection(Drawing d, int side)
    {
        //Add the drawing and sides to the list of drawing and side connections
        partConnections.add(d);
        partSides.add(side);
    }
    
    /** Accessor method to get the arraylist of drawings this wire is connected to
     * @return the arraylist of drawing connections
     */
    public ArrayList <Drawing> getPartConnections()
    {
        return partConnections;
    }
    
    /**Accessor method to get the arraylist of sides of the drawings this wire is connected to
     * @return the arraylist of drawing side connections
     */ 
    public ArrayList <Integer> getPartSides()
    {
        return partSides;
    }
    
    /**Accessor method to get the arraylist of wires that this wire is connected to
     * @return the arraylist of wire connections
     */ 
    public ArrayList <Wire> getWireConnections()
    {
        return wireConnections;
    }
    
    /** Mutator method to set the checked value of this wire
     * @param b     The value checked will equal to
     */ 
    public void setChecked(boolean b)
    {
        checked = b;
    }
    
    /**Accessor method to get the checked value of this wire
     * @return the checked value
     */ 
    public boolean isChecked()
    {
        return checked;
    }
    
    /** Mutator method to set the checked2 value of this wire
     * @param b     The value checked2 will equal to
     */
    public void setChecked2(boolean b)
    {
        checked2 = b;
    }
    
    /**Accessor method to get the checked2 value of this wire
     * @return the checked2 value
     */ 
    public boolean isChecked2()
    {
        return checked2;
    }
    
    /** Mutator method to set the checked3 value of this wire
     * @param b     The value checked3 will equal to
     */
    public void setChecked3(boolean b)
    {
        checked3 = b;
    }
    
    /**Accessor method to get the checked3 value of this wire
     * @return the checked3 value
     */ 
    public boolean isChecked3()
    {
        return checked3;
    }
    
    /**Removes a drawing connection from this wire
     * @param d     The drawing that will be removed
     */ 
    public void removeConnections(Drawing d)
    {
        //Remove all of the connections of d to this wire
        while (partConnections.contains(d))
        {
            partSides.remove(partConnections.indexOf(d));
            partConnections.remove(d);
        }
    }
    
    /**Removes a wire connection from this wire
     * @param w     The wire that will be removed
     */ 
    public void removeConnections(Wire w)
    {
        wireConnections.remove(w);
    }
    
    /**Removes all of the connections that are connected to this wire
     */ 
    public void removeAllConnections()
    {
        //Clears the connections arraylist
        partConnections.clear();
        partSides.clear();
        wireConnections.clear();
    }
    
    /**Accessor method to get the colour value of this wire
     * @return the integer value of the colour
     */ 
    public int getColour()
    {
        return colour;
    }
}
