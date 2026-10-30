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

    public static void mostrarCiclos(Document doc) {

        NodeList listaCiclosFormativos =
                doc.getElementsByTagName("cicloformativo");

        for (int i = 0; i < listaCiclosFormativos.getLength(); i++) {

            Node nodo = listaCiclosFormativos.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                Element cicloformativo = (Element) nodo;

                String id = cicloformativo.getAttribute("id");

                String nombre = cicloformativo.getElementsByTagName("nombre")
                        .item(0).getTextContent();

                String instituto = cicloformativo.getElementsByTagName("instituto")
                        .item(0).getTextContent();

                String curso = cicloformativo.getElementsByTagName("curso")
                        .item(0).getTextContent();

                String localidad = cicloformativo.getElementsByTagName("localidad")
                        .item(0).getTextContent();

                String numalumnos = cicloformativo.getElementsByTagName("numalumnos")
                        .item(0).getTextContent();

                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Instituto: " + instituto);
                System.out.println("Curso: " + curso);
                System.out.println("Localidad: " + localidad);
                System.out.println("Número de alumnos: " + numalumnos);
                System.out.println("-----------------------------");
            }
        }
    }

    public static void addNuevoCiclo(Document doc) {

        System.out.println("Introduce el nombre del ciclo: ");
        String nombreCiclo = sc.nextLine();

        System.out.println("Introduce el instituto: ");
        String instituto = sc.nextLine();

        System.out.println("Introduce el curso: ");
        String curso = sc.nextLine();

        System.out.println("Introduce la localidad: ");
        String localidad = sc.nextLine();

        System.out.println("Introduce el número de alumnos: ");
        String numalumnos = sc.nextLine();

        // Crear el nuevo cicloformativo
        Element nuevocicloformativo =
                doc.createElement("cicloformativo");

        // Añadir el atributo id
        nuevocicloformativo.setAttribute("id", "5");

        // Crear el elemento nombre
        Element nombre = doc.createElement("nombre");
        nombre.appendChild(doc.createTextNode(nombreCiclo));
        nuevocicloformativo.appendChild(nombre);

        // Crear el elemento instituto
        Element elementoInstituto = doc.createElement("instituto");
        elementoInstituto.appendChild(doc.createTextNode(instituto));
        nuevocicloformativo.appendChild(elementoInstituto);

        // Crear el elemento curso
        Element elementoCurso = doc.createElement("curso");
        elementoCurso.appendChild(doc.createTextNode(curso));
        nuevocicloformativo.appendChild(elementoCurso);

        // Crear el elemento localidad
        Element elementoLocalidad = doc.createElement("localidad");
        elementoLocalidad.appendChild(doc.createTextNode(localidad));
        nuevocicloformativo.appendChild(elementoLocalidad);

        // Crear el elemento numalumnos
        Element elementoNumalumnos = doc.createElement("numalumnos");
        elementoNumalumnos.appendChild(doc.createTextNode(numalumnos));
        nuevocicloformativo.appendChild(elementoNumalumnos);

        // Añadir el nuevo ciclo al documento
        doc.getDocumentElement().appendChild(nuevocicloformativo);

        System.out.println("Nuevo ciclo añadido correctamente.");
    }

    public static void modificarNumAlumnos(Document doc) {

        NodeList listaCiclosFormativos =
                doc.getElementsByTagName("cicloformativo");

        for (int i = 0; i < listaCiclosFormativos.getLength(); i++) {

            Node nodo = listaCiclosFormativos.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                Element cicloformativo = (Element) nodo;

                String id = cicloformativo.getAttribute("id");

                if (id.equals("3")) {

                    cicloformativo.getElementsByTagName("numalumnos")
                            .item(0).setTextContent("25");

                    System.out.println("Número de alumnos modificado.");
                }
            }
        }
    }

    public static void guardarXML(Document doc) {

        try {

            Scanner sc = new Scanner(System.in);
            String rutaGuardar = sc.nextLine();


            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();

            Transformer transformer =
                    transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(
                    new File(rutaGuardar));

            transformer.transform(source, result);

            System.out.println("Archivo XML guardado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
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
