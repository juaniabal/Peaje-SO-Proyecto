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
 * @author Agroa
 */
public class HiloLevantarBarrera implements Runnable {
    private Casilla casilla = null;
    public HiloLevantarBarrera(Casilla casilla){
        this.casilla = casilla;
    }
    @Override
    public void run() {
        try {
            while (true) {
                if(this.casilla.getListaVehiculos().isEmpty() && !Main.peaje.isVehiculosEnCola()) break;
                Main.semaforoVehiculos.decrease();
                if(this.casilla.getListaVehiculos().size()>0) System.out.println("Se ha despachado al vehiculo : " + this.casilla.getListaVehiculos().pollFirst().getID() + " de la casilla " + this.casilla.getID());
                Main.semaforoVehiculos.increase();
                Thread.sleep(1000);
                
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(HiloLevantarBarrera.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
