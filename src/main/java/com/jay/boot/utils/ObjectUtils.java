package com.jay.boot.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @desc 字节数组和对象之间转换
 */
public final class ObjectUtils {
    private ObjectUtils() {
    }

    public static Object getObjectFromBytes(byte[] objBytes) throws Exception {
        if (objBytes != null && objBytes.length != 0) {
            ByteArrayInputStream bis = new ByteArrayInputStream(objBytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();
            ois.close();
            bis.close();
            return obj;
        }
        return null;
    }

    public static byte[] getBytesFromObject(Object obj) throws Exception {
        if (obj == null) {
            return null;
        } else {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            return bo.toByteArray();
        }
    }
}
