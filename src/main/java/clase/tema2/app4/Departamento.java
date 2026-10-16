package clase.tema2.app4;

import java.io.Serializable;

public class Departamento implements Serializable {
    private String nombre;
    private String loc;
    private int dep;

    public Departamento(String nombre, int dep, String loc) {
        this.nombre = nombre;
        this.dep = dep;
        this.loc = loc;
    }

    public Departamento() {}

    public String getNombre() { return nombre; }
    public String getLoc() { return loc; }
    public int getDep() { return dep; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setLoc(String loc) { this.loc = loc; }
    public void setDep(int dep) { this.dep = dep; }

    public void mostrar() {
        System.out.println("Departamento: " + dep +
                " | Nombre: " + nombre +
                " | Localidad: " + loc);
    }
}