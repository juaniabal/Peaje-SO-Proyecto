/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Casilla.
 *
 * @author Eacosta
 */
public class Casilla implements ICasilla, Runnable {

    public int getID() {
        return ID;
    }

    public LinkedList<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setGanancia(int ganancia) {
        this.ganancia += ganancia;
    }

    public void setListaVehiculos(Vehiculo v) {
        this.listaVehiculos.add(v);
    }

    private final int ID;
    private boolean barrera;
    //private PriorityQueue<Integer> listaVehiculos;
    private final Peaje peaje;
    private Integer tiempoCasilla;
    private LinkedList<Vehiculo> listaVehiculos = new LinkedList<>();
    private int ganancia = 0;
    private int tiempoInicial = 0;
    private int tiempoFinal = 0;

    /**
     * Constructor de la clase Casilla.
     *
     * @param id
     * @param peaje
     */
    public Casilla(int id, Peaje peaje) {
        this.ID = id;
        this.barrera = false;
        this.peaje = peaje;
        this.tiempoCasilla = 0;
    }

    @Override
    public void cambiarEstadoBarrera() {
        this.barrera = !barrera;
    }

    @Override
    public void cobrar(Vehiculo ve,int tipoVehiculo) {
        switch(tipoVehiculo){
            case 1:
                ve.pagarPeaje(100);
                break;
            case 2:
                ve.pagarPeaje(120);
                break;
            case 3:
                ve.pagarPeaje(140);
                break;
            default:
                break;
        }
    }
    

    @Override
    public void run() {
        int retardo;
        Vehiculo vehiculo = null;
        
        while (peaje.isVehiculosEnCola()) {
            
            tiempoInicial = Main.reloj;
            this.cambiarEstadoBarrera();
            retardo = (int) (Math.random() * 90 + 10);
            //Esto hay que ver como cambiarlo porque en realidad necesitamos calcular cuanto tiempo estuvo acá adentro.
            vehiculo = peaje.tomarVehiculoDeCarretera(tiempoInicial);
            //Carga de la lista general un vehiculo.
            if(vehiculo!=null) {
                this.tomarAutoDeLista(vehiculo);
            }
            
            System.out.println(this.getListaVehiculos().size());
            //En la misma casilla si hay mas de 10 autos en una misma casilla abro otra...
            Main.peaje.manejadorCasilla(true);
            //System.out.println(this.getListaVehiculos().size() + " Este es el tamaño" + this.ID);
            this.cambiarEstadoBarrera();
            this.cobrar(vehiculo, vehiculo.getTipoVehiculo());
            tiempoFinal = Main.reloj;
            int tiempo = tiempoFinal - tiempoInicial;
            tiempoCasilla += tiempo;
             if(vehiculo!=null)System.out.println("El Vehiculo " + vehiculo.getID() + " pasó por la casilla " + this.ID + " en un tiempo de " + retardo);
        }
       System.out.println("Fin de la casilla " + this.ID + ", que termina con un tiempo parcial de " + tiempoCasilla);
    }

    @Override
    public void despertarCasilla() {
        //Este metodo quedó hecho en peajes.
    }

    @Override
    public void dejarPasarAuto(Vehiculo vehiculo) {
        this.cobrar(vehiculo,vehiculo.getTipoVehiculo());
        this.getListaVehiculos().remove(vehiculo);
        
    }
    
    @Override
    public void tomarAutoDeLista(Vehiculo v) {
        this.getListaVehiculos().add(v);
    }

}
