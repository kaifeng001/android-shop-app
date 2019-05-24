package com.fengkai.zhouyang.yangyanghongkong.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * @author xiangyang550
 * @ClassName: NumberConvert
 * @Description: 字符串转数字方法
 * @date 2014年11月26日 上午10:44:47
 */
public class NumberConvert {

    /**
     * 无效值
     */
    public static final int INVALID_VALUE = Integer.MIN_VALUE;

    /**
     * @param str
     * @return double
     * @Description string转double，如果异常
     */
    public static double stringToDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return INVALID_VALUE;
        }
    }

    /**
     * @param
     * @return
     * @Title: stringToInt
     * @Description 如果异常
     */
    public static int stringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return INVALID_VALUE;
        }
    }

    /**
     * @param
     * @return
     * @Title: stringToLong
     * @Description 如果异常
     */
    public static long stringToLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return INVALID_VALUE;
        }
    }

    /**
     * @param
     * @return
     * @Title: stringToFloat
     * @Description 如果异常
     */
    public static float stringToFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return INVALID_VALUE;
        }
    }


    public static String getThumNum(long count) {
        String countStr = "";
        if (count > 99) {
            countStr = "...";
        } else {
            countStr = count + "";
        }
        return countStr;
    }

    public static String getDealNum(String count) {
        String countStr = count;
        if (!TextUtils.isEmpty(count)) {
            double dCount = Double.parseDouble(count);
            if (dCount > 9999) {
                DecimalFormat df = new DecimalFormat("0.0");
                countStr = df.format(dCount / 10000) + "万";
            }
        } else {
            countStr = "0";
        }
        return countStr;
    }

    public static String getDealNum(int count) {
        String countStr = count + "";
        if (count > 9999) {
            double dCount = (double) count;
            DecimalFormat df = new DecimalFormat("0.0");
            countStr = df.format(dCount / 10000) + "万";
        }
        return countStr;
    }


    public static String getThousandNum(int count) {
        String countStr = count + "";
        if (count > 99999) {
            countStr = "10万+";
        } else if (count <= 0) {
            countStr = "0";
        }
        return countStr;
    }


}
