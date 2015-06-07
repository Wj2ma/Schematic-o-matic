package Schematicomatic;

/** TransistorController Class
 * The controller class that creates a new transistor
 * Date Created: 03/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.event.*;

public class TransistorController implements ActionListener{
    SchematicomaticView view;   //Instance of the view to be used for interfacing with the main screen
    
    /** Constructor for the Transistor Controller. Interfaces the main screen with the controller.
     * @param view Instance of the main screen
     */
    public TransistorController (SchematicomaticView view){
        this.view = view;
    }
    
    /** Creates a new transistor.
     * @param e The event sent from the button component.
     */
    public void actionPerformed (ActionEvent e){
        if (e.getActionCommand().equals("npn transistor")){
            view.createPart("transistor", 1);
        }
        else if (e.getActionCommand().equals("pnp transistor")){
            view.createPart("transistor", 2);
        }
    }
}
