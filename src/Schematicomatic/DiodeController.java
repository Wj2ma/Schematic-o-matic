/** DiodeController Class
 * The controller that is used create a new LED object and display on the screen
 * Date Created: 28/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
package Schematicomatic;

import java.awt.event.*;


public class DiodeController implements ActionListener {
    
    SchematicomaticView view;   //Instance of the view to be used for interfacing with the Mainscreen 

    /**Constructor for the Diode Controller. Interfaces the Main screen with the controller. 
     * @param view Instance of the Main screen
     */
    public DiodeController (SchematicomaticView view){
        this.view = view;   //Instatiates the view
    }
    
    /**Creates the desired diode based on the action command of the button pressed
     * @param e The event sent from the button component.
     */
    public void actionPerformed (ActionEvent e){
        
        if (e.getActionCommand().equals("green led")){
            view.createPart("led", Drawing.GREEN);
        }
        else if (e.getActionCommand().equals("red led")){
            view.createPart("led", Drawing.RED);
        }
        else if (e.getActionCommand().equals("blue led")){
            view.createPart("led", Drawing.BLUE);
        }
        else if (e.getActionCommand().equals("yellow led")){
            view.createPart("led", Drawing.YELLOW);
        }
        else if (e.getActionCommand().equals("diode")){
            view.createPart("diode", 0);
        }
    }
}
