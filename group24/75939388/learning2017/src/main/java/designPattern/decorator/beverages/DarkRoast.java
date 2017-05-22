package designPattern.decorator.beverages;

import designPattern.decorator.Beverage;

/**
 * @author : 温友朝
 * @date : 2017/5/5
 */
public class DarkRoast extends Beverage {

    public DarkRoast(){
        description = "Dark Roast";
    }

    @Override
    public double cost() {
        return 0.99;
    }

    @Override
    public int size() {
        return 0;
    }
}
