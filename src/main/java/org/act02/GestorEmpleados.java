package org.act02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class GestorEmpleados {
    private final File fichero = new File("src/main/java/org/act02/AleatorioEmple.dat");
    private int idEmpleado = 0;

    private boolean empleadoExists(int id) {
        try {
            RandomAccessFile raf = new RandomAccessFile(fichero, "r");

            int idAux;

            for (;;) {
                String[] data = raf.readLine().split(",");
                idAux = Integer.parseInt(data[0]);

                if (id == idAux) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException al comprobar empleado. " + e);
        } catch (IOException e) {
            System.out.println("IOException al comprobar empleado. " + e);
        }

        return false;
    }

    public void getEmpleado(int id) {
        try {
            RandomAccessFile raf = new RandomAccessFile(fichero, "r");

            String apellido, depto;
            Double salario;
            int idAux;

            for (;;) {
                String[] data = raf.readLine().split(",");
                idAux = Integer.parseInt(data[0]);
                if (empleadoExists(id)) {
                    if (id == idAux) {
                        apellido = data[1];
                        depto = data[2];
                        salario = Double.parseDouble(data[3]);

                        System.out.println("EMPLEADO CON ID " + id + ": ");
                        System.out.println("\tAPELLIDO: " + apellido);
                        System.out.println("\tDEPTO: " + depto);
                        System.out.println("\tSALARIO: " + salario);
                        System.out.println();
                        break;
                    }
                } else System.out.println("EL EMPLEADO CON ID " + id + " NO EXISTE");
            }
            raf.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException AL OBTENER EL EMPLEADO: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException AL OBTENER EL EMPLEADO: " + e.getMessage());
        }
    }

    void insertarEmpleado() {
        Scanner sc = new Scanner(System.in);
        String apellido, depto;
        Double salario;

        try {
            System.out.println("INTRODUZCA EL APELLIDO DEL EMPLEADO: ");
            apellido = sc.next();
            System.out.println("INTRODUZCA EL DEPARTAMENTO DEL EMPLEADO: ");
            depto = sc.next();
            System.out.println("INTRODUZCA EL SALARIO DEL EMPLEADO: ");
            salario = Double.parseDouble(sc.next());

            RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

            StringBuffer buffer = new StringBuffer(idEmpleado + "," + apellido + "," + depto + "," + salario);
            raf.writeChars(buffer.toString());

            raf.close();
            getEmpleado(idEmpleado);
            idEmpleado++;
        } catch (Exception e) {
            System.out.println("SE PRODUJO UN ERROR AL INSERTAR EL EMPLEADO: " + e.getMessage());
        }

    }
}
