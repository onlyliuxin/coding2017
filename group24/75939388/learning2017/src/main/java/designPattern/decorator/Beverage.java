package designPattern.decorator;

/**
 * @author : 温友朝
 * @date : 2017/5/5
 */
public abstract class Beverage {
    public static final int TALL = 0;
    public static final int GRANDE = 1;
    public static final int VENTI = 2;


    public String description = "";
    public static int size = TALL;

    public String getDescription(){
        return description;
    }

    public abstract double cost();

    public abstract int size();
}
