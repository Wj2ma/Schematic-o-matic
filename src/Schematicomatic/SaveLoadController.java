package Schematicomatic;

/** SaveLoadController Class
 * The controller class that saves or loads the schematics
 * Date Created: 03/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.event.*;

public class SaveLoadController extends Object implements ActionListener
{
    SchematicomaticView view;   //Instance of the view to be used for interfaceing with the Main screen
    
    /** Constructor for the Save/Load Controller. Interfaces the Main Screen with the controller.
     * @param v Instance of the Main screen
     */
    public SaveLoadController(SchematicomaticView v)
    {
        view = v;
    }
    
    /** Saves or loads the files when the button is pressed.
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("Save"))
            view.save();
        else
            view.load();
    }
}
