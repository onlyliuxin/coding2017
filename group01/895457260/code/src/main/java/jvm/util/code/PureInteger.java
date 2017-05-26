package jvm.util.code;

/**
 * Created by Haochen on 2017/1/5.
 */
public class PureInteger extends Getter {
    @Override
    public double value(String trueCode) {
        char[] array = trueCode.toCharArray();

        int result = 0;
        for (int i = array.length - 1, r = 1; i > 0; --i, r *= 2) {
            result += Integer.parseInt("" + array[i]) * r;
        }

        if (trueCode.startsWith("1")) {
            result *= -1;
        }
        return result;
    }

    @Override
    public Code valueCode(String trueCode) {
        return new ValueCode(trueCode.startsWith("1") ?
                trueCode.replaceFirst("1", "-") : trueCode.replaceFirst("0", ""));
    }

    @Override
    public void expand(Code code, int bit, int fill) {
        StringBuilder builder = new StringBuilder(code.code);
        for (int i = code.code.length(); i < bit; ++i) {
            builder.insert(1, fill);
        }
        code.code = builder.toString();
    }
}
