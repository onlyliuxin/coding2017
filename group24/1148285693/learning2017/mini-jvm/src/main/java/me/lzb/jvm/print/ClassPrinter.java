package me.lzb.jvm.print;

import me.lzb.common.utils.AppUtils;
import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.ConstantInfo;
import me.lzb.jvm.constant.ConstantPool;

/**
 * @author LZB
 */
public class ClassPrinter {

    private ClassFile classFile;

    private ConstantPool pool;

    private int poolLong;

    public ClassPrinter(ClassFile classFile) {
        this.classFile = classFile;
        this.pool = classFile.getConstantPool();

        this.poolLong = AppUtils.getDigit(this.pool.getSize());

    }


    public void print() {
        PrintVisitor visitor = new PrintFormat();

        classFile.print(visitor);

        System.out.println("Constant Pool:");


        for (int i = 1; i <= pool.getSize(); i++) {
            ConstantInfo constantInfo = pool.getConstantInfo(i);
            System.out.print(dq(i) + " = ");
            constantInfo.print(visitor);
        }
    }

    private String dq(int i) {
        int iLong = AppUtils.getDigit(i);

        int c = poolLong - iLong;

        StringBuffer sb = new StringBuffer();
        if (c > 0) {
            for (int j = 0; j < c; j++) {
                sb.append(" ");
            }
        }
        sb.append("#");
        sb.append(i);
        return sb.toString();
    }

}
