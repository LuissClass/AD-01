package clase.tema1.ej1_2;

import java.io.Serializable;

public class Pedido implements Serializable {
    private int id;
    private String nombre;
    private int cantidad;
    private int idCliente;
    private int idProducto;

    public Pedido() {}

    public Pedido(int id, String nombre, int cantidad, int idCliente, int idProducto) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Nombre: " + nombre + '\'' +
                ", Cantidad: " + cantidad +
                ", Cliente ID: " + idCliente +
                ", Producto ID: " + idProducto +
                '}';
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
}
