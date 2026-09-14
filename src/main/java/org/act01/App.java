package org.act01;

public class App {
    public static void main(String[] args) {
        Producto producto = new Producto(1, "Producto 1", 10, 5, 100.0f);
        Pedido pedido = new Pedido(1, 1, new java.util.Date(), 5);
        producto.addPropertyChangeListener(pedido);

        producto.setStockActual(3); // Esto debería disparar el evento de cambio de propiedad y llamar al método propertyChange del pedido

        if (pedido.isPedir()) {
            System.out.println("Se necesita hacer un pedido para el producto: " + producto.getDescripcion());
        } else {
            System.out.println("No se necesita hacer un pedido para el producto: " + producto.getDescripcion());
        }
    }
}
