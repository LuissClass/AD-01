package clase.tema2.app1;

import java.io.*;

public class VerDir {
    public static void main(String[] args) {
        System.out.println("Archivos en el directorio actual:");
        File f = new File("d:\\db");
        String[] archivos = f.list();
        for (int i = 0; i < archivos.length; i++) {
            System.out.println(archivos[i]);
        }
        //otro ejemplo
        if (args.length > 0) {
            String dir = args[0];
            System.out.println("Archivos en el directorio " + dir);
            f = new File(dir);
            archivos = f.list();
            for (int i = 0; i < archivos.length; i++) {
                System.out.println(archivos[i]);
            }
        }
    }
}

