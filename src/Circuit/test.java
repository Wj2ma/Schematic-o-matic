/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Circuit;

/**
 *
 * @author dushz77
 */
import java.util.*;
import java.awt.Color;

public class test 
{
    static ArrayList <CircuitPart> list = new ArrayList <CircuitPart>();
    public static void main(String []args)
    {
        ANDGate gate = new ANDGate("a");
        ANDGate gate2 = new ANDGate("b");
        ANDGate gate3 = new ANDGate("c");
        Circuit circuit = new Circuit();
        circuit.newPart("andgate", 0);
        circuit.newPart("andgate", 0);
        circuit.newPart("andgate", 0);
        circuit.newPart("orgate", 0);
        circuit.newPart("led", LED.BLUE);
        circuit.newPart("plusbattery", 6);
//        circuit.newPart("posbattery", 6);
//        circuit.newPart("posbattery", 6);
//        circuit.newPart("posbattery", 6);
        circuit.newPart("negbattery", 0);
        
        ArrayList <ANDGate> aGate = circuit.getANDGates();
        ArrayList <ORGate> oGate = circuit.getORGates();
        ArrayList <PosBattery> pBat = circuit.getPosBatteries();
        ArrayList <NegBattery> nBat = circuit.getNegBatteries();
        ArrayList <LED> led = circuit.getLEDs();
        
        circuit.setConnection(pBat.get(0), 1, led.get(0), 1);
        circuit.setConnection(nBat.get(0), 1, led.get(0), 2);
        circuit.updatePower();
        System.out.println(led.get(0).isPower(1));
        System.out.println(led.get(0).isNegPower(2));
        
//        circuit.setConnection(pBat.get(0), 1, aGate.get(0), 1);
//        circuit.setConnection(pBat.get(1), 1, aGate.get(0), 2);
//        circuit.setConnection(pBat.get(2), 1, aGate.get(1), 1);
//        circuit.setConnection(pBat.get(3), 1, oGate.get(0), 1);
//        circuit.setConnection(aGate.get(0), 3, aGate.get(2), 1);
//        circuit.setConnection(aGate.get(1), 3, aGate.get(2), 2);
//        circuit.setConnection(aGate.get(2), 3, led.get(0), 1);
//        circuit.setConnection(oGate.get(0), 3, led.get(0), 1);
//        circuit.setConnection(led.get(0), 2, nBat.get(0), 2);
//        circuit.setConnection(oGate.get(0), 2, pBat.get(3), 1);
//        circuit.updatePower();
//        System.out.println(aGate.get(0).isPower(1));
//        System.out.println(aGate.get(0).isPower(2));
//        System.out.println(aGate.get(0).isNegPower(3));
//        System.out.println();
//        System.out.println(aGate.get(1).isPower(1));
//        System.out.println(aGate.get(1).isPower(2));
//        System.out.println(aGate.get(1).isNegPower(3));
//        System.out.println();
//        System.out.println(aGate.get(2).isPower(1));
//        System.out.println(aGate.get(2).isPower(2));
//        System.out.println(aGate.get(2).isNegPower(3));
//        System.out.println();
//        System.out.println(oGate.get(0).isPower(1));
//        System.out.println(oGate.get(0).isPower(2));
//        System.out.println(oGate.get(0).isNegPower(3));
//        System.out.println();
//        System.out.println(led.get(0).isPower(1));
//        System.out.println(led.get(0).isNegPower(2));
//        do
//        {
//            System.out.println("a");
//        } while (1==1);
        
    }
}
