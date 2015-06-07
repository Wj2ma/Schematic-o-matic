/** GatesController Class
 * The controller that is used to create Gate Components
 * Date Modified: 20/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
package Schematicomatic;

import java.awt.event.*;

public class GatesController implements ActionListener{
    
    SchematicomaticView view; //Instance of the view to be used for interfacing with the mainscreen
    
    /**Constructor for the Gates Controller. Interfaces the Main screen with the controller. 
     * @param view Instance of the Main screen
     */
    public GatesController (SchematicomaticView view){
        this.view = view;
    }
    
    /**Creates a Gate Component based on the actionCommand of the button pressed
     * @param e The event sent from the button component.
     */
    public void actionPerformed (ActionEvent e){
        if (e.getActionCommand().equals("and gate")){
            view.createPart("andgate", 0);  //0 for extras because gates do not require any extra qualities
        }
        else if (e.getActionCommand().equals("nor gate")){
            view.createPart("norgate", 0);
        }
        else if (e.getActionCommand().equals("or gate")){
            view.createPart("orgate", 0);
        }
        else if (e.getActionCommand().equals("not gate")){
            view.createPart("notgate", 0);
        }
        else if (e.getActionCommand().equals("xor gate")){
            view.createPart("xorgate", 0);
        }
        else if (e.getActionCommand().equals("nand gate")){
            view.createPart("nandgate", 0);
        }
        else if (e.getActionCommand().equals("flip flop")){
            view.createPart("flipflop", 0);
        }
    }
}
