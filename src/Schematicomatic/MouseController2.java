package Schematicomatic;

/** MouseController2 Class
 * A MouseController2 object that tracks mouse clicks and holds in the program.
 * Date Created: 8/1/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.event.*;
import java.awt.*;

public class MouseController2 extends Object implements MouseListener
{
    private SchematicomaticView view;       //The view of the schematic
    private SchematicomaticModel model;     //The model used to simulate the schematic
    private Drawing temp;                   //The drawing that is selected with the mouse
    private Wire temp2;                     //The wire that is selected with the mouse
    private MouseController mouse;          
    
    private boolean delete = false;         //determines whether the user is in delete mode
    private boolean wireStart = false;      //determines whether the user is in draw wire mode
    private boolean highlight = false;      //determines whether the user is in highlight mode
    private boolean unHighlight = false;    //determines whether the user is in unhighlight mode
    
    
    /**Constructor for MouseController2. Manipulates the view and model based on user mouse inputs
     * @param v     the view showing the schematic window
     * @param m     the model that will affect the view's simulation
     */ 
    public MouseController2(SchematicomaticView v, SchematicomaticModel m)
    {
        this.view = v;
        this.model = m;
    }
    
    /**Tries to select a drawing and deselect the previous drawing
     * @param e     the event sent from the mouse
     */ 
    public void mousePressed(MouseEvent e)
    {
        //Try to deselect the current drawing that is selected
        try {
            temp.setSelected(false);
        } catch (NullPointerException ex){} //Does nothing if there is no drawing
        
        //Make temp equal to null
        this.setTemp();
        
        //Try to capture a drawing if the mouse is on the location of a drawing
        try{
            temp = (Drawing)e.getComponent();
            temp.setSelected(true);
        } catch (ClassCastException ex){}   //does nothing if mouse is over nothing
    }
    
    /** Calls the model to restart all the connections of the drawing the use just moved
     * @param e     the event sent from the mouse
     */ 
    public void mouseReleased(MouseEvent e)
    {
        //If any of the modes are on, do nothing
        if (delete || highlight || unHighlight);
        
        else if (wireStart)
        {
            mouse.resetFirst();
            view.findConnection();
        }
        
        //If a drawing is selected, remove all of the part's connections and find its new connections
        else if (temp != null)
        {
            this.model.removeAllConnections(temp.getName());
            this.view.removeConnections(temp, null);
            this.view.findConnection(temp, null);   
        }
    }
    
    /**Part of the mouselistener interface. Does nothing.
     */ 
    public void mouseEntered(MouseEvent e){}
    
    /**Part of the mouselistener interface. Does nothing.
     */ 
    public void mouseExited(MouseEvent e){}
    
    /**Tries to deselect a drawing. Can delete, select, highlight, or unhighlight 
     * drawings and wire based on the mode it is in.
     * @param e     the event sent from the mouse
     */ 
    public void mouseClicked(MouseEvent e)
    {
        //Tries to deselect the drawing that was just selected
        try{
            temp.setSelected(false);
        } catch (NullPointerException ex){}
        //Get rid of the previous drawing and wire that was selected by the mouse before
        temp = null;
        temp2 = null;
        
        
        if (wireStart);
        
        else
        {
            //Try to select a drawing underneath the mouse location
            try{
                    temp = (Drawing)e.getComponent();
                    temp.setSelected(true);         //set this drawing to be selected
                } catch (ClassCastException ex){} //if there is no drawing underneath, do nothing           

            //if a drawing is selected and delete mode is on
            if (this.temp != null && delete)
            {
                //Call the view and model to remove all the connections to this part
                //and delete the part afterwards
                this.view.removePart(this.temp);
                this.model.removeAllConnections(this.temp.getName());
                this.model.deletePart(this.temp.getName());
            }

            //if delete or highlight or unhighlight is on
            else if (delete || highlight || unHighlight)
            {
                //Store this mouse location into x and y that are rounded to the
                //nearest 5 pixels
                int x = (int) Math.round(e.getPoint().x/5.0)*5;
                int y = (int) Math.round(e.getPoint().y/5.0)*5;

                //For each wire in the list of wire in view, if the x and y value
                //is inside the bounds of the wire, then store that wire into temp2.
                //break the loop afterwards
                for (Wire wire: view.getListW())
                {
                    Point loc = wire.getLocation();
                    if (x>=loc.getX() && x<=loc.getX()+wire.getWidth() && y>=loc.getY() && y<=loc.getY()+wire.getHeight())
                    {
                        temp2 = wire;
                        break;
                    }
                }

                if (temp2 != null)
                {
                //If delete mode is on, call the view to remove all connections to this wire
                //and delete the wire afterwards.
                    if (delete)    
                    {
                        this.view.removeConnections(temp2);
                        this.view.removeWire(temp2);
                    }

                    //If highlight mode is on,call the selected wire's setHighlighted(true)
                    //to highlight it
                    else if (highlight)
                        temp2.setHighlighted(true);

                    //The only option left is unhighlighted mode, so call the
                    //selected wire's setHighlighted(false) to unhighlight it
                    else
                        temp2.setHighlighted(false);
                }
            } 
        }
    }
        
    /**Gets rid of the selected drawing     Note: possibly not needed anymore after
     *                                      changing many different methods.. but keep it just in case for now
     */ 
    private void setTemp()
    {
        temp = null;
    }
    
    /** Sets the delete mode to on or off
     * @param b     the true/false value that delete will become
     */ 
    public void deleteMode(boolean b)
    {
        delete = b;
    }
    
    /** Gets the drawing that is selected
     * @returns the selected drawing temp
     */
    public Drawing getTempD()
    {
        return temp;
    }
    
    /** Gets the wire that is selected
     * @returns the selected wire, temp2
     */ 
    public Wire getTempW()
    {
        return temp2;
    }
    
    /** Checks to see if draw wire mode is on
     * @return the wire mode
     */ 
    public boolean isWireStart()
    {
        return wireStart;
    }
    
    /** Sets the highlight mode to be on or off
     * @param b     The true/false that turns highlight on or off
     */ 
    public void setHighlight(boolean b)
    {
        highlight = b;
    }
    
    /** Sets the unhighlight mode to be on or off
     * @param b     the true/false that turns unhighlight on or off
     */ 
    public void setUnHighlight(boolean b)
    {
        unHighlight = b;
    }
    
    /**Sets the wireStart to true or false to start drawing a wire or stop drawing wire
     * @param b     the value that will be stored into wireStart
     */ 
    public void startWire(boolean b)
    {
        wireStart = b;
    }
    
    public void setMouse(MouseController m)
    {
        mouse = m;
    }
}

