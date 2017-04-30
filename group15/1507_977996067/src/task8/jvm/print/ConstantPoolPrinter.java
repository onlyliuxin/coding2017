package task8.jvm.print;

import task8.jvm.constant.ConstantInfo;
import task8.jvm.constant.ConstantPool;

public class ConstantPoolPrinter {

    private ConstantPool pool;

    ConstantPoolPrinter(ConstantPool pool) {
        this.pool = pool;
    }

    public void print() {

        System.out.println("Constant Pool:");

        ConstantInfoVisitor visitor = new ConstantInfoVisitorImpl();
        int size = (int) pool.getSize();
        for (int i = 0; i < size; i++) {
            System.out.print("#" + i + "= ");
            ConstantInfo constantInfo = pool.getConstantInfo(i);
            constantInfo.accept(visitor);
        }
    }
}
