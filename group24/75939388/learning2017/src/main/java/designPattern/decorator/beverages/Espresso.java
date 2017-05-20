package designPattern.decorator.beverages;

import designPattern.decorator.Beverage;

/**
 * @author : 温友朝
 * @date : 2017/5/5
 */
public class Espresso extends Beverage {

    public Espresso(){
        description = "Espresso";
    }

    public double cost() {
        return 1.99d;
    }


    @Override
    public int size() {
        return 0;
    }
}
