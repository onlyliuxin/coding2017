package dp.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片
 * Created by lx on 2017/7/29.
 */
public class Picture implements Shape{

    private List<Shape> shapes = new ArrayList<>();

    @Override
    public void draw() {
       shapes.forEach((shape) -> shape.draw());
    }

    /**
     * 添加图形
     * @param shape
     */
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    /**
     * 获取图形
     * @param i
     */
    public void getShape(int i) {
        shapes.get(i);
    }

    /**
     * 删除图形
     * @param i
     */
    public void deleteShape(int i) {
        shapes.remove(i);
    }
}
