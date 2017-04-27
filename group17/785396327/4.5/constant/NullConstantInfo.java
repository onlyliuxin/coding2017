package constant;

/**
 * Created by IBM on 2017/4/10.
 */
public class NullConstantInfo extends ConstantInfo {
    public NullConstantInfo(){

    }
    @Override
    public int getType() {
        return -1;
    }

    @Override
    public void accept(Visitor visitor) {
        System.out.println("null Constant info");
    }

}
