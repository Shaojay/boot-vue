package com.jay.boot.utils;

import java.util.Random;

/***
 * 随机密码库
 */
public class RandomPassword {

    private static final char[] chars_upper = new char[] {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
        'W', 'X', 'Y', 'Z'
    };

    private static final char[] chars_lower = new char[] {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z'
    };

    private static final char[] chars_number = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    private static final char[] chars_special = new char[] {
        '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', '_', '=', ':', ';', '[', ']', '{', '}', '?', '/'
    };

    private static final char[] chars_hex = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    /**
     * 随机字符串
     *
     * @param length 长度
     * @return
     */
    public static String getComplexChars(int length) {
        if (length < 0) {
            return "";
        }
        Random random = new Random();
        StringBuffer sbf = new StringBuffer();
        /*
         * if (length > 4) { boolean upper = false, lower = false, number = false, special = false; for (int i = 0; i <
         * length - 4; i++) { int type = random.nextInt(4); switch (type) { case 0 : upper = true; break; case 1 : lower
         * = true; break; case 2 : number = true; break; case 3 : special = true; break; default : break; } char c =
         * random(type); sbf.append(c); } if (!upper || !lower || !number || !special) {
         * sbf.append(random(0)).append(random(1)).append(random(2)).append(random(3)); } else { for (int i = 0; i < 4;
         * i++) { int type = random.nextInt(4); char c = random(type); sbf.append(c); } } } else {
         */
        for (int i = 0; i < length; i++) {
            int type = random.nextInt(4);
            char c = random(type);
            sbf.append(c);
        }
        // }
        return sbf.toString();
    }

    /**
     * 随机字符串(没有特殊字符)
     *
     * @param length 长度
     * @return
     */
    public static String getNospecialChars(int length) {
        if (length < 0) {
            return "";
        }
        Random random = new Random();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int type = random.nextInt(4);
            if (type == 3) {
                type = 2;
            }
            char c = random(type);
            sbf.append(c);
        }
        return sbf.toString();
    }

    /**
     * 随机数字
     *
     * @param length 长度
     * @return
     */
    public static String getNumberChars(int length) {
        if (length < 0) {
            return "";
        }
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char c = random(2);
            sbf.append(c);
        }
        return sbf.toString();
    }

    /**
     * 随机大写字母
     *
     * @param length 长度
     * @return
     */
    public static String getUpperLetterChars(int length) {
        if (length < 0) {
            return "";
        }
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char c = random(0);
            sbf.append(c);
        }
        return sbf.toString();
    }

    /**
     * 随机小写字母
     *
     * @param length 长度
     * @return
     */
    public static String getLowerLetterChars(int length) {
        if (length < 0) {
            return "";
        }
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char c = random(1);
            sbf.append(c);
        }
        return sbf.toString();
    }

    /**
     * 随机数字或字母
     *
     * @param length
     * @return
     */
    public static String getNumberAndLetterChars(int length) {
        if (length < 0) {
            return "";
        }
        StringBuffer sbf = new StringBuffer();
        char c = '#';
        char[] chars_number = new char[] {
            '0', '2'
        };
        for (int i = 0; i < length; i++) {
            Random random_type = new Random();
            String type_s = chars_number[random_type.nextInt(2)] + "";
            if ("0".equals(type_s)) {
                c = random(0);
                sbf.append(c);
            }
            if ("2".equals(type_s)) {
                c = random(2);
                sbf.append(c);
            }

        }
        return sbf.toString();
    }

    /**
     * 随机十六进制
     *
     * @param length
     * @return
     */
    public static String getRandomHex(int length) {
        if (length < 0) {
            return "";
        }
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            Random random_type = new Random();
            sbf.append(chars_hex[random_type.nextInt(chars_hex.length)]);
        }
        return sbf.toString();
    }

    private static char random(int type) {
        Random random_char = new Random();
        char c = '#';
        switch (type) {
            case 0:
                c = chars_upper[random_char.nextInt(chars_upper.length)];
                break;
            case 1:
                c = chars_lower[random_char.nextInt(chars_lower.length)];
                break;
            case 2:
                c = chars_number[random_char.nextInt(chars_number.length)];
                break;
            case 3:
                c = chars_special[random_char.nextInt(chars_special.length)];
                break;
            default:
                break;
        }
        return c;
    }

    public static void main(String[] args) {
    }

}
