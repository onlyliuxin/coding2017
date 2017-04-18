package dataStruct.com.coderising.litestruts;


import java.util.Map;

/**
 * Created by songbao.yang on 2017/3/1.
 *
 */
public class Action {
    private String name;
    private String className;
    private Map<String, String> results;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map getResults() {
        return results;
    }

    public void setResults(Map results) {
        this.results = results;
    }
}
