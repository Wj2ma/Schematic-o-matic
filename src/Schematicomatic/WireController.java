package Schematicomatic;

/** WireController Class
 * A WireController object that turns on wire mode in the MouseControllers.
 * Date Created: 28/12/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */

import java.awt.event.*;
import javax.swing.*;

public class WireController extends Object implements ActionListener
{
    private MouseController2 mouse2;    //The mouse listener that will be modified based on this class
    private MouseController mouse;      //The second mouse listener that will be modified based on this class
    private SchematicomaticView view;   //The view for the schematic window
    
    /**Constructor for WireController. Sets the different modes for the MouseControllers
     * @param m     The first mousecontroller
     * @param mo    The second mousecontroller
     * @param v     The view of the schematic
     */ 
    public WireController(MouseController m, MouseController2 mo, SchematicomaticView v)
    {
        mouse = m;
        mouse2 = mo;
        view = v;
    }
    
    /**Sets the MouseController's startWire to true to turn on draw wire mode
     * @param e     The event called by the wire button
     */ 
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("black wire"))
        {
            //Turn on the wire modes and make the colour 0 for black
            mouse2.startWire(true);
            mouse.startWire(true, 0);
            //Hide the top panel and show the stop button
            view.hideAll();
            view.showStop(true);
        }
        else if (e.getActionCommand().equals("blue wire"))
        {
            //Same thing as top but sets colour to 1 for blue
            mouse2.startWire(true);
            mouse.startWire(true, 1);
            view.hideAll();
            view.showStop(true);
        }
        else if (e.getActionCommand().equals("red wire"))
        {
            mouse2.startWire(true);
            mouse.startWire(true, 2);  //2 is for red wire
            view.hideAll();
            view.showStop(true);
        }
        else if (e.getActionCommand().equals("yellow wire"))
        {
            mouse2.startWire(true);
            mouse.startWire(true, 3);  //3 is for yellow wire
            view.hideAll();
            view.showStop(true);
        }
        else if (e.getActionCommand().equals("green wire"))
        {
            mouse2.startWire(true);
            mouse.startWire(true, 4);  //4 is for green wire
            view.hideAll();
            view.showStop(true);
        }
        else if (e.getActionCommand().equals("purple wire"))
        {
            mouse2.startWire(true);
            mouse.startWire(true, 5);  //5 is for purple wire
            view.hideAll();
            view.showStop(true);
        }
        else if (e.getActionCommand().equals("orange wire"))
        {
            mouse2.startWire(true);
            mouse.startWire(true, 6);  //6 is for orange wire
            view.hideAll();
            view.showStop(true);
        }
    }
}

