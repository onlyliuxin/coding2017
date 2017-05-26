package designPattern.decorator.beverages;

import designPattern.decorator.Beverage;

/**
 * @author : 温友朝
 * @date : 2017/5/5
 */
public class Decaf extends Beverage {

    public Decaf(){
        description = "Decaf";
    }

    @Override
    public double cost() {
        return 1.99;
    }

    @Override
    public int size() {
        return 0;
    }
}
