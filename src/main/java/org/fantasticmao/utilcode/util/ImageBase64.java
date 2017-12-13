package org.fantasticmao.utilcode.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author maokeluo
 * @desc
 * @create 17-12-13
 */
public class ImageBase64 {

    /**
     * @desc 将图片通过base64加密获得加密后的字符串
     * @author maokeluo
     * @methodName encodeImage
     * @param  path 图片路径
     * @create 17-12-13
     * @return java.lang.String
     */
    public static String encodeImage(String path){
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(path);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    /**
     * @desc 将base64编码后的字符串解码成图片输出
     * @author maokeluo
     * @methodName decodeImage
     * @param  imgBase64 图片字符串, filePath 图片输出位置及名称
     * @create 17-12-13
     * @return boolean
     */
    public static boolean decodeImage(String imgBase64, String filePath){
        if ("".equals(imgBase64)){
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] bytes = decoder.decodeBuffer(imgBase64);
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 0){
                    bytes[i] += 256;
                }
            }
            OutputStream outputStream = new FileOutputStream(filePath);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
