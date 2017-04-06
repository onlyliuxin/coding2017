package coding.coderising.download.api;

/**
 * @author jiaxun
 */
public interface ConnectionManager {

    Connection open(String url) throws ConnectionException;

}
