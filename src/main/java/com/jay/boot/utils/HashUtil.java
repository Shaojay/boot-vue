package com.jay.boot.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 哈希加密算法
 */
public class HashUtil {

    public static final String ENC_MD5 = "MD5";

    public static final String ENC_SHA1 = "SHA-1";

    public static final String ENC_SHA256 = "SHA-256";
    /// 推荐使用

    /**
     * SHA-256哈希加密算法
     *
     * @param strSrc 明文字符串
     * @return
     */
    public static String sha256(String strSrc) {
        return hash(strSrc, ENC_SHA256);
    }

    /**
     * SHA-256哈希加密算法
     *
     * @param strSrc 明文字符串
     * @param salt 盐
     * @return
     */
    public static String sha256(String strSrc, String salt) {
        return hash(strSrc, ENC_SHA256, salt);
    }

    /**
     * 哈希+盐 加密算法
     *
     * @param strSrc 明文字符串
     * @param encName 哈希加密算法名称
     * @param salt 盐
     * @return
     */
    private static String hash(String strSrc, String encName, String salt) {
        return hash(strSrc + salt, encName);
    }

    /**
     * 哈希加密算法
     *
     * @param strSrc 明文字符串
     * @param encName 哈希加密算法名称
     * @return
     */
    private static String hash(String strSrc, String encName) {
        if (encName == null || "".equals(encName)) {
            encName = ENC_SHA256;
        }
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            // to HexString
            strDes = bytes2Hex(md.digest());
        }
        catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        StringBuffer des = new StringBuffer();
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des.append('0');
            }
            des.append(tmp);
        }
        return des.toString();
    }

}
