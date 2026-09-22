package clase.tema1.ej3;

import java.util.Scanner;

public class Menu {

    private OperacionesCRUD crud;

    public Menu(OperacionesCRUD crud) {
        this.crud = crud;
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n========= MENÚ PRINCIPAL =========");
            System.out.println("1. Listar oficinas");
            System.out.println("2. Insertar oficina");
            System.out.println("3. Borrar oficina");
            System.out.println("4. Buscar oficina");

            System.out.println("5. Listar vendedores");
            System.out.println("6. Insertar vendedor");
            System.out.println("7. Borrar vendedor");
            System.out.println("8. Buscar vendedor");

            System.out.println("9. Listar clientes");
            System.out.println("10. Insertar cliente");
            System.out.println("11. Borrar cliente");
            System.out.println("12. Buscar cliente");

            System.out.println("13. Listar productos");
            System.out.println("14. Insertar producto");
            System.out.println("15. Borrar producto");
            System.out.println("16. Buscar producto");

            System.out.println("17. Listar pedidos");
            System.out.println("18. Insertar pedido");
            System.out.println("19. Borrar pedido");
            System.out.println("20. Buscar pedido");

            System.out.println("21. Mostrar todas las oficinas juntos con sus vendedores y clientes");
            System.out.println("22. Mostrar el Vendedor que tiene MÁS ventas");
            System.out.println("23. Mostrar el Cliente que tenga MAYOR crédito");
            System.out.println("24. Mostrar el Producto que tenga el MENOR stock");

            System.out.println("25. Salir");

            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {

                case 1 -> listarOficinas();
                case 2 -> insertarOficina(sc);
                case 3 -> borrarOficina(sc);
                case 4 -> buscarOficina(sc);

                case 5 -> listarVendedores();
                case 6 -> insertarVendedor(sc);
                case 7 -> borrarVendedor(sc);
                case 8 -> buscarVendedor(sc);

                case 9 -> listarClientes();
                case 10 -> insertarCliente(sc);
                case 11 -> borrarCliente(sc);
                case 12 -> buscarCliente(sc);

                case 13 -> listarProductos();
                case 14 -> insertarProducto(sc);
                case 15 -> borrarProducto(sc);
                case 16 -> buscarProducto(sc);

                case 17 -> listarPedidos();
                case 18 -> insertarPedido(sc);
                case 19 -> borrarPedido(sc);
                case 20 -> buscarPedido(sc);

                case 21 -> relacionOficinaVendedorCliente();
                case 22 -> vendedorMasVentas();
                case 23 -> clienteMayorCredito();
                case 24 -> productoMenorStock();

                case 25 -> System.out.println("FIN DEL PROGRAMA");
                default -> System.out.println("Opción incorrecta.");
            }

        } while (opcion != 25);
    }

    private void listarOficinas() {
        var lista = crud.listarOficinas();
        if (lista.isEmpty()) System.out.println("NO HAY OFICINAS");
        else lista.forEach(o -> System.out.println(
                o.getNumeroOficina() + " " + o.getCiudad() + " " + o.getRegion() +
                        " " + o.getNumeroDirector() + " " + o.getObjetivo() + " " + o.getVentas()));
    }

    private void insertarOficina(Scanner sc) {
        System.out.print("Número oficina: ");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.print("Ciudad: ");
        String c = sc.nextLine();
        System.out.print("Región: ");
        String r = sc.nextLine();
        System.out.print("Número director: ");
        int d = sc.nextInt();
        System.out.print("Objetivo: ");
        double o = sc.nextDouble();
        System.out.print("Ventas: ");
        double v = sc.nextDouble();

        if (crud.insertarOficina(new Oficina(n, c, r, d, o, v)))
            System.out.println("INSERTADO");
        else System.out.println("NO SE PUEDE INSERTAR (YA EXISTE)");
    }

    private void borrarOficina(Scanner sc) {
        System.out.println("Introduzca el id de oficina a borrar: ");
        int id = sc.nextInt();
        if (crud.borrarOficina(id)) System.out.println("BORRADO");
        else System.out.println("ERROR AL BORRAR");
        ;
    }

    private void buscarOficina(Scanner sc) {
        System.out.println("Introduzca el id de oficina a buscar: ");
        int id = sc.nextInt();
        Oficina o = crud.buscarOficina(id);
        if (o != null) {
            System.out.println("Oficina: " + o.getNumeroOficina()
                    + "\nCiudad: " + o.getCiudad()
                    + "\nRegion: " + o.getRegion()
                    + "\nNum Director" + o.getNumeroDirector()
                    + "\nObjetivo: " + o.getObjetivo()
                    + "\nVentas: " + o.getVentas());
        } else System.out.println("NO EXISTE LA OFICINA");
    }

    private void listarVendedores() {
        var lista = crud.listarVendedores();
        if (lista.isEmpty()) System.out.println("NO HAY VENDEDORES");
        else lista.forEach(v -> System.out.println(
                v.getNumeroEmpleado() + " " + v.getNombre() + " " + v.getVentas()));
    }

    private void insertarVendedor(Scanner sc) {
        System.out.print("Número empleado: ");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Edad: ");
        int edad = sc.nextInt();
        System.out.print("Número oficina: ");
        int no = sc.nextInt();
        sc.nextLine();
        System.out.print("Oficio: ");
        String oficio = sc.nextLine();
        System.out.print("Fecha contrato: ");
        String fecha = sc.nextLine();
        System.out.print("Número director: ");
        int nd = sc.nextInt();
        System.out.print("Cuota: ");
        double cuota = sc.nextDouble();
        System.out.print("Ventas: ");
        double ventas = sc.nextDouble();

        if (crud.insertarVendedor(new Vendedor(n, nombre, edad, no, oficio, fecha, nd, cuota, ventas)))
            System.out.println("INSERTADO");
        else System.out.println("NO SE PUEDE INSERTAR");
    }

    private void borrarVendedor(Scanner sc) {
        System.out.println("Inserte el id del vendedor a borrar: ");
        int id = sc.nextInt();
        if (crud.borrarVendedor(id)) System.out.println("BORRADO"); else System.out.println("ERROR AL BORRAR");
    }

    private void buscarVendedor(Scanner sc) {
        System.out.println("Inserte el id del vendedor a buscar: ");
        int id = sc.nextInt();
        Vendedor v = crud.buscarVendedor(id);
        if (v != null) {
            System.out.println("Vendedor: " + v.getNumeroEmpleado()
                    + "\nNombre: " + v.getNombre()
                    + "\nEdad: " + v.getEdad()
                    + "\nNum Oficina: " + v.getNumeroOficina()
                    + "\nOficio: " + v.getOficio()
                    + "\nFecha Contrato: " + v.getFechaContrato()
                    + "\nNum Director: " + v.getNumeroDirector()
                    + "\nCuota: " + v.getCuota()
                    + "\nVentas: " + v.getVentas());
        } else System.out.println("NO EXISTE EL VENDEDOR");
    }

    private void listarClientes() {
        var lista = crud.listarClientes();
        if (lista.isEmpty()) System.out.println("NO HAY CLIENTES");
        else lista.forEach(c -> System.out.println(
                c.getNumeroCliente() + " " + c.getEmpresa() + " " + c.getLimiteCredito()));
    }

    private void insertarCliente(Scanner sc) {
        System.out.print("Número cliente: ");
        int nc = sc.nextInt();
        sc.nextLine();
        System.out.print("Empresa: ");
        String emp = sc.nextLine();
        System.out.print("Número empleado: ");
        int ne = sc.nextInt();
        System.out.print("Límite crédito: ");
        double lim = sc.nextDouble();

        if (crud.insertarCliente(new Cliente(nc, emp, ne, lim)))
            System.out.println("INSERTADO");
        else System.out.println("NO SE PUEDE INSERTAR");
    }

    private void borrarCliente(Scanner sc) {
        System.out.println("Inserte el numero del cliente a borrar: ");
        int numCli = sc.nextInt();
        if (crud.borrarCliente(numCli)) System.out.println("BORRADO"); else System.out.println("ERROR AL BORRAR");
    }

    private void buscarCliente(Scanner sc) {
        System.out.println("Inserte el numero del cliente a buscar: ");
        int numCli = sc.nextInt();
        Cliente c = crud.buscarCliente(numCli);

        if (c != null) {
            System.out.println("Cliente: " + c.getNumeroCliente()
                    + "\nEmpresa: " + c.getEmpresa()
                    + "\nNum Empleado: " + c.getNumeroEmpleado()
                    + "\nLimite Credito: " + c.getLimiteCredito());
        } else System.out.println("NO EXISTE EL CLIENTE");
    }

    private void listarProductos() {
        var lista = crud.listarProductos();
        if (lista.isEmpty()) System.out.println("NO HAY PRODUCTOS");
        else lista.forEach(p -> System.out.println(
                p.getCodigoProducto() + " " + p.getDescripcion() + " " + p.getExistencias()));
    }

    private void insertarProducto(Scanner sc) {
        sc.nextLine();
        System.out.print("Código fábrica: ");
        String cf = sc.nextLine();
        System.out.print("Código producto: ");
        String cp = sc.nextLine();
        System.out.print("Descripción: ");
        String des = sc.nextLine();
        System.out.print("Precio: ");
        double pr = sc.nextDouble();
        System.out.print("Existencias: ");
        int ex = sc.nextInt();

        if (crud.insertarProducto(new Producto(cf, cp, des, pr, ex)))
            System.out.println("INSERTADO");
        else System.out.println("NO SE PUEDE INSERTAR");
    }

    private void borrarProducto(Scanner sc) {
        System.out.println("Inserte el código del producto a borrar: ");
        String codProd = sc.next();
        if (crud.borrarProducto(codProd)) System.out.println("BORRADO"); else System.out.println("ERROR AL BORRAR");
    }

    private void buscarProducto(Scanner sc) {
        System.out.println("Inserte el id del producto a buscar: ");
        String id = sc.next();
        Producto p = crud.buscarProducto(id);
        if (p != null) {
            System.out.println("Producto: " + p.getCodigoProducto()
                    + "\nCódigo Fábrica: " + p.getCodigoFabrica()
                    + "\nDescripción: " + p.getDescripcion()
                    + "\nPrecio: " + p.getPrecio()
                    + "\nExistencias: " + p.getExistencias());
        } else System.out.println("NO EXISTE EL PRODUCTO");
    }

    private void listarPedidos() {
        var lista = crud.listarPedidos();
        if (lista.isEmpty()) System.out.println("NO HAY PEDIDOS");
        else lista.forEach(p -> System.out.println(
                p.getNumeroPedido() + " " + p.getFechaPedido() + " " + p.getImporte()));
    }

    private void insertarPedido(Scanner sc) {
        System.out.print("Número pedido: ");
        int np = sc.nextInt();
        sc.nextLine();

        System.out.print("Fecha pedido: ");
        String fp = sc.nextLine();

        System.out.print("Número cliente: ");
        int nc = sc.nextInt();

        System.out.print("Número empleado: ");
        int ne = sc.nextInt();
        sc.nextLine();

        System.out.print("Código fábrica: ");
        String cf = sc.nextLine();

        System.out.print("Código producto: ");
        String cp = sc.nextLine();

        System.out.print("Cantidad: ");
        int cant = sc.nextInt();

        System.out.print("Importe: ");
        double imp = sc.nextDouble();

        if (crud.insertarPedido(new Pedido(np, fp, nc, ne, cf, cp, cant, imp)))
            System.out.println("INSERTADO");
        else System.out.println("NO SE PUEDE INSERTAR");
    }

    private void borrarPedido(Scanner sc) {
        System.out.println("Inserte el numero del pedido a borrar: ");
        int numPed = sc.nextInt();
        if (crud.borrarPedido(numPed)) System.out.println("BORRADO"); else System.out.println("ERROR AL BORRAR");
    }

    private void buscarPedido(Scanner sc) {
        System.out.println("Inserte el numero del pedido a buscar: ");
        int numPed = sc.nextInt();
        Pedido p = crud.buscarPedido(numPed);
        if (p != null) {
            System.out.println("Pedido: " + p.getNumeroPedido()
                    + "\nFecha Pedido: " + p.getFechaPedido()
                    + "\nNum Cliente: " + p.getNumeroCliente()
                    + "\nNum Empleado: " + p.getNumeroEmpleado()
                    + "\nCódigo Fábrica: " + p.getCodigoFabrica()
                    + "\nCódigo Producto: " + p.getCodigoProducto()
                    + "\nCantidad: " + p.getCantidad()
                    + "\nImporte: " + p.getImporte());
        } else System.out.println("NO EXISTE EL PEDIDO");
    }

    private void relacionOficinaVendedorCliente() {
        System.out.println("OFICINAS CON SUS VENDEDORES Y CLIENTES");
        System.out.println(crud.MostrarOficinasVendedoresClientes());
    }

    private void vendedorMasVentas() {
        System.out.println("VENDEDOR CON MÁS VENTAS");
        Vendedor v = crud.MostrarVendedorConMasVentas();
        System.out.println(v.getNombre() + " con " + v.getVentas());
    }

    private void clienteMayorCredito() {
        System.out.println("CLIENTE CON MAYOR CRÉDITO");
        Cliente c = crud.MostrarClienteconMayorCredito();
        System.out.println(c.getEmpresa() + " con " + c.getLimiteCredito());
    }

    private void productoMenorStock() {
        System.out.println("PRODUCTO CON MENOR STOCK");
        Producto p = crud.MostrarProductoconMenorStock();
        System.out.println(p.getDescripcion() + " con " + p.getExistencias());
    }
}
