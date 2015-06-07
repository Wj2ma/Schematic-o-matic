package Schematicomatic;

/** TextController Class
 * The controller class that highlights the text field when clicked
 * Date Created: 17/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.event.*;
import javax.swing.*;

public class TextController extends Object implements MouseListener
{
    SchematicomaticView view;   //Instance of the view to be used for interfacing with the main screen
    JTextField text;    //Instance of the text field to be used for interfacing with the main screen
    
    /** Constructor for the Text Controller. Interfaces the main screen with the controller.
     * @param view Instance of the main screen
     * @param text Instance of the text field
     */
    public TextController(SchematicomaticView view, JTextField text)
    {
        this.view = view;
        this.text = text;
    }
    
    /** Highlights the text field.
     * @param e The event sent from the mouse.
     */
    public void mouseClicked(MouseEvent e)
    {
        text.selectAll();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
