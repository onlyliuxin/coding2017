package constant;

/**
 * Created by IBM on 2017/4/10.
 */
public class MethodRefInfo extends ConstantInfo implements ConstantInfo.Visitor {
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

    @Override
    public void visitClassInfo(ClassInfo info) {

    }

    @Override
    public void visitFieldRef(FieldRefInfo info) {

    }

    @Override
    public void visitMethodRef(MethodRefInfo info) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t# =   Methodref\t\t\t#")
                .append(classInfoIndex)
                .append(".#")
                .append(nameAndTypeIndex)
                .append("\t\t// ")
                .append(getClassName() + ".")
                .append(((NameAndTypeInfo) getConstantInfo(nameAndTypeIndex)).getName())
                .append(".")
                .append(((NameAndTypeInfo) getConstantInfo(nameAndTypeIndex)).getTypeInfo());
        System.out.println(sb.toString());
    }

    @Override
    public void visitNameAndType(NameAndTypeInfo info) {

    }

    @Override
    public void visitString(StringInfo info) {

    }

    @Override
    public void visistUTF8(UTF8Info info) {

    }
}
