package XMLDAO;

import java.io.Serializable;

import static XMLDAO.XMLConstants.XMLPARSERCONST.*;


/**
 * Created by Alexey on 03.04.2017.
 * Class of user, that was saving in the XML file on Server
 */
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String telephoneNumber;
    private String mail;
    private String workPlace;
    private int workExperience;

    /**
     * Constructor of this class. Check inputs Strings for correct work and initialize fields of object
     * @param firstName First Name of User [a-Z]
     * @param lastName Last Name of User [a-Z]
     * @param fatherName Father Name of User [a-Z]
     * @param telephoneNumber Telephone Number of User '+'[0-9]
     * @param mail Mail adress of User [0-9a-Z]@[gmail,mail,...]'.'[com,ru,...]$
     * @param workPlace The name of the workPlace of User. Don't have checking
     * @param workExperience The work experience of User. Don't have checking
     */
    public User(String firstName, String lastName, String fatherName, String telephoneNumber, String mail, String workPlace, int workExperience){
        if (checkName(firstName)) {
            this.firstName = firstName;
        }
        else {
            this.firstName = null;
        }
        if (checkName(lastName)) {
            this.lastName = lastName;
        }
        else {
            this.lastName = null;
        }
        if (checkName(fatherName)) {
            this.fatherName = fatherName;
        }
        else {
            this.fatherName = null;
        }
        if (checkNumber(telephoneNumber)) {
            this.telephoneNumber = telephoneNumber;
        }
        else {
            this.telephoneNumber = null;
        }
        if (checkMail(mail)) {
            this.mail = mail;
        }
        else {
            this.mail = null;
        }
        this.workPlace = workPlace;
        this.workExperience = workExperience;
    }

    /**
     * getting First Name of this User
     * @return First Name of this User
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setting First Name of this User
     * @param firstName First Name of this User
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getting Last Name of this User
     * @return Last Name of this User
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setting Last Name of this User
     * @param lastName Last Name of this User
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getting Father Name of this User
     * @return Father Name of this User
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * setting Father Name of this User
     * @param fatherName Father Name of this User
     */
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    /**
     * getting Telephone Number of this User
     * @return Telephone Number of this User
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * setting Telephone Number of this User
     * @param telephoneNumber Telephone Number of this User
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * getting mail adress of this User
     * @return mail adress of this User
     */
    public String getMail() {
        return mail;
    }

    /**
     * setting mail adress of this User
     * @param mail mail adress of this User
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * getting Work Place of this User
     * @return Work Place of this User
     */
    public String getWorkPlace() {
        return workPlace;
    }

    /**
     * setting Work Place of this User
     * @param workPlace Work Place of this User
     */
    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    /**
     * getting Work experience of this User
     * @return Work experience of this User
     */
    public int getWorkExperience() {
        return workExperience;
    }

    /**
     * setting Work experience of this User
     * @param workExperience Work experience of this User
     */
    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }


    /**
     * check correct of the Name (for FirstName, LastName, FatherName)
     * @param name the String with name
     * @return true - this String is name
     *        false - this String isn't name
     */
    private boolean checkName(String name) {
        return (name.matches("^[a-zA-Z ]+$"));
    }

    /**
     * check correct of the Telephone Number (for Telephone Number)
     * @param number the String with Telephone Number
     * @return true - this String is Telephone Number
     *        false - this String isn't Telephone Number
     */
    private boolean checkNumber(String number) {
        return (number.matches("^[+][0-9]+$"));
    }

    /**
     * check correct of the mail adress (for Mail)
     * @param mail the String with mail adress
     * @return true - this String is mail adress
     *        false - this String isn't mail adress
     */
    private boolean checkMail(String mail) {
        return (mail.matches(".*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(ru|aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$"));
    }

    /**
     * method for transform object of User into XML format
     * @param mask int value for adding bounders:
     *             XMLBEGIN - up bound
     *             XMLEND   - down bound
     *             XMLNONE  - none of the above
     * @return the result of transform to XML
     */
    public String parseToXML(int mask) {
        String result = "";
        if ((mask & XMLBEGIN) == XMLBEGIN){
            result += XMLBegin();
        }
        result += ("  <user>\n");
        result += ("    <FIO>\n");
        result += ("      <firstname>" + this.firstName + "</firstname>\n");
        result += ("      <lastname>" + this.lastName + "</lastname>\n");
        result += ("      <fathername>" + this.fatherName + "</fathername>\n");
        result += ("    </FIO>\n");
        result += ("    <contact>\n");
        result += ("      <telephonenumber>" + this.telephoneNumber + "</telephonenumber>\n");
        result += ("      <mail>" + this.mail + "</mail>\n");
        result += ("    </contact>\n");
        result += ("    <work>\n");
        result += ("      <workplace>" + this.workPlace + "</workplace>\n");
        result += ("      <experience>" + this.workExperience + "</experience>\n");
        result += ("    </work>\n");
        result += ("  </user>\n");
        if ((mask & XMLEND) == XMLEND){
            result += XMLEnd();
        }
        return result;
    }

    /**
     * static method for transform the array of objects into XML format
     * @param users the array with User objects
     * @param mask int value for adding bounders:
     *             XMLBEGIN - up bound
     *             XMLEND   - down bound
     *             XMLNONE  - none of the above
     * @return the result of transforming into XML format
     */
    public static String parseToXML(User[] users, int mask) { //vtrusevich
        String result = "";
        if ((mask & XMLBEGIN) == XMLBEGIN) {
            result += XMLBegin();
        }
        if (users != null) {
            for (User user : users) {
                if (user != null) {
                    result += user.parseToXML(XMLNONE);
                }
            }
        }
        if ((mask & XMLEND) == XMLEND) {
            result += XMLEnd();
        }
        return result;
    }


    /**
     * method for transforming User into String
     * @return the result of this transforming
     */
    @Override
    public String toString() {
        String result = "";
        result += "first Name - " + this.firstName + "\n";
        result += "last Name - " + this.lastName + "\n";
        result += "father Name - " + this.fatherName + "\n";
        result += "telephone Number - " + this.telephoneNumber + "\n";
        result += "Mail - " + this.mail + "\n";
        result += "Workplace - " + this.workPlace + "\n";
        result += "Experience - " + this.workExperience + "\n";
        return result;
    }

    /**
     * method for equals with other User
     * @param user the other object of the User
     * @return true - if this User equals other User
     *        false - if this User not equals other User
     */
    public boolean equals(User user){
        return (this.getFirstName().equals(user.getFirstName()) &&
                this.getLastName().equals(user.getLastName()) &&
                this.getFatherName().equals(user.getFatherName()) &&
                this.getTelephoneNumber().equals(user.getTelephoneNumber()) &&
                this.getMail().equals(user.getMail()) &&
                this.getWorkPlace().equals(user.getWorkPlace()) &&
                this.getWorkExperience() == user.getWorkExperience());
    }
}
