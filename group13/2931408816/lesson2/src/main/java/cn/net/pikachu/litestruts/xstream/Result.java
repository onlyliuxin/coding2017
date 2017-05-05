package cn.net.pikachu.litestruts.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

/**
 * Created by pikachu on 17-3-2.
 */
@XStreamAlias("result")
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"result"})
public class Result {
    private String name;
    private String result;

    public Result(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
