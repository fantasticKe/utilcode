package org.fantasticmao.utilcode.algorithm;

import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author maokeluo
 * @desc 加密算法
 * @create 17-9-26
 */
public class DigestUtil {

    private static final String MD5 = "MD5";
    private static final String SHA1 = "SHA1";

    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    public static String MD5Encode(String str){
        return digestAsHex(str,MD5);
    }

    public static String SHAEncode(String str){
        return digestAsHex(str,SHA1);
    }

    /**
     * @desc MD5 字符串验证
     * @author maokeluo
     * @methodName MD5verify
     * @param  str 待验证字符串, MD5code MD5编码后的字符串
     * @create 17-9-26
     * @return boolean
     */
    public static boolean MD5verify(String str,String MD5code){
        return digestAsHex(str,MD5).equals(MD5code);
    }

    /**
     * @desc SHA1 字符串验证
     * @author maokeluo
     * @methodName SHA1verify
     * @param  str, SHA1code
     * @create 17-9-26
     * @return boolean
     */
    public static boolean SHA1verify(String str, String SHA1code){
        return digestAsHex(str,SHA1).equals(SHA1code);
    }

    /**
     * @desc Spring 字节数组MD5加密
     * @author maokeluo
     * @methodName MD5Encode
     * @param  bytes
     * @create 17-9-26
     * @return java.lang.String
     */
    public static String MD5Encode(byte[] bytes){
        return DigestUtils.md5DigestAsHex(bytes);
    }
    /**
     * @desc Spring 输入流 MD5加密
     * @author maokeluo
     * @methodName MD5Encode
     * @param  inputStream
     * @create 17-9-26
     * @return java.lang.String
     */
    public static String MD5Encode(InputStream inputStream) throws IOException {
        return DigestUtils.md5DigestAsHex(inputStream);
    }

    /**
     * @desc JDK实现MD5/SHA算法
     * @author maokeluo
     * @methodName digestAsHex
     * @param  str, algorithm
     * @create 17-9-26
     * @return java.lang.String
     */
    private static String digestAsHex(String str, String algorithm){
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(str.getBytes());
            char[] chars = encodeHex(md.digest(str.getBytes()));
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @desc 将byte转化为16进制
     * @author maokeluo
     * @methodName encodeHex
     * @param  bytes
     * @create 17-9-26
     * @return char[]
     */
    private static char[] encodeHex(byte[] bytes) {
        char[] chars = new char[32];

        for(int i = 0; i < chars.length; i += 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[b >>> 4 & 15];
            chars[i + 1] = HEX_CHARS[b & 15];
        }
        return chars;
    }
}
