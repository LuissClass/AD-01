
package clase.tema2.appGeneral;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n========== MENÚ PRINCIPAL ==========");
            System.out.println("1. Operaciones con ficheros de texto");
            System.out.println("2. Operaciones con ficheros binarios");
            System.out.println("3. Operaciones con ficheros aleatorios");
            System.out.println("4. Operaciones con ficheros XML");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    menuTextos(sc);
                    break;

                case 2:
                    menuBinarios(sc);
                    break;

                case 3:
                    menuAleatorios(sc);
                    break;

                case 4:
                    menuXML(sc);
                    break;

                case 0:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Introduce un número válido: ");
            sc.nextLine();
        }

        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    // ==================================================
    // 1. MENÚ DE FICHEROS DE TEXTO: OPCIONES 1-13
    // ==================================================

    private static void menuTextos(Scanner sc) {

        int opcion;

        do {
            System.out.println("\n===== FICHEROS DE TEXTO =====");
            System.out.println("1. Información de un fichero");
            System.out.println("2. Crear fichero y directorio");
            System.out.println("3. Leer caracteres");
            System.out.println("4. Leer líneas");
            System.out.println("5. Leer líneas pares");
            System.out.println("6. Escribir en un fichero");
            System.out.println("7. Escribir líneas impares");
            System.out.println("8. Copiar fichero añadiendo texto al final");
            System.out.println("9. Separar líneas pares e impares");
            System.out.println("10. Buscar una línea");
            System.out.println("11. Copiar líneas de más de 10 caracteres");
            System.out.println("12. Copiar un fichero");
            System.out.println("13. Volver al menú principal");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(sc);

            String origen;
            String destino;
            String texto;

            switch (opcion) {
                case 1:
                    System.out.print("Ruta del directorio: ");
                    origen = sc.nextLine();
                    System.out.print("Nombre del fichero: ");
                    texto = sc.nextLine();
                    OperacionesTextos.infoFichero(origen, texto);
                    break;

                case 2:
                    System.out.print("Ruta del directorio: ");
                    origen = sc.nextLine();
                    System.out.print("Nombre del directorio nuevo: ");
                    destino = sc.nextLine();
                    System.out.print("Nombre del fichero: ");
                    texto = sc.nextLine();
                    OperacionesTextos.crearFicheroDirectorio(
                            origen, destino, texto);
                    break;

                case 3:
                    System.out.print("Ruta del fichero: ");
                    OperacionesTextos.leerCaracteres(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Ruta del fichero: ");
                    OperacionesTextos.leerLineas(sc.nextLine());
                    break;

                case 5:
                    System.out.print("Ruta del fichero: ");
                    OperacionesTextos.leerLineasPares(sc.nextLine());
                    break;

                case 6:
                    System.out.print("Ruta del fichero: ");
                    destino = sc.nextLine();
                    System.out.println(
                            "Introduce las líneas. Escribe FIN para terminar:");

                    java.util.ArrayList<String> lineas =
                            new java.util.ArrayList<>();

                    while (true) {
                        texto = sc.nextLine();
                        if (texto.equalsIgnoreCase("FIN")) {
                            break;
                        }
                        lineas.add(texto);
                    }

                    OperacionesTextos.escribirFichero(
                            destino, lineas.toArray(new String[0]));
                    break;

                case 7:
                    System.out.print("Ruta del fichero: ");
                    destino = sc.nextLine();
                    System.out.println(
                            "Introduce las líneas. Escribe FIN para terminar:");

                    java.util.ArrayList<String> impares =
                            new java.util.ArrayList<>();

                    while (true) {
                        texto = sc.nextLine();
                        if (texto.equalsIgnoreCase("FIN")) {
                            break;
                        }
                        impares.add(texto);
                    }

                    OperacionesTextos.escribirLineasImpares(
                            destino, impares.toArray(new String[0]));
                    break;

                case 8:
                    System.out.print("Fichero de origen: ");
                    origen = sc.nextLine();
                    System.out.print("Fichero de destino: ");
                    destino = sc.nextLine();
                    OperacionesTextos.copiarFicheroConFin(origen, destino);
                    break;

                case 9:
                    System.out.print("Fichero de origen: ");
                    origen = sc.nextLine();
                    System.out.print("Fichero para líneas pares: ");
                    destino = sc.nextLine();
                    System.out.print("Fichero para líneas impares: ");
                    texto = sc.nextLine();
                    OperacionesTextos.separarParesImpares(
                            origen, destino, texto);
                    break;

                case 10:
                    System.out.print("Ruta del fichero: ");
                    origen = sc.nextLine();
                    System.out.print("Texto que quieres buscar: ");
                    texto = sc.nextLine();
                    OperacionesTextos.buscarLinea(origen, texto);
                    break;

                case 11:
                    System.out.print("Fichero de origen: ");
                    origen = sc.nextLine();
                    System.out.print("Fichero de destino: ");
                    destino = sc.nextLine();
                    OperacionesTextos.copiarLineasMayores10(
                            origen, destino);
                    break;

                case 12:
                    System.out.print("Fichero de origen: ");
                    origen = sc.nextLine();
                    System.out.print("Fichero de destino: ");
                    destino = sc.nextLine();
                    OperacionesTextos.copiarFicheros(origen, destino);
                    break;

                case 13:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 13);
    }

    // ==================================================
    // 2. MENÚ DE FICHEROS BINARIOS: OPCIONES 14-26
    // ==================================================

    private static void menuBinarios(Scanner sc) {

        int opcion;

        do {
            System.out.println("\n===== FICHEROS BINARIOS =====");
            System.out.println("14. Ejemplo simple de fichero binario");
            System.out.println("15. Ejemplo de fichero binario de empleados");
            System.out.println("16. Crear fichero de libros");
            System.out.println("17. Mostrar libros por código o precio");
            System.out.println("18. Mostrar libro más caro");
            System.out.println("19. Mostrar todos los libros");
            System.out.println("20. Crear fichero de empleados");
            System.out.println("21. Mostrar todos los empleados");
            System.out.println("22. Mostrar empleado con nombre y apellido más largos");
            System.out.println("23. Mostrar empleados con más de dos hijos");
            System.out.println("24. Crear fichero inicial de departamentos");
            System.out.println("25. Modificar departamento");
            System.out.println("26. Eliminar o listar departamentos");
            System.out.println("27. Volver al menú principal");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(sc);

            switch (opcion) {
                case 14:
                    OperacionesEjemplo.ejemploSimple();
                    break;

                case 15:
                    OperacionesEjemplo.ejemploEmpleados();
                    break;

                case 16:
                    new OperacionesBinarios().generarFicheroLibros();
                    break;

                case 17:
                    OperacionesBinarios.mostrarLibrosprecio17();
                    break;

                case 18:
                    OperacionesBinarios.libroMayorPrecio();
                    break;

                case 19:
                    OperacionesBinarios.mostrarTodosLibros();
                    break;

                case 20:
                    new OperacionesBinarios().generarFicheroEmpleados();
                    break;

                case 21:
                    OperacionesBinarios.mostrarEmpleados();
                    break;

                case 22:
                    OperacionesBinarios.empleadoNombreMasLargo();
                    break;

                case 23:
                    OperacionesBinarios.empleadosConMasDeDosHijos();
                    break;

                case 24:
                    OperacionesDepartamentos.crearFicheroInicial();
                    break;

                case 25:
                    System.out.print("Número de departamento: ");
                    int numero = leerEntero(sc);
                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nueva localidad: ");
                    String localidad = sc.nextLine();

                    OperacionesDepartamentos.modificarDepartamento(
                            numero, nombre, localidad);
                    break;

                case 26:
                    System.out.println("1. Eliminar departamento");
                    System.out.println("2. Listar departamentos");
                    System.out.print("Elige una opción: ");
                    int subopcion = leerEntero(sc);

                    if (subopcion == 1) {
                        System.out.print("Número de departamento: ");
                        OperacionesDepartamentos.eliminarDepartamento(
                                leerEntero(sc));
                    } else if (subopcion == 2) {
                        OperacionesDepartamentos.listarDepartamentos();
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;

                case 27:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 27);
    }

    // ==================================================
    // 3. MENÚ DE FICHEROS ALEATORIOS: OPCIONES 27-32
    // ==================================================

    private static void menuAleatorios(Scanner sc) {

        int opcion;

        do {
            System.out.println("\n===== FICHEROS ALEATORIOS =====");
            System.out.println("27. Escribir un empleado");
            System.out.println("28. Escribir varios empleados");
            System.out.println("29. Leer empleado por ID");
            System.out.println("30. Insertar empleado");
            System.out.println("31. Modificar empleado");
            System.out.println("32. Operaciones con fichero directo");
            System.out.println("33. Volver al menú principal");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(sc);

            switch (opcion) {
                case 27:
                    System.out.print("Ruta del directorio: ");
                    try {
                        String ruta = sc.nextLine();
                        OperacionesAleatorios.escribirEmpleado(new File(ruta));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 28:
                    System.out.print("Ruta del directorio: ");
                    try {
                        String ruta = sc.nextLine();
                        OperacionesAleatorios.escribirVariosEmpleados(new File(ruta));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 29:
                    try {
                        OperacionesAleatorios.leerEmpleadoID(sc);
                    } catch (IOException e) {
                        System.out.println("ERROR");
                    }
                    break;

                case 30:
                    try {
                        OperacionesAleatorios.insertarEmpleado(sc);
                    } catch (IOException e) {
                        System.out.println("ERROR");
                    }
                    break;

                case 31:
                    try {
                        OperacionesAleatorios.modificarEmpleado(sc);
                    } catch (IOException e) {
                        System.out.println("ERROR");
                    }
                    break;

                case 32:
                    menuFicheroDirecto(sc);
                    break;

                case 33:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 33);
    }

    private static void menuFicheroDirecto(Scanner sc) {

        int opcion;

        do {
            System.out.println("\n===== FICHERO DIRECTO =====");
            System.out.println("1. Grabar empleados en fichero directo");
            System.out.println("2. Visualizar fichero directo");
            System.out.println("3. Buscar secuencialmente y modificar código");
            System.out.println("4. Volver");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    //OperacionesAleatorios.grabarFicheroDirecto(sc);
                    break;

                case 2:
                    //OperacionesAleatorios.visualizarFicheroDirecto();
                    break;

                case 3:
                    //OperacionesAleatorios.buscarSecuencial(sc);
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 4);
    }

    // ==================================================
    // 4. MENÚ XML: OPCIONES 33-35
    // ==================================================

    private static void menuXML(Scanner sc) {

        int opcion;

        do {
            System.out.println("\n===== FICHEROS XML =====");
            System.out.println("33. Mostrar departamentos de Guadalajara");
            System.out.println("34. Mostrar departamentos por localidad");
            System.out.println("35. Operaciones con ciclos formativos");
            System.out.println("36. Volver al menú principal");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(sc);

            switch (opcion) {
                case 33:
                    OperacionesXML.DeptMayorYGuadalajara();
                    break;

                case 34:
                    OperacionesXML.ListarXML();
                    break;

                case 35:
                    //OperacionesXML.opcion3_Ciclos(sc);
                    break;

                case 36:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 36);
    }
}

