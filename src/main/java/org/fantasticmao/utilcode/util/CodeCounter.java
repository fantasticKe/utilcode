package org.fantasticmao.utilcode.util;

import java.io.*;
import java.util.ArrayList;

/**
 * @author maokeluo
 * @desc 代码行数统计工具类
 * @create 17-9-24
 */
public class CodeCounter {

    static long files = 0;
    static long codeLines = 0;
    static long commentLines = 0;
    static long blankLines = 0;
    static ArrayList<File> fileArray = new ArrayList<File>();

    public static void main(String[] args) {
        String path = "";//项目文件地址
        ArrayList<File> files = getFiles(new File(path));
        for (File file : files){
            if (file.getName().matches(".*\\.java$")){
                count(file);
            }
        }
        System.out.println("文件数：" + files);
        System.out.println("代码行数：" + codeLines);
        System.out.println("注释行数：" + commentLines);
        System.out.println("空白行数：" + blankLines);
    }

    /**
     * @desc 获取当前目录及子目录下的文件
     * @author maokeluo
     * @methodName getFiles
     * @param  file
     * @create 17-9-24
     * @return java.util.ArrayList<java.io.File>
     */
    public static ArrayList<File> getFiles(File file){
        File[] files = file.listFiles();
        for (File f : files){
            if (f.isDirectory()){
                getFiles(f);
            }else {
                fileArray.add(f);
            }
        }
        return fileArray;
    }

    /**
     * @desc 统计空行/注释/代码行数
     * @author maokeluo
     * @methodName count
     * @param  file
     * @create 17-9-24
     * @return void
     */
    public static void count(File file){
        BufferedReader br = null;
        boolean flag = false;
        try {
           br = new BufferedReader(new FileReader(file));
           String line = "";
           while ((line = br.readLine()) != null){
               line = line.trim();//去除注释前的空格
               if (line.matches("^[ ]*$")){//匹配空格
                   blankLines++;
               }else if (line.startsWith("//")){
                   commentLines++;
               }else if (line.startsWith("/*") && !line.startsWith("*/")){
                   commentLines++;
                   flag = true;
               }else if (line.startsWith("/*") && line.startsWith("*/")){
                   commentLines++;
               }else if (flag){
                   commentLines++;
                   if (line.endsWith("*/")){
                       flag = false;
                   }
               }else {
                   codeLines++;
               }
           }
           files++;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br != null){
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
