package yangsongbao.model;

/**
 * Created by songbao.yang on 2017/6/17.
 */
public class ProductInfo {
    private String id;
    private String desc;

    public ProductInfo() {
    }

    public ProductInfo(String id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
