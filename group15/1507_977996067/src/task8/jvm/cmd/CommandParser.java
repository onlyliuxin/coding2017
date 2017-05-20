package task8.jvm.cmd;

import task8.jvm.clz.ClassFile;

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
        codes = codes.toUpperCase();
        System.out.println("=========> codes: " + codes);
        CommandIterator iterator = new CommandIterator(codes);
        List<ByteCodeCommand> commands = new ArrayList<>();
        while (iterator.hasNext()) {
            String opCode = iterator.next2CharAsString();
            switch (opCode) {
                case new_object:
                    NewObjectCmd newObjectCmd = new NewObjectCmd(clzFile, codes);
                    newObjectCmd.setOprand1(iterator.next2CharAsInt());
                    newObjectCmd.setOprand2(iterator.next2CharAsInt());
                    commands.add(newObjectCmd);
                    break;
                case ldc:
                    LdcCmd ldcCmd = new LdcCmd(clzFile, codes);
                    ldcCmd.setOperand(iterator.next2CharAsInt());
                    commands.add(ldcCmd);
                    break;
                case bipush:
                    BiPushCmd biPushCmd = new BiPushCmd(clzFile, codes);
                    biPushCmd.setOperand(iterator.next2CharAsInt());
                    commands.add(biPushCmd);
                    break;
                case invokespecial:
                    InvokeSpecialCmd invokeSpecialCmd = new InvokeSpecialCmd(clzFile, codes);
                    invokeSpecialCmd.setOprand1(iterator.next2CharAsInt());
                    invokeSpecialCmd.setOprand2(iterator.next2CharAsInt());
                    commands.add(invokeSpecialCmd);
                    break;
                case invokevirtual:
                    InvokeVirtualCmd invokeVirtualCmd = new InvokeVirtualCmd(clzFile, codes);
                    invokeVirtualCmd.setOprand1(iterator.next2CharAsInt());
                    invokeVirtualCmd.setOprand2(iterator.next2CharAsInt());
                    commands.add(invokeVirtualCmd);
                    break;
                default:
                    NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, codes);
                    commands.add(noOperandCmd);
            }
        }
        calcuateOffset(commands);
        return commands.toArray(new ByteCodeCommand[commands.size()]);
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
            return Integer.valueOf(s, 16);
        }

    }
}
