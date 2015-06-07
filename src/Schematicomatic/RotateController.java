package Schematicomatic;

/** RotateController Class
 * The controller class that rotates the object
 * Date Created: 03/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.event.*;

public class RotateController extends Object implements ActionListener
{
    SchematicomaticView view;   //Instance of the view to be used for interfaceing with the Main screen
    MouseController2 mouse; //Instance of the mouse to be used for interfacing with the Main screen
    
    /** Constructor for the Rotate Controller. Interfaces the Main Screen with the controller.
     * @param v Instance of the Main screen
     * @param mo Instance of the mouse
     */
    public RotateController(SchematicomaticView v, MouseController2 mo)
    {
        view = v;
        mouse = mo;
    }
    
    /** Rotates the object when the button is pressed.
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e)
    {
        try{
            mouse.getTempD().rotate();
            view.updateBounds(mouse.getTempD());
        } catch (NullPointerException ex){}       
    }
}
