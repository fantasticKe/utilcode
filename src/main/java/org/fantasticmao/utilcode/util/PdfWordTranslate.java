package org.fantasticmao.utilcode.util;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

/**
 * @author maokeluo
 * @desc
 * @create 17-11-10
 */
public class PdfWordTranslate {

    public static void main(String[] args) {
        //pdf文件所在位置
        String file = "";
        PdfWordTranslate.pdf2Doc(file);
    }
    public static void pdf2Doc(String fileDir){
        PDDocument doc = null;
        PDFTextStripper stripper = null;
        Writer writer = null;
        try {
            doc = PDDocument.load(new File(fileDir));
            int pagenumber = doc.getNumberOfPages();
            fileDir = fileDir.substring(0, fileDir.lastIndexOf("."));
            String fileName = fileDir + ".doc";
            createFile(fileName);
            FileOutputStream fos = new FileOutputStream(fileName);
            writer = new OutputStreamWriter(fos, "UTF-8");
            stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
            stripper.setStartPage(1);
            stripper.setEndPage(pagenumber);
            stripper.writeText(doc, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
                doc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("pdf转换word成功！");
    }

    public static void createFile(String filePath) {
        File file = new File(filePath);
        if(file.exists()) {
            System.out.println("目标文件已存在" + filePath);
        }

        if(filePath.endsWith(File.separator)) {
            System.out.println("目标文件不能为目录！");
        }

        if(!file.getParentFile().exists()) {
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if(!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在的目录失败！");
            }
        }

        try {
            if(file.createNewFile()) {
                System.out.println("创建文件成功:" + filePath);
            } else {
                System.out.println("创建文件失败！");
            }
        } catch (IOException var3) {
            var3.printStackTrace();
            System.out.println("创建文件失败！" + var3.getMessage());
        }

    }
}
