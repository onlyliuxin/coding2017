package com.pop.practice.homework.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author haipop Date: 17-2-18 Time: 下午1:00
 */
public class Math {

    public static double div(int left, int right) throws IllegalAccessException {
        return div((double) left, (double) right, 2);
    }

    public static double div(int left, int right, int scale) throws IllegalAccessException {
        return div((double) left, (double) right, scale);
    }

    @SuppressWarnings("unchecked")
    public static double div(double left, double right, int scale) throws IllegalAccessException {
        // 如果精确范围小于0，抛出异常信息
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal divisor = new BigDecimal(left);
        BigDecimal dividend = new BigDecimal(right);
        return divisor.divide(dividend, scale, RoundingMode.HALF_EVEN).doubleValue();
    }
}