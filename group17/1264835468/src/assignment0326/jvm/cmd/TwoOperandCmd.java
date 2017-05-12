package assignment0326.jvm.cmd;


import assignment0326.jvm.clz.ClassFile;
import assignment0326.jvm.constant.*;

public abstract class TwoOperandCmd extends ByteCodeCommand {

    int opreand1 = -1;
    int opreand2 = -1;

    public int getOpreand1() {
        return opreand1;
    }

    public void setOpreand1(int opreand1) {
        this.opreand1 = opreand1;
    }

    public void setOpreand2(int opreand2) {
        this.opreand2 = opreand2;
    }

    public int getOpreand2() {
        return opreand2;
    }

    public TwoOperandCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    public int getIndex() {
        int oprand1 = this.getOpreand1();
        int oprand2 = this.getOpreand2();
        int index = oprand1 << 8 | oprand2;
        return index;
    }

    protected String getOperandAsClassInfo(ConstantPool pool) {
        int index = getIndex();
        String codeTxt = getReadableCodeText();
        ClassInfo info = (ClassInfo) pool.getConstantInfo(index);
        return this.getOffset() + ":" + this.getOpCode() + " " + codeTxt + "  " + info.getClassName();
    }

    protected String getOperandAsMethod(ConstantPool pool) {
        int index = getIndex();
        String codeTxt = getReadableCodeText();
        ConstantInfo constInfo = this.getConstantInfo(index);
        MethodRefInfo info = (MethodRefInfo) this.getConstantInfo(index);
        return this.getOffset() + ":" + this.getOpCode() + " " + codeTxt + "  " + info.toString();
    }

    protected String getOperandAsField(ConstantPool pool) {
        int index = getIndex();

        String codeTxt = getReadableCodeText();
        FieldRefInfo info = (FieldRefInfo) this.getConstantInfo(index);
        return this.getOffset() + ":" + this.getOpCode() + " " + codeTxt + "  " + info.toString();
    }

    public int getLength() {
        return 3;
    }
}
