package me.lzb.jvm.cmd;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.ConstantInfo;
import me.lzb.jvm.constant.ConstantPool;
import me.lzb.jvm.engine.ExecutionResult;
import me.lzb.jvm.engine.StackFrame;
import me.lzb.jvm.print.ExecutionVisitor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LZB
 */
public abstract class ByteCodeCommand {

    String opCode;
    ClassFile clzFile;
    private int offset;


    public static final String aconst_null = "01";
    public static final String new_object = "BB";
    public static final String lstore = "37";
    public static final String invokespecial = "B7";
    public static final String invokevirtual = "B6";
    public static final String getfield = "B4";
    public static final String putfield = "B5";
    public static final String getstatic = "B2";
    public static final String ldc = "12";
    public static final String dup = "59";
    public static final String bipush = "10";
    public static final String aload_0 = "2A";
    public static final String aload_1 = "2B";
    public static final String aload_2 = "2C";
    public static final String iload = "15";
    public static final String iload_1 = "1B";
    public static final String iload_2 = "1C";
    public static final String iload_3 = "1D";
    public static final String fload_3 = "25";

    public static final String voidreturn = "B1";
    public static final String ireturn = "AC";
    public static final String freturn = "AE";

    public static final String astore_1 = "4C";
    public static final String if_icmpge = "A2";
    public static final String if_icmpgt = "A3";
    public static final String if_icmple = "A4";
    public static final String goto_no_condition = "A7";
    public static final String iconst_0 = "03";
    public static final String iconst_1 = "04";
    public static final String istore_1 = "3C";
    public static final String istore_2 = "3D";
    public static final String iadd = "60";
    public static final String iinc = "84";

    public static final String iload_0 = "1A";
    public static final String lload_0 = "1E";
    public static final String fload_2 = "24";


    private static Map<String, String> codeMap = new HashMap<>();

    static {
        codeMap.put(aconst_null, "aconst_null");

        codeMap.put(new_object, "new");
        codeMap.put(lstore, "lstore");
        codeMap.put(invokespecial, "invokespecial");
        codeMap.put(invokevirtual, "invokevirtual");
        codeMap.put(getfield, "getfield");
        codeMap.put(putfield, "putfield");
        codeMap.put(getstatic, "getstatic");

        codeMap.put(aload_0, "aload_0");
        codeMap.put(aload_1, "aload_1");
        codeMap.put(aload_2, "aload_2");

        codeMap.put(bipush, "bipush");
        codeMap.put(iload, "iload");
        codeMap.put(iload_0, "iload_0");
        codeMap.put(iload_1, "iload_1");
        codeMap.put(iload_2, "iload_2");
        codeMap.put(iload_3, "iload_3");

        codeMap.put(fload_3, "fload_3");

        codeMap.put(lload_0, "lload_0");

        codeMap.put(fload_2, "fload_2");
        codeMap.put(astore_1, "astore_1");

        codeMap.put(if_icmpge, "if_icmpge");
        codeMap.put(if_icmple, "if_icmple");
        codeMap.put(if_icmpgt, "if_icmpgt");
        codeMap.put("A7", "goto");

        codeMap.put("B1", "return");
        codeMap.put(ireturn, "ireturn");
        codeMap.put(freturn, "freturn");

        codeMap.put(iconst_0, "iconst_0");
        codeMap.put(iconst_1, "iconst_1");

        codeMap.put(istore_1, "istore_1");
        codeMap.put(istore_2, "istore_2");

        codeMap.put(dup, "dup");

        codeMap.put(iadd, "iadd");
        codeMap.put(iinc, "iinc");

        codeMap.put(ldc, "ldc");
    }


    protected ByteCodeCommand(ClassFile clzFile, String opCode) {
        this.clzFile = clzFile;
        this.opCode = opCode;
    }

    protected ClassFile getClassFile() {
        return clzFile;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public ConstantInfo getConstantInfo(int index) {
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

        StringBuffer buffer = new StringBuffer();
        buffer.append(this.opCode);
        buffer.append(":");
        buffer.append(this.codeMap.get(opCode));
        return buffer.toString();
    }


    public String getReadableCodeText() {
        String txt = codeMap.get(opCode);
        if (txt == null) {
            return opCode;
        }
        return txt;
    }

    public abstract void execute(StackFrame frame, ExecutionResult result);

    public abstract void printExecute(ExecutionVisitor visitor);
}
