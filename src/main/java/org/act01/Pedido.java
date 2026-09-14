package org.act01;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Date;

public class Pedido implements Serializable, PropertyChangeListener {

    private int numPedido;
    private int idProducto;
    private Date fecha;
    private int cantidad;
    private boolean pedir; // se usa para controlar si hay que hacer pedido

    public Pedido() {}

    public Pedido(int numPedido, int idProducto, Date fecha, int cantidad) {
        this.numPedido = numPedido;
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Stock anterior: " + evt.getOldValue());
        System.out.println("Stock actual: " + evt.getNewValue());
        setPedir(true);
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isPedir() {
        return pedir;
    }

    public void setPedir(boolean pedir) {
        this.pedir = pedir;
    }
}
