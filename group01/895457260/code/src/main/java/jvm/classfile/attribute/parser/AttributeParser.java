package jvm.classfile.attribute.parser;

import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.impl.UTF8Info;
import jvm.util.ByteCodeIterator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Haochen on 2017/4/12.
 * TODO:
 */
public class AttributeParser {
    public static AttributeInfo parse(ByteCodeIterator iterator, ConstantPool constantPool) {
        int nameIndex = iterator.nextU2ToInt();
        String name = ((UTF8Info) constantPool.getConstantInfo(nameIndex)).getValue();
        int length = iterator.nextU4ToInt();

        String className = AttributeParser.class.getPackage().getName()
                + ".impl." + name + "Parser";
        try {
            Class<?> clazz = Class.forName(className);
            Method parse = clazz.getMethod("parse",
                    int.class, int.class, ByteCodeIterator.class, ConstantPool.class);
            byte[] bytes = iterator.getBytes(length);
            ByteCodeIterator subIterator = new ByteCodeIterator(bytes);
            return (AttributeInfo) parse.invoke(clazz.newInstance(),
                    nameIndex, length, subIterator, constantPool);
        } catch (ClassNotFoundException | NoSuchMethodException
                | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
