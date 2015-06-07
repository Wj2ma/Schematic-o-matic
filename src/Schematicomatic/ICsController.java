/** IC Controller Class
 * The controller that is used to create IC components
 * Date Created: 28/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
package Schematicomatic;

import java.awt.event.*;

public class ICsController implements ActionListener{
    
    SchematicomaticView view; //Instance of the view to be used for interfacing with the mainscreen
    
    /**Constructor for the IC Controller. Interfaces the Main screen with the controller. 
     * @param view Instance of the Main screen
     */
    public ICsController (SchematicomaticView view){
        this.view = view;
    }
    
    /**Creates a IC Component based on the actionCommand of the button pressed
     * @param e The event sent from the button component.
     */
    public void actionPerformed (ActionEvent e){
        if (e.getActionCommand().equals("hbridge")){
            view.createPart("hbridge", 0);
        }
        else if (e.getActionCommand().equals("pic24")){
            view.createPart("pic24", 0);
        }
    }
}
