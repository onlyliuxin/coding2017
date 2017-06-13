import reflex.Struts;
import reflex.View;

import java.util.HashMap;
import java.util.Map;

public class MyStrutsTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("passWord","123");
        map.put("name","admin");
        View view= Struts.runAction("login",map);
        System.out.println(view.getJsp());
    }
}
