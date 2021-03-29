/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eacosta
 */
public class Tiempo implements Runnable {

    @Override
    public void run() {
        while (Main.relojActivo) {
            try {
                Main.semaforoReloj.decrease();
                Main.reloj = Main.reloj + 1;
                //System.out.println("El tiempo transcurrido es: " + Main.reloj);
                Main.semaforoReloj.increase();
            } catch (InterruptedException ex) {
                Logger.getLogger(Tiempo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
