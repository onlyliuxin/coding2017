package jvm.util.code;

/**
 * Created by Haochen on 2017/1/5.
 */
public class TrueCode extends Code {
    public TrueCode(int type, String code) {
        super(type, code);
    }

    @Override
    public Code compCode() {
        return new CompCode(type, compString());
    }

    @Override
    public Code trueCode() {
        return this;
    }

    @Override
    public Code inverseSign() {
        String str = code.substring(1);
        Code code = new TrueCode(type, isNegative() ? "0" + str : "1" + str);
        return code;
    }
}
