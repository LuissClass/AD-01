package clase.tema2.appGeneral;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class OperacionesAleatorios {

    static void escribirEmpleado(File fichero) throws IOException {
        //declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        StringBuffer buffer = null;    //bufer para almacenar apellido

        String apellido = "GONZALEZ";
        Double salario = 1230.87;
        int id = 20;
        int dep = 10;
        long posicion = (id - 1) * 36;

        file.seek(posicion); //nos posicionamos
        file.writeInt(id); //uso id para identificar empleado
        buffer = new StringBuffer(apellido);
        buffer.setLength(10); //10 caracteres para el apellido
        file.writeChars(buffer.toString());//insertar apellido
        file.writeInt(dep);       //insertar departamento
        file.writeDouble(salario);//insertar salario

        file.close();  //cerrar fichero
    }

    static void escribirVariosEmpleados(File fichero) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");
        //arrays con los datos
        String apellido[] = {"FERNANDEZ", "GIL", "LOPEZ", "RAMOS",
                "SEVILLA", "CASILLA", "REY"};//apellidos
        int dep[] = {10, 20, 10, 10, 30, 30, 20};       //departamentos
        Double salario[] = {1000.45, 2400.60, 3000.0, 1500.56,
                2200.0, 1435.87, 2000.0};//salarios

        StringBuffer buffer = null;//buffer para almacenar apellido
        int n = apellido.length;//numero de elementos del array

        for (int i = 0; i < n; i++) { //recorro los arrays
            file.writeInt(i + 1); //uso i+1 para identificar empleado
            buffer = new StringBuffer(apellido[i]);
            buffer.setLength(10); //10 caracteres para el apellido
            file.writeChars(buffer.toString());//insertar apellido
            file.writeInt(dep[i]);       //insertar departamento
            file.writeDouble(salario[i]);//insertar salario
        }
        file.close();  //cerrar fichero
    }

    void leerFich(File fichero) throws IOException {
        //declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
        //
        int id, dep, posicion;
        Double salario;
        char apellido[] = new char[10], aux;

        posicion = 0;  //para situarnos al principio

        for (; ; ) {  //recorro el fichero
            file.seek(posicion); //nos posicionamos en posicion
            id = file.readInt();   // obtengo id de empleado
            for (int i = 0; i < apellido.length; i++) {
                aux = file.readChar();//recorro uno a uno los caracteres del apellido
                apellido[i] = aux;    //los voy guardando en el array
            }
            String apellidoS = new String(apellido);//convierto a String el array
            dep = file.readInt();//obtengo dep
            salario = file.readDouble();  //obtengo salario

            System.out.println("ID: " + id + ", Apellido: " + apellidoS +
                    ", Departamento: " + dep + ", Salario: " + salario);
            posicion = posicion + 36; // me posiciono para el sig empleado
            //Cada empleado ocupa 36 bytes (4+20+4+8)
            //Si he recorrido todos los bytes salgo del for
            if (file.getFilePointer() == file.length()) break;

        }//fin bucle for
        file.close();  //cerrar fichero
    }

    static boolean leerUnEmpl(File fichero, int idBuscar) throws IOException {
        boolean existe = false;
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
        int id, dep, posicion;
        Double salario;
        char apellido[] = new char[10], aux;
        int registro = idBuscar; //recoger id de empleado a consultar

        posicion = (registro - 1) * 36;
        if (posicion >= file.length())
            System.out.println("ID: " + registro + ", NO EXISTE EMPLEADO...");
        else {
            file.seek(posicion); //nos posicionamos
            id = file.readInt(); // obtengo id de empleado

            existe = id == idBuscar;

            for (int i = 0; i < apellido.length; i++) {
                aux = file.readChar();//recorro uno a uno los caracteres del apellido
                apellido[i] = aux;    //los voy guardando en el array
            }

            String apellidoS = new String(apellido);//convierto a String el array
            dep = file.readInt();//obtengo dep
            salario = file.readDouble();  //obtengo salario

            System.out.println("ID: " + registro + ", Apellido: " + apellidoS +
                    ", Departamento: " + dep + ", Salario: " + salario);
        }
        file.close();  //cerrar fichero
        return  existe;
    }

    static void leerEmpleadoID(Scanner sc) throws IOException {
        System.out.print("Ruta del directorio: ");
        String ruta = sc.nextLine();
        System.out.println("Id: ");
        int idBuscar = Integer.parseInt(sc.nextLine());

        leerUnEmpl(new File(ruta), idBuscar);
    }

    static void insertarEmpleado(Scanner sc) throws IOException {
        System.out.print("Ruta del directorio: ");
        String ruta = sc.nextLine();
        File f = new File(ruta);

        //declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(f, "rw");

        StringBuffer buffer = null;    //bufer para almacenar apellido

        System.out.println("Apellido:");
        String apellido = sc.nextLine();
        System.out.println("Salario:");
        Double salario = Double.parseDouble(sc.nextLine());
        System.out.println("Id:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Dep:");
        int dep = Integer.parseInt(sc.nextLine());;
        long posicion = (id - 1) * 36;

        if (leerUnEmpl(f, id)) {
            System.out.println("EL EMPLEADO CON id " + id + " YA EXISTE");
            return;
        }

        file.seek(posicion); //nos posicionamos
        file.writeInt(id); //uso id para identificar empleado
        buffer = new StringBuffer(apellido);
        buffer.setLength(10); //10 caracteres para el apellido
        file.writeChars(buffer.toString());//insertar apellido
        file.writeInt(dep);       //insertar departamento
        file.writeDouble(salario);//insertar salario

        file.close();  //cerrar fichero
    }

    static void modificarEmpleado(Scanner sc) throws IOException {
            System.out.print("Ruta del directorio: ");
            String ruta = sc.nextLine();
            System.out.println("Id del empleado a buscar: ");
            int idBuscar = Integer.parseInt(sc.nextLine());

            if (!leerUnEmpl(new File(ruta), idBuscar)) {
                System.out.println("EL EMPLEADO CON id " + idBuscar + " NO EXISTE");
                return;
            }

            //declara el fichero de acceso aleatorio
            RandomAccessFile file = new RandomAccessFile(new File(ruta), "rw");

            StringBuffer buffer = null;    //bufer para almacenar apellido

            System.out.println("Apellido:");
            String apellido = sc.nextLine();
            System.out.println("Salario:");
            Double salario = Double.parseDouble(sc.nextLine());
            System.out.println("Dep:");
            int dep = Integer.parseInt(sc.nextLine());;
            long posicion = (idBuscar - 1) * 36;

            file.seek(posicion); //nos posicionamos
            file.writeInt(idBuscar); //uso id para identificar empleado
            buffer = new StringBuffer(apellido);
            buffer.setLength(10); //10 caracteres para el apellido
            file.writeChars(buffer.toString());//insertar apellido
            file.writeInt(dep);       //insertar departamento
            file.writeDouble(salario);//insertar salario

            file.close();  //cerrar fichero
        }

}
