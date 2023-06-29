package com.sirpho.blog.common.utils;

import java.io.File;
import java.security.MessageDigest;
public class CommonUtils {
    /**
     * 创建文件目录
     * @param path 文件路径
     */
    public static void createFolder(String path) {
        try {
            File dirFile = new File(path);
            boolean bFile = dirFile.exists();
            if (!bFile) {
                //多层目录
                bFile = dirFile.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * md5
     * @param aString 原始字符串
     * @return md5
     */
    public static String encrypt(String aString) {
        String retval = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(aString.getBytes());
            byte[] digest = md.digest();
            StringBuilder s = new StringBuilder();

            for(int i = 0; i < digest.length; ++i) {
                s.append(Integer.toString((digest[i] & 240) >> 4, 16));
                s.append(Integer.toString(digest[i] & 15, 16));
            }

            retval = s.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retval;
    }

    /**
     * 驼峰转下划线
     * @param camelCase 驼峰命名字符串
     * @return 下划线命名字符串
     */
    public static String toSnakeCase(String camelCase) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char ch = camelCase.charAt(i);
            if (Character.isUpperCase(ch)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(ch));
        }
        return sb.toString();
    }
}
