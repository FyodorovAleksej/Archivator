package XMLDAO;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Alexey on 16.04.2017.
 */
public class XMLSchemaValidator {

    public static boolean checkXMLforXSD(String pathXml, String pathXsd) throws FileNotFoundException {

        File xml = new File(pathXml);
        File xsd = new File(pathXsd);

        if (!xml.exists()) {
            throw new FileNotFoundException("Can't find xml file");
        }

        if (!xsd.exists()) {
            throw new FileNotFoundException("Can't find xsd schema file");
        }

        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(xsd).newValidator().validate(new StreamSource(xml));
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }
}
