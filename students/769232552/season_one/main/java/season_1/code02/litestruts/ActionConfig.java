package code02.litestruts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaoyuan on 2017/3/22.
 */
public class ActionConfig {
    String name;
    String clzName;
    Map<String,String> viewResult = new HashMap<String,String>();


    public ActionConfig(String actionName, String clzName) {
        this.name = actionName;
        this.clzName = clzName;
    }
    public String getClassName(){
        return clzName;
    }
    public void addViewResult(String name, String viewName){
        viewResult.put(name, viewName);
    }
    public String getViewName(String resultName){
        return viewResult.get(resultName);
    }
}
