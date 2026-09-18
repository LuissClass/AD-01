package org.act02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class GestorEmpleados {
    private final File fichero = new File("src/main/java/org/act02/AleatorioEmple.dat");
    private int idEmpleado = 0;

    public void getEmpleado(int id) {
        try {
            RandomAccessFile raf = new RandomAccessFile(fichero, "r");

            int idAux;
            String apellido, depto;
            Double salario;

            int pos = 0;
            boolean existe = false;

            for (;;) {
                String[] data = raf.readLine().split(",");
                idAux = Integer.parseInt(data[0]);
                apellido = data[1];
                depto = data[2];
                salario = Double.parseDouble(data[3]);

                if (id == idAux) {
                    System.out.println("EMPLEADO CON ID " + id + ": ");
                    System.out.println("\tAPELLIDO: " + apellido);
                    System.out.println("\tDEPTO: " + depto);
                    System.out.println("\tSALARIO: " + salario);
                    System.out.println();
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                System.out.println("EL EMPLEADO CON ID " + id + " NO EXISTE");
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException AL OBTENER EL EMPLEADO: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException AL OBTENER EL EMPLEADO: " + e.getMessage());
        }
    }

    void insertarEmpleado(String apellido, String depto, Double salario) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("INTRODUZCA EL APELLIDO DEL EMPLEADO: ");
            apellido = sc.next();
            System.out.println("INTRODUZCA EL DEPARTAMENTO DEL EMPLEADO: ");
            depto = sc.next();
            System.out.println("INTRODUZCA EL SALARIO DEL EMPLEADO: ");
            salario = sc.nextDouble();

            RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

            raf.writeInt(idEmpleado);
            raf.writeUTF(",");
            raf.writeUTF(apellido);
            raf.writeUTF(",");
            raf.writeUTF(depto);
            raf.writeUTF(",");
            raf.writeDouble(salario);

            idEmpleado++;
        } catch (Exception e) {
            System.out.println("SE PRODUJO UN ERROR AL INSERTAR EL EMPLEADO: " + e.getMessage());
        }
    }
}
