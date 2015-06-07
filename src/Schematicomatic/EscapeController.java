package Schematicomatic;

/** UPCOMING IN FURTHER PATCH..
 */ 
import java.awt.event.*;

public class EscapeController extends Object implements KeyListener
{
    SchematicomaticView view;
    MouseController2 mouse2;
    
    public EscapeController(SchematicomaticView v, MouseController2 mo)
    {
        view = v;
        mouse2 = mo;
    }
    
    public void keyPressed(KeyEvent e)
    {
        System.out.println();
    }
    public void keyReleased(KeyEvent e){
        System.out.println();
    }
    public void keyTyped(KeyEvent e)
    {
        if (mouse2.isWireStart() == true)
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                view.removeWire(mouse2.getTempW());
    }
}
