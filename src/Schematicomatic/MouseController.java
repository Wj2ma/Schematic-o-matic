package Schematicomatic;

/** MouseController Class
 * A MouseController object that tracks mouse movement in the program.
 * Date Created: 08/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.event.*;
import java.awt.*;

public class MouseController extends Object implements MouseMotionListener
{
    private SchematicomaticView view;       //The view of the schematic
    private MouseController2 mouse2;        //The other mouselistener
    private boolean wireStart = false;      //determines whether the first click for draw wire has been pressed
    private boolean highlight = false;      //determines whether the user is in highlight mode
    private boolean unHighlight = false;    //determines whether the user is in unhighlight mode
    private boolean delete = false;         //determines whether the user is in delete mode
    private boolean enabled = true;         //determines whether this listener should be enabled or not
    private boolean first = false;          //determines whether the user has already clicked the first point for drawing a wire
    private int colour;                     //the colour of the wire
    
    /**Contructor for MouseController. Manipulates the view based on user mouse input
     * @param v     The view showing the schematic window
     * @param mo    The other mouselistener that tracks mouse clicks
     */ 
    public MouseController(SchematicomaticView v, MouseController2 mo)
    {
        view = v;
        mouse2 = mo;
        mouse2.setMouse(this);
    }
    
    /** Drags a drawing around the screen when user drags the mouse with an image underneath
     * @param e     The event sent from the mouse
     */ 
    public void mouseDragged(MouseEvent e)
    {
        //If any of these are true or enabled is false, do nothing
        if (!enabled || delete || highlight || unHighlight);
        
        //If in wire mode
        else if (wireStart)
        {
            //If the listener came from the schematicPanel
            if (e.getComponent() == view.getSchematicPanel())
            {
                //If the first point of wire has already been drawn, call
                //the view's draw wire giving this mouse location rounded to the
                //nearest 5 pixels as the parameter. Also set first to false
                if (first)          
                    view.drawWire((int)Math.round(e.getX()/5.0)*5, (int)Math.round(e.getY()/5.0)*5);
                else
                {
                    //Call the view to create a new wire giving this mouse location rounded
                    //to the nearest 5 pixels as the parameter. Set first to true
                    view.createWire((int)Math.round(e.getX()/5.0)*5, (int)Math.round(e.getY()/5.0)*5, colour);
                    first = true;
                }
            }
        }
        
        else
        {
            //Make d equal to the selected drawing in mouse2
            Drawing d = mouse2.getTempD();
           
            if (d != null)
            {
                //If the part is horizontal, subtract 30 from the x
                //and 15 from the y and if it is vertical, subtract 15 from the x and
                //30 from the y
                if (mouse2.getTempD().getOrientation()%2 == 0)
                    view.dragDrop(mouse2.getTempD(), e.getPoint().x-30, e.getPoint().y-15);
                else
                    view.dragDrop(mouse2.getTempD(), e.getPoint().x-15, e.getPoint().y-30);
            }
        }
    }
    
    /** Part of the mousemotionlistener interface. Does nothing.
     */ 
    public void mouseMoved(MouseEvent e){}       
    
    /**Sets the delete to true or false to start deleting or stop deleting
     * @param b     the value that will be stored into delete
     */ 
    public void deleteMode(boolean b)
    {
        delete = b;
    }
    
    /**Sets the highlight to be true or false to start highlighting or stop highlighting
     * @param b     the value that will be stored into highlight
     */ 
    public void setHighlight(boolean b)
    {
        highlight = b;
    }
    
    /** Sets the unHighlight to be true or false to start unhighlighting or stop unhighlighting 
     * @param b     the value that will be stored into unHighlight
     */
    public void setUnHighlight(boolean b)
    {
        unHighlight = b;
    }
    
    /** Sets the enable to be true or false to enable this listener or disable it
     * @param b     the value that will be stored into enabled
     */ 
    public void enable(boolean b)
    {
        enabled = b;
    }
    
    /** Sets the draw wire mode to on or off
     * @param b     the true/false value that wireStart will become
     * @param c     the integer value of a colour that was selected
     */ 
    public void startWire(boolean b, int c)
    {
        wireStart = b;
        colour = c;
        
        //If the first point of wire has been started already and the wiremode 
        //has been shut off, remove the most recent wire because it is unfinished
        if (first && !b)
        {
            view.removeWire();
            first = false;
        }
    }
    
    public void resetFirst()
    {
        first = false;
    }
}
