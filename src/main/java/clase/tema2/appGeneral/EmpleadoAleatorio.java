package clase.tema2.appGeneral;

import java.io.IOException;
import java.io.RandomAccessFile;

public class EmpleadoAleatorio {

    public static final int TAMANIO_REGISTRO = 36;
    private static final int LONGITUD_APELLIDO = 10;

    private final int id;
    private final String apellido;
    private final int departamento;
    private final double salario;

    public EmpleadoAleatorio(
            int id,
            String apellido,
            int departamento,
            double salario) {

        this.id = id;
        this.apellido = apellido == null ? "" : apellido.trim();
        this.departamento = departamento;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDepartamento() {
        return departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void escribir(RandomAccessFile fichero) throws IOException {
	/*IMPLEMENTAR POR EL ALUMNO*/        
    }

    public static EmpleadoAleatorio leer(
            RandomAccessFile fichero) throws IOException {

	/*IMPLEMENTAR POR EL ALUMNO*/
       return null;
    }

    public void mostrar() {
        /*IMPLEMENTAR POR EL ALUMNO*/ 
    }
}
