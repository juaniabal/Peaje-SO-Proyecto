/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

/**
 *
 * @author Agroa
 */
public interface IPeaje {
     void nuevoVehiculo(Vehiculo vehiculo)throws InterruptedException;
     Vehiculo tomarVehiculoDeCarretera(int tiempoParcial);
     //Este metodo hay que estudiarlo para ver que no choque con los metodos nuevVehiculo y terminarVehiculo.
     //
     void distribuirAutosEnCasillas(Vehiculo v); //Puede que reciba una lista de autos y devuelva un bool.
     /*
     Hace determinado calculo para abrir alguna otra casilla.
     */
     void manejadorCasilla(boolean accion);
     
     /*
     DOrmir o despertar casilla.
     */
     void dormirODespertarCasilla();
     
     Casilla casillaMenosOcupada();
}
