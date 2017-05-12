package jvm.util.code;

/**
 * Created by Haochen on 2017/1/5.
 */
public class PureDecimal extends Getter {
    @Override
    public double value(String trueCode) {
        char[] array = trueCode.toCharArray();

        double result = 0;
        double r = 0.5;
        for (int i = 1; i < array.length; ++i, r /= 2) {
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
                trueCode.replaceFirst("1", "-0.") : trueCode.replaceFirst("0", "0."));
    }

    @Override
    public void expand(Code code, int bit, int fill) {
        for (int i = code.code.length(); i < bit; ++i) {
            code.code += fill;
        }
    }
}
