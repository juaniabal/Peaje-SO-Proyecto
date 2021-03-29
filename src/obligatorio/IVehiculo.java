/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

/**
 * Interfaz de la clase Vehiculo.
 *
 * @author juanizquierdo
 */
public interface IVehiculo {

    /**
     * Metodo encargado de pagar el precio del telePeaje.
     *
     * @param precio
     */
    int pagarPeaje(int precio);

    /**
     * Metodo que avanza al vehiculo en la queue del peaje.
     */
    void avanzar();
}
