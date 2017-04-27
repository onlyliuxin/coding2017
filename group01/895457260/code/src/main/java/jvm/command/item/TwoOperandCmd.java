package jvm.command.item;

import jvm.classfile.ClassFile;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.ClassInfo;
import jvm.classfile.constant.item.impl.FieldRefInfo;
import jvm.classfile.constant.item.impl.MethodRefInfo;
import jvm.command.CommandIterator;

public abstract class TwoOperandCmd extends ByteCodeCommand {

    protected int operand1;
    protected int operand2;

    public int getOperand1() {
        return operand1;
    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public int getOperand2() {
        return operand2;
    }

    public TwoOperandCmd(ClassFile clzFile, String opCode, CommandIterator iterator) {
        super(clzFile, opCode, iterator);
    }

    @Override
    protected void initOperands(CommandIterator iterator) {
        setOperand1(iterator.next2CharAsInt());
        setOperand2(iterator.next2CharAsInt());
    }

    public int getIndex() {
        int operand1 = this.getOperand1();
        int operand2 = this.getOperand2();
        return operand1 << 8 | operand2;
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
        Constant constInfo = this.getConstantInfo(index);
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
