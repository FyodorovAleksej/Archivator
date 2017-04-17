import XMLDAO.Parsers.XMLDOMParser;
import XMLDAO.Parsers.XMLJDOMParser;
import XMLDAO.Parsers.XMLSAXParser;
import XMLDAO.Parsers.XMLStAXParser;
import XMLDAO.Person;
import XMLDAO.XMLEditor;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;


/**
 * Created by Alexey on 15.04.2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class XMLEditorTest {

    final String schema = "validationSchema.xsd";
    final String path = "test.xml";
    final String archive = "test.zip";

    XMLEditor editor = new XMLEditor(path,new XMLStAXParser(), archive);

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void add() throws Exception {
        editor.add(new Person("addr","ADDRR","Telyatnik","+375298808076","Telyatina@gmail.com","BSUIR",5),0);
        assert (editor.get(0).getFirstName().equals("ADDRR"));
        //System.out.println(editor);
    }

    @org.junit.Test
    public void addInBegin() throws Exception {
        editor.addInBegin(new Person("begin","BEGIN","Telyatnik","+375298808076","Telyatnik.loh@gmail.com","BSUIR",1));
        assert (editor.get(0).getFirstName().equals("BEGIN"));
        //System.out.println(editor);
    }

    @org.junit.Test
    public void addInEnd() throws Exception {
        editor.addInEnd(new Person("end","END","Telyatnik","+375298808076","Telyatnik.loh@gmail.com","BSUIR",1));
        assert (editor.get(editor.length() - 1).getFirstName().equals("END"));
        //System.out.println(editor);
    }

    @org.junit.Test
    public void delete() throws Exception {
        System.out.println(editor);
        if (editor.delete(0)) {
            System.out.println(editor);
        }
        else {
            System.out.println("can't delete");
        }
        assert (editor.get(0).getFirstName().equals("ADDRR"));
    }

    @org.junit.Test
    public void edit() throws Exception {
        editor.edit(new Person("belka","Belka","Telyatnik","+375298808076","Telyatnik.loh@gmail.com","BSUIR",1),1);
        assert (editor.get(1).getFirstName().equals("Belka"));

    }

    @org.junit.Test
    public void validate() throws Exception {
        assert (editor.validate(schema));
    }

    @org.junit.Test
    public void compress() throws Exception {
        editor.compress(6);
    }

    @org.junit.Test
    public void decompress() throws Exception {
        editor.uncompress();
    }

    @org.junit.Test
    public void getListTest(){
        ArrayList<Person> list = editor.getList();
        for(Person person : list) {
            System.out.println(person);
        }
    }

    @org.junit.Test
    public void find(){
        assert (editor.findIndexAsName("addr") == 0);
    }
}