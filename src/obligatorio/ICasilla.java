/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

/**
 * Interfaz de la clase Casilla
 *
 * @author Eacosta
 */
public interface ICasilla {

    /**
     * Metodo encargado de levantar y bajar la barrera de la casilla.
     */
    void cambiarEstadoBarrera();

    /**
     * Metodo encargado de cobrarle al vehiculo el precio del telePeaje.
     * @param ve
     */
    void cobrar(Vehiculo ve, int precio);

    //void avanzarAuto();
    //void quitarAutoDeLista();
    //Un metodo que si se llena ella misma, envie una señal de notify a otra casilla que se encuentre dormida.
    void despertarCasilla();
    /*
    Quitar de la lista de autos a la espera al vehiculo que acaba de dejar pasar 
    */
    void dejarPasarAuto(Vehiculo vehiculo);
    
    /*
    Metodo para cargar los autos.
    */
    void tomarAutoDeLista(Vehiculo v);
}
