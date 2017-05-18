package jvm.util.code;

/**
 * Created by Haochen on 2017/1/5.
 */
public class CompCode extends Code {
    public CompCode(int type, String code) {
        super(type, code);
    }

    @Override
    public Code compCode() {
        return this;
    }

    @Override
    public Code trueCode() {
        return new TrueCode(type, compString());
    }

    @Override
    public Code inverseSign() {
        String str = bitwiseNOTAddOne().substring(1);
        Code code = new CompCode(type, isNegative() ? "0" + str : "1" + str);
        return code;
    }
}
