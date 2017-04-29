package constant;

/**
 * Created by IBM on 2017/4/10.
 */
public class ClassInfo extends ConstantInfo {
    private int type = ConstantInfo.CLASS_INFO;
    private int utf8Index ;
    public ClassInfo(ConstantPool pool) {
        super(pool);
    }
    public int getUtf8Index() {
        return utf8Index;
    }
    public void setUtf8Index(int utf8Index) {
        this.utf8Index = utf8Index;
    }
    public int getType() {
        return type;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitClassInfo(this);
    }

    public String getClassName() {
        int index = getUtf8Index();
        UTF8Info utf8Info = (UTF8Info)constantPool.getConstantInfo(index);
        return utf8Info.getValue();
    }
}
