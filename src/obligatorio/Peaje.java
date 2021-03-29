/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

import java.util.LinkedList;

/**
 * Clase Peaje.
 *
 * @author Agroa
 */
public class Peaje implements IPeaje {

    public LinkedList<Casilla> getListaCasillas() {
        return listaCasillas;
    }

    //private final PriorityQueue<Integer> listaVehiculos;
    /*    //private final LinkedList<Vehiculo> listaVehiculos;
    public LinkedList<Vehiculo> getListaVehiculos() {
    return listaVehiculos;
    }*/
    private int tiempoTotal;
    private Vehiculo vehiculo;
    private LinkedList<Casilla> listaCasillas = new LinkedList<>();

    /**
     * Constructor de la clase Peaje.
     */
    public Peaje() {
        //listaVehiculos = new LinkedList<>();
        tiempoTotal = 0;
    }

    /**
     * Agrega un nuevo vehiculo a la lista de vehiculos del peaje.
     *
     * @param vehiculo
     * @throws java.lang.InterruptedException
     */
    @Override
    public synchronized void nuevoVehiculo(Vehiculo vehiculo) throws InterruptedException {
        //Main.atenderVehiculo.decrease();
        this.vehiculo = vehiculo;
        //Main.atenderVehiculo.increase();
    }

    /**
     * Metodo que quita de la lista general de vehiculos
     * un vehiculo y lo retorno.
     *
     * @param tiempoParcial
     * @return
     */
    @Override
    public synchronized Vehiculo tomarVehiculoDeCarretera(int tiempoParcial) {
        Vehiculo vehic = null;
        //Main.mutexCrearYTerminarVehiculo.decrease();
        if (isVehiculosEnCola()) {
            vehic = Main.carretera.pollFirst();
            tiempoTotal += tiempoParcial;
            //Main.mutexCrearYTerminarVehiculo.increase();
            //Thread.sleep(100);
        }
        return vehic;
    }

    /**
     * Metodo encargado de saber si todavia hay vehiculos en la cola.
     *
     * @return
     */
    public boolean isVehiculosEnCola() {
        return Main.carretera.size() > 0;
    }

    /**
     * Metodo que devuelve el tiempo total de las casillas del peaje.
     *
     * @return
     */
    public Integer getTiempoTotal() {
        return tiempoTotal;
    }

    @Override
    public void distribuirAutosEnCasillas(Vehiculo v) {
        //Este metodo me parece que no va a servir, porque en realidad ya tenemos este metodo en casilla.
    }
    /*
    
    */
    @Override
    public synchronized void manejadorCasilla(boolean ca) {
        //Boolean que lo que hace es ver si una casilla ya tiene mas de 10 vehiculos en su lista (Es parametrizable, podría ser 5)
        boolean estaLlena = false;
        //Recorro las casillas que hayan en el peaje. (Debería duplicar esta lista, para no perder la original, esto para cuando haya que cerrar las casillas)
        if (!this.listaCasillas.isEmpty()) {
            for (Casilla casilla : this.listaCasillas) {
                //Si la casilla tiene mas de 10 vehiculos
                if (casilla.getListaVehiculos().size() > 10) {
                    //la bandera la seteamos a true
                    estaLlena = true;

                }
            }
        }
        //Si tiene mas de 10 vehiculos
        if (estaLlena) {
            //quitamos el primer elemento de la lista, para que no confunda en proximas recorridas
            if (!this.getListaCasillas().isEmpty()) {
                this.getListaCasillas().pollFirst();
            }
            Thread casilla = null;
            //Si hay casillas disponibles para despertar
            if (this.listaCasillas.size() > 0) {
                //hacemos despertar una casilla. La primera, usamos un peekFirst, porque la idea es que aun no salga de la lista, si no cuando supere la cantidad de vehiculos.
                casilla = new Thread(this.getListaCasillas().peekFirst());
                casilla.start();
            }
            
        }
    }

    /*
    Comment: Se me ocurre hacerlo 
     */
    @Override
    public void dormirODespertarCasilla() {

    }

    @Override
    public Casilla casillaMenosOcupada() {
        Casilla casillaMenosOcupada = this.getListaCasillas().peekFirst();
        for(Casilla casilla : this.getListaCasillas()){
            if(casilla.getListaVehiculos().size()<casillaMenosOcupada.getListaVehiculos().size()){
                casillaMenosOcupada = casilla;
            }
        }return casillaMenosOcupada;
    }

}

