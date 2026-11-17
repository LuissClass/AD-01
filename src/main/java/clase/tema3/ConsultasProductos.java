package clase.tema3;

import net.sf.saxon.dom.DOMNodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println(">> Error " + e.getMessage());
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
            System.out.println(">> Error " + e.getMessage());
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
            System.out.println(">> Error " + e.getMessage());
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
            System.out.println(">> Error " + e.getMessage());
        }
        return null;
    }

    public double calcularMediaPrecioMicros(String archivo) {
        try {
            Document doc = cargarXML(archivo);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/productos/produc";

            NodeList productos = (NodeList) xPath.compile(expression)
                    .evaluate(doc, XPathConstants.NODESET);

            double suma = 0;
            int contador = 0;

            for (int i = 0; i < productos.getLength(); i++) {

                Element producto = (Element) productos.item(i);

                String denominacion = producto
                        .getElementsByTagName("denominacion")
                        .item(0).getTextContent();

                if (denominacion.contains("Micro")) {

                    String precioTexto = producto
                            .getElementsByTagName("precio")
                            .item(0).getTextContent();

                    double precio = Double.parseDouble(precioTexto);

                    suma += precio;
                    contador++;
                }
            }

            if (contador == 0) {
                return 0;
            }

            return suma / contador;

        } catch (Exception e) {
            System.out.println(">> Error " + e.getMessage());
        }

        return 0;
    }

    public NodeList mostrarProductosConFaltaDeStock(String archivo) {
        try {
            Document doc = cargarXML(archivo);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/productos/produc";

            NodeList productos = (NodeList) xPath.compile(expression)
                    .evaluate(doc, XPathConstants.NODESET);

            List<Node> listaProdsFaltaDeStock = new ArrayList<>();


            for (int i = 0; i < productos.getLength(); i++) {
                Element producto = (Element) productos.item(i);

                int stock_actual = Integer.parseInt(producto
                        .getElementsByTagName("stock_actual")
                        .item(0).getTextContent());

                int stock_minimo = Integer.parseInt(producto
                        .getElementsByTagName("stock_minimo")
                        .item(0).getTextContent());

                if (stock_actual < stock_minimo) {
                    listaProdsFaltaDeStock.add(producto);
                }
            }

            NodeList nodeListProdFaltaDeStock = new NodeList() {
                @Override
                public Node item(int index) {
                    return index >= 0 && index < listaProdsFaltaDeStock.size()
                            ? listaProdsFaltaDeStock.get(index) : null;
                }

                @Override
                public int getLength() {
                    return listaProdsFaltaDeStock.size();
                }
            };

            return nodeListProdFaltaDeStock;
        } catch (Exception e) {
            System.out.println(">> Error " + e.getMessage());
        }

        return null;
    }

    public NodeList mostrarProductosZona40ConFaltaDeStock(String archivo) {
        try {
            Document doc = cargarXML(archivo);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/productos/produc[cod_zona = 40]";

            NodeList productos = (NodeList) xPath.compile(expression)
                    .evaluate(doc, XPathConstants.NODESET);

            List<Node> listaProdsFaltaDeStock = new ArrayList<>();


            for (int i = 0; i < productos.getLength(); i++) {
                Element producto = (Element) productos.item(i);

                int stock_actual = Integer.parseInt(producto
                        .getElementsByTagName("stock_actual")
                        .item(0).getTextContent());

                int stock_minimo = Integer.parseInt(producto
                        .getElementsByTagName("stock_minimo")
                        .item(0).getTextContent());

                if (stock_actual < stock_minimo) {
                    listaProdsFaltaDeStock.add(producto);
                }
            }

            NodeList nodeListProdFaltaDeStock = new NodeList() {
                @Override
                public Node item(int index) {
                    return index >= 0 && index < listaProdsFaltaDeStock.size()
                            ? listaProdsFaltaDeStock.get(index) : null;
                }

                @Override
                public int getLength() {
                    return listaProdsFaltaDeStock.size();
                }
            };

            return nodeListProdFaltaDeStock;
        } catch (Exception e) {
            System.out.println(">> Error " + e.getMessage());
        }

        return null;
    }

    public NodeList mostrarProductoMasCaro(String archivo) {
        try {
            Document doc = cargarXML(archivo);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/productos/produc";

            NodeList productos = (NodeList) xPath.compile(expression)
                    .evaluate(doc, XPathConstants.NODESET);

            List<Node> lista = new ArrayList<>();
            double precioMax = 0;

            for (int i = 0; i < productos.getLength(); i++) {
                Element producto = (Element) productos.item(i);

                double precioActual = Double.parseDouble((producto
                        .getElementsByTagName("precio")
                        .item(0).getTextContent()));

                if (precioActual > precioMax) {
                    lista.clear();
                    lista.add(producto);
                    precioMax = precioActual;
                }
            }

            NodeList nodeList = new NodeList() {
                @Override
                public Node item(int index) {
                    return index >= 0 && index < lista.size()
                            ? lista.get(index) : null;
                }

                @Override
                public int getLength() {
                    return lista.size();
                }
            };

            return nodeList;
        } catch (Exception e) {
            System.out.println(">> Error " + e.getMessage());
        }

        return null;
    }

    public NodeList mostrarProductoMasBaratoZona20(String archivo) {
        try {
            Document doc = cargarXML(archivo);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/productos/produc[cod_zona = 20]";

            NodeList productos = (NodeList) xPath.compile(expression)
                    .evaluate(doc, XPathConstants.NODESET);

            List<Node> lista = new ArrayList<>();
            double precioMas = Double.MAX_VALUE;

            for (int i = 0; i < productos.getLength(); i++) {
                Element producto = (Element) productos.item(i);

                double precioActual = Double.parseDouble((producto
                        .getElementsByTagName("precio")
                        .item(0).getTextContent()));

                if (precioActual < precioMas) {
                    lista.clear();
                    lista.add(producto);
                    precioMas = precioActual;
                }
            }

            NodeList nodeList = new NodeList() {
                @Override
                public Node item(int index) {
                    return index >= 0 && index < lista.size()
                            ? lista.get(index) : null;
                }

                @Override
                public int getLength() {
                    return lista.size();
                }
            };

            return nodeList;
        } catch (Exception e) {
            System.out.println(">> Error " + e.getMessage());
        }

        return null;
    }

    public NodeList mostrarProductoMasCaroZona10(String archivo) {
        try {
            Document doc = cargarXML(archivo);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/productos/produc[cod_zona = 10]";

            NodeList productos = (NodeList) xPath.compile(expression)
                    .evaluate(doc, XPathConstants.NODESET);

            List<Node> lista = new ArrayList<>();
            double precioMas = 0;

            for (int i = 0; i < productos.getLength(); i++) {
                Element producto = (Element) productos.item(i);

                double precioActual = Double.parseDouble((producto
                        .getElementsByTagName("precio")
                        .item(0).getTextContent()));

                if (precioActual > precioMas) {
                    lista.clear();
                    lista.add(producto);
                    precioMas = precioActual;
                }
            }

            NodeList nodeList = new NodeList() {
                @Override
                public Node item(int index) {
                    return index >= 0 && index < lista.size()
                            ? lista.get(index) : null;
                }

                @Override
                public int getLength() {
                    return lista.size();
                }
            };

            return nodeList;
        } catch (Exception e) {
            System.out.println(">> Error " + e.getMessage());
        }

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