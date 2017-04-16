package XMLDAO.Parsers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import XMLDAO.Parsers.Parserable;
import XMLDAO.User;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


public class XMLJDOMParser implements Parserable {

    public User[] parseFromXML(String path) {
        try {
            File inputFile = new File(path);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element classElement = document.getRootElement();
            List<Element> userList = classElement.getChildren();
            User[] users = null;
            if (userList.size() > 0){
                users = new User[userList.size()];
                for (int i = 0; i < userList.size(); i++){
                    users[i] = parseFromXML(path, i);
                }
            }
            return users;
        } catch (Exception e){
          e.printStackTrace();
        }
        return null;
    }

    public User parseFromXML(String path, int index) {
        try {
            String firstName = null;
            String lastName = null;
            String fatherName = null;
            String telephoneNumber = null;
            String mail = null;
            String workPlace = null;
            int workExperience = 0;

            File inputFile = new File(path);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element classElement = document.getRootElement();
            List<Element> userList = classElement.getChildren();

            Element user = userList.get(index);
            Element FIO =  user.getChild("FIO");

            firstName = FIO.getChild("firstname").getText();
            lastName = FIO.getChild("lastname").getText();
            fatherName = FIO.getChild("fathername").getText();

            Element contact = user.getChild("contact");

            telephoneNumber = contact.getChild("telephonenumber").getText();
            mail = contact.getChild("mail").getText();

            Element work = user.getChild("work");

            workPlace = work.getChild("workplace").getText();
            workExperience = Integer.valueOf(work.getChild("experience").getText());

            return new User(firstName,lastName,fatherName,telephoneNumber,mail,workPlace,workExperience);
        }catch(JDOMException e){
            e.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        return null;
    }
}