package constant;

/**
 * Created by IBM on 2017/4/10.
 */
public class MethodRefInfo extends ConstantInfo{
    private int type = ConstantInfo.METHOD_INFO;

    private int classInfoIndex;
    private int nameAndTypeIndex;

    public MethodRefInfo(ConstantPool pool) {
        super(pool);
    }

    public int getType() {
        return type;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitMethodRef(this);
    }

    public int getClassInfoIndex() {
        return classInfoIndex;
    }

    public void setClassInfoIndex(int classInfoIndex) {
        this.classInfoIndex = classInfoIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public String toString() {

        return getClassName() + " : " + this.getMethodName() + " : " + this.getNameAndTypeIndex();
    }

    public String getClassName() {
        ConstantPool pool = this.getConstantPool();
        ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(this.getClassInfoIndex());
        return clzInfo.getClassName();
    }

    public String getMethodName() {
        ConstantPool pool = this.getConstantPool();
        NameAndTypeInfo typeInfo = (NameAndTypeInfo) pool.getConstantInfo(this.getNameAndTypeIndex());
        return typeInfo.getName();
    }

}
