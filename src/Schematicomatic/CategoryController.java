/** CategoryController Class
 * The controller that is used display the desired buttons on the screen
 * Date Created: 28/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
package Schematicomatic;

import java.awt.event.*;


public class CategoryController implements ActionListener {
    
    SchematicomaticView view;   //Instance of the view to be used for interfacing with the Main screen
    
    /**Constructor for the Category Controller. Interfaces the Main screen with the controller. 
     * @param view Instance of the Main screen
     */
    public CategoryController(SchematicomaticView view){
        this.view = view;
    }
    
    /**Displays the desired buttons on the screen based on the action command of the pressed button
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("gates")){
            view.showGates(true);
            view.showMain(false);
        }
        else if (e.getActionCommand().equals("transistors")){
            view.showTransistors(true);
            view.showMain(false);
        }
        else if (e.getActionCommand().equals("ics")){
            view.showICS(true);
            view.showMain(false);
        }
        else if (e.getActionCommand().equals("capacitors")){
            view.showCapacitors(true);
            view.showMain(false);
            view.setInput("microFarads");
        }
        else if (e.getActionCommand().equals("resistors")){
            view.showResistors(true);
            view.showMain(false);
            view.setInput("ohms");
        }
        else if (e.getActionCommand().equals("power supplies")){
            view.showPowerSupplies(true);
            view.showMain(false);
            view.setInput("volts");
        }
        else if (e.getActionCommand().equals("diodes")){
            view.showDiodes(true);
            view.showMain(false);
        }
        else if (e.getActionCommand().equals("switches")){
            view.showSwitches(true);
            view.showMain(false);
        }
        else if (e.getActionCommand().equals("wire")){
            view.showWire(true);
            view.showMain(false);
        }
    }
    
   
}
