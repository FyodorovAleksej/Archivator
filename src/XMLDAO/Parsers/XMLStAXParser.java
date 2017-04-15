package XMLDAO.Parsers;

import XMLDAO.User;

/**
 * Created by Alexey on 15.04.2017.
 */
public class XMLStAXParser implements Parserable {
    @Override
    public User[] parseFromXML(String path) {
        return new User[0];
    }

    @Override
    public User parseFromXML(String path, int index) {
        return null;
    }
}
