package org.act02;

/*
* App CLI
*
* Trabajando sobre un fichero de acceso aleatorio
* Consultar datos de un empleado (id, apellido, dept, salario)
* Insertar datos de empleado  (comprobando que no exista ya)
* Modificar el salario del empleado sumando el importe introducido
* Borrar un empleado (el id se vuelve -1 y el apellido se vuelve el id, depart y salario a 0)
*
* Otro programa debe mostrar los id de los empleados borrados
*
* */

public class Main {
    public static void main(String[] args) {
        GestorEmpleados ge = new GestorEmpleados();
        ge.getEmpleado(20);
    }
}
