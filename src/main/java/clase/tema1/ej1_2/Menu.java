package clase.tema1.ej1_2;

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
            System.out.println("7. Listar Pedidos");
            System.out.println("8. Agregar Pedido");
            System.out.println("9. Listar Pedidos de un Cliente");
            System.out.println("10. Listar Pedidos Detallada");
            System.out.println("0. Salir");
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
                case 7 -> listarPedidos();
                case 8 -> agregarPedido(scanner);
                case 9 -> listarPedidosCliente(scanner);
                case 10 -> listarPedidosDetallada();
                case 0 -> System.out.println("Fin!");
                default -> System.out.println("Opción errónea, intente de nuevo.");
            }

        } while (opcion != 0);

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

    private void listarPedidos() {
        System.out.println("\n--- LISTA DE PEDIDOS ---");
        gestorDatos.obtenerPedidos()
                .forEach(System.out::println);
    }

    private void listarPedidosDetallada() {
        System.out.println("\n--- LISTA DE PEDIDOS DETALLADA ---");
        String msg = "";

        for (Pedido pedido : gestorDatos.obtenerPedidos()) {
            msg += "Pedido ID: " + pedido.getId() + "\nNombre: " + pedido.getNombre() +
                    "\nCantidad: " + pedido.getCantidad() +
                    "\nCliente ID: " + pedido.getIdCliente();

            for (Producto producto : gestorDatos.obtenerProductos()) {
                if (producto.getId() == pedido.getIdProducto()) {
                    msg += "\nProducto: " + producto.getNombre() + "\n\n";
                    break;
                }
            }
        }

        System.out.println(msg);
    }

    private void listarPedidosCliente(Scanner scanner) {
        System.out.print("Ingrese el id del cliente: ");
        int idCliente = scanner.nextInt();

        if (gestorDatos.existeCliente(idCliente)) {
            System.out.println("\n--- LISTA DE PEDIDOS DEL CLIENTE ---");
            gestorDatos.obtenerPedidos().stream()
                    .filter(p -> p.getIdCliente() == idCliente)
                    .forEach(System.out::println);
        } else {
            throw new IllegalArgumentException("El cliente con id " + idCliente + " no existe.");
        }


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

    private void agregarPedido(Scanner scanner) {
        int id;
        String nombre;
        int cantidad;
        int idCliente;
        int idProducto;
        System.out.print("Ingrese el id del pedido: ");
        id = scanner.nextInt();
        scanner.nextLine();

        if (gestorDatos.existePedido(id)) {
            throw new IllegalArgumentException("El pedido con id " + id + " ya existe.");
        }

        System.out.print("Ingrese el nombre del pedido: ");
        nombre = scanner.nextLine();

        System.out.print("Ingrese la cantidad del pedido: ");
        cantidad = scanner.nextInt();

        System.out.print("Ingrese el id del cliente: ");
        idCliente = scanner.nextInt();

        if (!gestorDatos.existeCliente(idCliente)) {
            throw new IllegalArgumentException("El cliente con id " + id + " no existe.");
        }

        System.out.print("Ingrese el id del producto: ");
        idProducto = scanner.nextInt();

        gestorDatos.agregarPedido(new Pedido(id, nombre, cantidad, idCliente, idProducto));
    }
}

