package com.xiufeng.wedleairplane.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Patterns;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ValidateUtils {

    private static Integer[] weightingCoefficient = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static String[] validateNums = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
    public static HashMap<Integer, String> FINAL_CODE = new HashMap<Integer, String>() {
        {
            put(0, "1");
            put(1, "0");
            put(2, "x");
            put(3, "9");
            put(4, "8");
            put(5, "7");
            put(6, "6");
            put(7, "5");
            put(8, "4");
            put(9, "3");
            put(10, "2");

        }
    };

    public static boolean isValidateData(String strDate, int year, int month, int date) {
        boolean result = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        try {
            Date date1 = sdf.parse(strDate);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 判断输入参数是否为整数
     *
     * @param input 输入参数
     *              return boolean 判断结果
     */
    public static boolean isInteger(String input) {
        boolean result = false;
        if (input.equals("")) {
            return false;
        }
        String patternString = "^[0-9]*$";
        result = Pattern.matches(patternString, input);
        return result;
    }

    /**
     * 判断输入参数是否为全0的字符串
     *
     * @param input 输入参数
     *              return boolean 判断结果
     */
    public static boolean isAll0String(String input) {
        boolean result = false;
        if (input.equals("")) {
            return false;
        }
        String patternString = "^[0]*$";
        result = Pattern.matches(patternString, input);
        return result;
    }

    public static boolean canInputForMoney(String input) {
        boolean result = false;
        if (input.equals("")) {
            return false;
        }
        String patternString = "(^\\d+\\.+\\d+$)|(^\\d+$)|(^\\d+\\.+$)|(^\\d+\\.+\\d+\\.$)";
        result = Pattern.matches(patternString, input);
        return result;
    }

    /**
     * 判断输入参数是否为整数
     *
     * @param input 输入参数
     *              return boolean 判断结果
     */
    public static boolean isNumber(String input) {
        boolean result = false;
        if (input.equals("")) {
            return false;
        }
        String patternString = "(^[\\d]+\\.[\\d]+$)|(^[\\d]+$)";
        result = Pattern.matches(patternString, input);
        return result;
    }

    /**
     * 判断输入参数是否为浙金网要求的密码格式
     * 密码格式为：长度为8到20位，必须有数字，字母或者其他字符的组合
     *
     * @param strPasswrod 输入参数
     *                    return boolean 判断结果
     */
    public static boolean isPassword(String strPasswrod) {
        boolean result = true;
        if (strPasswrod.length() < 8 || strPasswrod.length() > 20) {
            result = false;
        }
        if (strPasswrod.matches("[0-9]{8,20}")) {
            result = false;
        }
        if (strPasswrod.matches("[a-zA-Z]{8,20}")) {
            result = false;
        }
        return result;
    }

    /**
     * 判断输入参数是否为正确的手机号码
     *
     * @param strMobilePhone 输入参数
     *                       return boolean 判断结果
     */
    public static boolean isMobilePhone(String strMobilePhone) {
        boolean result;
        result = false;
        String patternString = "^1[0-9]{10}$";
        result = Pattern.matches(patternString, strMobilePhone);
        return result;
    }

    /**
     * 判断输入参数是否为正确的身份证号码
     *
     * @param strIdentificationNo 输入参数
     *                            return boolean 判断结果
     */
    public static boolean isIdentificationNO(String strIdentificationNo) {

        boolean result = false;
        String patternString = "^[0-9]{15}$|^[0-9]{18}$|^[0-9]{17}([0-9]|X|x)$";

        result = Pattern.matches(patternString, strIdentificationNo);
        if (result) {
            result = calculateIdentificationNo(strIdentificationNo);
        }
        return result;
    }

    /**
     * 验证身份证是否真实
     *
     * @param strIdentificationNo 输入参数
     *                            return boolean 判断结果
     */
    private static boolean calculateIdentificationNo(String strIdentificationNo) {
        boolean result = false;
        //int a,b,c;
        if (strIdentificationNo.length() == 18) {
            if (!isInteger(strIdentificationNo.substring(0, 17))) {
                result = false;
            } else {
                int sum = 0;
                for (int i = 0; i < weightingCoefficient.length; i++) {
                    int bitNum = Integer.parseInt(strIdentificationNo.substring(i, i + 1));
                    int num = weightingCoefficient[i];
                    int bit = bitNum * num;
                    sum += bit;
                }
                int index = sum % 11;
                String validateBit = FINAL_CODE.get(index).toLowerCase();
                Character vBit = strIdentificationNo.charAt(strIdentificationNo.length() - 1);
                if (validateBit.equals(vBit.toString().toLowerCase())) {
                    result = true;
                }
                int year = Integer.parseInt(strIdentificationNo.substring(6, 10));
                int month = Integer.parseInt(strIdentificationNo.substring(10, 12));
                int date = Integer.parseInt(strIdentificationNo.substring(12, 14));
                String strDate = String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(date);
                if (!isValidateData(strDate, year, month, date)) {
                    result = false;
                }
            }

        } else if (strIdentificationNo.length() == 15) {
            if (!isInteger(strIdentificationNo)) {
                result = false;
            }
            int year = Integer.parseInt("19" + strIdentificationNo.substring(6, 8));
            int month = Integer.parseInt(strIdentificationNo.substring(8, 10));
            int date = Integer.parseInt(strIdentificationNo.substring(10, 12));
            String strDate = String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(date);
            if (!isValidateData(strDate, year, month, date)) {
                result = false;
            } else {
                result = true;
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * 判断输入参数是否手机验证码(6位数字)
     *
     * @param strCode 输入参数
     *                return boolean 判断结果
     */
    public static boolean isSMSValideCode(String strCode) {
        boolean result = false;
        String patternString = "^[0-9]{6}$";
        result = Pattern.matches(patternString, strCode);
        return result;
    }

    //监测投资金额输入的第一个数字
    public static boolean FirstNumCheck(String number) {
        boolean result = false;
        String patternStr = "^[1-9][0-9]$";
        result = Pattern.matches(patternStr, number);
        return result;
    }

    //监测网络状态
    public static boolean isNetworkAvailable(Context context) {
        boolean result = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        result = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String matchLuhn(String cardNo) {
        if (cardNo.length() < 13 || cardNo.length() > 19) {
            return "0";
        } else {
            int[] cardNoArr = new int[cardNo.length()];
            for (int i = 0; i < cardNo.length(); i++) {
                cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
            }
            for (int i = cardNoArr.length - 2; i >= 0; i -= 2) {
                cardNoArr[i] <<= 1;
                cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
            }
            int sum = 0;
            for (int i = 0; i < cardNoArr.length; i++) {
                sum += cardNoArr[i];
            }
            if (sum % 10 == 0) {
                return "1";
            } else {
                return "2";
            }
        }
    }

    /*
    验证是不是url地址
     */
    public static boolean isUrl(String url) {
        if (Patterns.WEB_URL.matcher(url).matches()) {
            return true;
        }
        return false;
    }
}
