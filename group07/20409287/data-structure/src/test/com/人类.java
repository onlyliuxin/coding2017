package test.com;

/**
 * Created by xudanxia on 2017/4/11.
 */
public class 人类 {

    private String 姓名;

    public 人类(String 姓名) {
        this.姓名 = 姓名;
    }

    public void 说话() {
        System.out.println(姓名 + "在说话,但是不知道说什么.");
    }

    public void 吃饭() {
        System.out.println(姓名 + "在吃饭!");
    }

    public static void main(String[] args) {
        人类 刘启维 = new 人类("刘启维");
        刘启维.吃饭();
        刘启维.说话();
    }
}
