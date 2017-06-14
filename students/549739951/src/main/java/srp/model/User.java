package srp.model;

/**
 * @version V1.0
 * @Title： User
 * @Package： srp.model
 * @Description： 用户对象
 * @author： 南来
 * @date： 2017-06-12 10:07
 */
public class User {

    /**
     * 用户名
     */
    private String name;
    /**
     * e-mail
     */
    private String email;
    /**
     * 关注的商品Id
     */
    private String watchProductId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWatchProductId() {
        return watchProductId;
    }

    public void setWatchProductId(String watchProductId) {
        this.watchProductId = watchProductId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", watchProductId='" + watchProductId + '\'' +
                '}';
    }
}
