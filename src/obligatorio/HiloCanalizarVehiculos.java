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
public class HiloCanalizarVehiculos implements Runnable {

    @Override
    public void run() {
        try {
            while (Main.peaje.isVehiculosEnCola()) {
                Main.semaforoVehiculos.decrease();
                boolean estaLlena = false;
                for (Casilla casilla : Main.peaje.getListaCasillas()) {
                    if (casilla.getListaVehiculos().size() < 10) {
                        casilla.getListaVehiculos().add(Main.carretera.pollFirst());
                        
                        //Thread.sleep(2);
                        System.out.println("La casilla : " + casilla.getID() + " tiene " + casilla.getListaVehiculos().size() + " vehiculos");
                        estaLlena = false;
                        break;
                    }else{
                        estaLlena = true;
                        break;
                    }
                }if(estaLlena){
                    Main.peaje.casillaMenosOcupada().getListaVehiculos().add(Main.carretera.pollFirst());
                    System.out.println("La casilla : " + Main.peaje.casillaMenosOcupada().getID() + " tiene " + Main.peaje.casillaMenosOcupada().getListaVehiculos().size()+ " vehiculos");
                }
                Main.semaforoVehiculos.increase();
                Thread.sleep(10);
            }
            System.out.println("Algo");
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloCanalizarVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
