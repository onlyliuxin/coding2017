package designPattern.decorator.condiments;

import designPattern.decorator.Beverage;
import designPattern.decorator.CondimentDecorator;

/**
 * @author : 温友朝
 * @date : 2017/5/5
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage = beverage;

        this.description += this.beverage.description + ", Mocha";
    }

    public String getDescription() {
        return this.description;
    }

    public double cost() {
        return 0.2 + beverage.cost();
    }

    @Override
    public int size() {
        return 0;
    }
}
