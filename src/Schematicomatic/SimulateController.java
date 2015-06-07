package Schematicomatic;

/** RotateController Class
 * The controller class that rotates the object
 * Date Created: 03/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.event.*;

public class SimulateController extends Object implements ActionListener {

    SchematicomaticView view;   //Instance of the view to be used for interfaceing with the Main screen
    SchematicomaticModel model; //Instance of the model to be used for interfacing with the Main screen
    
    /** Constructor for the Simulate Controller. Interfaces the Main Screen with the controller.
     * @param view  Instance of the Main screen
     * @param model Instance of the model
     */
    public SimulateController (SchematicomaticView view, SchematicomaticModel model)
    {
        this.view = view;
        this.model = model;
    }
    
    /** Begins or ends simulation when the button is pressed.
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("simulate")){
            model.simulate();
            view.setStartStopSimulate("Stop", "stop simulate");
            view.changeLabelOn();
            view.update();
            view.resetToMain();
            view.enableAllButtons(false);
            view.enableAllActions(false);
        }
        else if(e.getActionCommand().equals("stop simulate")){
            view.turnOffLEDs();
            view.setStartStopSimulate("Start", "simulate");
            view.changeLabelOff();
            view.enableAllButtons(true);
            view.enableAllActions(true);
            model.resetFlipFlops();
        }
    }
}
