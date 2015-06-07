/** GatesController Class
 * The controller that is used to create Power Components
 * Date Modified: 20/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
package Schematicomatic;

import java.awt.event.*;


public class PowerController implements ActionListener{
    
    SchematicomaticView view; //Instance of the view to be used for interfacing with the mainscreen
     
    /**Constructor for the Gates Controller. Interfaces the Main screen with the controller. 
     * @param view Instance of the Main screen
     */
    public PowerController (SchematicomaticView view){
        this.view = view;
    }
    
    /**Creates a Power Component based on the actionCommand of the button pressed
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("positive battery")){
            view.createPart("plusbattery", 6);
        }
        else if (e.getActionCommand().equals("ground")){
            view.createPart("negbattery", 0);
        }
        else if (e.getActionCommand().equals("create custom battery"))
        {
            view.createPart("plusbattery", view.getInput());
        }
    }
    
}
