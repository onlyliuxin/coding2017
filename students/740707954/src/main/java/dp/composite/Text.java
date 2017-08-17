package dp.composite;

/**
 * 文本
 * Created by lx on 2017/7/29.
 */
public class Text implements Shape{
    @Override
    public void draw() {
        System.out.println("画文本");
    }
}
