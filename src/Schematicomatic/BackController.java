/** BackController Class
 * The controller that is used to reset the GUI to the main panel of buttons
 * Date Created: 28/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
package Schematicomatic;

import java.awt.event.*;


public class BackController implements ActionListener{
    
    private SchematicomaticView view; //Instance of the view to be used for interfacing with the mainscreen
    
    /**Constructor for the Back Controller. Interfaces the Main screen with the controller. 
     * @param view Instance of the Main screen
     */
    public BackController(SchematicomaticView view){
        this.view = view; //Instatiates the view
    }
    
    /**Returns the Button Panel to its default state.
     * @param e The event sent from the button component.
     */
    public void actionPerformed (ActionEvent e){
        view.resetToMain(); //Calls the method to reset the buttons panel
    }   
}

