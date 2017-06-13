package srp.model;

/**
 * @version V1.0
 * @Title： Product
 * @Package： srp.model
 * @Description： 商品对象
 * @author： 南来
 * @date： 2017-06-12 9:46
 */
public class Product {

    /**
     * 商品主键
     */
    private String Id;
    /**
     * 商品描述
     */
    private String Desc;
    /**
     * 是否降价
     */
    private boolean down;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public boolean getDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id='" + Id + '\'' +
                ", Desc='" + Desc + '\'' +
                ", down='" + down + '\'' +
                '}';
    }
}
