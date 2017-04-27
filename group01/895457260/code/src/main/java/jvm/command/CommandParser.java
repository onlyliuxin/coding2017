package jvm.command;

import jvm.classfile.ClassFile;
import jvm.command.item.ByteCodeCommand;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    public static final String ACONST_NULL = "01";
    public static final String NEW_OBJECT = "BB";
    public static final String LSTORE = "37";
    public static final String INVOKESPECIAL = "B7";
    public static final String INVOKEVIRTUAL = "B6";
    public static final String GETFIELD = "B4";
    public static final String PUTFIELD = "B5";
    public static final String GETSTATIC = "B2";
    public static final String LDC = "12";
    public static final String DUP = "59";
    public static final String BIPUSH = "10";
    public static final String ALOAD_0 = "2A";
    public static final String ALOAD_1 = "2B";
    public static final String ALOAD_2 = "2C";
    public static final String ILOAD = "15";
    public static final String ILOAD_1 = "1B";
    public static final String ILOAD_2 = "1C";
    public static final String ILOAD_3 = "1D";
    public static final String FLOAD_3 = "25";

    public static final String VOIDRETURN = "B1";
    public static final String IRETURN = "AC";
    public static final String FRETURN = "AE";

    public static final String ASTORE_1 = "4C";
    public static final String IF_ICMP_GE = "A2";
    public static final String IF_ICMPLE = "A4";
    public static final String GOTO_NO_CONDITION = "A7";
    public static final String ICONST_0 = "03";
    public static final String ICONST_1 = "04";
    public static final String ISTORE_1 = "3C";
    public static final String ISTORE_2 = "3D";
    public static final String IADD = "60";
    public static final String IINC = "84";

    public static ByteCodeCommand[] parse(ClassFile clzFile, String codes) {
        String packageName = CommandParser.class.getPackage().getName() + ".item.impl.";
        CommandIterator iterator = new CommandIterator(codes);
        List<ByteCodeCommand> commands = new ArrayList<>();
        try {
            while (iterator.hasNext()) {
                String opCode = iterator.next2CharAsString().toUpperCase();
                String cmdClassName = packageName
                        + ByteCodeCommand.codeMap.get(opCode).replaceAll("_", "")
                        + "Cmd";
                Class<?> clazz = Class.forName(cmdClassName);
                Constructor<?> constructor = clazz.getConstructor(
                        ClassFile.class, String.class, CommandIterator.class);

                ByteCodeCommand command = (ByteCodeCommand) constructor.newInstance(clzFile, opCode, iterator);
                commands.add(command);
            }
        } catch (ClassNotFoundException | NoSuchMethodException
                | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        calculateOffset(commands);
        return commands.toArray(new ByteCodeCommand[commands.size()]);
    }

    private static void calculateOffset(List<ByteCodeCommand> commands) {
        int offset = 0;
        for (ByteCodeCommand cmd : commands) {
            cmd.setOffset(offset);
            offset += cmd.getLength();
        }
    }
}
