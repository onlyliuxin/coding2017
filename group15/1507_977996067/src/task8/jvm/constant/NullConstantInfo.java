package task8.jvm.constant;

import task8.jvm.print.ConstantInfoVisitor;

public class NullConstantInfo extends ConstantInfo {

    public NullConstantInfo() {

    }

    @Override
    public int getType() {
        return -1;
    }

    @Override
    public void accept(ConstantInfoVisitor visitor) {
        // non impl
    }

}
