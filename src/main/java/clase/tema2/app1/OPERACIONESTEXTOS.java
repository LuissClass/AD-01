package clase.tema2.app1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class OPERACIONESTEXTOS {

    public static void infoFichero(String rutaFichero, String rutaDirectorio) {
        File f1 = new File(rutaFichero);
        File d1 = new File(rutaDirectorio);

        System.out.println("FICHERO: " + f1.getName());
        System.out.println("\t" + f1.getAbsolutePath());
        System.out.println("\tExiste: " + f1.exists());
        System.out.println("\tPermiso de escritura: " + f1.canWrite());
        System.out.println("\tPermiso de lectura: " + f1.canRead());
        System.out.println("\tEs directorio: " + f1.isDirectory());
        System.out.println("\tEs fichero: " + f1.isFile());
        System.out.println("\tEs absoluto: " + f1.isAbsolute());
        System.out.println("\nCONTENIDO DEL DIRECTORIO: " + d1.getName());

        String[] files = d1.list();
        for (String file : files) {
            System.out.println(file);
        }
    }

    public static void crearFicheroDirectorio(String rutaDirectorio, String nombreArchivo, String nombreDirectorio) {
        File d = new File(rutaDirectorio);
        File f1 = new File(d, nombreArchivo);
        File d2 = new File(d, nombreDirectorio);

        d.mkdir();

        try {
            if (f1.createNewFile())
                System.out.println("DIR de C: " + d.getName());
            else
                System.out.println("NO SE HA PODIDO CREAR EL DIR");
            if (f1.createNewFile())
                System.out.println("FICHERO CREADO: " + f1.getName());
            else
                System.out.println("No se ha podido crear FICHERO " + f1.getName());
            if (d2.mkdir())
                System.out.println("DIR CREADO: " + d2.getName());
            else
                System.out.println("No se ha podido crear DIR " + d2.getName());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void leerCaracteres(String rutaFichero) {
        try (BufferedReader fic = new BufferedReader(
                new FileReader(rutaFichero, StandardCharsets.UTF_8))) {
            int i;
            int contador = 0;
            while ((i = fic.read()) != -1) {
                System.out.println(contador + ":" + (char) i + "UNICODE: " + i);
                contador++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException e) {
            System.out.println("Error de E/S ");
        }
    }

    public static void leerLineas(String rutaFichero) {
        try (BufferedReader fic = new BufferedReader(
                new FileReader(rutaFichero, StandardCharsets.UTF_8))) {
            String line;
            while ((line = fic.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException e) {
            System.out.println("Error de E/S ");
        }
    }

    public static void leerLineasPares(String rutaFichero) {
        try (BufferedReader fic = new BufferedReader(
                new FileReader(rutaFichero, StandardCharsets.UTF_8))) {
            String line;
            int cont = 1;
            while ((line = fic.readLine()) != null) {
                if (cont % 2 == 0) {
                    System.out.println(line);
                }
                cont++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException e) {
            System.out.println("Error de E/S ");
        }
    }

    public static void escribirFichero(String rutaFichero, String[] lineas) {
        try (BufferedWriter fic = new BufferedWriter(
                new FileWriter(rutaFichero, StandardCharsets.UTF_8))) {
            for (String l : lineas) {
                fic.write(l + "\n");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException e) {
            System.out.println("Error de E/S ");
        }
    }

    public static void escribirLineasImpares(String rutaFichero, String[] lineas) {
        int cont = 1;
        try (BufferedWriter fic = new BufferedWriter(
                new FileWriter(rutaFichero, StandardCharsets.UTF_8))) {
            for (String l : lineas) {
                if (cont % 2 != 0) fic.write(l + "\n");
                cont++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException e) {
            System.out.println("Error de E/S ");
        }
    }

    public static void copiarFicheroConFin(String rutaOrigen, String rutaDestino) {
        try (BufferedReader fic = new BufferedReader(
                new FileReader(rutaOrigen, StandardCharsets.UTF_8))) {
            String line;
            try (BufferedWriter fic2 = new BufferedWriter(
                    new FileWriter(rutaDestino, StandardCharsets.UTF_8))) {
                while ((line = fic.readLine()) != null) {
                    fic2.write(line + "\n");
                }
                fic2.write("FIN DEL FICHERO DE ENTRADA");
            } catch (FileNotFoundException e) {
                System.out.println("No se encuentra el fichero");
            } catch (IOException e) {
                System.out.println("Error de E/S ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException e) {
            System.out.println("Error de E/S ");
        }

    }

    public static void separarParesImpares(String rutaFichero, String rutaPares, String rutaImpares) {
        try (BufferedReader fic = new BufferedReader(
                new FileReader(rutaFichero, StandardCharsets.UTF_8))) {
            int i;
            int contador = 0;
            try (BufferedWriter bwPar = new BufferedWriter(
                    new FileWriter(rutaPares));
                 BufferedWriter bwImpar = new BufferedWriter(
                         new FileWriter(rutaImpares));) {
                while ((i = fic.read()) != -1) {
                    if (contador % 2 == 0) {
                        bwPar.write((char) i);
                    } else bwImpar.write((char) i);
                    contador++;
                }

                bwPar.write("\nFIN FICHERO PARES");
                bwImpar.write("\nFIN FICHERO IMPARES");

            } catch (FileNotFoundException e) {
                System.out.println("No se encuentra el fichero");
            } catch (IOException e) {
                System.out.println("Error de E/S ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException e) {
            System.out.println("Error de E/S ");
        }
    }

    public static void buscarLinea(String rutaFichero, String lineaBuscada) {
        String[] palabras;
        String line;
        int cont = 0;
        boolean palabraEncontrada = false;
        try (BufferedReader fic = new BufferedReader(
                new FileReader(rutaFichero, StandardCharsets.UTF_8))) {
            while ((line = fic.readLine()) != null) {
                palabras = line.split(" ");
                for (String p: palabras) {
                    if (p.equalsIgnoreCase(lineaBuscada)) {
                        System.out.println("LINEA ENCONTRADA (" + cont +"): " + line);
                        palabraEncontrada = true;
                    }
                }
                cont++;
            }

            if (!palabraEncontrada) System.out.println("NO SE ENCONTRO LA LINEA: " + lineaBuscada);
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException e) {
            System.out.println("Error de E/S ");
        }
    }

    public static void copiarLineasMayores10(String rutaEntrada, String rutaSalida) {
        try (BufferedReader fic = new BufferedReader(
                new FileReader(rutaEntrada, StandardCharsets.UTF_8))) {
            String line;
            try (BufferedWriter fic2 = new BufferedWriter(
                    new FileWriter(rutaSalida, StandardCharsets.UTF_8))) {
                while ((line = fic.readLine()) != null) {
                    if (line.length() > 10) {
                        fic2.write(line + "\n");
                    }
                }
                fic2.write("FIN DEL FICHERO DE ENTRADA");
            } catch (FileNotFoundException e) {
                System.out.println("No se encuentra el fichero");
            } catch (IOException e) {
                System.out.println("Error de E/S ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException e) {
            System.out.println("Error de E/S ");
        }

    }

    public static void copiarFicheros(String rutaOrigen, String rutaDestino) {
        File f1 = new File(rutaOrigen);
        File f2 = new File(rutaDestino);

        try {
            if (f2.createNewFile()) {
                copiarFicheroConFin(rutaOrigen, rutaDestino);
                System.out.println("COPIA RALIZADA DE " + f1.getAbsolutePath() + " A " + f2.getAbsolutePath());
            }
            else System.out.println("NO SE HA PODIDO CREAR LA COPIA");
        } catch (IOException ioe) {
            System.out.println("ERROR IOException");
        }
    }

}