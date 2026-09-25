package clase.tema2.app1;

import java.io.*;

public class AbrirFicheros {
    public static void main(String[] args) throws IOException {
        String directorio = "C:/EJERCICIOS/UNI1";
        File fichero1 = new File(directorio, "ejemplo1.txt");

        File fichero2 = new File("C:\\EJERCICIOS\\ejemplo2.txt");

        File direc = new File(directorio);
        File fichero3 = new File(direc, "ejemplo3.txt");

        try {
            fichero1.createNewFile();
            fichero2.createNewFile();
            fichero3.createNewFile();

            System.out.println("Ficheros creados ");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

