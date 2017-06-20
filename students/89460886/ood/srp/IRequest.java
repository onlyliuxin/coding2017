package ood.srp;

import java.util.Map;

/**
 * @author jiaxun
 */
public interface IRequest {

    Map<String, String> getHeaders();

    Map<String, String> getParams();

    String getUrl();

}
