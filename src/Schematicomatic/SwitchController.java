package Schematicomatic;

/** SwitchController Class
 * The controller class that creates a switch
 * Date Created 03/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.event.*;

public class SwitchController implements ActionListener{
    
    SchematicomaticView view;   //Instance of the view to be used for interfacing with the main screen
    
    /** Constructor for the Switch controller. Interfaces the main screen with the controller. 
     * @param view Instance of the main screen
     */
    public SwitchController (SchematicomaticView view){
        this.view = view;
    }
    
    /** Creates the a new switch
     * @param e The event sent from the button component.
     */
    public void actionPerformed (ActionEvent e){
        if (e.getActionCommand().equals("traditional switch")){
            view.createPart("switch", 1);
        }
        else if (e.getActionCommand().equals("positive switch")){
            view.createPart("switch", 2);
        }
        else if (e.getActionCommand().equals("negative switch")){
            view.createPart("switch", 3);
        }
    }
}
