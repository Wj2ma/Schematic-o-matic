package Schematicomatic;

/** StartUp Class
 * The main class. Starts the program.
 * Date Created: 24/12/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */

//project has 11520 lines of code

import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class StartUp
{
    public static void main(String [] args) throws IOException
    {
        SchematicomaticModel model = new SchematicomaticModel();    //creates a new instance of the model
        SchematicomaticView mainScreen = new SchematicomaticView(model);    //creates a main screen
        
        //gets the icon and sets it to an image
        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("logo.png"));
        Image temp = icon.getImage();
        
        JFrame f = new JFrame(); //creates a new JFrame
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        f.setSize(dimension.width, dimension.height-50);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(Frame.MAXIMIZED_BOTH);
        f.setVisible(true); 
        f.setResizable(false);
        f.setTitle("Schematic-o-matic");
        f.setIconImage(temp);
        
        //intilizes the splash screen
        SplashScreen.splashScreen(f);
        
        //sets the content pane to be the mainscreen
        f.setContentPane(mainScreen);
        f.setVisible(true);
    }
    
}