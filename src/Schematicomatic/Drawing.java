package Schematicomatic;

/** Drawing Class
 * The drawing class that initializes the pictures that are being used in the program
 * Date Created: 03/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.*;
import javax.swing.*;

public class Drawing extends JLabel
{
    private ImageIcon [][] icons;       //a 2D array holding the pictures
    private ImageIcon icon;             //the icon being used
    private int orientation;            //which direction the picture is facing
    private int type;                   //the part used
    public static final int BLACK = 0;  //the number for black
    public static final int BLUE = 1;   //the number for blue
    public static final int RED = 2;    //the number for red
    public static final int YELLOW = 3; //the number for yellow
    public static final int GREEN = 4;  //the number for green
    public static final int PURPLE = 5; //the number for purple
    public static final int ORANGE = 6; //the number for orange
    public static final int NPN = 1;    //the number for the npn transistor
    public static final int PNP = 2;    //the number for the pnp transistor
    public static final int TRAD_SWITCH = 1;    //the number for the traditional switch
    public static final int PLUS_SWITCH = 2;    //the number for the positive switch
    public static final int NEG_SWITCH = 3;     //the number for the negatice switch
    public static final int ON = 1;     //the number for on
    public static final int OFF = 0;    //the number for off
    private int extra;      //the value of the part used
    private String name;    //the name of the part used
    private String parent;  //the parent name of the part used
    private int xOffside1;  //x offside from the top left corner to the first wire
    private int yOffside1;  //y offside from the top left corner to the first wire
    private int xOffside2;  //x offside from the top left corner to the second wire        
    private int yOffside2;  //y offside from the top left corner to the second wire
    private int xOffside3;  //x offside from the top left corner to the third wire
    private int yOffside3;  //y offside from the top left corner to the third wire
    private int xOffside4;  //x offside from the top left corner to the fourth wire
    private int yOffside4;  //y offside from the top left corner to the fourth wire
    private int sides;      //the amount of sides there are on the part
    
    private JButton switcher;   //the button that sets the drawings button to the one created
    
    /**Constructor for the Drawing. Instantiates different variables.
     * @param parent    The parent name of the object
     * @param n         The name of the object
     * @param value     the value of the object
     */ 
    public Drawing (String parent, String n, int value)
    {
        super();
        icons = new ImageIcon[30][4];
        setImages();
        this.extra = value;      
        this.name = n;
        this.parent = parent;
        this.type = getType(parent);
        this.icon = icons[type][0];      
        this.setIcon(icon);
        this.setNewOffsides();
        this.setHorizontalAlignment(JLabel.CENTER);
    }
    
    /** Gets the actual object part name.
     * @return the object part name
     */
    public String getName()
    {
        return name;
    }
    
    /** Gets the parent name of the object.
     * @return the objects parent name
     */
    public String getPart()
    {
        return parent;
    }
    
    
    /** Gets the value of the object.
     * @return the objects value
     */
    public int getValue()
    {
        return extra;
    }
    
    /** Changes the orientation of the object being rotated.
     */
    public void rotate()
    {
        if (orientation < 3)
            orientation++;
        else
            orientation = 0;
        
        setNewOffsides();
        update();
    }
    
    /** Get the type of object it is.
     * @return the type of object it is
     */
    public int getType()
    {
        return type;
    }
    
    /** Changes the object to be on or off.
     * @param change an integer value to be on or off
     */
    public void change(int change)
    {
        //pictures of on and off values are right beside
        //each other so just by increasing or decreasing type,
        //the picture will change to an on or off state
        if (change == ON)
            type++;
        else if (change == OFF)
            type--;
        update();
    }
    
    /** Gets the type of of part as an integer.
     * @param parent    The parent name of the object
     * @return the type of part the object is
     */
    private int getType(String parent)
    {
        //if the parent equals a part, set its tooltiptext to that part's name,
        //set the sides to the amount of sides the part has, and return
        //the type value, which is what index the part is located in the arraylist
        //of imageicons
        if (parent.equals("andgate"))
        {
            this.setToolTipText("AND Gate");
            this.sides = 3;
            return 0;
        }
        else if (parent.equals("nandgate"))
        {
            this.setToolTipText("NAND Gate");
            this.sides = 3;
            return 1;
        }
        else if (parent.equals("orgate"))
        {
            this.setToolTipText("OR Gate");
            this.sides = 3;
            return 2;
        }
        else if (parent.equals("norgate"))
        {
            this.setToolTipText("NOR Gate");
            this.sides = 3;
            return 3;
        }
        else if (parent.equals("xorgate"))
        {
            this.setToolTipText("XOR Gate");
            this.sides = 3;
            return 4;
        }
        else if (parent.equals("notgate"))
        {
            this.setToolTipText("NOT Gate");
            this.sides = 2;
            return 5;
        }
        else if (parent.equals("flipflop"))
        {
            this.setToolTipText("Clocked Flip Flop");
            this.sides = 4;
            return 6;
        }
        else if (parent.equals("diode"))
        {
            this.setToolTipText("Diode");
            this.sides = 2;
            return 7;
        }
        else if (parent.equals("led"))
        {
            if (extra == RED)
            {
                this.setToolTipText("Red LED (Off)");
                this.sides = 2;
                return 8;
            }
            else if (extra == BLUE)
            {
                this.setToolTipText("Blue LED (Off)");
                this.sides = 2;
                return 10;
            }
            else if (extra == GREEN)
            {
                this.setToolTipText("Green LED (Off)");
                this.sides = 2;
                return 12;
            }
            else if (extra == YELLOW)
            {
                this.setToolTipText("Yellow LED (Off)");
                this.sides = 2;
                return 14;
            }
            else
            {
                return -1;
            }
        }
        else if (parent.equals("switch"))
        {
            if (extra == TRAD_SWITCH)
            {
                this.setToolTipText("Switch (Off)");
                this.sides = 2;
                
                return 16;
            }
            else if (extra == PLUS_SWITCH)
            {
                this.setToolTipText("Postitive Switch (Off)");
                this.sides = 1;
                return 18;
            }
            else if (extra == NEG_SWITCH)
            {
                this.setToolTipText("Negative Switch (Off)");
                this.sides = 1;
                return 20;
            }
        }
        else if (parent.equals("resistor"))
        {
            this.setToolTipText("Resistor "+extra+"Ω");
            this.sides = 2;
            return 22;
        }
        else if (parent.equals("capacitor"))
        {
            this.setToolTipText("Capacitor "+extra+"uF");
            this.sides = 2;
            return 23;
        }
        else if (parent.equals("transistor"))
        {
            if (extra == NPN)
            {
                this.setToolTipText("NPN Transistor");
                this.sides = 3;
                return 24;
            }
            else if (extra == PNP)
            {
                this.setToolTipText("PNP Transistor");
                this.sides = 3;
                return 25;
            }
            else 
            {
                return -1;
            }
        }
        else if (parent.equals("plusbattery"))
        {
            this.setToolTipText(extra+"V battery");
            this.sides = 1;
            return 26;
        }
        else if (parent.equals("negbattery"))
        {
            this.setToolTipText("Ground");
            this.sides = 1;
            return 27;
        }
        else if (parent.equals("pic24"))
        {
            this.setToolTipText("PIC24 Chip");
            this.sides = 28;
            return 28;
        }
        else if (parent.equals("hbridge"))
        {
            this.setToolTipText("H-Bridge");
            this.sides = 12;
            return 29;
        }
        return 0;
    }
    
    /** Updates the object. Used when the object is rotated or turned on or off
     */
    private void update()
    {
        //removes all of the icons and such to this drawing
        this.removeAll();
        //set the icon to they new indexes of type and orientation
        this.icon = icons[type][orientation];
        
        //if the drawing has been turned off or on, set it's new tooltiptext
        if (type == 8)
        {
            this.setToolTipText("Red LED (Off)");
        }
        else if (type == 9)
        {
            this.setToolTipText("Red LED (On)");
        }
        else if (type == 10)
        {
            this.setToolTipText("Blue LED (Off)");
        }
        else if (type == 11)
        {
            this.setToolTipText("Blue LED (On)");
        }
        else if (type == 12)
        {
            this.setToolTipText("Green LED (Off)");
        }
        else if (type == 13)
        {
            this.setToolTipText("Green LED (On)");
        }
        else if (type == 14)
        {
            this.setToolTipText("Yellow LED (Off)");
        }
        else if (type == 15)
        {
            this.setToolTipText("Yellow LED (On)");
        }
        else if (type == 16)
        {
            this.setToolTipText("Switch (Off)");
        }
        else if (type == 17)
        {
            this.setToolTipText("Switch (On)");
        }
        else if (type == 18)
        {
            this.setToolTipText("Positive Switch (Off)");
        }
        else if (type == 19)
        {
            this.setToolTipText("Positive Switch (On)");
        }
        else if (type == 20)
        {
            this.setToolTipText("Negative Switch (Off)");
        }
        else if (type == 21)
        {
            this.setToolTipText("Negative Switch (On)");
        }
        
        //set the icon to the icon in the new positions
        this.setIcon(icon);
    }
    
    /** Sets all the images in the 2D array. The first number is the part type,
     * and the second number is the orientation of the part. Each part will have
     * 4 different pictures for east, south, west, and north facing.
     */
    private void setImages()
    {
        icons[0][0] = new ImageIcon ("Resorces/ANDGate (right).png");
        icons[0][1] = new ImageIcon ("Resorces/ANDGate (down).png");
        icons[0][2] = new ImageIcon ("Resorces/ANDGate (left).png");
        icons[0][3] = new ImageIcon ("Resorces/ANDGate (up).png");
        icons[1][0] = new ImageIcon ("Resorces/NANDGate (right).png");
        icons[1][1] = new ImageIcon ("Resorces/NANDGate (down).png");
        icons[1][2] = new ImageIcon ("Resorces/NANDGate (left).png");
        icons[1][3] = new ImageIcon ("Resorces/NANDGate (up).png");
        icons[2][0] = new ImageIcon ("Resorces/ORGate (right).png");
        icons[2][1] = new ImageIcon ("Resorces/ORGate (down).png");
        icons[2][2] = new ImageIcon ("Resorces/ORGate (left).png");
        icons[2][3] = new ImageIcon ("Resorces/ORGate (up).png");
        icons[3][0] = new ImageIcon ("Resorces/NORGate (right).png");
        icons[3][1] = new ImageIcon ("Resorces/NORGate (down).png");
        icons[3][2] = new ImageIcon ("Resorces/NORGate (left).png");
        icons[3][3] = new ImageIcon ("Resorces/NORGate (up).png");
        icons[4][0] = new ImageIcon ("Resorces/XORGate (right).png");
        icons[4][1] = new ImageIcon ("Resorces/XORGate (down).png");
        icons[4][2] = new ImageIcon ("Resorces/XORGate (left).png");
        icons[4][3] = new ImageIcon ("Resorces/XORGate (up).png");
        icons[5][0] = new ImageIcon ("Resorces/NOTGate (right).png");
        icons[5][1] = new ImageIcon ("Resorces/NOTGate (down).png");
        icons[5][2] = new ImageIcon ("Resorces/NOTGate (left).png");
        icons[5][3] = new ImageIcon ("Resorces/NOTGate (up).png");
        icons[6][0] = new ImageIcon ("Resorces/flipflop (right).png");
        icons[6][1] = new ImageIcon ("Resorces/flipflop (down).png");
        icons[6][2] = new ImageIcon ("Resorces/flipflop (left).png");
        icons[6][3] = new ImageIcon ("Resorces/flipflop (up).png");
        icons[7][0] = new ImageIcon ("Resorces/Diode (right).png");
        icons[7][1] = new ImageIcon ("Resorces/Diode (down).png");
        icons[7][2] = new ImageIcon ("Resorces/Diode (left).png");
        icons[7][3] = new ImageIcon ("Resorces/Diode (up).png");
        icons[8][0] = new ImageIcon ("Resorces/LEDRed (off)(right).png");
        icons[8][1] = new ImageIcon ("Resorces/LEDRed (off)(down).png");
        icons[8][2] = new ImageIcon ("Resorces/LEDRed (off)(left).png");
        icons[8][3] = new ImageIcon ("Resorces/LEDRed (off)(up).png");
        icons[9][0] = new ImageIcon ("Resorces/LEDRed (on)(right).png");
        icons[9][1] = new ImageIcon ("Resorces/LEDRed (on)(down).png");
        icons[9][2] = new ImageIcon ("Resorces/LEDRed (on)(left).png");
        icons[9][3] = new ImageIcon ("Resorces/LEDRed (on)(up).png");
        icons[10][0] = new ImageIcon ("Resorces/LEDBlue (off)(right).png");
        icons[10][1] = new ImageIcon ("Resorces/LEDBlue (off)(down).png");
        icons[10][2] = new ImageIcon ("Resorces/LEDBlue (off)(left).png");
        icons[10][3] = new ImageIcon ("Resorces/LEDBlue (off)(up).png");
        icons[11][0] = new ImageIcon ("Resorces/LEDBlue (on)(right).png");
        icons[11][1] = new ImageIcon ("Resorces/LEDBlue (on)(down).png");
        icons[11][2] = new ImageIcon ("Resorces/LEDBlue (on)(left).png");
        icons[11][3] = new ImageIcon ("Resorces/LEDBlue (on)(up).png");
        icons[12][0] = new ImageIcon ("Resorces/LEDGreen (off)(right).png");
        icons[12][1] = new ImageIcon ("Resorces/LEDGreen (off)(down).png");
        icons[12][2] = new ImageIcon ("Resorces/LEDGreen (off)(left).png");
        icons[12][3] = new ImageIcon ("Resorces/LEDGreen (off)(up).png");
        icons[13][0] = new ImageIcon ("Resorces/LEDGreen (on)(right).png");
        icons[13][1] = new ImageIcon ("Resorces/LEDGreen (on)(down).png");
        icons[13][2] = new ImageIcon ("Resorces/LEDGreen (on)(left).png");
        icons[13][3] = new ImageIcon ("Resorces/LEDGreen (on)(up).png");
        icons[14][0] = new ImageIcon ("Resorces/LEDYellow (off)(right).png");
        icons[14][1] = new ImageIcon ("Resorces/LEDYellow (off)(down).png");
        icons[14][2] = new ImageIcon ("Resorces/LEDYellow (off)(left).png");
        icons[14][3] = new ImageIcon ("Resorces/LEDYellow (off)(up).png");
        icons[15][0] = new ImageIcon ("Resorces/LEDYellow (on)(right).png");
        icons[15][1] = new ImageIcon ("Resorces/LEDYellow (on)(down).png");
        icons[15][2] = new ImageIcon ("Resorces/LEDYellow (on)(left).png");
        icons[15][3] = new ImageIcon ("Resorces/LEDYellow (on)(up).png");
        icons[17][0] = new ImageIcon ("Resorces/TRADSwitchPOS (right).png");
        icons[17][1] = new ImageIcon ("Resorces/TRADSwitchPOS (down).png");
        icons[17][2] = new ImageIcon ("Resorces/TRADSwitchPOS (right).png");
        icons[17][3] = new ImageIcon ("Resorces/TRADSwitchPOS (down).png");
        icons[16][0] = new ImageIcon ("Resorces/TRADSwitchNEG (right).png");
        icons[16][1] = new ImageIcon ("Resorces/TRADSwitchNEG (down).png");
        icons[16][2] = new ImageIcon ("Resorces/TRADSwitchNEG (right).png");
        icons[16][3] = new ImageIcon ("Resorces/TRADSwitchNEG (down).png");
        icons[18][0] = new ImageIcon ("Resorces/SwitchPOS (right).png");
        icons[18][1] = new ImageIcon ("Resorces/SwitchPOS (down).png");
        icons[18][2] = new ImageIcon ("Resorces/SwitchPOS (left).png");
        icons[18][3] = new ImageIcon ("Resorces/SwitchPOS (up).png");
        icons[20][0] = new ImageIcon ("Resorces/SwitchNEG (right).png");
        icons[20][1] = new ImageIcon ("Resorces/SwitchNEG (down).png");
        icons[20][2] = new ImageIcon ("Resorces/SwitchNEG (left).png");
        icons[20][3] = new ImageIcon ("Resorces/SwitchNEG (up).png");
        icons[22][0] = new ImageIcon ("Resorces/Resistor (right).png");
        icons[22][1] = new ImageIcon ("Resorces/Resistor (down).png");
        icons[22][2] = new ImageIcon ("Resorces/Resistor (right).png");
        icons[22][3] = new ImageIcon ("Resorces/Resistor (down).png");
        icons[23][0] = new ImageIcon ("Resorces/Capacitor (right).png");
        icons[23][1] = new ImageIcon ("Resorces/Capacitor (down).png");
        icons[23][2] = new ImageIcon ("Resorces/Capacitor (right).png");
        icons[23][3] = new ImageIcon ("Resorces/Capacitor (down).png");
        icons[24][0] = new ImageIcon ("Resorces/TransistorNPN (right).png");
        icons[24][1] = new ImageIcon ("Resorces/TransistorNPN (down).png");
        icons[24][2] = new ImageIcon ("Resorces/TransistorNPN (left).png");
        icons[24][3] = new ImageIcon ("Resorces/TransistorNPN (up).png");
        icons[25][0] = new ImageIcon ("Resorces/TransistorPNP (right).png");
        icons[25][1] = new ImageIcon ("Resorces/TransistorPNP (down).png");
        icons[25][2] = new ImageIcon ("Resorces/TransistorPNP (left).png");
        icons[25][3] = new ImageIcon ("Resorces/TransistorPNP (up).png");
        icons[26][0] = new ImageIcon ("Resorces/BatteryPOS (right).png");
        icons[26][1] = new ImageIcon ("Resorces/BatteryPOS (down).png");
        icons[26][2] = new ImageIcon ("Resorces/BatteryPOS (left).png");
        icons[26][3] = new ImageIcon ("Resorces/BatteryPOS (up).png");
        icons[27][0] = new ImageIcon ("Resorces/BatteryNEG (right).png");
        icons[27][1] = new ImageIcon ("Resorces/BatteryNEG (down).png");
        icons[27][2] = new ImageIcon ("Resorces/BatteryNEG (left).png");
        icons[27][3] = new ImageIcon ("Resorces/BatteryNEG (up).png");
        icons[28][0] = new ImageIcon ("Resorces/PIC24 (right).png");
        icons[28][1] = new ImageIcon ("Resorces/PIC24 (down).png");
        icons[28][2] = new ImageIcon ("Resorces/PIC24 (left).png");
        icons[28][3] = new ImageIcon ("Resorces/PIC24 (up).png");
        icons[29][0] = new ImageIcon ("Resorces/hbridge (right).png");
        icons[29][1] = new ImageIcon ("Resorces/hbridge (down).png");
        icons[29][2] = new ImageIcon ("Resorces/hbridge (left).png");
        icons[29][3] = new ImageIcon ("Resorces/hbridge (up).png");
    }
    
    //duplicated method
