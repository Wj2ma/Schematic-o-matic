package Schematicomatic;

/** SearchController Class
 * The controller class that allows the user to search for a part.
 * Date Created: 03/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.event.*;
import javax.swing.JTextField;

public class SearchController implements ActionListener{
    
    SchematicomaticView view;   //Instance of the view to be used for interfaceing with the Main screen
    JTextField text; //Instance of the text field to be used for interfaceing with the Main screen
    
    /** Constructor for the Search Controller. Interfaces the Main Screen with the controller.
     * @param view Instance of the Main screen
     * @param t Instance of the text field
     */
    public SearchController(SchematicomaticView view, JTextField t){
        this.view = view;
        this.text = t;
    }

    /** Gets the text from the text field and shows the part(s) found on screen.
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e) {
        
        String part = text.getText();
        view.showSearch(part);
    }
    
    
}
