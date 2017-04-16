package XMLDAO;

import XMLDAO.Compress.Compressor;
import XMLDAO.Parsers.Parserable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static XMLDAO.XMLConstants.XMLPARSERCONST.XMLBEGIN;
import static XMLDAO.XMLConstants.XMLPARSERCONST.XMLEND;
import static XMLDAO.XMLConstants.XMLPARSERCONST.XMLNONE;


/**
 * Created by Alexey on 15.04.2017.
 * The class for doing logic operations with XML file and User
 */
public class XMLEditor {

    private Parserable parser;

    private String path;
    private String archivePath;

    private static final String prefix = "temp";

    /**
     * Creating XML Editor for XML file with this path, uses this parser and save result into this archive
     * @param path the path of the XML file for store and editing File
     * @param parser the Object of parser for reading XML file
     * @param archivePath the path of archive for zipping XML file
     */
    public XMLEditor(String path, Parserable parser, String archivePath) {
        this.path = path;
        this.parser = parser;
        this.archivePath = archivePath;
    }

    /**
     * adding this User into this position in XML file
     * @param user the User, that was insert into XML file
     * @param index the index of position to insert this User
     */
    public void add(User user, int index) {
        User users[] = parser.parseFromXML(path);
        if (users != null)
        {
            if (index < 0 || index > users.length){
                return;
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(path, false);
            fileWriter.write(User.parseToXML(null, XMLBEGIN));
            if (users != null && index >= 0 && index <= users.length) {
                for (int i = 0; i < index; i++) {
                    fileWriter.append(users[i].parseToXML(XMLNONE));
                }
            }
            fileWriter.append(user.parseToXML(XMLNONE));
            if (users != null && index >= 0 && index < users.length) {
                for (int i = index; i < users.length; i++) {
                    fileWriter.append(users[i].parseToXML(XMLNONE));
                }
            }
            fileWriter.append(User.parseToXML(null, XMLEND));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * adding this User in begin of the XML file
     * @param user the User, that was been adding in the Begin of the XML file
     */
    public void addInBegin(User user) {
        this.add(user, 0);
    }

    /**
     * adding this User in the end of the XML file
     * @param user the User, that was been adding in the end of the XML file
     */
    public void addInEnd(User user){
        if (parser.parseFromXML(path) != null){
            this.add(user, parser.parseFromXML(path).length);
        }
        else
        {
            this.add(user,0);
        }
    }

    /**
     * deleting User in XML file on the this position
     * @param index the index of User for deleting
     * @return true - if deleting ending successfully
     *        false - if deleting don't ending successfully
     */
    public boolean delete(int index) {
        try {
            User users[] = parser.parseFromXML(path);
            if (users != null && index >= 0 && index < users.length) {
                FileWriter fileWriter = new FileWriter(prefix + path, false);
                fileWriter.write(User.parseToXML(null, XMLBEGIN));
                if (index < 0 || index >= users.length) {
                    return false;
                }
                for (int i = 0; i < index; i++) {
                    fileWriter.append(users[i].parseToXML(XMLNONE));
                }
                for (int i = index + 1; i < users.length; i++) {
                    fileWriter.append(users[i].parseToXML(XMLNONE));
                }
                fileWriter.append(User.parseToXML(null, XMLEND));
                fileWriter.flush();
                fileWriter.close();

                File file = new File(prefix + path);
                File oldFile = new File(path);
                if (oldFile.delete()) {
                    if (!file.renameTo(oldFile)){
                        System.out.println("Can't rename");
                    }
                }
                else {
                    System.out.println("Can't delete");
                }
                return true;
            }
            else {
                if (users == null){
                    File file = new File(path);
                    file.delete();
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * change User on this index in file with this User
     * @param user the new User for insert in File
     * @param index the index in file of Old User
     */
    public void edit(User user, int index){
        if ( this.delete(index)) {
            this.add(user, index);
        }
    }

    /**
     * getting User from file on this index
     * @param index the index of User, that was getting
     * @return the User on this position in File
     */
    public User get(int index){
        return parser.parseFromXML(path, index);
    }

    /**
     * check for validation this XML file with this XML schema
     * @param schemaPath the path of the XML schema for checking
     * @return true - if this XML file validate with this XML schema
     *        false - if this XML file not validate with this XML schema
     */
    public boolean validate(String schemaPath){
        try {
            return XMLSchemaValidator.checkXMLforXSD(path,schemaPath);
        }
        catch (FileNotFoundException e){
            return false;
        }
    }

    /**
     * the count of Users into this XML File
     * @return count of Users into XML File
     */
    public int length(){
        User[] users = parser.parseFromXML(path);
        if (users != null){
            return users.length;
        }
        else {
            return 0;
        }
    }

    /**
     * transform into String all Users from XMl file
     * @return the result of transform
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        User[] users = parser.parseFromXML(path);
        if (users != null) {
            for (User user : parser.parseFromXML(path)) {
                if (user != null) {
                    s.append(user.toString());
                }
            }
        }
        return s.toString();
    }

    /**
     * zipping XML file with this capacity of zipping
     * @param capacity capacity of zipping in Bounds: [0,9]. Other -> default capacity
     */
    public void compress(int capacity){
        Compressor compressor = new Compressor();
        if (archivePath != null) {
            compressor.compress(path, archivePath, capacity);
        }
    }

    /**
     * unzipping zip archive with XML file. IFf don't zipped file with this name is Exist, that this file was REMOVED!
     */
    public void uncompress(){
        Compressor compressor = new Compressor();
        if (archivePath != null) {
            compressor.decompress(archivePath);
        }
    }

    /**
     * getting the current path of the XML file
     * @return the current path of the XML file
     */
    public String getPath() {
        return path;
    }

    /**
     * setting the current path of the XML file
     * @param path the current path of the XML file
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * setting the new parser of the XML file
     * @param  parser new parser of the XML file
     */
    public void setParser(Parserable parser) {
        this.parser = parser;
    }

    /**
     * getting path of zip archive of this XML file
     * @return zip path of the XML file
     */
    public String getArchivePath() {
        return archivePath;
    }

    /**
     * setting path of zip archive of this XML file
     * @param archivePath zip path of the XML file
     */
    public void setArchivePath(String archivePath) {
        this.archivePath = archivePath;
    }
}
