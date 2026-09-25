package clase.tema2.app1;

import java.io.*;
import java.util.Scanner;

public class OPERACIONESTEXTOS {

    public static void infoFichero(String rutaFichero, String rutaDirectorio) {
        File f1 = new File(rutaFichero);
        File d1 = new File(rutaDirectorio);

        System.out.println("FICHERO: " + f1.getName());
        System.out.println("\t" + f1.getAbsolutePath());
        System.out.println("\tExiste: " + f1.exists());
        System.out.println("\tPermiso de escritura: " + f1.canWrite());
        System.out.println("\tPermiso de lectura: " + f1.canRead());
        System.out.println("\tEs directorio: " + f1.isDirectory());
        System.out.println("\tEs fichero: " + f1.isFile());
        System.out.println("\tEs absoluto: " + f1.isAbsolute());
        System.out.println("\nCONTENIDO DEL DIRECTORIO: " + d1.getName());
        System.out.println(d1.list());
    }

    public static void crearFicheroDirectorio(String rutaDirectorio, String nombreArchivo, String nombreDirectorio) {
   


    }

    public static void leerCaracteres(String rutaFichero) {
 

    }

    public static void leerLineas(String rutaFichero) {
   

    }

    public static void leerLineasPares(String rutaFichero) {
 

    }

    public static void escribirFichero(String rutaFichero, String[] lineas) {


    }

    public static void escribirLineasImpares(String rutaFichero, String[] lineas) {
   

    }

    public static void copiarFicheroConFin(String rutaOrigen, String rutaDestino) {
     

    }

    public static void separarParesImpares(String rutaFichero, String rutaPares, String rutaImpares) {


    }

    public static void buscarLinea(String rutaFichero, String lineaBuscada) {


    }

    public static void copiarLineasMayores10(String rutaEntrada, String rutaSalida) {


    }

    public static void copiarFicheros(String rutaOrigen, String rutaDestino) {
   
    }

}