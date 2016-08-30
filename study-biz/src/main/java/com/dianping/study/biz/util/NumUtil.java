package com.dianping.study.biz.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by wu on 16/8/21.
 */
public class NumUtil {

    public static short parseShort(String s) {
        if (StringUtils.isNotBlank(s)) {
            try {
                return Short.parseShort(s);
            } catch (Exception e) {
                //ignore
            }
        }

        return 0;
    }

    public static int parseInteger(String s) {
        if (StringUtils.isNotBlank(s)) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                //ignore
            }
        }
        return 0;
    }

    public static double parseDouble(String s) {
        if (StringUtils.isNotBlank(s)) {
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                //ignore
            }
        }
        return 0;
    }


    public static BigDecimal parseBigDecimal(String s) {
        if (StringUtils.isNotBlank(s)) {
            try {
                return new BigDecimal(s);
            } catch (NumberFormatException e) {
                //ignore
            }
        }
        return new BigDecimal(0);
    }

    public static String formateDigital(BigDecimal faceValue) {
        if (faceValue == null) {
            return null;
        }
        if (faceValue.compareTo(new BigDecimal(faceValue.intValue())) == 0) {
            //整数时返回整数
            return String.valueOf(faceValue.intValue());
        } else {
            BigDecimal tenFaceValue = faceValue.multiply(new BigDecimal(10));
            if (tenFaceValue.compareTo(new BigDecimal(tenFaceValue.intValue())) == 0) {
                //小数点后一位,保留一位小数
                DecimalFormat decimalFormat = new DecimalFormat("#.0");
                return decimalFormat.format(faceValue);
            } else {
                //小数点后两位四舍五入
                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                return decimalFormat.format(faceValue);
            }

        }
    }
}
