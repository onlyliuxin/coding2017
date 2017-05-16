package teePrivate.designPattern.decorator.beverages;

import teePrivate.designPattern.decorator.Beverage;

/**
 * @author : 温友朝
 * @date : 2017/5/5
 */
public class HouseBlend extends Beverage {

    public HouseBlend(){
        description = "House Blend";
    }

    public double cost() {
        return 0.89;
    }

    @Override
    public int size() {
        return 0;
    }
}
