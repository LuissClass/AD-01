package clase.tema3;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConsultasSucursales {
    private Document cargarXML(String filePath) throws Exception {
        File entrada = new File(filePath);

        DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(entrada);
        doc.getDocumentElement().normalize();

        return doc;
    }

    public NodeList mostrarCuentasAhorro(String archivo) {
        try {
            Document doc = cargarXML(archivo);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/sucursales/sucursal/cuenta";

            NodeList cuentas = (NodeList) xPath.compile(expression)
                    .evaluate(doc, XPathConstants.NODESET);

            List<Node> lista = new ArrayList<>();

            for (int i = 0; i < cuentas.getLength(); i++) {
                Element cuenta = (Element) cuentas.item(i);

                String tipo = cuenta.getAttribute("tipo");

                if (tipo.equals("AHORRO")) {
                    lista.add(cuenta);
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

    public void mostrarNumeroCuentasAhorro(String archivo) {
    }

    public NodeList mostrarCuentasPensionesSUC3(String archivo) {
        try {
            Document doc = cargarXML(archivo);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/sucursales/sucursal[@codigo = \"SUC3\"]/cuenta[@tipo = \"PENSIONES\"]";

            NodeList cuentas = (NodeList) xPath.compile(expression)
                    .evaluate(doc, XPathConstants.NODESET);


            return cuentas;

        } catch (Exception e) {
            System.out.println(">> Error " + e.getMessage());
        }

        return null;
    }

    public void mostrarSaldoTotalPorSucursal(String archivo) {
        try {
            Document doc = cargarXML(archivo);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/sucursales/sucursal";

            NodeList sucursales = (NodeList) xPath.compile(expression)
                    .evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < sucursales.getLength(); i++) {
                Double saldoTotal = 0.0;
                Element sucursal = (Element) sucursales.item(i);

                String nameSucursal = sucursal.getAttribute("codigo");
                String director = sucursal.getElementsByTagName("director")
                        .item(0).getTextContent();


                NodeList cuentas = sucursal.getElementsByTagName("cuenta");

                for (int j = 0; j < cuentas.getLength(); j++) {
                    Element cuenta = (Element) cuentas.item(j);

                    double saldoActual = Double.parseDouble(cuenta.getElementsByTagName("saldohaber")
                            .item(0).getTextContent());

                    saldoTotal += saldoActual;
                }

                System.out.println(nameSucursal + " " + director + " " + saldoTotal);
            }
        } catch (Exception e) {
            System.out.println(">> Error " + e.getMessage());
        }
    }

    public void mostrarSucursalesMasDe3Cuentas(String archivo) {
        try {
            Document doc = cargarXML(archivo);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/sucursales/sucursal";

            NodeList sucursales = (NodeList) xPath.compile(expression)
                    .evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < sucursales.getLength(); i++) {
                Element sucursal = (Element) sucursales.item(i);

                String nameSucursal = sucursal.getAttribute("codigo");
                String dir = sucursal.getElementsByTagName("director")
                        .item(0).getTextContent();
                String pob = sucursal.getElementsByTagName("poblacion")
                        .item(0).getTextContent();


                NodeList cuentas = sucursal.getElementsByTagName("cuenta");

                if (cuentas.getLength() > 3) {
                    System.out.println(
                            "Sucursal: " + nameSucursal
                                    + "\n\tdirector: " + dir
                                    + "\n\tpoblacion: " + pob
                    );
                    for (int j = 0; j < cuentas.getLength(); j++) {
                        Element cuenta = (Element) cuentas.item(j);

                        String nomC = (cuenta.getElementsByTagName("nombre")
                                .item(0).getTextContent());
                        String tipo = cuenta.getAttribute("tipo");
                        String numC = (cuenta.getElementsByTagName("numero")
                                .item(0).getTextContent());
                        double saldoHaber = Double.parseDouble(cuenta.getElementsByTagName("saldohaber")
                                .item(0).getTextContent());
                        double saldoDebe = Double.parseDouble(cuenta.getElementsByTagName("saldodebe")
                                .item(0).getTextContent());

                        System.out.println(
                                "\tCuenta: " +
                                        "\n\t\tnombre: " + nomC +
                                        "\n\t\tnumero: " + numC +
                                        "\n\t\tsaldohaber: " + saldoHaber +
                                        "\n\t\tsaldodebe: " + saldoDebe +
                                        "\n\t\ttipo: " + tipo
                        );
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(">> Error " + e.getMessage());
        }
    }

    public void mostrarSucursalesMasDe3CuentasAhorro(String archivo) {
        try {
            Document doc = cargarXML(archivo);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/sucursales/sucursal";

            NodeList sucursales = (NodeList) xPath.compile(expression)
                    .evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < sucursales.getLength(); i++) {
                Element sucursal = (Element) sucursales.item(i);

                String nameSucursal = sucursal.getAttribute("codigo");
                String dir = sucursal.getElementsByTagName("director")
                        .item(0).getTextContent();
                String pob = sucursal.getElementsByTagName("poblacion")
                        .item(0).getTextContent();


                NodeList cuentas = sucursal.getElementsByTagName("cuenta");
                int cont = 0;

                String msg =
                        "Sucursal: " + nameSucursal
                                + "\n\tdirector: " + dir
                                + "\n\tpoblacion: " + pob;
                for (int j = 0; j < cuentas.getLength(); j++) {
                    Element cuenta = (Element) cuentas.item(j);

                    String nomC = (cuenta.getElementsByTagName("nombre")
                            .item(0).getTextContent());
                    String tipo = cuenta.getAttribute("tipo");
                    String numC = (cuenta.getElementsByTagName("numero")
                            .item(0).getTextContent());
                    double saldoHaber = Double.parseDouble(cuenta.getElementsByTagName("saldohaber")
                            .item(0).getTextContent());
                    double saldoDebe = Double.parseDouble(cuenta.getElementsByTagName("saldodebe")
                            .item(0).getTextContent());

                    if (tipo.equalsIgnoreCase("AHORRO")) {
                        cont++;

                        msg += "\tCuenta: " +
                                "\n\t\tnombre: " + nomC +
                                "\n\t\tnumero: " + numC +
                                "\n\t\tsaldohaber: " + saldoHaber +
                                "\n\t\tsaldodebe: " + saldoDebe +
                                "\n\t\ttipo: " + tipo;
                    }
                }

                if (cont > 3) {
                    System.out.println(msg);
                }
            }
        } catch (Exception e) {
            System.out.println(">> Error " + e.getMessage());
        }
    }

    public void mostrarDirectorYPoblacionMasDe3Cuentas(String archivo) {
            try {
                Document doc = cargarXML(archivo);

                XPath xPath = XPathFactory.newInstance().newXPath();

                String expression = "/sucursales/sucursal";

                NodeList sucursales = (NodeList) xPath.compile(expression)
                        .evaluate(doc, XPathConstants.NODESET);

                for (int i = 0; i < sucursales.getLength(); i++) {
                    Element sucursal = (Element) sucursales.item(i);

                    String nameSucursal = sucursal.getAttribute("codigo");
                    String dir = sucursal.getElementsByTagName("director")
                            .item(0).getTextContent();
                    String pob = sucursal.getElementsByTagName("poblacion")
                            .item(0).getTextContent();


                    NodeList cuentas = sucursal.getElementsByTagName("cuenta");

                    if (cuentas.getLength() > 3) {
                        System.out.println(
                            "director: " + dir
                                + "\npoblacion: " + pob
                        );
                    }
                }
            } catch (Exception e) {
                System.out.println(">> Error " + e.getMessage());
            }
    }

    public void contarSucursalesMadrid(String archivo) {
        try {
            Document doc = cargarXML(archivo);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/sucursales/sucursal";

            NodeList sucursales = (NodeList) xPath.compile(expression)
                    .evaluate(doc, XPathConstants.NODESET);

            int cont = 0;

            for (int i = 0; i < sucursales.getLength(); i++) {
                Element sucursal = (Element) sucursales.item(i);

                String pob = sucursal.getElementsByTagName("poblacion")
                        .item(0).getTextContent();


                if (pob.equalsIgnoreCase("Madrid")) {
                    cont++;
                }
            }

            System.out.println(
                    "Numero de sucursales de Madrid: " + cont
            );
        } catch (Exception e) {
            System.out.println(">> Error " + e.getMessage());
        }
    }


}