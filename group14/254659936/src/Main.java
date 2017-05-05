import com.coderising.litestruts.Struts;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("name", "zg");
        parameters.put("password", "123456");
        Struts.runAction("login", parameters);
    }
}
