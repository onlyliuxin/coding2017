package teePrivate.designPattern;

import teePrivate.designPattern.decorator.Beverage;
import teePrivate.designPattern.decorator.beverages.DarkRoast;
import teePrivate.designPattern.decorator.beverages.Espresso;
import teePrivate.designPattern.decorator.beverages.HouseBlend;
import teePrivate.designPattern.decorator.condiments.Mocha;
import teePrivate.designPattern.decorator.condiments.Soy;
import teePrivate.designPattern.decorator.condiments.Whip;
import org.junit.Test;

/**
 * @author : 温友朝
 * @date : 2017/5/5
 */
public class StarBuzzCoffeeTest {

    @Test
    public void test1(){
        Beverage espresso = new Espresso();
        System.out.println(espresso.getDescription() + " cost $" + espresso.cost());

        Beverage darkRoast = new DarkRoast();
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Whip(darkRoast);
        System.out.println(darkRoast.getDescription() + " cost $" + darkRoast.cost());

        Beverage houseBlend = new HouseBlend();
        houseBlend = new Soy(houseBlend);
        houseBlend = new Mocha(houseBlend);
        houseBlend = new Whip(houseBlend);
        System.out.println(houseBlend.getDescription() + " cost $" + houseBlend.cost());
    }
}
