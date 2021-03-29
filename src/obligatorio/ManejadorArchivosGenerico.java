/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ManejadorArchivosGenerico {
    
    public static void borrarArchivo(String nombreCompletoArchivo){
        ManejadorArchivosGenerico.escribirArchivo(nombreCompletoArchivo, new String[0], false);
    }
    
    public static void escribirArchivo(String nombreCompletoArchivo,
            String[] listaLineasArchivo, boolean agregaFinal) { //para sobreescribir es false
        FileWriter fw;
        try {
            fw = new FileWriter(nombreCompletoArchivo, agregaFinal);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listaLineasArchivo.length; i++) {
                String lineaActual = listaLineasArchivo[i];
                bw.write(lineaActual);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "
                    + nombreCompletoArchivo);
            e.printStackTrace();
        }
    }
    
    public static String[] leerArchivo(String nombreCompletoArchivo, boolean ignoreHeader) {
        FileReader fr;
        ArrayList<String> listaLineasArchivo = new ArrayList<String>();
        try {
            fr = new FileReader(nombreCompletoArchivo);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            if (ignoreHeader) lineaActual = br.readLine();
            while (lineaActual != null) {
                listaLineasArchivo.add(lineaActual);
                lineaActual = br.readLine();
            }
            System.out.println("Se leyó el archivo con exito");
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo "
                    + nombreCompletoArchivo);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo "
                    + nombreCompletoArchivo);
            e.printStackTrace();
        }
        return listaLineasArchivo.toArray(new String[0]);
    }
}
