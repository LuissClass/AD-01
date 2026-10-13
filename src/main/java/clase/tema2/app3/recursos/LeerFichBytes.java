package clase.tema2.app3.recursos;

import java.io.*;
public class LeerFichBytes {
  public static void main(String[] args) throws IOException {
   File fichero = new File("C:\\EJERCICIOS\\FichBytes.dat");
   FileInputStream fic = new FileInputStream(fichero);    
   int i;
	
   while ((i = fic.read()) != -1)
	    System.out.println(i);
	
    fic.close();    
  }
}