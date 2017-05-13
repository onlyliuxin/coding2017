package designPattern.decorator.condiments;

import designPattern.decorator.Beverage;
import designPattern.decorator.CondimentDecorator;

/**
 * @author : 温友朝
 * @date : 2017/5/5
 */
public class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage){
        this.beverage = beverage;

        this.description += beverage.description + ", ";
    }

    public String getDescription() {
        return this.description;
    }

    public double cost() {
        return 0.1 + beverage.cost();
    }

    @Override
    public int size() {
        return 0;
    }
}
