package cn.net.pikachu.litestruts.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by pikachu on 17-3-2.
 */
@XStreamAlias("action")
public class Action {
    @XStreamAsAttribute
    private String name;
    @XStreamAlias("class")
    @XStreamAsAttribute
    private String clazz;
    @XStreamImplicit
    private List<Result> results;

    public Action(String name, String clazz, List<Result> results) {
        this.name = name;
        this.clazz = clazz;
        this.results = results;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
