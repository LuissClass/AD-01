
package clase.tema3.app1;

public class ConsultasXquerys {

    // 1. Listar todos los hospitales
    public String consulta1() {
        String xquery =
                "for $hospital in /hospitales/hospital " +
                        "return $hospital/nombre";

        return xquery;
    }

    // 2. Obtener los hospitales que están en Madrid
    public String consulta2() {
   	String xquery =
            "for $hospital in /hospitales/hospital "
                    + "where $hospital/ciudad = 'Madrid' " +
                    "return $hospital/nombre";

        return xquery;
    }

    // 3. Obtener las direcciones de todos los hospitales
    public String consulta3() {
        String xquery =
                "for $hospital in /hospitales/hospital "
                        + "return $hospital/direccion";

        return xquery;
    }

    // 4. Obtener el teléfono del hospital con id H3
    public String consulta4() {
         String xquery =
                 "for $hospital in /hospitales/hospital "
                         + "where $hospital/@id = 'H3' "
                         + "return $hospital/telefonos/telefono";

        return xquery;
    }

    // 5. Hospitales con más de un teléfono
    public String consulta5() {
     String xquery =
             "for $hospital in /hospitales/hospital "
                     + "where count($hospital/telefonos/telefono) > 1 "
                     + "return $hospital/nombre";

        return xquery;
    }

    // 6. Hospitales ordenados por nombre
    public String consulta6() {
   String xquery =
           "for $hospital in /hospitales/hospital "
                   + "order by $hospital/nombre "
                   + "return $hospital/nombre";

        return xquery;
    }

    // 7. Obtener el nombre y la ciudad de cada hospital
    public String consulta7() {
   String xquery =
           "for $hospital in /hospitales/hospital "
                   + "return <hospital>{($hospital/nombre, $hospital/ciudad)}</hospital>";

        return xquery;
    }

    // 8. Médicos que trabajan en Fundación Jimenez Diaz
    public String consulta8() {
 	String xquery =
            "for $medico in /medicos/medico "
                    + "where $medico/hospital_id = 'H2'"
                    + "return $medico/nombre";

        return xquery;
    }
}
