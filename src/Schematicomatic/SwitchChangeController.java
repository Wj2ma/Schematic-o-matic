package Schematicomatic;

/** SwitchChangeController Class
 * The controller class that changes the switch
 * Date Created: 03/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.event.*;
import javax.swing.*;

public class SwitchChangeController extends Object implements ActionListener {
    //SchematicomaticModel model;
    SchematicomaticView view;   //Instance of the view to be used for interfacing with the main screen
    
    /** Constructor for the Switch Change controller. Interfaces the main screen with the controller
     * @param view Instance of the main screen
     */
    public SwitchChangeController (SchematicomaticView view)
    {
        this.view = view;
        //this.model = model;
    }
    
    /** Changes the label from a label to a button.
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e)
    {
        
        if (e.getActionCommand().equals("trad switch"))
        {
            view.changeLabel("trad switch", (JButton) e.getSource());
        }
        else if (e.getActionCommand().equals("pos switch"))
        {
            view.changeLabel("pos switch",(JButton) e.getSource());
        }
        else if (e.getActionCommand().equals("neg switch"))
        {
            view.changeLabel("neg switch",(JButton) e.getSource());
        }
    }
}