//    /** Set if the part is on or not.
//     * @param on shows if the object is on or off
//     */
//    public void setOn(Boolean on)
//    {
//        if (on == true)
//            type++;
//        else
//            type--;
//        update();
//    }
    
    /** Gets the new x and y point on what side it's on.
     * @param side the side of the object
     * @return the new point of the side
     */
    public Point getSidePoint(int side)
    {
        if (sides == 1)
        {
            return new Point(this.getX()+xOffside1, this.getY()+yOffside1);
        }
        else if (sides == 2)
        {
            return new Point(this.getX()+xOffside2, this.getY()+yOffside2);
        }
        else if (sides == 3)
        {
            return new Point(this.getX()+xOffside3, this.getY()+yOffside3);
        }
        else
        {
            return new Point(this.getX()+xOffside4, this.getY()+yOffside4);
        }
    }
    
    /** Sets and removes a border on the selected object.
     * @param selected if the object is selected or not
     */
    public void setSelected (Boolean selected)
    {
        if (selected == true)
            this.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
        else
            this.setBorder(null);
    }
    
    /** Gets the number of sides on the object.
     * @return the number of sides on the object
     */
    public int getSides()
    {
        return sides;
    }
    
    /** Sets the offsides on each of the parts on each orientation.
     */
    private void setNewOffsides()
    {
        if (sides == 1)
        {
            //if there is only one side, the side will start 60 from the right and 15 from the top of the picture
            switch (orientation)
            {
                //based on the orientation, set the offsides accordingly
                case 0:                 
                    this.xOffside1 = 60;
                    this.yOffside1 = 15;
                    break;
                case 1:
                    this.xOffside1 = 15;
                    this.yOffside1 = 60;
                    break;
                case 2:
                    this.xOffside1 = 0;
                    this.yOffside1 = 15;
                    break;
                case 3:
                    this.xOffside1 = 15;
                    this.yOffside1 = 0;
                    break;
            }
        }
        
        //Same thing as side one but the first side starts at 0 and 15 from the top left side of the picture 
        //and the second side is the same location as the side1 of a one sided object
        else if (sides == 2)
        {
            switch (orientation)
            {
                case 0:
                    this.xOffside1 = 0;
                    this.yOffside1 = 15;
                    this.xOffside2 = 60;
                    this.yOffside2 = 15;
                    break;
                case 1:
                    this.xOffside1 = 15;
                    this.yOffside1 = 0;
                    this.xOffside2 = 15;
                    this.yOffside2 = 60;
                    break;
                case 2:
                    this.xOffside1 = 60;
                    this.yOffside1 = 15;
                    this.xOffside2 = 0;
                    this.yOffside2 = 15;
                    break;
                case 3:
                    this.xOffside1 = 15;
                    this.yOffside1 = 60;
                    this.xOffside2 = 15;
                    this.yOffside2 = 0;
                    break;
            }
        }
        
        //Same thing as other 2 but add an extra side and they are all shifted a different location
        else if (sides == 3)
        {
            switch (orientation)
            {
                case 0:
                    this.xOffside1 = 0;
                    this.yOffside1 = 20;
                    //transistors have a special side so change it accordingly
                    if (parent.equals("transistor"))
                    {
                        this.xOffside2 = 60;
                        this.yOffside2 = 15;
                        this.xOffside3 = 0;
                        this.yOffside3 = 10;
                    }
                    else
                    {
                        this.xOffside2 = 0;
                        this.yOffside2 = 10;
                        this.xOffside3 = 60;
                        this.yOffside3 = 15;
                    }
                    break;
                case 1:
                    this.xOffside1 = 10;
                    this.yOffside1 = 0;
                    if (parent.equals("transistor"))
                    {
                        this.xOffside2 = 15;
                        this.yOffside2 = 60;
                        this.xOffside3 = 20;
                        this.yOffside3 = 0;
                    }
                    else
                    {
                        this.xOffside2 = 20;
                        this.yOffside2 = 0;
                        this.xOffside3 = 15;
                        this.yOffside3 = 60;
                    }
                    break;
                case 2:
                    this.xOffside1 = 60;
                    this.yOffside1 = 10;
                    if (parent.equals("transistor"))
                    {
                        this.xOffside2 = 0;
                        this.yOffside2 = 15;
                        this.xOffside3 = 60;
                        this.yOffside3 = 20;
                    }
                    else
                    {
                        this.xOffside2 = 60;
                        this.yOffside2 = 20;
                        this.xOffside3 = 0;
                        this.yOffside3 = 15;
                    }
                    break;
                case 3:
                    this.xOffside1 = 20;
                    this.yOffside1 = 60;
                    if (parent.equals("transistor"))
                    {
                        this.xOffside2 = 15;
                        this.yOffside2 = 0;
                        this.xOffside3 = 10;
                        this.yOffside3 = 60;
                    }
                    else
                    {
                        this.xOffside2 = 10;
                        this.yOffside2 = 60;
                        this.xOffside3 = 15;
                        this.yOffside3 = 0;
                    }
                    break;
            }
        }
        
        else
        {
            //This is for flip flops only. Set the correct offsides
            switch (orientation)
            {
                case 0:
                    this.xOffside1 = 0;
                    this.yOffside1 = 5;
                    this.xOffside2 = 60;
                    this.yOffside2 = 5;
                    this.xOffside3 = 60;
                    this.yOffside3 = 25;
                    this.xOffside4 = 0;
                    this.yOffside4 = 25;
                    break;
                case 1:
                    this.xOffside1 = 25;
                    this.yOffside1 = 0;
                    this.xOffside2 = 25;
                    this.yOffside2 = 60;
                    this.xOffside3 = 5;
                    this.yOffside3 = 60;
                    this.xOffside4 = 5;
                    this.yOffside4 = 0;
                    break;
                case 2:
                    this.xOffside1 = 60;
                    this.yOffside1 = 25;
                    this.xOffside2 = 0;
                    this.yOffside2 = 25;
                    this.xOffside3 = 0;
                    this.yOffside3 = 5;
                    this.xOffside4 = 60;
                    this.yOffside4 = 5;
                    break;
                case 3:
                    this.xOffside1 = 5;
                    this.yOffside1 = 60;
                    this.xOffside2 = 5;
                    this.yOffside2 = 0;
                    this.xOffside3 = 25;
                    this.yOffside3 = 0;
                    this.xOffside4 = 25;
                    this.yOffside4 = 60;
                    break;
            }
        } 
    }
    
    /** Gets the orientation of the object.
     */
    public int getOrientation()
    {
        return orientation;
    }
    
    /** Gets the x offside for the side.
     * @param side  the side
     * @return the offside for the side needed
     */
    public int getXOffside(int side)
    {
        switch (side)
        {
            case 1:
                return xOffside1;
            case 2:
                return xOffside2;
            case 3:
                return xOffside3;
            case 4:
                return xOffside4;
        }
        return 0;
    }
    
    /** Gets the y offside for the side.
     * @param side the side
     * @return the offside for the side needed
     */
    public int getYOffside(int side)
    {
        switch (side)
        {
            case 1:
                return yOffside1;
            case 2:
                return yOffside2;
            case 3:
                return yOffside3;
            case 4:
                return yOffside4;
        }       
        return 0;
    }
    
    /** Sets the button to the switcher button.
     * @param button    the objects button
     */
    public void setButton(JButton button)
    {
        switcher = button;
    }
    
    /** Gets the objects button.
     * @return the objects button
     */
    public JButton getButton()
    {
        return switcher;
    }
}
