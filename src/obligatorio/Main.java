/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

import java.util.LinkedList;

/**
 * Clase Main
 *
 * @author juanizquierdo
 */
public class Main {

    //Defino todos los semaforos
    static Semaphore mutexCrearYTerminarVehiculo = new Semaphore(1);
    static Semaphore semaforoMomento = new Semaphore(1);
    static Semaphore atenderVehiculo = new Semaphore(1);
    static Semaphore semaforoReloj = new Semaphore(1);
    static Semaphore semaforoVehiculos = new Semaphore(1);

    //Defino las listas a utilizar
    static LinkedList<Vehiculo> carretera = new LinkedList<>();
    //static LinkedList<Thread> casillas = new LinkedList<>();
    static LinkedList<Registro> registros = new LinkedList<>();
    static LinkedList<Casilla> listaCasillas = new LinkedList<>();

    //Defino las variables de tiempo...
    static int reloj = 0;
    static int momento = 0;
    static Peaje peaje = new Peaje();

    //Defino variables auxiliares
    static boolean relojActivo = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        //Leemos el archivo, nos devuelve un array de autos.
        //Hay que crear las casillas creadas.
        //Podemos tener una variable que vaya contando la cantidad de autos en una casilla
        //Que se controle con un semaforo, y todas las casillas que intenten obtener autos, si ese numero es menor a no se, 10, se queden en wait...
        //De lo contrario se van para otro lado...
        //Cada auto debe pasar por el peaje, y a todo esto, tenemos que ir controlando los tiempos de cada auto. desde que se pone el fila
        //Hasta que sale...
        ManejadorArchivosGenerico man = new ManejadorArchivosGenerico();
        String[] arrVeh = man.leerArchivo("src/obligatorio/entrada1.csv", true);
        int cantCasillas = 10;

        System.out.println(arrVeh.length + " vehículos serán atendidos por " + cantCasillas + " casillas.\n");
        // Creación de casilla
        Casilla[] casilla = new Casilla[cantCasillas];
        //Thread[] threadCasillas = new Thread[cantCasillas];
        for (int i = 0; i < cantCasillas; i++) {
            casilla[i] = new Casilla(i + 1, peaje);
            //casillas.add(new Thread(casilla[i]));
            peaje.getListaCasillas().add(casilla[i]);
        }
        //Creo el hilo de tiempo
        Tiempo tiempo = new Tiempo();
        Thread time = new Thread(tiempo);
        time.start();
        // Creación de vehículos
        Vehiculo[] vehiculo = new Vehiculo[arrVeh.length];
        Thread[] threadVehiculos = new Thread[arrVeh.length];
        for (int i = 0; i < arrVeh.length; i++) {
            String[] linea = arrVeh[i].split(";");
            //Creo los vehiculos 1x1
            vehiculo[i] = new Vehiculo(Integer.parseInt(linea[0]), peaje, Integer.parseInt(linea[1]));
            //Agrego los vehículos a la carretera
            Main.carretera.add(vehiculo[i]);
            //Este paso hay que revisarlo
            //casilla[Integer.parseInt(linea[2])].setListaVehiculos(vehiculo[i]);
            //Se crean los hilos de vehículos
            //threadVehiculos[i] = new Thread(vehiculo[i]);
            //se dan inicio a los vehículos esto posteriormente podríamos separarlo.
           // threadVehiculos[i].start();
        }
        HiloCanalizarVehiculos hiloCanalizador = new HiloCanalizarVehiculos();
        Thread hiloCanalizadorDeVehiculos = new Thread(hiloCanalizador);
        hiloCanalizadorDeVehiculos.start();
        Thread[] hilosBarrera = new Thread[10];
        int i = 0;
        for(Casilla cas : peaje.getListaCasillas()){
            HiloLevantarBarrera hlb = new HiloLevantarBarrera(cas);
            Thread hlbThread = new Thread(hlb);
            hilosBarrera[i] = hlbThread;
            i++;
            hlbThread.start();
        }
        hiloCanalizadorDeVehiculos.join();
        for(int e = 0;i<hilosBarrera.length;i++){
            hilosBarrera[e].join();
        }
        
        /*
        // Empiezo las casillas
        /*for (int i = 0; i < cantCasillas; i++) {
        threadCasillas[i].start();
        }
        //Este thread es la primer casilla en abrirse, se hace acá y no en el metodo de peaje para tener por lo menos una abierta siempre, hay que ver
        //Si no son dos por las dudas que querramos agregar la de prioridad.
        new Thread(casillas.peekFirst()).start();
        //inicializamos el manejadorCasilla.
        //peaje.manejadorCasilla(true);
        for (int i = 0; i < cantCasillas; i++) {
            try {
                casillas.get(i).join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        for (int i = 0; i < arrVeh.length; i++) {
            try {
                threadVehiculos[i].join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        
        System.out.println("\nSe cierran las casillas del peaje con un tiempo total acumulado de " + peaje.getTiempoTotal());
        */
        relojActivo = false;
    }

}
