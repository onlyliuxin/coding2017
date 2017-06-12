package ood.srp.config;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private static final String SMTP_SERVER = "smtp.server";
    private static final String ALT_SMTP_SERVER = "alt.smtp.server";


    private static Map<String, String> configurations = null;

    public Configuration config() {

        configurations = new HashMap<>();
        configurations.put(SMTP_SERVER, "smtp.163.com");
        configurations.put(ALT_SMTP_SERVER, "smtp1.163.com");

        return this;
    }

    public ServerConfig getServerConfig() {

        ServerConfig sc = new ServerConfig();

        sc.setSmtpHost(getProperty(SMTP_SERVER));
        sc.setAltSmtpHost(getProperty(ALT_SMTP_SERVER));

        return sc;
    }

    private String getProperty(String key) {
        return configurations.get(key);
    }

}
