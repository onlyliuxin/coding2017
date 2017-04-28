package week567_miniJVM.loader;

import structure.week1.ArrayList;
import week567_miniJVM.clz.AccessFlag;
import week567_miniJVM.clz.ClassFile;
import week567_miniJVM.clz.ClassIndex;
import week567_miniJVM.constant.ConstantInfo;
import week567_miniJVM.constant.ConstantPool;
import week567_miniJVM.constant.InfoFactory;
import week567_miniJVM.constant.NullConstantInfo;
import week567_miniJVM.field.Field;
import week567_miniJVM.method.Method;


public class ClassFileParser {
	public ClassFile parse(byte[] bytes) {
        ClassFile clzFile = new ClassFile();
        ByteCodeIterator iter = new ByteCodeIterator(bytes);
        if(!"cafebabe".equals(iter.nextUxToHexString(4))) return null;
        
        clzFile.setVersion(iter.nextU2ToInt(),iter.nextU2ToInt());
        
        ConstantPool pool = parseConstantPool(iter);
        clzFile.setConstantPool(pool);    
        
        clzFile.setAccessFlag(parseAccessFlag(iter));
        clzFile.setClassIndex(parseClassIndex(iter));
        
        int collectionNum = iter.nextU2ToInt();
        iter.skip(collectionNum*2);
        
        clzFile.setFields(parseFields(iter,pool));
        clzFile.setMethods(parseMethods(clzFile,iter));
		return clzFile;
	}
	
	private ConstantPool parseConstantPool(ByteCodeIterator iter){
        ConstantPool pool = new ConstantPool();
        pool.addConstantInfo(new NullConstantInfo());
        int lenpool = iter.nextU2ToInt();
        while(lenpool>1){
            lenpool -= 1;
            int tag = iter.nextU1ToInt();
            ConstantInfo info = InfoFactory.Invoke().getInfoObj(tag,iter,pool);
            pool.addConstantInfo(info);
        }
		return pool;
	}
	
	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        AccessFlag caflag = new AccessFlag(iter.nextU2ToInt());
		return caflag;
	}
	
	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
        ClassIndex clzIndex = new ClassIndex();
        clzIndex.setThisClassIndex(iter.nextU2ToInt());
        clzIndex.setSuperClassIndex(iter.nextU2ToInt());
		return clzIndex;
	}
	
    private ArrayList<Field> parseFields(ByteCodeIterator iter,ConstantPool pool){
        ArrayList<Field> fields = new ArrayList<Field>();
        int fieldNum = iter.nextU2ToInt();
        while(fieldNum>0){
            fieldNum -= 1;
            int accflag = iter.nextU2ToInt();       // 例如是public
            int nameIndex = iter.nextU2ToInt();// 指向常量池的入口
            int descIndex = iter.nextU2ToInt(); // 指向常量池的入口
            Field field = new Field(accflag,nameIndex,descIndex,pool);
            field.parse(pool,iter);
            fields.add(field);
        }
        return fields;
    }
    
    private ArrayList<Method> parseMethods(ClassFile clzFile,ByteCodeIterator iter){
        ArrayList<Method> methods = new ArrayList<Method>();
        int methodNum = iter.nextU2ToInt();
        while(methodNum>0){
            methodNum -= 1;
            int accflag = iter.nextU2ToInt();
            int nameIndex = iter.nextU2ToInt();
            int descIndex = iter.nextU2ToInt();
            Method method = new Method(clzFile,accflag,nameIndex,descIndex);
            method.parse(clzFile,iter);
            methods.add(method);
        }
        return methods;
    }
}
