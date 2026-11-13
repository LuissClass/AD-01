package clase.tema3;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class ConsultasProductos {
    private Document cargarXML(String filePath) throws Exception {
        File entrada = new File(filePath);

        DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(entrada);
        doc.getDocumentElement().normalize();

        return doc;
    }

    public NodeList mostrarProductosPlacaBase(String archivo) {
        try {
            Document doc = cargarXML(archivo);
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression =
                    "/productos/produc[contains(denominacion, 'Placa Base')]";

            XPathExpression xPathExpression = xPath.compile(expression);


            return (NodeList) xPathExpression.evaluate(
                    doc, XPathConstants.NODESET);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public int contarMemoriasZona10(String archivo) {
        try {
            Document doc = cargarXML(archivo);
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression =
                    "count(/productos/produc[contains(denominacion, 'Memoria') and cod_zona=10])";

            XPathExpression xPathExpression = xPath.compile(expression);

            Double contador = (Double) xPathExpression.evaluate(
                    doc, XPathConstants.NUMBER);

            return contador.intValue();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public NodeList mostrarDenominacionYPrecio(String archivo) {
        try {
            Document doc = cargarXML(archivo);
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression =
                    "/productos/produc";

            XPathExpression xPathExpression = xPath.compile(expression);


            return (NodeList) xPathExpression.evaluate(
                    doc, XPathConstants.NODESET);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NodeList mostrarProductosPrecioMayor60Zona20(String archivo) {
        try {
            Document doc = cargarXML(archivo);
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression =
                    "/productos/produc[precio > 60 and cod_zona = 20]";

            XPathExpression xPathExpression = xPath.compile(expression);

            return (NodeList) xPathExpression.evaluate(
                    doc, XPathConstants.NODESET);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public double calcularMediaPrecioMicros(String archivo) {
        try {
            Document doc = cargarXML(archivo);
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression =
                    "sum(/productos/produc[contains(denominacion, 'Micro')]/precio) div count(/productos/produc[contains(denominacion, 'Micro')])";

            XPathExpression xPathExpression = xPath.compile(expression);

            Double media = (Double) xPathExpression.evaluate(
                    doc, XPathConstants.NUMBER);

            return media;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public NodeList mostrarProductosConFaltaDeStock(String archivo) {
        return null;
    }

    public NodeList mostrarProductosZona40ConFaltaDeStock(String archivo) {
        return null;
    }

    public NodeList mostrarProductoMasCaro(String archivo) {
        return null;
    }

    public NodeList mostrarProductoMasBaratoZona20(String archivo) {
        return null;
    }

    public NodeList mostrarProductoMasCaroZona10(String archivo) {
        return null;
    }

    // Imprimir todos los campos de cada producto
    public void imprimirProductos(NodeList productos) {
        if (productos == null || productos.getLength() == 0) {
            System.out.println("No se encontraron productos.");
            return;
        }

        for (int i = 0; i < productos.getLength(); i++) {
            Node producto = productos.item(i);

            System.out.println("\nProducto:");

            if (producto.getNodeName().equals("produc")) {
                NodeList detalles = producto.getChildNodes();

                for (int j = 0; j < detalles.getLength(); j++) {
                    Node detalle = detalles.item(j);

                    if (detalle.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println(
                                "  " + detalle.getNodeName() + ": "
                                        + detalle.getTextContent().trim());
                    }
                }
            } else {
                System.out.println(
                        "  " + producto.getNodeName() + ": "
                                + producto.getTextContent().trim());
            }
        }
    }


    public void imprimirDenominacionYPrecio(NodeList productos) {
        if (productos == null || productos.getLength() == 0) {
            System.out.println("No se encontraron productos.");
            return;
        }

        for (int i = 0; i < productos.getLength(); i++) {
            Node producto = productos.item(i);

            try {
                XPath xPath = XPathFactory.newInstance().newXPath();

                String denominacion =
                        xPath.evaluate("denominacion", producto).trim();

                String precio =
                        xPath.evaluate("precio", producto).trim();

                System.out.println(
                        "Producto: " + denominacion
                                + " | Precio: " + precio + " euros");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}