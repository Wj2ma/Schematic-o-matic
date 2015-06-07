/** GatesController Class
 * The controller that is used to highlight and un-highlight components
 * Date Modified: 20/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
package Schematicomatic;

import java.awt.event.*;

public class HighlighterController extends Object implements ActionListener
{
    MouseController mouse; // Instance of the Mouse Controller used for interfacing mouse  
    MouseController2 mouse2; // Instance of the Mouse Motion Controller used for interfacing with the mount
    SchematicomaticView view; //Instance of the view to be used for interfacing with the Main screen   
    
    /**Constructor for the High-lighter Controller. Interfaces the Main screen with the controller. 
     * @param view Instance of the Main screen
     * @param m Instance of the Mouse Listener
     * @param m2 Instance of the Mouse Motion Controller
     */ 
    public HighlighterController(MouseController m, MouseController2 m2, SchematicomaticView v)
    {
        mouse = m;
        mouse2 = m2;
        view = v;
    }
    
    /**Turns highlight mode on if the highlight button is pressed else the un-highlight mode is turned on
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("Highlight"))
        {
            mouse.setHighlight(true);
            mouse2.setHighlight(true);
            view.showHighlights(false);
        }       
        else
        {
            mouse.setUnHighlight(true);
            mouse2.setUnHighlight(true);
            view.showHighlights(false);
        }
    }
}
