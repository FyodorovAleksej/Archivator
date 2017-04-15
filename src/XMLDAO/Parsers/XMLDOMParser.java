package XMLDAO.Parsers;

import XMLDAO.User;
import XMLDAO.XMLConstants.XMLCONST;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


/**
 * Created by Alexey on 15.04.2017.
 */
public class XMLDOMParser implements Parserable{

    @Nullable
    public User[] parseFromXML(String path) {
        try {
            File inputFile = new File(path);
            if (!inputFile.exists()){
                System.out.println("NOT EXIST!");
                return null;
            }
            System.out.println("EXIST");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("user");
            System.out.println(nList.getLength());
            User[] users;
            if (nList.getLength() > 0) {
                users = new User[nList.getLength()];
            }
            else {
                users = null;
            }
            for (int temp = 0; temp < nList.getLength(); temp++) {
                    users[temp] = parseFromXML(path, temp);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public User parseFromXML(String path, int index) {
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("user");
            Node nNode = nList.item(index);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                String firstName = getXMLArgument(nNode, "firstname", XMLCONST.XMLNAME);
                String lastName = getXMLArgument(nNode, "lastname", XMLCONST.XMLNAME);
                String fatherName = getXMLArgument(nNode, "fathername", XMLCONST.XMLNAME);
                String telephoneNumber = getXMLArgument(nNode, "telephonenumber", XMLCONST.XMLPHONENUMBER);
                String mail = getXMLArgument(nNode, "mail", XMLCONST.XMLMAIl);
                String workPlace = getXMLArgument(nNode, "workplace", XMLCONST.XMLNONE);
                int workExperience = Integer.valueOf(getXMLArgument(nNode, "experience", XMLCONST.XMLEXPERIENCE));
                return new User(firstName, lastName, fatherName, telephoneNumber, mail, workPlace, workExperience);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Nullable
    private static String getXMLArgument(Node nNode, String attribute, int mask) {
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            String result = eElement.getElementsByTagName(attribute).item(0).getTextContent();
            if ((mask & XMLCONST.XMLNAME) == XMLCONST.XMLNAME) {
                if (result.matches("^[a-zA-Z ]+$")) {
                    return result;
                } else {
                    return null;
                }
            }
            if ((mask & XMLCONST.XMLPHONENUMBER) == XMLCONST.XMLPHONENUMBER) {
                if (result.matches("^[+][0-9]+$")) {
                    return result;
                } else {
                    return null;
                }
            }
            if ((mask & XMLCONST.XMLMAIl) == XMLCONST.XMLMAIl) {
                if (result.matches(".*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(ru|aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$")) {
                    return result;
                } else {
                    return null;
                }
            }
            if ((mask & XMLCONST.XMLEXPERIENCE) == XMLCONST.XMLEXPERIENCE) {
                if (result.matches("^[0-9]+$")) {
                    return result;
                } else {
                    return "0";
                }
            }
            return result;
        }
        return null;
    }

    public static String XMLBegin(){
        return ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n") + ("<class>\n");
    }

    public static String XMLEnd(){
        return ("</class>");
    }
}
