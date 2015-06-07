/** DeleteController Class
 * The controller that is used delete already created components
 * Date Created: 28/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
package Schematicomatic;

import java.awt.event.*;


public class DeleteController  implements ActionListener {

    MouseController mouse; // Instance of the Mouse Controller used for interfacing mouse  
    MouseController2 mouse2; // Instance of the Mouse Motion Controller used for interfacing with the mount
    SchematicomaticView view; //Instance of the view to be used for interfacing with the Main screen   
    
    /**Constructor for the Delete Controller. Interfaces the Main screen with the controller. 
     * @param view Instance of the Main screen
     * @param m Instance of the Mouse Listener
     * @param m2 Instance of the Mouse Motion Controller
     */
    public DeleteController (MouseController m, MouseController2 m2, SchematicomaticView view){
        this.mouse = m;
        this.mouse2 = m2;
        this.view = view;
    }
    
    /**Sets the mouse to delete mode and sets the stop button visible
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e) {
        mouse.deleteMode(true);
        mouse2.deleteMode(true);
        view.hideAll();
        view.showStop(true);
    }
    
}
