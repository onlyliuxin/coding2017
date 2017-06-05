package assignment0326.jvm.cmd;

import assignment0326.jvm.clz.ClassFile;

import java.util.ArrayList;
import java.util.List;


public class CommandParser {

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
    public static final String if_icmp_ge = "A2";
    public static final String if_icmple = "A4";
    public static final String goto_no_condition = "A7";
    public static final String iconst_0 = "03";
    public static final String iconst_1 = "04";
    public static final String istore_1 = "3C";
    public static final String istore_2 = "3D";
    public static final String iadd = "60";
    public static final String iinc = "84";

    public static ByteCodeCommand[] parse(ClassFile clzFile, String codes) {
        List<ByteCodeCommand> cmds = new ArrayList<>();
        CommandIterator commandIterator = new CommandIterator(codes);
        while (commandIterator.hasNext()) {

            String opCode = commandIterator.next2CharAsString().toUpperCase();

            if (opCode.equals(bipush)) {
                BiPushCmd biPushCmd = new BiPushCmd(clzFile, opCode);
                biPushCmd.setOperand(commandIterator.next2CharAsInt());
                cmds.add(biPushCmd);
            } else if (opCode.equals(getfield)) {
                GetFieldCmd getFieldCmd = new GetFieldCmd(clzFile, opCode);
                getFieldCmd.setOpreand1(commandIterator.next2CharAsInt());
                getFieldCmd.setOpreand2(commandIterator.next2CharAsInt());
                cmds.add(getFieldCmd);
            } else if (opCode.equals(getstatic)) {
                GetStaticFieldCmd getStaticFieldCmd = new GetStaticFieldCmd(clzFile, opCode);
                getStaticFieldCmd.setOpreand1(commandIterator.next2CharAsInt());
                getStaticFieldCmd.setOpreand2(commandIterator.next2CharAsInt());
                cmds.add(getStaticFieldCmd);
            } else if (opCode.equals(invokespecial)) {
                InvokeSpecialCmd invokeSpecialCmd = new InvokeSpecialCmd(clzFile, opCode);
                invokeSpecialCmd.setOpreand1(commandIterator.next2CharAsInt());
                invokeSpecialCmd.setOpreand2(commandIterator.next2CharAsInt());
                cmds.add(invokeSpecialCmd);
            } else if (opCode.equals(invokevirtual)) {
                InvokeVirtualCmd invokeVirtualCmd = new InvokeVirtualCmd(clzFile, opCode);
                invokeVirtualCmd.setOpreand1(commandIterator.next2CharAsInt());
                invokeVirtualCmd.setOpreand2(commandIterator.next2CharAsInt());
                cmds.add(invokeVirtualCmd);
            } else if (opCode.equals(getfield)) {
                GetFieldCmd getFieldCmd = new GetFieldCmd(clzFile, opCode);
                getFieldCmd.setOpreand1(commandIterator.next2CharAsInt());
                getFieldCmd.setOpreand2(commandIterator.next2CharAsInt());
                cmds.add(getFieldCmd);
            } else if (opCode.equals(ldc)) {
                LdcCmd ldcCmd = new LdcCmd(clzFile, opCode);
                ldcCmd.setOperand(commandIterator.next2CharAsInt());
                cmds.add(ldcCmd);
            } else if (opCode.equals(new_object)) {
                NewObjectCmd newObjectCmd = new NewObjectCmd(clzFile, opCode);
                newObjectCmd.setOpreand1(commandIterator.next2CharAsInt());
                newObjectCmd.setOpreand2(commandIterator.next2CharAsInt());
                cmds.add(newObjectCmd);
            } else if (opCode.equals(putfield)) {
                PutFieldCmd putFieldCmd = new PutFieldCmd(clzFile, opCode);
                putFieldCmd.setOpreand1(commandIterator.next2CharAsInt());
                putFieldCmd.setOpreand2(commandIterator.next2CharAsInt());
                cmds.add(putFieldCmd);
            } else if (dup.equals(opCode) || aload_0.equals(opCode) || aload_1.equals(opCode) || aload_2.equals(opCode)
                    || iload_1.equals(opCode) || iload_2.equals(opCode) || iload_3.equals(opCode)
                    || fload_3.equals(opCode) || voidreturn.equals(opCode) || astore_1.equals(opCode)) {
                NoOperandCmd cmd = new NoOperandCmd(clzFile, opCode);
                cmds.add(cmd);
            } else {
                throw new RuntimeException("command:" + opCode + " has not been implemented yet.");
            }
        }
        calcuateOffset(cmds);
        return cmds.toArray(new ByteCodeCommand[cmds.size()]);
    }

    private static void calcuateOffset(List<ByteCodeCommand> cmds) {

        int offset = 0;
        for (ByteCodeCommand cmd : cmds) {
            cmd.setOffset(offset);
            offset += cmd.getLength();
        }

    }

    private static class CommandIterator {
        String codes = null;
        int pos = 0;

        CommandIterator(String codes) {
            this.codes = codes;
        }

        public boolean hasNext() {
            return pos < this.codes.length();
        }

        public String next2CharAsString() {
            String result = codes.substring(pos, pos + 2);
            pos += 2;
            return result;
        }

        public int next2CharAsInt() {
            String s = this.next2CharAsString();
            return Integer.valueOf(s, 16).intValue();
        }

    }
}
