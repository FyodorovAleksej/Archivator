import XMLDAO.Person;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.io.*;


/**
 * Created by Alexey on 15.04.2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonTest {

    final String temp = "temp.out";

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void serialize() throws Exception {
        try {
            FileOutputStream fos = new FileOutputStream(temp);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Person person = new Person("123","ADDRR", "Telyatnik", "+375298808076", "Telyatina@gmail.com", "BSUIR", 5);
            oos.writeObject(person);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("temp.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            Person input = (Person) oin.readObject();
            assert (input.equals(person));

        }catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return;
    }
}