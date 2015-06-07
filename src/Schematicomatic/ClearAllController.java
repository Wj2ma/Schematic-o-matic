/** ClearAllController Class
 * The controller that is used to clear all the components on the schematic panel
 * Date Created: 18/1/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
package Schematicomatic;

import java.awt.event.*;

public class ClearAllController extends Object implements ActionListener
{
    SchematicomaticModel model; //Instance of the view to be used for interfacing with the mainscreen
    SchematicomaticView view;   //Instance of the model to be used for interfacing with the main model
    
    /**Constructor for the Clear All Controller. Interfaces the Main screen with the controller. 
     * @param v Instance of the Main screen
     * @param m Instance of the main model
     */
    public ClearAllController(SchematicomaticModel m, SchematicomaticView v)
    {
        model = m;
        view = v;
    }
    
    /**Shows the conformation screen to clear all then clears all the components if the user presses yes
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("Clear All"))
            view.showConfirmation(true);
        else if (e.getActionCommand().equals("NO"))
            view.showConfirmation(false);
        else if (e.getActionCommand().equals("YES"))
        {
            view.showConfirmation(false);
            view.clearAll();
            model.clearAll();
        }
    }
}
