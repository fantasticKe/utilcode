package org.fantasticmao.utilcode.util;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author maokeluo
 * @desc
 * @create 17-12-20
 */
public class LuaUtils {

    /**
     * 执行lua脚本 返回结果
     * @param lua
     * @return
     */
    public static String getLuaOut(String lua){
        ByteArrayOutputStream byteArrayOutputStream = runLua(lua);
        String result = byteArrayOutputStream.toString();
        System.setOut(System.out);
        return result;
    }

    /**
     * 执行lua脚本,将输出值存入输入流中
     * @param lua
     * @return
     */
    private static ByteArrayOutputStream runLua(String lua){
        ByteArrayOutputStream baoStream = new ByteArrayOutputStream(1024);
        PrintStream cacheStream = new PrintStream(baoStream);
        System.setOut(cacheStream);

        Globals globals = JsePlatform.standardGlobals();
        LuaValue chunk = globals.load(lua);
        chunk.call();
        return baoStream;
    }
}
