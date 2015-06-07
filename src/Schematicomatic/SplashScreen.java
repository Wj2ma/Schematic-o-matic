package Schematicomatic;

/** RotateController Class
 * The splash screen class that displays the loading screen at boot up
 * Date Created: 17/01/2013
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */
import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.*;

public class SplashScreen 
{
    /** Displays the logo, program name and contractors names
     * @param f The frame where the splash screen will be displayed on
     */
    public static void splashScreen (JFrame f)
    {
        JPanel splashScreen = new JPanel(); //The main panel
        JPanel top = new JPanel();          //the panel with the progarm name
        JPanel bot = new JPanel();          //the panel with the contractors names
        
        JLabel logo = new JLabel();     //the label where the logo picture will be
        JLabel program = new JLabel();  //the label where the program name will be
        JLabel authors = new JLabel();  //the label with the authors names
        
        Font font = new Font("Virtual DJ", Font.BOLD, 80);  //creates the new font
        Font font2 = new Font("Virtual DJ", Font.BOLD, 20); //creates the new font
        
        //sets the background colour and adds the label to the panel
        top.setBackground(Color.WHITE);
        top.add(program);
        
        //sets the font for the text
        authors.setFont(font2);
        authors.setText("Created By: William Ma, Theodore Tan, and Ryan Hasmatali");
        
        //sets teh background colour and adds the label to the panel
        bot.setBackground(Color.WHITE);
        bot.add(authors);
        
        //sets the font for the text
        program.setFont(font);
        program.setText("Schematic-o-matic");
        
        //sets the layout for the main screen, sets the background colour and adds the components
        splashScreen.setLayout(new BorderLayout());
        splashScreen.setBackground(Color.WHITE);
        splashScreen.add(top, BorderLayout.NORTH);
        splashScreen.add(bot, BorderLayout.SOUTH);
        
        //The loading process
        for (int count = 0; count < 4; count++)
        {
            splashScreen.remove(logo);
            logo = new JLabel(new ImageIcon ("Resorces/logo"+count+".png"));
            splashScreen.add(logo, BorderLayout.CENTER);
            f.setContentPane(splashScreen);
            f.setVisible(true);
            
            //makes the program sleep for 1 seconds to let the user see the loading process
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
