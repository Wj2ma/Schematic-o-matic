package Schematicomatic;

/** StopController Class
 * The controller class that rotates the object
 * Date Created: 03/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.event.*;

public class StopController extends Object implements ActionListener {
    
    SchematicomaticView view;   //Instance of the view to be used for interfaceing with the Main screen
    MouseController mouse;      //Instance of the mouse to be used for interfacing with the Main screen
    MouseController2 mouse2;    //Instance of the mouse2 to be used for interfacing with the Main screen
    
    /**Constructor for the Stop Controller. Interfaces the Main screen with the controller.
     * @param m Instance of the mouse
     * @param m2 Instance of the mouse2
     * @param view Instance of the main screen
     */
    public StopController (MouseController m, MouseController2 m2, SchematicomaticView view){
        this.mouse = m;
        this.mouse2 = m2;
        this.view = view;
    }

    /** Stops the simulation.
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e) {
        mouse.deleteMode(false);
        mouse2.deleteMode(false);
        mouse.startWire(false, 0);
        mouse2.startWire(false);
        view.resetToMain();    
        view.showHighlights(true);
        mouse.setHighlight(false);
        mouse2.setHighlight(false);
        mouse2.setUnHighlight(false);
        mouse.setUnHighlight(false);
    }   
}