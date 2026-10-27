package clase.tema2.appGeneral;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OperacionesXML {
    private static Scanner sc = new Scanner(System.in);

    private static String getNodo(String etiqueta, Element elem) {
        NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node valornodo = (Node) nodo.item(0);
        return valornodo.getNodeValue();
    }

    private void GuardarXML(Document doc, String nombreFichero) throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(
                new DOMSource(doc),
                new StreamResult(new File(nombreFichero))
        );

        System.out.println("Archivo guardado con éxito.");
    }

    public static void ListarXML () {
        System.out.println("Ruta fichero DEPARTAMENTOS: ");
        String nombreFichero = sc.nextLine();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(nombreFichero));
            document.getDocumentElement().normalize();

            System.out.println("Elemento raíz: " + document.getDocumentElement().getNodeName());
            NodeList departamentos = document.getElementsByTagName("departamento");

            for (int i = 0; i < departamentos.getLength(); i++) {
                Node depar = departamentos.item(i);
                if (depar.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) depar;
                    System.out.println("Numero: " + getNodo("numero", elemento));
                    System.out.println("Nombre: " + getNodo("nombre", elemento));
                    System.out.println("Localidad: " + getNodo("localidad", elemento));
                    System.out.println("----------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DeptMayorYGuadalajara() {
        System.out.println("Ruta fichero DEPARTAMENTOS: ");
        String nombreFichero = sc.nextLine();

        int numDeptMayor = 0;
        List<String> deptsGuadalajara = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(nombreFichero));
            document.getDocumentElement().normalize();

            System.out.println("Elemento raíz: " + document.getDocumentElement().getNodeName());
            NodeList departamentos = document.getElementsByTagName("departamento");

            for (int i = 0; i < departamentos.getLength(); i++) {
                Node depar = departamentos.item(i);
                if (depar.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) depar;

                    if (Integer.parseInt(getNodo("numero", elemento)) > numDeptMayor) {
                        numDeptMayor = Integer.parseInt(getNodo("numero", elemento));
                    }

                    if (getNodo("localidad", elemento).equalsIgnoreCase("Guadalajara")) {
                        deptsGuadalajara.add("Numero: " + getNodo("numero", elemento) + "\n"
                                + "Nombre: " + getNodo("nombre", elemento) + "\n"
                                + "Localidad: " + getNodo("localidad", elemento));
                    }
                }
            }

            for (String dep: deptsGuadalajara) {
                System.out.println(dep);
            }
            System.out.println("El numero mayor es: " + numDeptMayor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
