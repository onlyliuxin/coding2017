package jvm.command;

import jvm.classfile.ClassFile;
import jvm.command.item.ByteCodeCommand;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    public static ByteCodeCommand[] parse(ClassFile clzFile, String codes) {
        String packageName = CommandParser.class.getPackage().getName() + ".item.impl.";
        CommandIterator iterator = new CommandIterator(codes);
        List<ByteCodeCommand> commands = new ArrayList<>();
        try {
            while (iterator.hasNext()) {
                String opCode = iterator.next2CharAsString().toUpperCase();
                String cmdName = ByteCodeCommand.codeMap.get(opCode);
                String cmdClassName = packageName + cmdName.replaceAll("_", "") + "Cmd";

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
