package cmd;

import clz.ClassFile;
import constant.ConstantPool;

/**
 * Created by IBM on 2017/4/17.
 */
public class NewObjectCmd extends TwoOperandCmd {
    public NewObjectCmd(ClassFile clzFile, String opCode){
        super(clzFile,opCode);
    }

    @Override
    public String toString(ConstantPool pool) {

        return super.getOperandAsClassInfo(pool);
    }

}
