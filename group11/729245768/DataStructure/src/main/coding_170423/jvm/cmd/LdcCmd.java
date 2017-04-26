package main.coding_170423.jvm.cmd;

import main.coding_170423.jvm.clz.ClassFile;
import main.coding_170423.jvm.constant.ConstantInfo;
import main.coding_170423.jvm.constant.StringInfo;

/**
 * Created by peterchen on 2017/4/26.
 */
public class LdcCmd extends OneOperandCmd {
    public LdcCmd(ClassFile clzFile, String opCode) {
        super(clzFile,opCode);
    }

    @Override
    public String toString() {

        ConstantInfo info = getConstantInfo(this.getOperand());

        String value = "TBD";
        if(info instanceof StringInfo){
            StringInfo strInfo = (StringInfo)info;
            value = strInfo.toString();
        }

        return this.getOffset()+":"+this.getOpCode()+" " + this.getReadableCodeText() + " "+  value;

    }

}
