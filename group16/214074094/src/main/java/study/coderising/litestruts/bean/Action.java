package study.coderising.litestruts.bean;


import java.util.List;

/**
 * @Author shane
 * @Time 2017/3/4 21:49
 * @Email shanbaohua@lxfintech.com
 * @Desc ...
 */
public class Action {

    private String name;

    private String classPath;

    private List<Result> results;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
