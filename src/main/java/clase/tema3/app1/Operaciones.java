
package clase.tema3.app1;

import net.sf.saxon.s9api.*;

import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class Operaciones {

    private static final Processor processor = new Processor(false);
    private static final XQueryCompiler compiler =
            processor.newXQueryCompiler();

    public static XdmValue ejecutarConsulta(
            String xquery, String ficheroXml)
            throws SaxonApiException {

        XQueryExecutable executable = compiler.compile(xquery);
        XQueryEvaluator evaluator = executable.load();

        evaluator.setSource(new StreamSource(new File(ficheroXml)));

        return evaluator.evaluate();
    }
}
 