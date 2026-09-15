package clase.tema1.ej1;

import java.util.Comparator;
import java.util.Scanner;

public class Menu {
    private GestorDatos gestorDatos;

    public Menu(GestorDatos gestorDatos) {
        this.gestorDatos = gestorDatos;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Listar Clientes");
            System.out.println("2. Listar Productos");
            System.out.println("3. Agregar Cliente");
            System.out.println("4. Agregar Producto");
            System.out.println("5. Producto con precio más alto");
            System.out.println("6. Cliente con el nombre más largo");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> listarClientes();
                case 2 -> listarProductos();
                case 3 -> agregarCliente(scanner);
                case 4 -> agregarProducto(scanner);
                case 5 -> productoPrecioMasAlto();
                case 6 -> clienteNombreMasLargo();
                case 7 -> System.out.println("Fin!");
                default -> System.out.println("Opción errónea, intente de nuevo.");
            }

        } while (opcion != 7);

        scanner.close();
    }

    private void listarClientes() {
        gestorDatos.obtenerClientes()
                .forEach(c -> System.out.println(c.getNombre()));
    }

    private void listarProductos() {
        gestorDatos.obtenerProductos()
                .forEach(p -> System.out.println(p.getNombre()));
    }

    private void agregarCliente(Scanner scanner) {
        int id;
        String nombre, correo, telefono;
        System.out.print("Ingrese el id del cliente: ");
        id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del cliente: ");
        nombre = scanner.nextLine();
        System.out.print("Ingrese el correo del cliente: ");
        correo = scanner.nextLine();
        System.out.print("Ingrese el teléfono del cliente: ");
        telefono = scanner.nextLine();
        gestorDatos.agregarCliente(new Cliente(id, nombre, correo, telefono));
    }

    private void agregarProducto(Scanner scanner) {
        int id;
        String nombre;
        double precio;
        System.out.print("Ingrese el id del producto: ");
        id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del producto: ");
        nombre = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        precio = scanner.nextDouble();
        gestorDatos.agregarProducto(new Producto(id, nombre, precio));
    }

    private void productoPrecioMasAlto() {
        gestorDatos.obtenerProductos().stream()
                .max(Comparator.comparingDouble(Producto::getPrecio))
                .ifPresent(p -> System.out.println("Producto con precio más alto: " + p.getNombre() + " - $" + p.getPrecio()));
    }

    private void clienteNombreMasLargo() {
        gestorDatos.obtenerClientes().stream()
                .max(Comparator.comparingInt(c -> c.getNombre().length()))
                .ifPresent(c -> System.out.println("Cliente con el nombre más largo: " + c.getNombre()));
    }
}

