package clase.tema2.app3.recursos;

import java.io.*;
public class LeerDep {
  public static void main(String[] args) throws IOException {    
   File fichero = new File("C:\\EJERCICIOS\\FichDepart.dat");
   FileInputStream filein = new FileInputStream(fichero);   
   DataInputStream dataIS = new DataInputStream(filein);

   String n,l;
   int d;

   try {
    while (true) {
        d = dataIS.readInt();  
		n = dataIS.readUTF();   
        l = dataIS.readUTF(); 
        System.out.println("Dep: " + d + ", nombre: " + n + ", loc: "+l);        
    }
    }catch (EOFException eo) {}
	
   dataIS.close();     
  }
}
