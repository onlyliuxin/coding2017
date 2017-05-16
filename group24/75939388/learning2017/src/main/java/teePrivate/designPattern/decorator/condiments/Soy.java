package teePrivate.designPattern.decorator.condiments;

import teePrivate.designPattern.decorator.Beverage;
import teePrivate.designPattern.decorator.CondimentDecorator;

/**
 * @author : 温友朝
 * @date : 2017/5/5
 */
public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage){
        this.beverage = beverage;

        this.description += this.beverage.description + ", Soy";
    }

    public String getDescription() {
        return this.description;
    }

    public double cost() {
        return 0.15 + beverage.cost();
    }

    @Override
    public int size() {
        return 0;
    }
}
