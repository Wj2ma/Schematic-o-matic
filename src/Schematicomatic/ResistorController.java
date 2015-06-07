/** GatesController Class
 * The controller that is used to create Resistor Components
 * Date Modified: 20/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
package Schematicomatic;

import java.awt.event.*;


public class ResistorController implements ActionListener {
     
    SchematicomaticView view; //Instance of the view to be used for interfacing with the mainscreen
     
    /**Constructor for the Resistor Controller. Interfaces the Main screen with the controller. 
     * @param view Instance of the Main screen
     */
    public ResistorController (SchematicomaticView view){
        this.view = view;
    }
    
    /**Creates a Resistor Component based on the actionCommand of the button pressed
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("one k resistor"))
        {
            view.createPart("resistor", 1000);  //1000 is the resistance of the resistor
        }
        else if (e.getActionCommand().equals("ten k resistor"))
        {
            view.createPart("resistor", 10000);
        }
        else if (e.getActionCommand().equals("fourseventy resistor"))
        {
            view.createPart("resistor", 470);
        }
        else if (e.getActionCommand().equals("create custom resistor"))
        {
            view.createPart("resistor", view.getInput());
        }
    }
}
