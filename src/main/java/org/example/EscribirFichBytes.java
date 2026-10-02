package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class EscribirFichBytes {
    public static void main(String[] args) {
        String nombreFichero = "src/main/java/org/example/ficheroBytes01.dat";
        String contenido = "Hola, este es un ejemplo de escritura en un fichero de bytes.\n";

        try (FileOutputStream fos = new FileOutputStream(nombreFichero, true)) {
            byte[] bytes = contenido.getBytes();
            fos.write(bytes);
            System.out.println("Se ha escrito correctamente en el fichero: " + nombreFichero);
        } catch (java.io.IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        }

        try (FileInputStream fis = new FileInputStream(nombreFichero)) {
            while (fis.available() > 0) {
                System.out.print((char) fis.read());
            }

        } catch (java.io.IOException e) {
            System.err.println("Error al leer en el fichero: " + e.getMessage());
        }
    }
}
