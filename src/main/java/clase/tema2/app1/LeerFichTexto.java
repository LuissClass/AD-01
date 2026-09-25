package clase.tema2.app1;

import java.io.*;

public class LeerFichTexto {
  public static void main(String[] args) throws IOException {
    File fichero = new File("C:\\EJERCICIOS\\LeerFichTexto.java");
                
    FileReader fic = new FileReader(fichero);  
    int i;
    while ((i = fic.read()) != -1)  
      System.out.println((char) i);
    fic.close(); //cerrar fichero   
  }
}
