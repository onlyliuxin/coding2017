package week2.struts;

import org.jdom2.JDOMException;

import java.io.IOException;

/**
 * Created by Bugu on 3/17/2017.
 */
public class ConfigurationException extends RuntimeException {
    public ConfigurationException(String msg) {
        super(msg);
    }
    public ConfigurationException(JDOMException e) {
        super(e);
    }
    public ConfigurationException(IOException e) {
        super(e);
    }
}
