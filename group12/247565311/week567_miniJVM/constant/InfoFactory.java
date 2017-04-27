package week567_miniJVM.constant;
import week567_miniJVM.loader.ByteCodeIterator;
public class InfoFactory{
    private static InfoFactory infoFact = new InfoFactory();
    public static InfoFactory Invoke(){
        return infoFact;
    }
    public ConstantInfo getInfoObj(int tag,ByteCodeIterator iter,ConstantPool pool){
        switch(tag){
        case 1:{
        	UTF8Info info = new UTF8Info(pool);
            info.setLength(iter.nextU2ToInt());
            info.setValue(iter.nextUxToHexString(info.getLength()));
            return info;}
        case 7:{
        	ClassInfo info = new ClassInfo(pool);
            info.setUtf8Index(iter.nextU2ToInt());
            return info;  }
        case 8:{
        	StringInfo info = new StringInfo(pool);
            info.setIndex(iter.nextU2ToInt());
            return info;}
        case 9:{
        	FieldRefInfo info = new FieldRefInfo(pool);
            info.setClassInfoIndex(iter.nextU2ToInt());
            info.setNameAndTypeIndex(iter.nextU2ToInt());
            return info;}  
        case 10:{
        	MethodRefInfo info = new MethodRefInfo(pool);
            info.setClassInfoIndex(iter.nextU2ToInt());
            info.setNameAndTypeIndex(iter.nextU2ToInt());
            return info;}
        case 12:{
        	NameAndTypeInfo info = new NameAndTypeInfo(pool);
            info.setIndex1(iter.nextU2ToInt());
            info.setIndex2(iter.nextU2ToInt());
            return info;}
        default:
			new RuntimeException("tag 为"+tag+" 的常亮项尚不支持!").printStackTrace();
            break;
        }
        return null;
    }
}










