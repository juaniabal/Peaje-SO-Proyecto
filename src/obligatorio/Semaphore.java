/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

/**
 * Clase Semaforo
 *
 * @author Eacosta
 */
public final class Semaphore {

    private int counter;

    /**
     * Constructor de la clase Semaforo.
     *
     * @param counter
     */
    public Semaphore(final int counter) {
        this.counter = counter;
    }

    /**
     * Metodo encargado de decrementar el contador del semaforo.
     *
     * @throws InterruptedException
     */
    public synchronized void decrease() throws InterruptedException {
        //System.out.println("ENTRO AL SEM: " + counter);
        while (counter == 0) {
            //System.out.println("Espero: " + counter);
            wait();
        }
        //System.out.println("Me estoy ejec: " + counter);
        counter--;
    }

    /**
     * Metodo encargado de incrementar el contador del semaforo.
     */
    public synchronized void increase() {

        counter++;
        notify();
        //System.out.println("SALGO DEL SEM: " + counter);
    }
}
