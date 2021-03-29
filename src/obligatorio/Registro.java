/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

/**
 *
 * @author Eacosta
 * Clase que registra lo que va sucediendo
 */
public class Registro {
    
    public Registro(){
        this.tiempoInicio = 0;
        this.tiempoFin = 0;
        this.momento = 0;
    }

    public int getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(int tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public int getTiempoFin() {
        return tiempoFin;
    }

    public void setTiempoFin(int tiempoFin) {
        this.tiempoFin = tiempoFin;
    }

    public int getMomento() {
        return momento;
    }

    public void setMomento(int momento) {
        this.momento = momento;
    }
    private int tiempoInicio;
    private int tiempoFin;
    private int momento;
    
}

//En los que un hilo tenga tipo una región crítica... solo pueda acceder de a un hilo...
