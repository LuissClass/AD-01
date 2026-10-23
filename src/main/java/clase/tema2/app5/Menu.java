package clase.tema2.app5;

import java.io.File;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    // Constante con el nombre del fichero
    private static final File FICHERO = new File("C:\\EJERCICIOS\\UNI1\\AleatorioEmple.dat");

    public void menu() throws IOException {

        Scanner sc = new Scanner(System.in);
        OperacionesFichAleatorio operaciones = new OperacionesFichAleatorio();
        int opcion;

        do {
            System.out.println("\n--- MENU EMPLEADOS ---");
            System.out.println("1. Escribir un empleado");
            System.out.println("2. Escribir varios empleados");
            System.out.println("3. Leer todos los empleados");
            System.out.println("4. Leer un empleado por ID");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    operaciones.escribirEmpleado(FICHERO);
                    break;

                case 2:
                    operaciones.escribirVariosEmpl(FICHERO);
                    break;

                case 3:
                    operaciones.leerFich(FICHERO);
                    break;

                case 4:
                    System.out.print("Introduce el ID: ");
                    int id = sc.nextInt();
                    operaciones.leerUnEmpl(FICHERO, id);
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
