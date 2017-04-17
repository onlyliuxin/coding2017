package cmd;

import clz.ClassFile;
import constant.ConstantPool;

/**
 * Created by IBM on 2017/4/17.
 */
public class NoOperandCmd extends ByteCodeCommand {
    public NoOperandCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public String toString(ConstantPool pool) {
        return this.getOffset()+":" +this.getOpCode() + " "+ this.getReadableCodeText();
    }



    public  int getLength(){
        return 1;
    }
}
