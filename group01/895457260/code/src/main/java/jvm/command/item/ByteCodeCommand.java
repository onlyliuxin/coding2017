package jvm.command.item;

import jvm.classfile.ClassFile;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.Constant;
import jvm.command.CommandIterator;

import java.util.HashMap;
import java.util.Map;

public abstract class ByteCodeCommand {

    String opCode;
    ClassFile clzFile;
    private int offset;

    public static Map<String, String> codeMap = new HashMap<>();

    static {
        codeMap.put("01", "AConst_Null");

        codeMap.put("BB", "New");
        codeMap.put("37", "LStore");
        codeMap.put("B7", "InvokeSpecial");
        codeMap.put("B6", "InvokeVirtual");
        codeMap.put("B4", "GetField");
        codeMap.put("B5", "PutField");
        codeMap.put("B2", "GetStatic");

        codeMap.put("2A", "ALoad_0");
        codeMap.put("2B", "ALoad_1");
        codeMap.put("2C", "ALoad_2");

        codeMap.put("10", "BiPush");
        codeMap.put("15", "ILoad");
        codeMap.put("1A", "ILoad_0");
        codeMap.put("1B", "ILoad_1");
        codeMap.put("1C", "ILoad_2");
        codeMap.put("1D", "ILoad_3");

        codeMap.put("25", "FLoad_3");

        codeMap.put("1E", "LLoad_0");

        codeMap.put("24", "FLoad_2");
        codeMap.put("4C", "AStore_1");

        codeMap.put("A2", "If_Icmp_Ge");
        codeMap.put("A4", "If_Icmple");

        codeMap.put("A7", "GoTo");

        codeMap.put("B1", "Return");
        codeMap.put("AC", "IReturn");
        codeMap.put("AE", "FReturn");

        codeMap.put("03", "IConst_0");
        codeMap.put("04", "IConst_1");

        codeMap.put("3C", "IStore_1");
        codeMap.put("3D", "IStore_2");

        codeMap.put("59", "Dup");

        codeMap.put("60", "IAdd");
        codeMap.put("84", "IInc");

        codeMap.put("12", "Ldc");
    }

    ByteCodeCommand(ClassFile clzFile, String opCode, CommandIterator iterator) {
        this.clzFile = clzFile;
        this.opCode = opCode;
        initOperands(iterator);
    }

    protected abstract void initOperands(CommandIterator iterator);

    protected ClassFile getClassFile() {
        return clzFile;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    protected Constant getConstantInfo(int index) {
        return this.getClassFile().getConstantPool().getConstantInfo(index);
    }

    protected ConstantPool getConstantPool() {
        return this.getClassFile().getConstantPool();
    }

    public String getOpCode() {
        return opCode;
    }

    public abstract int getLength();

    public String toString() {
        return opCode;
    }

    public abstract String toString(ConstantPool pool);

    public String getReadableCodeText() {
        String txt = codeMap.get(opCode);
        return txt == null ? opCode : txt.toLowerCase();
    }

    //public abstract void execute(StackFrame frame,FrameResult result);
}
