package clase.tema2.app2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        OperacionesEjemplo op = new OperacionesEjemplo();
        Scanner sc = new Scanner(System.in);
        int opcion;

        try {
            do {
                System.out.println("\n===== MENÚ FICHEROS BINARIOS EJEMPLO. =====");
                System.out.println("1. Ejemplo Fichero Binario sin clases");
                System.out.println("2. Ejemplo Fichero Binario con clases (Serializable)");
                System.out.println("0. Salir");
                System.out.print("Opción: ");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1 -> op.ejemploSimple();
                    case 2 -> op.ejemploEmpleados();
                    case 0 -> System.out.println("FIN DEL PROGRAMA");
                    default -> System.out.println("OPCION ERRONEA");
                }
            } while (opcion != 0);

            sc.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

