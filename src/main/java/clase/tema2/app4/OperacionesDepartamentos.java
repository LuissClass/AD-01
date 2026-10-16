package clase.tema2.app4;

import java.io.*;

public class OperacionesDepartamentos {

    private static final String FICHERO = "c:\\FicherosBinarios\\Departamentos.dat";
    private static final String FICHERO_AUX = "c:\\FicherosBinarios\\DepartamentosAux.dat";

    public void crearFicheroInicial() {

        File fa = new File(FICHERO_AUX);
        File f = new File(FICHERO);

        if (!fa.exists()) {
            try {
                fa.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (f.exists()) return;


        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FICHERO))) {

            out.writeObject(new Departamento("Contabilidad", 10, "Madrid"));
            out.writeObject(new Departamento("Informática", 20, "Sevilla"));
            out.writeObject(new Departamento("RRHH", 30, "Valencia"));
            out.writeObject(new Departamento("Marketing", 40, "Barcelona"));
            out.writeObject(new Departamento("Ventas", 50, "Bilbao"));
            out.writeObject(new Departamento("Producción", 60, "Zaragoza"));
            out.writeObject(new Departamento("Logística", 70, "Murcia"));
            out.writeObject(new Departamento("Calidad", 80, "Valladolid"));
            out.writeObject(new Departamento("I+D", 90, "Málaga"));
            out.writeObject(new Departamento("Atención al Cliente", 100, "Alicante"));


            System.out.println("Fichero de departamentos creado.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Departamento devolverDepartamento(int depId) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FICHERO)))
        {
            Departamento dep;

            while(true) {
                dep = (Departamento) in.readObject();
                if (dep.getDep() == depId) {
                    return dep;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer " + FICHERO);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            //fin bucle
        }
        System.out.println(">> EL DEPARTAMENTO NO EXISTE <<");
        return null;
    }

    public void modificarDepartamento(int depBuscado, String nuevoNombre, String nuevaLoc) {
        Departamento depOriginal = devolverDepartamento(depBuscado);
        Departamento depNuevo = null;

        if (depOriginal == null) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FICHERO));
             ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FICHERO_AUX))) {

            while (true) {
                Departamento dep = (Departamento) in.readObject();

                if (dep.getDep() == depBuscado) {
                    dep.setNombre(nuevoNombre);
                    dep.setLoc(nuevaLoc);
                    depNuevo = dep;
                }

                out.writeObject(dep);
            }

        } catch (EOFException e) {
            // final esperado
        } catch (Exception e) {
            e.printStackTrace();
        }

        reemplazarFichero();

        System.out.println(">>> DEPARTAMENTO ORIGINAL <<<");
        depOriginal.mostrar();
        System.out.println(">>> DEPARTAMENTO NUEVO <<<");
        depNuevo.mostrar();

        System.out.println("\n>>> MODIFICACIÓN COMPLETADA con éxito <<<\n");
    }

    public void eliminarDepartamento(int depEliminar) {
        int cont = 0;


        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FICHERO));
             ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FICHERO_AUX))) {

            while (true) {
                Departamento dep = (Departamento) in.readObject();

                if (dep.getDep() == depEliminar) {
                    continue;
                }

                cont++;
                out.writeObject(dep);
            }

        } catch (EOFException e) {
            // final esperado
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (devolverDepartamento(depEliminar) == null) {
            System.out.println("Departamentos restantes: " + cont);
            return;
        }

        reemplazarFichero();
        System.out.println("\n>>> DEPARTAMENTO ELIMINADO <<<");
        System.out.println("Departamentos restantes: " + cont);
    }

    private void reemplazarFichero() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FICHERO_AUX));
             ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FICHERO))) {

            while (true) {
                Departamento dep = (Departamento) in.readObject();
                out.writeObject(dep);
            }

        } catch (EOFException e) {
            // final esperado
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarDepartamentos() {
        System.out.println("\n=== LISTADO DE DEPARTAMENTOS ===");

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FICHERO))) {

            while (true) {
                Departamento dep = (Departamento) in.readObject();
                dep.mostrar();
            }

        } catch (EOFException e) {
            System.out.println("Fin del listado.\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

