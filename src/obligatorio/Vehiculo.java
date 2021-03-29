/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Vehiculo.
 *
 * @author Eacosta
 */
public class Vehiculo implements IVehiculo, Runnable {

    public int getTipoVehiculo() {
        return tipoVehiculo;
    }
    //En el vehiculo un int tiempo. Debendiendo el tipo de vehiculo
    //Hay que agregar también el tipo de vehiculo del cual estamos tratando (Esto en el archivo)
    //Tambien tenemos que pensar como agregarle la prioridad (En mi parecer hay que hacerlo con un metodo setPriority(NUMERO) que lo que hace es darle una prioridad
    //al Thread...

    private final int ID;
    //private final Peaje peaje;
    private int telePeaje;
    private int tiempoInicio, tiempoFinal;
    private int tipoVehiculo;
    

    /**
     * Constructor de la clase Vehiculo.
     *
     * @param id
     * @param peaje
     */
    public Vehiculo(int id, Peaje peaje, int telePeaje) {
        this.ID = id;
        //this.peaje = peaje;
        this.telePeaje = telePeaje;
    }

    /**
     * ID getter.
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    @Override
    public int pagarPeaje(int precio) {
        if (this.telePeaje >= precio) {
            this.telePeaje -= precio;
            return precio;
        } else {
            System.out.println("El vehiculo " + this.getID() + " no tiene saldo suficiente");
            return 0;
        }
    }

    @Override
    public void avanzar() {
   
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void run() {
        //peaje.nuevoVehiculo(this);
        //Thread.sleep(200);
        avanzar();
        /*        try {
        //Main.semaforoMomento.decrease();
        //Main.momento++;
        //System.out.println("Momento." + Main.momento);
        //Main.semaforoMomento.increase();
        
        //Tengo que ir midiendo el tiempo..
        } catch (InterruptedException ex) {
        Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

}
