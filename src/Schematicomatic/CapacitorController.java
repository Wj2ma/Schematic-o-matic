/** CapacitorController Class
 * The controller that is used create a capacitor object
 * Date Created: 28/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
package Schematicomatic;

import java.awt.event.*;


public class CapacitorController implements ActionListener{
    
    private SchematicomaticView view; //Instance of the view to be used for interfacing with the mainscreen
    
    /**Constructor for the Capacitor Controller. Interfaces the Main screen with the controller. 
     * @param view Instance of the Main screen
     */
    public CapacitorController (SchematicomaticView view){
        this.view = view;
    }
    
    /**Creates a capacitor object and adds it to the SchematicPanel
     * @param e The event sent from the button component.
     */
    public void actionPerformed (ActionEvent e){
        if (e.getActionCommand().equals("one mf capacitor")){
            view.createPart("capacitor", 1);    //Create a capacitor part with the value of 1uf
        }
        if (e.getActionCommand().equals("ten mf capacitor")){
            view.createPart("capacitor", 10);   //Create a capacitor part with the value of 10uf
        }
        if (e.getActionCommand().equals("onehundred mf capacitor")){
            view.createPart("capacitor", 100);  //Create a capacitor part with the value of 100uf
        }
         else if (e.getActionCommand().equals("create custom capacitor")){
            view.createPart("capacitor", view.getInput());  //Create a capacitor part with a custom value specified in a text field on the Main screen 
        }
    }
    
}
