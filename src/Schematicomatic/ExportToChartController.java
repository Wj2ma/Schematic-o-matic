/** ExportToChartController Class
 * The controller that is used to create a bill of materials including most of the parts used in the diagram
 * Date Created: 18/1/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
package Schematicomatic;

import java.awt.event.*;


public class ExportToChartController implements ActionListener {
 
    SchematicomaticModel model; //Instance of the model to be used for interfacing with the main model 
    SchematicomaticView view; //Instance of the view to be used for interfacing with the mainscreen
    
     /**Constructor for the Export to Chart Controller. Interfaces the Main screen and the model with the controller. 
     * @param v Instance of the Main screen
     * @param m Instance of the Main model
     */
     public ExportToChartController(SchematicomaticModel m, SchematicomaticView v){
        model = m;
        view = v;
    }
     
    /**Exports the Bill of materials to a chart 
     * @param e The event sent from the button component.
     */
    public void actionPerformed(ActionEvent e) {
        
        model.exportToChart(view.getNumPic(), view.getNumHBridge());
    }
    
}
