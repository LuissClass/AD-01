package clase.tema2.appGeneral;

import clase.tema2.app3.Empleado;
import clase.tema2.app3.Libro;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OperacionesBinarios {

    private static final String FICH_LIBROS = "c:\\FicherosBinarios\\libros1.dat";
    private static final String FICH_EMPLEADOS = "c:\\FicherosBinarios\\empleados.dat";

    public void generarFicheroLibros() {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(FICH_LIBROS))) {

            Libro[] lista = {
                    new Libro("0100", "Largo Pétalo de Mar", "Allende", "P&J", 21.76),
                    new Libro("0200", "Come comida Real", "Carlos Rios", "Paidos", 16.15),
                    new Libro("0300", "Los asquerosos", "Santiago Lorenzo", "Blackie Books", 19.95),
                    new Libro("0400", "Lo mejor de ir es volver", "Espinosa", "Grijalbo", 17.00),
                    new Libro("1000", "Malaherba", "Jabois", "Alfaguara", 17.00),

                    new Libro("0500", "The Emperor of Gladness", "Ocean Vuong", "Penguin Press", 24.50),
                    new Libro("0600", "Fourth Wing", "Rebecca Yarros", "Red Tower Books", 22.00),
                    new Libro("0700", "Onyx Storm", "Rebecca Yarros", "Red Tower Books", 22.00),
                    new Libro("0800", "The Let Them Theory", "Mel Robbins", "Harmony", 20.00),
                    new Libro("0900", "Sunrise on the Reaping", "Suzanne Collins", "Scholastic", 23.00),
                    new Libro("1100", "Great Big Beautiful Life", "Emily Henry", "Berkley", 18.50),
                    new Libro("1200", "The Wilderness", "Angela Flournoy", "Riverhead", 21.00),
                    new Libro("1300", "Heart the Lover", "Lily King", "G.P. Putnam's Sons", 22.50),
                    new Libro("1400", "Braiding Sweetgrass", "Robin Wall Kimmerer", "Milkweed", 19.95),
                    new Libro("1500", "The Housemaid", "Freida McFadden", "Berkley", 17.99),
                    new Libro("1600", "Archipelago of the Sun", "Yoko Tawada", "FSG Originals", 24.00),
                    new Libro("1700", "El viento conoce mi nombre", "Isabel Allende", "Plaza & Janés", 21.00),
                    new Libro("1800", "La anomalía", "Hervé Le Tellier", "Seix Barral", 19.00),
                    new Libro("1900", "Cómo no ser un esclavo del tiempo", "Oliver Burkeman", "Planeta", 18.90),
                    new Libro("2000", "Le dedico mi silencio", "Mario Vargas Llosa", "Alfaguara", 22.50)
            };

            for (Libro l : lista)
            {
                l.escribir(out);
                l.mostrar();
            }

            System.out.println("Fichero LIBROS generado correctamente.\n");

        } catch (IOException e) {
            System.out.println("Error generando fichero libros: " + e.getMessage());
        }
    }

    public static void mostrarTodosLibros() {
        try (DataInputStream dataIS = new DataInputStream(new FileInputStream(FICH_LIBROS));) {
             String codigo;
             String titulo;
             String autor;
             String editorial;
             double precio;

            while (dataIS.available() != 0) {
                codigo = dataIS.readUTF();
                titulo = dataIS.readUTF();
                autor = dataIS.readUTF();
                editorial = dataIS.readUTF();
                precio = dataIS.readDouble();
                System.out.println(STR.
                        "Código: \{codigo} | Título: \{titulo} | Autor: \{autor} | Editorial: \{editorial} | Precio: \{precio}");
            }
        } catch (IOException e) {
            System.out.println("El fichero de libros no existe o esta vacio. Primero genere el fichero con la opcion 1");
        }
    }


    public static void mostrarLibrosprecio17() {
        try (DataInputStream dataIS = new DataInputStream(new FileInputStream(FICH_LIBROS));) {
            String codigo;
            String titulo;
            String autor;
            String editorial;
            double precio;

            while (dataIS.available() != 0) {
                codigo = dataIS.readUTF();
                titulo = dataIS.readUTF();
                autor = dataIS.readUTF();
                editorial = dataIS.readUTF();
                precio = dataIS.readDouble();

                if (codigo.equals("0100") || precio == 17.0) {
                    System.out.println(STR."Código: \{codigo} | Título: \{titulo} | Autor: \{autor} | Editorial: \{editorial} | Precio: \{precio}");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al mostrar empleados: " + e);
        }
    }

    public static void libroMayorPrecio() {
        List<Libro> libros = new ArrayList<>();
        Libro maxLibro;

        try (DataInputStream dataIS = new DataInputStream(new FileInputStream(FICH_LIBROS));) {
            String codigo;
            String titulo;
            String autor;
            String editorial;
            double precio;

            while (dataIS.available() != 0) {
                codigo = dataIS.readUTF();
                titulo = dataIS.readUTF();
                autor = dataIS.readUTF();
                editorial = dataIS.readUTF();
                precio = dataIS.readDouble();

                libros.add(new Libro(codigo, titulo, autor, editorial, precio));
            }
        } catch (IOException e) {
            System.out.println("Error al mostrar empleados: " + e);
        }

        maxLibro = libros.stream().max(Comparator.comparingDouble(Libro::getPrecio)).get();
        System.out.println(maxLibro);
    }

    public void generarFicheroEmpleados() {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(FICH_EMPLEADOS))) {

            Empleado[]   lista = {

                    new Empleado("Ana", "López", 2),
                    new Empleado("Juan", "Martínez", 1),
                    new Empleado("Carmen", "Delgado Pérez", 3),
                    new Empleado("Luis", "García", 0),
                    new Empleado("María", "Sánchez Romero", 4),

                    new Empleado("Pedro", "Nuñez", 1),
                    new Empleado("Elena", "Hernández Rubio", 2),
                    new Empleado("Raúl", "Villar", 0),
                    new Empleado("Lucía", "Fernández", 3),
                    new Empleado("Jorge", "Prieto Zamora", 2),

                    new Empleado("Sofía", "Torres", 1),
                    new Empleado("Adrián", "Molina Pérez", 4),
                    new Empleado("Beatriz", "Calvo", 0),
                    new Empleado("Diego", "Serrano Montes", 3),
                    new Empleado("Paula", "Rivas Martín", 2)
            };

            for (Empleado e : lista)
            {
                e.escribir(out);
                e.mostrar();
            }

            System.out.println("Fichero EMPLEADOS generado correctamente.");

        } catch (IOException e) {
            System.out.println("Error generando fichero empleados: " + e.getMessage());
        }
    }

    public static void mostrarEmpleados() {
        try (DataInputStream dataIS = new DataInputStream(new FileInputStream(FICH_EMPLEADOS));) {
            String nombre;
            String apell;
            int numHijos;

            while (dataIS.available() != 0) {
                nombre = dataIS.readUTF();
                apell = dataIS.readUTF();
                numHijos = dataIS.readInt();
                System.out.println(nombre + " " + apell  + " - Hijos: " + numHijos);

            }
        } catch (IOException e) {
            System.out.println("El fichero de empleados no existe o esta vacio. Primero genere el fichero con la opcion 5");
        }
    }


    public static void empleadoNombreMasLargo() {
        try (DataInputStream dataIS = new DataInputStream(new FileInputStream(FICH_EMPLEADOS));) {
            String nombre = "";
            String apell = "";
            int numHijos = 0;
            int sumaNombreApellidoMasLargo = 0;

            String nombreAux;
            String apellAux;
            int numHijosAux;
            int sumaNombreApellido = 0;

            while (dataIS.available() != 0) {
                nombreAux = dataIS.readUTF();
                apellAux = dataIS.readUTF();
                numHijosAux = dataIS.readInt();
                sumaNombreApellido = nombreAux.length() + apellAux.length();

                if (sumaNombreApellido > sumaNombreApellidoMasLargo) {
                    nombre = nombreAux;
                    apell = apellAux;
                    numHijos = numHijosAux;
                    sumaNombreApellidoMasLargo = sumaNombreApellido;
                }
            }

            System.out.println(new Empleado(nombre, apell, numHijos));

        } catch (IOException e) {
            System.out.println("El fichero de empleados no existe o esta vacio. Primero genere el fichero con la opcion 5");
        }
    }

    public static void empleadosConMasDeDosHijos() {
        try (DataInputStream dataIS = new DataInputStream(new FileInputStream(FICH_EMPLEADOS));) {
            List<Empleado> empleados = new ArrayList<>();
            String nombre;
            String apell;
            int numHijos;

            while (dataIS.available() != 0) {
                nombre = dataIS.readUTF();
                apell = dataIS.readUTF();
                numHijos = dataIS.readInt();

                if (numHijos > 2) {
                    empleados.add(new Empleado(nombre, apell, numHijos));
                }
            }

            System.out.println("== EMPLEADOS CON MAS DE 2 HIJOS == ");
            empleados.forEach(e -> System.out.println(e.getNombre() + " " + e.getApellido()  + " - Hijos: " + e.getNumerohijos()));
        } catch (IOException e) {
            System.out.println("El fichero de empleados no existe o esta vacio. Primero genere el fichero con la opcion 5");
        }
    }



}
