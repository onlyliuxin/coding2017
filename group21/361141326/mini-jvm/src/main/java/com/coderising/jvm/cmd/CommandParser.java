package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;

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
        List<ByteCodeCommand> codeCommands = new ArrayList<>();

        CommandIterator iterator = new CommandIterator(codes);
        while (iterator.hasNext()) {
            String operationCode = iterator.next2CharAsString().toUpperCase();
            switch (operationCode) {
                case aconst_null:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case new_object:
                    NewObjectCmd newObjectCmd = new NewObjectCmd(clzFile, operationCode);
                    newObjectCmd.setOprand1(iterator.next2CharAsInt());
                    newObjectCmd.setOprand2(iterator.next2CharAsInt());

                    codeCommands.add(newObjectCmd);
                    break;
                case lstore:
                    break;
                case invokespecial:
                    InvokeSpecialCmd invokeSpecialCmd = new InvokeSpecialCmd(clzFile, operationCode);
                    invokeSpecialCmd.setOprand1(iterator.next2CharAsInt());
                    invokeSpecialCmd.setOprand2(iterator.next2CharAsInt());

                    codeCommands.add(invokeSpecialCmd);
                    break;
                case invokevirtual:
                    InvokeVirtualCmd invokeVirtualCmd = new InvokeVirtualCmd(clzFile, operationCode);
                    invokeVirtualCmd.setOprand1(iterator.next2CharAsInt());
                    invokeVirtualCmd.setOprand2(iterator.next2CharAsInt());

                    codeCommands.add(invokeVirtualCmd);
                    break;
                case getfield:
                    GetFieldCmd getFieldCmd = new GetFieldCmd(clzFile, operationCode);
                    getFieldCmd.setOprand1(iterator.next2CharAsInt());
                    getFieldCmd.setOprand2(iterator.next2CharAsInt());

                    codeCommands.add(getFieldCmd);
                    break;
                case putfield:
                    PutFieldCmd putFieldCmd = new PutFieldCmd(clzFile, operationCode);
                    putFieldCmd.setOprand1(iterator.next2CharAsInt());
                    putFieldCmd.setOprand2(iterator.next2CharAsInt());

                    codeCommands.add(putFieldCmd);
                    break;
                case getstatic:
                    GetStaticFieldCmd getStaticFieldCmd = new GetStaticFieldCmd(clzFile, operationCode);
                    getStaticFieldCmd.setOprand1(iterator.next2CharAsInt());
                    getStaticFieldCmd.setOprand2(iterator.next2CharAsInt());

                    codeCommands.add(getStaticFieldCmd);
                    break;
                case ldc:
                    LdcCmd ldcCmd = new LdcCmd(clzFile, operationCode);
                    ldcCmd.setOperand(iterator.next2CharAsInt());

                    codeCommands.add(ldcCmd);
                    break;
                case dup:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case bipush:
                    BiPushCmd biPushCmd = new BiPushCmd(clzFile, operationCode);
                    biPushCmd.setOperand(iterator.next2CharAsInt());
                    codeCommands.add(biPushCmd);
                    break;
                case aload_0:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case aload_1:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case aload_2:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case iload:
                    break;
                case iload_1:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case iload_2:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case iload_3:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case fload_3:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case voidreturn:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case ireturn:
                    break;
                case freturn:
                    break;
                case astore_1:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case if_icmp_ge:
                    break;
                case if_icmple:
                    break;
                case goto_no_condition:
                    break;
                case iconst_0:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case iconst_1:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case istore_1:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case istore_2:
                    codeCommands.add(new NoOperandCmd(clzFile, operationCode));
                    break;
                case iadd:
                    break;
                case iinc:
                    break;
                default:
                    break;
            }

        }

        calcuateOffset(codeCommands);
        return codeCommands.toArray(new ByteCodeCommand[]{});
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
