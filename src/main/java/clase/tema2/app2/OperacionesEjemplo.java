package clase.tema2.app2;

import java.io.*;
import java.util.Scanner;

public class OperacionesEjemplo {

    public void ejemploSimple() {
        String nombreFichero = "src/main/java/clase/tema2/app2/ficheroBinario.dat";
        long codigo = 1L;
        String nombre = "Juan Carlos";
        long salario = 12000L;
        String fecha = "2024-06-01";

        try (DataOutputStream d = new DataOutputStream(new FileOutputStream(nombreFichero, true));
        ) {

            d.writeLong(codigo);
            d.writeUTF(nombre);
            d.writeLong(salario);
            d.writeUTF(fecha);

            codigo = 0;
            nombre = "";
            salario = 0;
            fecha = "";
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se pudo crear el archivo. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: No se pudo escribir en el archivo. " + e.getMessage());
        }

        try (DataInputStream d = new DataInputStream(new FileInputStream(nombreFichero))) {
            while (true) {
                codigo = d.readLong();
                nombre = d.readUTF();
                salario = d.readLong();
                fecha = d.readUTF();

                System.out.println("=== Empleado ===");
                System.out.println("Código: " + codigo);
                System.out.println("Nombre: " + nombre);
                System.out.println("Salario: " + salario);
                System.out.println("Fecha: " + fecha);
                System.out.println();
            }
        } catch (java.io.IOException e) {
            System.err.println("Error al leer en el fichero: " + e.getMessage());
        }
    }


    public void ejemploEmpleados() {
        String nombreFichero = "src/main/java/clase/tema2/app2/ficheroBinario2.dat";

        try (DataOutputStream d = new DataOutputStream(new FileOutputStream(nombreFichero, true));
        ) {
            Empleado empleado1 = new Empleado(1L, "Juan Carlos", 12000L, "2024-06-01");
            Empleado empleado2 = new Empleado(2L, "María López", 15000L, "2024-06-02");

            empleado1.grabarEmpleado(d);
            empleado2.grabarEmpleado(d);

        } catch (FileNotFoundException e) {
            System.out.println("Error: No se pudo crear el archivo. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: No se pudo escribir en el archivo. " + e.getMessage());
        }

        try (DataInputStream d = new DataInputStream(new FileInputStream(nombreFichero))) {
            while (d.available() > 0) {
                Empleado empleado = new Empleado();
                empleado.leerEmpleado(d);
                empleado.mostrarEmpleado();
            }
        } catch (java.io.IOException e) {
            System.err.println("Error al leer en el fichero: " + e.getMessage());
        }
    }
}
