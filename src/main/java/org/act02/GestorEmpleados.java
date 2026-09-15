package org.act02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class GestorEmpleados {
    File fichero = new File("AleatorioEmpel.dat");

    void getEmpleado(int id) {
        try {
            RandomAccessFile raf = new RandomAccessFile(fichero, "r");

            int idAux;
            String apellido, depto;
            Double salario;

            int pos = 0;

            for (;;) {
                raf.seek(pos);
                idAux = raf.readInt();
                apellido = raf.readUTF();
                depto = raf.readUTF();
                salario = raf.readDouble();

                if (id == idAux) {
                    System.out.println("EL EMPLEADO CON ID" + id + ": ");
                    System.out.println("\tAPELLIDO: " + apellido);
                    System.out.println("\tDEPTO: " + depto);
                    System.out.println("\tSALARIO: " + salario);
                    System.out.println();
                } else {
                    System.out.println("EL EMPLEADO CON ID " + id + " NO EXISTE");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("SE PRODUJO UN ERROR AL OBTENER EL EMPLEADO: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("SE PRODUJO UN ERROR AL OBTENER EL EMPLEADO: " + e.getMessage());
        }
    }
}
