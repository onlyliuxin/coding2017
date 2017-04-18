package cmd;

import clz.ClassFile;
import constant.ClassInfo;

import java.util.List;

/**
 * Created by william on 2017/4/17.
 */
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
        CommandIterator commandIterator = new CommandIterator(codes);
        String command = commandIterator.next2CharAsString().toUpperCase();
        if (command.equals(aconst_null)) {
            NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
            noOperandCmd.setOffset(1);
        } else if (command.equals(new_object)) {
            NewObjectCmd newObjectCmd = new NewObjectCmd(clzFile, command);
            int oprand1 = commandIterator.next2CharAsInt();
            int oprand2 = commandIterator.next2CharAsInt();
            newObjectCmd.setOprand1(oprand1);
            newObjectCmd.setOprand2(oprand2);

        } else if (command.equals(lstore)) {

        } else if (command.equals(invokespecial)) {

        } else if (command.equals(invokevirtual)) {

        } else if (command.equals(getfield)) {
            GetFieldCmd getFieldCmd = new GetFieldCmd(clzFile, command);
        } else if (command.equals(putfield)) {
            PutFieldCmd putFieldCmd = new PutFieldCmd(clzFile, command);
        } else if (command.equals(getstatic)) {
            GetStaticFieldCmd getStaticFieldCmd = new GetStaticFieldCmd(clzFile, command);
        } else if (command.equals(ldc)) {
            LdcCmd ldcCmd = new LdcCmd(clzFile, command);
        } else if (command.equals(dup)) {

        } else if (command.equals(bipush)) {
            BiPushCmd biPushCmd = new BiPushCmd(clzFile, command);
        } else if (command.equals(aload_0)) {
            NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
        } else if (command.equals(aload_1)) {
            NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
        } else if (command.equals(aload_2)) {
            NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
        } else if (command.equals(iload)) {

        } else if (command.equals(iload_2)) {

        } else if (command.equals(iload_1)) {

        } else if (command.equals(iload_3)) {

        } else if (command.equals(fload_3)) {

        } else if (command.equals(voidreturn)) {

        } else if (command.equals(ireturn)) {

        } else if (command.equals(freturn)) {

        } else if (command.equals(astore_1)) {

        } else if (command.equals(if_icmp_ge)) {

        } else if (command.equals(if_icmple)) {

        } else if (command.equals(goto_no_condition)) {

        } else if (command.equals(iconst_0)) {

        } else if (command.equals(iconst_1)) {

        } else if (command.equals(istore_1)) {

        } else if (command.equals(istore_2)) {

        } else if (command.equals(iadd)) {

        } else if (command.equals(iinc)) {

        } else {
            throw new RuntimeException("wrong command : " + command);
        }
        return null;
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

        /**
         * 从字符串中截取前两个字符
         *
         * @return
         */
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
